package com.luojilab.componentservice.wheelpicker.interfaces;

public interface IDatePicker {
    void onDatePicked(String year, String month, String day);

    void onDateError();
}
