package com.luojilab.componentservice.wheelpicker;

import android.app.Activity;

import com.luojilab.componentservice.wheelpicker.interfaces.IDatePicker;

/**
 * export module services
 */

public interface WheelPickerService {

    void showDatePicker(Activity activity, IDatePicker iDatePicker);

}
