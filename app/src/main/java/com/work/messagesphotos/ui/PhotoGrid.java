package com.work.messagesphotos.ui;

import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.GridView;

import com.work.messagesphotos.R;
import com.work.messagesphotos.adapter.PhotoGridAdapter;
import com.work.messagesphotos.models.PhotoModel;
import com.work.messagesphotos.network.RetrofitAPIInterface;
import com.work.messagesphotos.network.RetrofitHelper;
import com.work.messagesphotos.utils.AppConstants;
import com.work.messagesphotos.utils.MyUtils;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class PhotoGrid extends AppCompatActivity {

    private MyUtils myUtils;
    private PhotoGridAdapter adapter;
    private GridView gridView;
    private int columnWidth;

    @NonNull
    private RetrofitAPIInterface retrofitAPIInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_grid);
        gridView = (GridView) findViewById(R.id.grid_view);

        //initRetrofit
        retrofitAPIInterface = new RetrofitHelper().getRetrofitAPIInterface(AppConstants.BASE_URL_PHOTO);

        myUtils = new MyUtils(this);

        requestDetails();
    }

    private void InitilizeGridLayout() {
        Resources r = getResources();
        float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                AppConstants.GRID_PADDING, r.getDisplayMetrics());

        columnWidth = (int) ((myUtils.getScreenWidth() - ((AppConstants.NUM_OF_COLUMNS + 1) * padding)) / AppConstants.NUM_OF_COLUMNS);

        gridView.setNumColumns(AppConstants.NUM_OF_COLUMNS);
        gridView.setColumnWidth(columnWidth);
        gridView.setStretchMode(GridView.NO_STRETCH);
        gridView.setPadding((int) padding, (int) padding, (int) padding,
                (int) padding);
        gridView.setHorizontalSpacing((int) padding);
        gridView.setVerticalSpacing((int) padding);
    }

    public void setAdapter(PhotoModel photoModel) {

// Initilizing Grid View
        InitilizeGridLayout();


        // Gridview adapter
        adapter = new PhotoGridAdapter(photoModel.getPugs(), PhotoGrid.this);

        // setting grid view adapter
        gridView.setAdapter(adapter);
    }

    private void requestDetails() {

        Observable<PhotoModel> observable = retrofitAPIInterface.queryPhoto();
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new DefaultObserver<PhotoModel>() {
                    @Override
                    public void onNext(PhotoModel photoModel) {
                        Log.d("Message", "Response" + photoModel.getPugs().get(1));
                        setAdapter(photoModel);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
