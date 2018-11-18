package com.work.messagesphotos.network;
import com.work.messagesphotos.models.Response;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPIInterface {

    @GET("messages")
    Observable<Response> queryMessage(@Query("page") int pageIndex);

}