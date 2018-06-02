package com.cui.android.jianchengdichan.view.ui.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.presenter.RepairsPresenter;
import com.cui.android.jianchengdichan.utils.SPKey;
import com.cui.android.jianchengdichan.utils.SPUtils;
import com.cui.android.jianchengdichan.utils.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RepairsFragment extends Fragment {

    private View view;
    private EditText ed_tel;
    private EditText ed_name;
    private EditText et_feedback_txt;
    private TextView tv_submit;
    private int uid;
    private String area;
    private static   RepairsPresenter mRepairsPresenter;
   private static RepairsFragment instance;
    public RepairsFragment(){}

    public static RepairsFragment getInstance( RepairsPresenter repairsPresenter){
        if(instance==null){
            instance=new RepairsFragment();
        }
        mRepairsPresenter =repairsPresenter;
        return instance;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        area = "gz";
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_repairs, null);

        uid = (int) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_UID_KEY, SPUtils.DATA_INT);


        initView();
        return view;
    }

    private void initView() {

        ed_tel = (EditText) view.findViewById(R.id.ed_tel);
        ed_name = (EditText) view.findViewById(R.id.ed_name);
        et_feedback_txt = (EditText) view.findViewById(R.id.et_feedback_txt);
        tv_submit = (TextView) view.findViewById(R.id.tv_submit);
        tv_submit.setOnClickListener(clickListener);
    }


    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_submit:
                    submitData();
                    break;
            }
        }
    };

    private void submitData() {
        String name = ed_name.getText().toString().trim();
        String tel = ed_tel.getText().toString().trim();
        String content = et_feedback_txt.getText().toString().trim();

        String token = (String) SPUtils.INSTANCE.getSPValue(SPKey.SP_USER_TOKEN_KEY, SPUtils.DATA_STRING);

        if (TextUtils.isEmpty(name)) {
            ToastUtil.makeToast("请输入联系人姓名");
            return;
        }

        if (TextUtils.isEmpty(tel)) {
            ToastUtil.makeToast("请输入联系人电话");
            return;
        } else {
            if (!verifyPhone(tel)) {
                ToastUtil.makeToast("请输入正确的手机号");
                return;
            }
        }

        if (TextUtils.isEmpty(content)) {
            ToastUtil.makeToast("请输入报修内容");
            return;
        }
        mRepairsPresenter.submitRepairInfo(uid,token,tel,name,content,"","1");
    }


    private boolean verifyPhone(String phone) {
        //在将数据写入数据库之前,判断用户输入的邮箱格式是否正确
        String filter = "^0?1[3|4|5|8|7][0-9]\\d{8}$";
        Pattern p = Pattern.compile(filter);
        Matcher matcher = p.matcher(phone);
        if (!matcher.find()) {
            return false;
        }
        return true;
    }

}
