package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.FitmentPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;
import com.cui.android.jianchengdichan.view.base.BaseActivtity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class FitmentActivity extends BaseActivtity {


    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.tv_fitment_name)
    TextView tvFitmentName;
    @BindView(R.id.et_fitment_name)
    EditText etFitmentName;
    @BindView(R.id.tv_fitment_num)
    TextView tvFitmentNum;
    @BindView(R.id.et_fitment_num)
    EditText etFitmentNum;
    @BindView(R.id.tv_fitment_company)
    TextView tvFitmentCompany;
    @BindView(R.id.et_fitment_company)
    EditText etFitmentCompany;
    @BindView(R.id.tv_fitment_time)
    TextView tvFitmentTime;
    @BindView(R.id.tv_fitemt_start_time)
    TextView tvFitemtStartTime;
    @BindView(R.id.tv_fitemt_end_time)
    TextView tvFitemtEndTime;
    @BindView(R.id.tv_fitment_phone)
    TextView tvFitmentPhone;
    @BindView(R.id.et_fitment_phone)
    EditText etFitmentPhone;
    @BindView(R.id.et_fitment_content)
    EditText etFitmentContent;
    @BindView(R.id.tv_fitment_submit)
    TextView tvFitmentSubmit;
    FitmentPresenter fitmentPresenter = new FitmentPresenter();


    @Override
    public BasePresenter initPresenter() {
        return fitmentPresenter;
    }

    @Override
    public void initParms(Bundle parms) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fitment;
    }

    @Override
    public void initView(View view) {
        tvContentName.setText("装修申请");
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }

    /**
     * 日期选择
     *
     * @param activity
     * @param themeResId
     * @param tv
     * @param calendar
     */
    public static void showDatePickerDialog(Activity activity, int themeResId, final TextView tv, Calendar calendar) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, themeResId, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                tv.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);

            }
        }
                // 设置初始日期
                , calendar.get(Calendar.YEAR)
                , calendar.get(Calendar.MONTH)
                , calendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    @OnClick({R.id.tv_fitemt_start_time, R.id.tv_fitemt_end_time, R.id.tv_fitment_submit})
    public void onViewClicked(View view) {
        Calendar calendar = Calendar.getInstance();

        switch (view.getId()) {
            case R.id.tv_fitemt_start_time:
                showDatePickerDialog(FitmentActivity.this
                        , 2
                        , tvFitemtStartTime
                        , calendar);
                break;
            case R.id.tv_fitemt_end_time:
                showDatePickerDialog(FitmentActivity.this
                        , 2
                        , tvFitemtEndTime
                        , calendar);
                break;
            case R.id.tv_fitment_submit:
                submit();
                break;
        }
    }

    private void submit() {
        String name = etFitmentName.getText().toString();//业主
        if(TextUtils.isEmpty(name)){
            ToastUtil.makeToast("业主不能为空");
            return;
        }
        String company = etFitmentCompany.getText().toString();//公司
        if(TextUtils.isEmpty(name)){
            ToastUtil.makeToast("公司不能为空");
            return;
        }
        String num = etFitmentNum.getText().toString();//房号
        if(TextUtils.isEmpty(name)){
            ToastUtil.makeToast("房号不能为空");
            return;
        }
        String phone = etFitmentPhone.getText().toString();//电话
        if(TextUtils.isEmpty(name)){
            ToastUtil.makeToast("电话不能为空");
            return;
        }
        String content = etFitmentContent.getText().toString();//备注
        if(TextUtils.isEmpty(name)){
            ToastUtil.makeToast("备注不能为空");
            return;
        }
        String startTime = tvFitemtStartTime.getText().toString();//开始时间
        if(TextUtils.isEmpty(name)){
            ToastUtil.makeToast("开始时间不能为空");
            return;
        }
        String endTime = tvFitemtEndTime.getText().toString();//结束时间
        if(TextUtils.isEmpty(name)){
            ToastUtil.makeToast("结束时间不能为空");
            return;
        }
        int uid = (Integer) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);
        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);
        fitmentPresenter.submitRenovationInfo(
                uid
                ,token
                ,name
                ,phone
                ,content
                ,company
                ,num
                ,startTime
                ,endTime
        );
    }


    public void submitRenovationInfo() {
        ToastUtil.makeToast("提交成功");
        finish();
    }
}
