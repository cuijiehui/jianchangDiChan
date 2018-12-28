package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.http.bean.CivilianserviceBean;
import com.cui.android.jianchengdichan.http.bean.TelephoneBean;
import com.cui.android.jianchengdichan.presenter.BasePresenter;
import com.cui.android.jianchengdichan.presenter.CellPhonePresenter;
import com.cui.android.jianchengdichan.view.base.BaseActivity;
import com.cui.android.jianchengdichan.view.ui.adapter.CellDataAdapter;
import com.cui.android.jianchengdichan.view.ui.adapter.CellTypeAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CellPhoneActivity extends BaseActivity {
    @BindView(R.id.top_back)
    RelativeLayout topBack;
    @BindView(R.id.tv_content_name)
    TextView tvContentName;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_top_right)
    ImageView ivTopRight;
    @BindView(R.id.rv_cell_type)
    RecyclerView rvCellType;
    @BindView(R.id.rv_cell_data)
    RecyclerView rvCellData;
    CellPhonePresenter cellPhonePresenter = new CellPhonePresenter();
    List<CivilianserviceBean> typeData = new ArrayList<>();
    List<TelephoneBean> dataList = new ArrayList<>();
    CellTypeAdapter cellTypeAdapter;
    CellDataAdapter cellDataAdapter;
    String type = "";
    @Override
    public BasePresenter initPresenter() {
        return cellPhonePresenter;
    }

    @Override
    public void initParam(Bundle param) {
        if(param !=null){
            type= param.getString("type");
        }
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_cell_phone;
    }

    @Override
    public void initView(View view) {
        if(type.equals("1")){
            tvContentName.setText("物业电话本");
            rvCellType.setVisibility(View.GONE);
        }else{//小区电话本
            tvContentName.setText("小区电话本");
            rvCellType.setVisibility(View.VISIBLE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvCellType.setLayoutManager(linearLayoutManager);
            cellTypeAdapter = new CellTypeAdapter(R.layout.item_cell_type_layout, typeData);
            rvCellType.setAdapter(cellTypeAdapter);
            cellTypeAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    TextView textView = view.findViewById(R.id.tv_type_text);
                    LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.ll_vut_off);
                    CivilianserviceBean civilianserviceBean = (CivilianserviceBean) adapter.getItem(position);
                    cellTypeAdapter.setClick(position, textView, linearLayout);
                    cellPhonePresenter.getPhoneList(2, 1, civilianserviceBean.getId());
                }
            });
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        rvCellData.setLayoutManager(layoutManager);
        cellDataAdapter = new CellDataAdapter(R.layout.item_cell_data_layout, dataList);
        rvCellData.setAdapter(cellDataAdapter);
        cellDataAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                TelephoneBean telephoneBean = dataList.get(position);
//                //获取剪贴板管理器：
//                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
//                // 创建普通字符型ClipData
//                ClipData mClipData = ClipData.newPlainText("Label", telephoneBean.getPhone());
//                // 将ClipData内容放到系统剪贴板里。
//                cm.setPrimaryClip(mClipData);
//                ToastUtil.makeToast("复制成功");
                diallPhone(telephoneBean.getPhone());
            }
        });
        rvCellData.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.VERTICAL));
    }
    /**
     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
     *
     * @param phoneNum 电话号码
     */
    public void diallPhone(final String phoneNum) {
        checkPermission(new CheckPermListener() {
            @Override
            public void superPermission() {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:" + phoneNum);
                intent.setData(data);
                startActivity(intent);
            }
        },R.string.perm_tip, Manifest.permission.CALL_PHONE);

    }
    @Override
    protected void onResume() {
        super.onResume();
        if(type.equals("2")){
            cellPhonePresenter.getPhoneType();
        }else{
            cellPhonePresenter.getPhoneList(1, 1, 0);
        }
    }

    @Override
    public void doBusiness(Context mContext) {

    }

    @Override
    public View initBack() {
        return topBack;
    }


    public void getPhoneType(List<CivilianserviceBean> data) {
        typeData.clear();
        CivilianserviceBean civilianserviceBean = new CivilianserviceBean();
        civilianserviceBean.setId(0);
        civilianserviceBean.setName("全部");
        typeData.add(civilianserviceBean);
        if (data != null) {
            typeData.addAll(data);
        }

        cellTypeAdapter.notifyDataSetChanged();
        cellPhonePresenter.getPhoneList(2, 1, 0);

    }

    public void getPhoneList(List<TelephoneBean> data) {
        dataList.clear();
        if (data != null) {
            dataList.addAll(data);
        }
        cellDataAdapter.notifyDataSetChanged();
    }
}
