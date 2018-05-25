package com.cui.android.jianchengdichan.view.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.cui.android.jianchengdichan.R;
import com.cui.android.jianchengdichan.utils.DBManager;
import com.cui.android.jianchengdichan.view.ui.beans.City;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CityPickerActivity extends AppCompatActivity {

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

    private DBManager dbManager;
    private List<City> mAllCities;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_picker);
        ButterKnife.bind(this);

    }
//    private void initData() {
//        dbManager = new DBManager(this);
//        dbManager.copyDBFile();
//        mAllCities = dbManager.getAllCities();
//
//        Log.i("www" , mAllCities.get(0).toString());
//
//        mCityAdapter = new CityListAdapter(this, mAllCities);
//        mCityAdapter.setOnCityClickListener(new CityListAdapter.OnCityClickListener() {
//            @Override
//            public void onCityClick(String name) {
//                back(name);
//            }
//
//            @Override
//            public void onLocateClick() {
//                mCityAdapter.updateLocateState(LocateState.LOCATING, null);
//                //mLocationClient.startLocation();
//            }
//        });
//
//        mResultAdapter = new ResultListAdapter(this, null);
//    }
}
