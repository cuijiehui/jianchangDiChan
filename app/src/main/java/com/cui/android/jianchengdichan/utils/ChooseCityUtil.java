package com.cui.android.jianchengdichan.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.TimePicker;

import com.alibaba.fastjson.JSON;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.view.interfaces.ChooseCityInterface;
import com.cui.android.jianchengdichan.view.ui.beans.CityBean;

import java.lang.reflect.Field;
import java.util.List;

public class ChooseCityUtil implements View.OnClickListener, NumberPicker.OnValueChangeListener {

    Context context;
    ChooseCityInterface cityInterface;
    NumberPicker npProvince, npCity, npCounty;
    Button saveBt;
    String[] newCityArray = new String[3];
    CityBean bean;
    PopupWindow window;
    public void createWindow(Context context, String[] oldCityArray,View rootView, ChooseCityInterface cityInterface) {
        this.context = context;
        this.cityInterface = cityInterface;
        bean = JSON.parseObject(CityData.readFromProperties("/assets/json.properties","json",""), CityBean.class);
        newCityArray[0] = oldCityArray[0];
        newCityArray[1] = oldCityArray[1];
        newCityArray[2] = oldCityArray[2];
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.city_popupwindow_layout, null);
        window = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);

        //初始化控件
        saveBt = view.findViewById(R.id.bt_city_save);
        saveBt.setOnClickListener(this);
        npProvince = (NumberPicker) view.findViewById(R.id.np_province);
        npCity = (NumberPicker) view.findViewById(R.id.np_city);
        npCounty = (NumberPicker) view.findViewById(R.id.np_county);
        npProvince.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npCity.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        npCounty.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNomal();
        //省：设置选择器最小值、最大值、初始值
        String[] provinceArray = new String[bean.getData().size()];//初始化省数组
        for (int i = 0; i < provinceArray.length; i++) {//省数组填充数据
            provinceArray[i] = bean.getData().get(i).getName();
        }
        npProvince.setDisplayedValues(provinceArray);//设置选择器数据、默认值
        npProvince.setMinValue(0);
        npProvince.setMaxValue(provinceArray.length - 1);
        for (int i = 0; i < provinceArray.length; i++) {
            if (provinceArray[i].equals(newCityArray[0])) {
                npProvince.setValue(i);
                changeCity(i);//联动市数据
            }
        }
        // 设置popWindow弹出窗体可点击，这句话必须添加，并且是true
        window.setFocusable(true);
//                 window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        window.setBackgroundDrawable(dw);
        // 设置PopupWindow是否能响应外部点击事件
        window.setOutsideTouchable(true);
        // 设置PopupWindow是否能响应点击事件
        window.setTouchable(true);
        // 设置popWindow的显示和消失动画
