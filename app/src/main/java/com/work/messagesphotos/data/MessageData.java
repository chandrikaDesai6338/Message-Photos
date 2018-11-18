package com.work.messagesphotos.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.work.messagesphotos.models.Response;
import com.work.messagesphotos.network.RetrofitAPIInterface;
import com.work.messagesphotos.network.RetrofitHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class MessageData {


    @NonNull
    private RetrofitAPIInterface retrofitAPIInterface;


}
