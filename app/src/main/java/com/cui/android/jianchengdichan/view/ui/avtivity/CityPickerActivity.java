package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.DBManager;
import com.cui.android.jianchengdichan.utils.LogUtils;
import com.cui.android.jianchengdichan.view.ui.adapter.CityListAdapter;
import com.cui.android.jianchengdichan.view.ui.adapter.ResultListAdapter;
import com.cui.android.jianchengdichan.view.ui.beans.City;
import com.cui.android.jianchengdichan.view.ui.beans.LocateState;
import com.cui.android.jianchengdichan.view.ui.customview.SideLetterBar;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityPickerActivity extends AppCompatActivity {
    public static final String KEY_PICKED_CITY = "picked_city";

    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.iv_search_clear)
    ImageView ivSearchClear;
    @BindView(R.id.listview_all_city)
    ListView listviewAllCity;
    @BindView(R.id.tv_letter_overlay)
    TextView tvLetterOverlay;
    @BindView(R.id.listview_search_result)
    ListView listviewSearchResult;
    @BindView(R.id.empty_view)
    LinearLayout emptyView;
    @BindView(R.id.side_letter_bar)
    SideLetterBar sideLetterBar;

    private DBManager dbManager;
    private List<City> mAllCities;

    CityListAdapter mCityAdapter;
    ResultListAdapter mResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_picker);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        initData();
        initView();
        initLocation();
    }

    private void initData() {
        dbManager = new DBManager(this);
        dbManager.copyDBFile();
        mAllCities = dbManager.getAllCities();

        Log.i("www", mAllCities.get(0).toString());

        mCityAdapter = new CityListAdapter(this, mAllCities);
        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
            @Override
            public void onCityClick(String name) {
                LogUtils.i("onCityClick="+name);

                back(name);
            }

            @Override
            public void onLocateClick() {
                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
                //mLocationClient.startLocation();
            }
        });

        mResultAdapter = new ResultListAdapter(this, null);
    }

    private void initView() {
        listviewAllCity.setAdapter(mCityAdapter);
        sideLetterBar.setOverlay(tvLetterOverlay);
        sideLetterBar.setOnLetterChangedListener(new SideLetterBar.OnLetterChangedListener() {
            @Override
            public void onLetterChanged(String letter) {
                int position = mCityAdapter.getLetterPosition(letter);
                listviewAllCity.setSelection(position);
            }
        });
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyword = s.toString();
                if (TextUtils.isEmpty(keyword)) {
                    ivSearchClear.setVisibility(View.GONE);
                    emptyView.setVisibility(View.GONE);
                    listviewSearchResult.setVisibility(View.GONE);
                } else {
                    ivSearchClear.setVisibility(View.VISIBLE);
                    listviewSearchResult.setVisibility(View.VISIBLE);
                    List<City> result = dbManager.searchCity(keyword);
                    if (result == null || result.size() == 0) {
                        emptyView.setVisibility(View.VISIBLE);
                    } else {
                        emptyView.setVisibility(View.GONE);
                        mResultAdapter.changeData(result);
                    }
                }
            }
        });
        listviewSearchResult.setAdapter(mResultAdapter);
        listviewSearchResult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtils.i("onItemClick="+mResultAdapter.getItem(position).getName());
                back(mResultAdapter.getItem(position).getName());
            }
        });
    }
    private void initLocation() {
        mCityAdapter.updateLocateState(LocateState.SUCCESS, "广州");
    }
    private void back(String city) {
        Intent data = new Intent();
        data.putExtra(KEY_PICKED_CITY, city);
        setResult(RESULT_OK, data);
        finish();
    }

    @OnClick(R.id.iv_search_clear)
    public void onViewClicked() {

    }
}
