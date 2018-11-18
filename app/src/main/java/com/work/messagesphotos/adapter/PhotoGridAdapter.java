package com.work.messagesphotos.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.work.messagesphotos.R;
import com.work.messagesphotos.ui.FullScreenPhotoActivity;

import java.util.ArrayList;
import java.util.List;

public class PhotoGridAdapter extends BaseAdapter {

    private List<String> photoUrl;
    private Context context;

    public PhotoGridAdapter(List<String> photoUrl, Context context) {
        this.photoUrl = photoUrl;
        this.context = context;
    }

    @Override
    public int getCount() {
       return photoUrl.size();
    }

    @Override
    public Object getItem(int position) {
        return photoUrl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(context);
        } else {
            imageView = (ImageView) convertView;
        }
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round);

        Glide.with(context)
                .load(photoUrl.get(position)) // image url
                .apply(options)
                .into(imageView);


        // image view click listener
        imageView.setOnClickListener(new OnImageClickListener(position));

        return imageView;
    }

    class OnImageClickListener implements View.OnClickListener {

        int _postion;

        // constructor
        public OnImageClickListener(int position) {
            this._postion = position;
        }

        @Override
        public void onClick(View v) {
            // on selecting grid view image
            // launch full screen activity
            Intent i = new Intent(context, FullScreenPhotoActivity.class);
            i.putStringArrayListExtra("photoUrl", (ArrayList<String>) photoUrl);
            i.putExtra("position", _postion);
            context.startActivity(i);
        }

    }


    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
