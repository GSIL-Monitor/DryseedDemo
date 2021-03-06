package com.dryseed.dryseedapp.widget.datapicker;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.util.ToastUtils;
import com.dryseed.dryseedapp.BaseActivity;
import com.dryseed.dryseedapp.R;
import com.luojilab.component.componentlib.router.Router;
import com.luojilab.componentservice.wheelpicker.WheelPickerService;
import com.luojilab.componentservice.wheelpicker.interfaces.IDatePicker;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by caiminming on 2018/1/4.
 */

public class DatePickerActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting_layout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sex)
    void onSexBtnClick() {

    }

    @OnClick(R.id.birthday)
    void onBirthdayBtnClick() {
        showBirthdayDatePicker();
    }

    private void showBirthdayDatePicker() {
        Router router = Router.getInstance();
        if (router.getService(WheelPickerService.class.getSimpleName()) != null) {
            WheelPickerService service = (WheelPickerService) router.getService(WheelPickerService.class.getSimpleName());
            service.showDatePicker(this, new IDatePicker() {
                @Override
                public void onDatePicked(String year, String month, String day) {
                    ToastUtils.showShort(String.format("%s-%s-%s", year, month, day));
                }

                @Override
                public void onDateError() {
                    ToastUtils.showShort("onDateError");
                }
            });
        } else {
            ToastUtils.showShort("Please load WheelpickerComponent first");
        }


        /*DatePicker picker = new DatePicker(this, DatePicker.YEAR_MONTH_DAY);

        int color = DPIUtil.getThemeColor(this, R.attr.default_black);
        picker.setTopLineColor(color);
        picker.setTitleTextColor(color);
        picker.setDividerColor(color);
        picker.setCancelTextColor(color);
        picker.setSubmitTextColor(color);
        picker.setTextColor(color, WheelView.TEXT_COLOR_NORMAL);
        picker.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
        picker.setCanceledOnTouchOutside(true);
        picker.setUseWeight(true);

        Calendar curCa = Calendar.getInstance();
        curCa.setTimeInMillis(System.currentTimeMillis());
        int curYear = curCa.get(Calendar.YEAR);
        if (curYear <= 1970) {
            ToastUtil.showToast("系统时间错误");
            return;
        }

        picker.setRangeStart(1970, 1, 1);
        picker.setRangeEnd(curCa.get(Calendar.YEAR), curCa.get(Calendar.MONTH) + 1, curCa.get(Calendar.DAY_OF_MONTH));

        Calendar c = Calendar.getInstance();
        final long date = 946656000000l;//2000/1/1
        c.setTimeInMillis(date);

        picker.setSelectedItem(c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1, c.get(Calendar.DAY_OF_MONTH));
        picker.setOnDatePickListener(new DatePicker.OnYearMonthDayPickListener() {
            @Override
            public void onDatePicked(String year, String month, String day) {
                ToastUtil.showToast(String.format("%s-%s-%s", year, month, day));
            }

        });
        picker.show();*/
    }
}