//                 window.setAnimationStyle(R.style.mypopwindow_anim_style);
        // 在底部显示
        window.showAtLocation(rootView, Gravity.CENTER, 0, 0);
    }

    //根据省,联动市数据
    private void changeCity(int provinceTag) {
        List<CityBean.Data.City> cityList = bean.getData().get(provinceTag).getCity();
        String[] cityArray = new String[cityList.size()];
        for (int i = 0; i < cityArray.length; i++) {
            cityArray[i] = cityList.get(i).getName();
        }
        try {
            npCity.setMinValue(0);
            npCity.setMaxValue(cityArray.length - 1);
            npCity.setWrapSelectorWheel(false);
            npCity.setDisplayedValues(cityArray);//设置选择器数据、默认值
        } catch (Exception e) {
            npCity.setDisplayedValues(cityArray);//设置选择器数据、默认值
            npCity.setMinValue(0);
            npCity.setMaxValue(cityArray.length - 1);
            npCity.setWrapSelectorWheel(false);
        }
        for (int i = 0; i < cityArray.length; i++) {
            if (cityArray[i].equals(newCityArray[1])) {
                npCity.setValue(i);
                changeCounty(provinceTag, i);//联动县数据
                return;
            }
        }
        npCity.setValue(0);
        changeCounty(provinceTag, npCity.getValue());//联动县数据
    }

    //根据市,联动县数据
    private void changeCounty(int provinceTag, int cityTag) {
        List<String> countyList = bean.getData().get(provinceTag).getCity().get(cityTag).getCounty();
        String[] countyArray = new String[countyList.size()];
        for (int i = 0; i < countyArray.length; i++) {
            countyArray[i] = countyList.get(i).toString();
        }
        try {
            npCounty.setMinValue(0);
            npCounty.setMaxValue(countyArray.length - 1);
            npCounty.setWrapSelectorWheel(false);
            npCounty.setDisplayedValues(countyArray);//设置选择器数据、默认值
        } catch (Exception e) {
            npCounty.setDisplayedValues(countyArray);//设置选择器数据、默认值
            npCounty.setMinValue(0);
            npCounty.setMaxValue(countyArray.length - 1);
            npCounty.setWrapSelectorWheel(false);
        }
        for (int i = 0; i < countyArray.length; i++) {
            if (countyArray[i].equals(newCityArray[2])) {
                npCounty.setValue(i);
                return;
            }
        }
        npCounty.setValue(0);
    }

    //设置NumberPicker的分割线透明、字体颜色、设置监听
    private void setNomal() {
        //设置监听
        npProvince.setOnValueChangedListener(this);
        npCity.setOnValueChangedListener(this);
        npCounty.setOnValueChangedListener(this);
        //去除分割线
        setNumberPickerDividerColor(npProvince);
        setNumberPickerDividerColor(npCity);
        setNumberPickerDividerColor(npCounty);
        //设置字体颜色
        setNumberPickerTextColor(npProvince, context.getResources().getColor(R.color.main_flash_sale_text));
        setNumberPickerTextColor(npCity, context.getResources().getColor(R.color.main_flash_sale_text));
        setNumberPickerTextColor(npCounty, context.getResources().getColor(R.color.main_flash_sale_text));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_city_save:
                window.dismiss();
                cityInterface.sure(newCityArray);
                break;
        }
    }

    //选择器选择值监听
    @Override
    public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
        switch (picker.getId()) {
            case R.id.np_province:
                List<CityBean.Data> dataList = bean.getData();
                newCityArray[0] = dataList.get(npProvince.getValue()).getName();
                changeCity(npProvince.getValue());
                newCityArray[1] = dataList.get(npProvince.getValue()).getCity().get(0).getName();
                newCityArray[2] = dataList.get(npProvince.getValue()).getCity().get(0).getCounty().get(0).toString();
                break;
            case R.id.np_city:
                List<CityBean.Data.City> cityList = bean.getData().get(npProvince.getValue()).getCity();
                newCityArray[1] = cityList.get(npCity.getValue()).getName();
                changeCounty(npProvince.getValue(), npCity.getValue());
                newCityArray[2] = cityList.get(npCity.getValue()).getCounty().get(0).toString();
                break;
            case R.id.np_county:
                List<String> countyList = bean.getData().get(npProvince.getValue()).getCity().get(npCity.getValue()).getCounty();
                newCityArray[2] = countyList.get(npCounty.getValue()).toString();
                break;
        }
    }

    //设置分割线颜色
    private void setNumberPickerDividerColor(NumberPicker numberPicker) {
        NumberPicker picker = numberPicker;
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(picker, new ColorDrawable(context.getResources().getColor(R.color.transparent)));// pf.set(picker, new Div)
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (Resources.NotFoundException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    //设置选择器字体颜色
    public static boolean setNumberPickerTextColor(NumberPicker numberPicker, int color) {
        boolean result = false;
        final int count = numberPicker.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = numberPicker.getChildAt(i);
            if (child instanceof EditText) {
                try {
                    Field selectorWheelPaintField = numberPicker.getClass()
                            .getDeclaredField("mSelectorWheelPaint");
                    selectorWheelPaintField.setAccessible(true);
                    ((Paint) selectorWheelPaintField.get(numberPicker)).setColor(color);
                    ((EditText) child).setTextColor(color);
                    numberPicker.invalidate();
                    result = true;
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}
