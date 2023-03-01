package com.trainer.heart;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class GoodsInformationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goodsinformation);
        setTitle("손목 스트랩");
        ImageView image = findViewById(R.id.goodsimage);
        image.setImageResource(R.drawable.wriststrap);
        image.setVisibility(View.VISIBLE);
    }
}
