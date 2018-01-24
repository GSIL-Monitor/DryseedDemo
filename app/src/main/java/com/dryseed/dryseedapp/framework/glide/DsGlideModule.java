package com.dryseed.dryseedapp.framework.glide;

import android.content.Context;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;
import com.dryseed.dryseedapp.utils.StorageDirectoryUtil;

/**
 * https://muyangmin.github.io/glide-docs-cn/doc/generatedapi.html
 */
@com.bumptech.glide.annotation.GlideModule
public class DsGlideModule extends AppGlideModule {

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(3) //setMemoryCacheScreens设置MemoryCache应该能够容纳的像素值的设备屏幕数，说白了就是缓存多少屏图片，默认值是2。
                .build();

        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.2 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.2 * defaultBitmapPoolSize);
        Log.d("MMM", GlideCacheUtil.getFormatSize(customMemoryCacheSize) + " " + GlideCacheUtil.getFormatSize(customBitmapPoolSize));

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

        //磁盘缓存 Glide使用DiskLruCacheWrapper作为默认的磁盘缓存，默认大小是250M,缓存文件放在APP的缓存文件夹下。
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, ExternalCacheDiskCacheFactory.DEFAULT_DISK_CACHE_DIR, GlideCacheUtil.DEFAULT_GLIDE_DISK_CACHE_SIZE));
    }

    @Override
    public void registerComponents(Context context, Glide glide, Registry registry) {
        super.registerComponents(context, glide, registry);
    }

    /**
     * 为了维持对 Glide v3 的 GlideModules 的向后兼容性，Glide 仍然会解析应用程序和所有被包含的库中的 AndroidManifest.xml 文件，
     * 并包含在这些清单中列出的旧 GlideModules 模块类。
     * 如果你已经迁移到 Glide v4 的 AppGlideModule 和 LibraryGlideModule ，你可以完全禁用清单解析。
     * 这样可以改善 Glide 的初始启动时间，并避免尝试解析元数据时的一些潜在问题。要禁用清单解析，请在你的 AppGlideModule 实现中复写 isManifestParsingEnabled() 方法
     * https://muyangmin.github.io/glide-docs-cn/doc/configuration.html#appglidemodule
     *
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

}
