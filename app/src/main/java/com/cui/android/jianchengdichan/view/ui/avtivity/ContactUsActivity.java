package com.cui.android.jianchengdichan.view.ui.avtivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cui.android.jianchengdichan.R;

public class ContactUsActivity extends AppCompatActivity {
    ImageView iv_back_icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        iv_back_icon = findViewById(R.id.iv_back_icon);
        iv_back_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
