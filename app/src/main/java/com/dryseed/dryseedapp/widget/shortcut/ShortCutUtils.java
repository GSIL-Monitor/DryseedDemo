package com.dryseed.dryseedapp.widget.shortcut;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.database.Cursor;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;

import com.luojilab.component.basiclib.utils.LogUtil;

import java.util.List;

/**
 * @author caiminming
 */
public class ShortCutUtils {
    /**
     * 创建快捷方式
     *
     * @param name
     * @param icon
     * @param launchIntent
     */
    public static void installShortCut(Context context, String name, int icon, Intent launchIntent) {
        // 桌面图标和应用绑定，卸载应用后系统会同时自动删除图标
        launchIntent.setAction(Intent.ACTION_VIEW);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            if (shortcutManager != null && shortcutManager.isRequestPinShortcutSupported()) {
                ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(context, name)
                        .setIcon(Icon.createWithResource(context, icon))
                        .setShortLabel(name)
                        .setIntent(launchIntent)
                        .build();
                shortcutManager.requestPinShortcut(shortcutInfo, null);
            } else {
                LogUtil.d("scm is null or isRequestPinShortcutSupported return false");
            }
        } else {
            Intent shortcut = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
            // 快捷方式的名称
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
            // 不允许重复创建
            shortcut.putExtra("duplicate", false);
            // 快捷方式的图标
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(context, icon));
            // 设置点击快捷方式启动的程序
            shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, launchIntent);
            // 向系统发送添加快捷键的广播
            context.sendBroadcast(shortcut);
        }
    }

    /**
     * 删除桌面快捷方式（某些系统不支持删除）
     *
     * @param context
     * @param shortcutName 快捷方式名
     * @param actionIntent 快捷方式操作，也就是上面创建的Intent
     * @param isDuplicate  为true时循环删除快捷方式（即存在很多相同的快捷方式）
     */
    public static void deleteShortCut(Context context, String shortcutName, Intent actionIntent, boolean isDuplicate) {
        actionIntent.setAction(Intent.ACTION_VIEW);

        Intent shortcutIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
        shortcutIntent.putExtra("duplicate", isDuplicate);
        shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, actionIntent);
        context.sendBroadcast(shortcutIntent);
    }

    /**
     * 判断当前应用在桌面是否有桌面快捷方式
     *
     * @param context
     * @param shortcutName
     * @return
     */
    public static boolean isShortcutExist(Context context, String shortcutName) {
        boolean isExist = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ShortcutManager shortcutManager = context.getSystemService(ShortcutManager.class);
            if (shortcutManager != null) {
                List<ShortcutInfo> shortcutInfoList = shortcutManager.getPinnedShortcuts();
                for (ShortcutInfo info : shortcutInfoList) {
                    if (info.getId().equals(shortcutName)) {
                        isExist = true;
                    }
                }
            }
        } else {
            ContentResolver cr = context.getContentResolver();
            String authority = getAuthorityFromPermission(context);
            if (authority == null) {
                return false;
            }
            Uri contentUri = Uri.parse("content://" + authority + "/favorites?notify=true");
            Cursor cursor = null;
            try {
                cursor = cr.query(contentUri, new String[]{"title", "iconResource"}, "title=?", new String[]{shortcutName}, null);
                if ((cursor != null && cursor.getCount() > 0)) {
                    isExist = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (cursor != null) {
                    cursor.close();
                }
            }
        }

        return isExist;
    }

    private static String getAuthorityFromPermission(Context context) {
        try {
            //由于这个API的调用会引起部分华为手机出现弹框，使用前需要和插件中心同事确认
            List<PackageInfo> packages = context.getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS);
            if (packages != null) {
                for (PackageInfo packageInfo : packages) {
                    ProviderInfo[] providers = packageInfo.providers;
                    if (providers != null) {
                        for (ProviderInfo provider : providers) {
                            if (provider == null || provider.readPermission == null) {
                                continue;
                            }
                            if (provider.readPermission.contains("launcher")) {
                                return provider.authority;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
