package com.example.smallgallery.Adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ViewpagerAdapter extends PagerAdapter {

    private int[] array;
    Context context;
    PhotoViewAttacher photoAttacher;

    public ViewpagerAdapter(int[] array, int picNumber, Context context) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        return array.length -1;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((ImageView) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(array[position+1]);
        ((ViewPager) container).addView(imageView,0);

        photoAttacher = new PhotoViewAttacher(imageView);
        photoAttacher.update();

        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((ImageView) object);
    }


}
