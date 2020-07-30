package com.example.smallgallery;


import androidx.viewpager.widget.ViewPager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.smallgallery.Adapters.ViewpagerAdapter;

public class profileBig extends Activity {

    int picNumber = 1;
    int[] array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_big);

        Intent intent = getIntent();
        picNumber = intent.getIntExtra("position", picNumber);
        array = intent.getIntArrayExtra("array");

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_page);
        ViewpagerAdapter adapter = new ViewpagerAdapter(array, picNumber, this);
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(picNumber);
    }
}

