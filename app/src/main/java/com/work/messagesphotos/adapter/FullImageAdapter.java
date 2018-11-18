package com.work.messagesphotos.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.work.messagesphotos.R;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class FullImageAdapter extends PagerAdapter {

    private Activity _activity;
    private List<String> imageurl;
    private LayoutInflater inflater;

    // constructor
    public FullImageAdapter(Activity activity, List<String> imageurl) {
        this._activity = activity;
        this.imageurl = imageurl;
    }

    @Override
    public int getCount() {
        return this.imageurl.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imgDisplay;

        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_full_image, container,
                false);

        imgDisplay = viewLayout.findViewById(R.id.imgDisplay);



        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(_activity)
                .load(imageurl.get(position)) // image url
                .apply(options)
                .into(imgDisplay);


        (container).addView(viewLayout);

        return viewLayout;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }
}