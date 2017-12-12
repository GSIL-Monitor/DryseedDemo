package com.dryseed.dryseedapp.widget.dialog;

import android.content.Context;
import android.view.View;

import com.dryseed.dryseedapp.R;

/**
 * Created by wanghao2 on 2017/6/26.
 */

public class DsDialogFactory {

    public static DsAlertDialog showTipDialog(Context context, CharSequence summary, final View.OnClickListener listener) {
        final DsAlertDialog dialog = new DsAlertDialog(context);
        dialog.setMessage(summary);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setPositiveButton(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (listener != null) {
                    listener.onClick(v);
                }
            }
        });
        dialog.setNegativeButton(R.string.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }

    public static DsAlertDialog showTipDialog(Context context, CharSequence text) {
        final DsAlertDialog dialog = new DsAlertDialog(context);
        dialog.setTitle(R.string.error_title);
        dialog.setMessage(text);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setPositiveButton(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;

    }

    public static DsAlertDialog showTipDialog(Context context, CharSequence text, CharSequence btnText) {
        final DsAlertDialog dialog = new DsAlertDialog(context);
        dialog.setTitle(R.string.error_title);
        dialog.setMessage(text);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setPositiveButton(btnText.toString(), new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        return dialog;
    }

    public static void showTipDialog(Context context, CharSequence text, final View.OnClickListener confirmListener, final View.OnClickListener cancelListener) {
        final DsAlertDialog dialog = new DsAlertDialog(context);
        dialog.setMessage(text);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setPositiveButton(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmListener.onClick(v);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton(R.string.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelListener.onClick(v);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showTipDialog(Context context, CharSequence text, String confirmText, String cancelText,
                                     final View.OnClickListener confirmListener, final View.OnClickListener cancelListener) {
        final DsAlertDialog dialog = new DsAlertDialog(context);
        dialog.setMessage(text);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setPositiveButton(confirmText, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmListener.onClick(v);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton(cancelText, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelListener.onClick(v);
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void showCustomDialog(Context context, int resId, final View.OnClickListener confirmListener, final View.OnClickListener cancelListener) {
        final DsAlertDialog dialog = new DsAlertDialog(context);
        dialog.setCanceledOnTouchOutside(true);
        View view = View.inflate(context, resId, null);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setPositiveButton(R.string.ok, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmListener.onClick(v);
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton(R.string.cancel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelListener.onClick(v);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
