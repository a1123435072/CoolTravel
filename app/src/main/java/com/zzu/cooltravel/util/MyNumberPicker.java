package com.zzu.cooltravel.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

/**
 * Created by xm on 2016/5/16.
 */
public class MyNumberPicker extends LinearLayout
{
    /*
     * 声明传值用的变量
     */
    public int number1 = 0;
    public int number2 = 0;

    public MyNumberPicker(Context context)
    {
        super(context);

        // 设置布局
        this.setGravity(Gravity.CENTER);
        // 声明5个NumberPicker
        NumberPicker num1 = new NumberPicker(context);
        NumberPicker num2 = new NumberPicker(context);

        num1.setMinValue(0);
        num1.setMaxValue(9);
        num2.setMinValue(0);
        num2.setMaxValue(9);

        this.addView(num1, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        this.addView(num2, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        num1.setOnValueChangedListener(new firstValueChanged());
        num2.setOnValueChangedListener(new secindValueChanged());

    }

    /*
     * 对五个NumberPicker的监听事件进行处理
     */
    public class firstValueChanged implements NumberPicker.OnValueChangeListener
    {

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal)
        {
            number1 = newVal;
        }

    }

    public class secindValueChanged implements NumberPicker.OnValueChangeListener
    {

        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal)
        {
            number2 = newVal;
        }

    }

}
