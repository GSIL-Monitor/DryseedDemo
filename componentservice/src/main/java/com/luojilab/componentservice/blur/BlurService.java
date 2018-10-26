package com.luojilab.componentservice.blur;

import android.content.Context;
import android.view.View;

/**
 * export module services
 */

public interface BlurService {
    void doBlur(Context context, int resId, View targetView);
}
