package cn.qqtheme.framework.serviceimpl;

import android.app.Activity;

import com.luojilab.componentservice.wheelpicker.WheelPickerService;
import com.luojilab.componentservice.wheelpicker.interfaces.IDatePicker;

import java.util.Calendar;

import cn.qqtheme.framework.lib.picker.DatePicker;
import cn.qqtheme.framework.lib.widget.WheelView;

public class WheelpickerServiceImpl implements WheelPickerService {
    @Override
    public void showDatePicker(Activity activity, final IDatePicker iDatePicker) {
        DatePicker picker = new DatePicker(activity, DatePicker.Mode.YEAR_MONTH_DAY);

        int color = 0xff2C292A;
        picker.setTopLineColor(color);
        //picker.setTitleTextColor(color);
        //picker.setDividerColor(color);
        picker.setCancelTextColor(color);
        picker.setSubmitTextColor(color);
        picker.setTextColor(color, WheelView.TEXT_COLOR_NORMAL);
        //picker.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        //picker.setCanceledOnTouchOutside(true);
        //picker.setUseWeight(true);

        Calendar curCa = Calendar.getInstance();
        curCa.setTimeInMillis(System.currentTimeMillis());
        int curYear = curCa.get(Calendar.YEAR);
        if (curYear <= 1970) {
            if (iDatePicker != null) {
                iDatePicker.onDateError();
            }
            return;
        }

        //picker.setRangeStart(1970, 1, 1);
        //picker.setRangeEnd(curCa.get(Calendar.YEAR), curCa.get(Calendar.MONTH) + 1, curCa.get(Calendar.DAY_OF_MONTH));

        Calendar c = Calendar.getInstance();
        final long date = 946656000000l;//2000/1/1
        c.setTimeInMillis(date);

        picker.setSelectedItem(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                if (iDatePicker != null) {
                    iDatePicker.onDatePicked(year, month, day);
                }
            }

        });
        picker.show();
    }
}
