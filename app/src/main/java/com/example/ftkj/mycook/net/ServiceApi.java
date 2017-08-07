package com.example.ftkj.mycook.net;

import com.example.ftkj.mycook.bean.ClassifyBean;
import com.example.ftkj.mycook.bean.ImageListBean;
import com.example.ftkj.mycook.bean.LoginBean;
import com.example.ftkj.mycook.bean.NewsDetailBean;
import com.example.ftkj.mycook.bean.NewsListBean;
import com.example.ftkj.mycook.bean.RegisterBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by FTKJ on 2017/5/23.
 */

public interface ServiceApi {
    @GET("/api/top/classify")
    Observable<ClassifyBean> getClassifyModel();

    @GET("/api/top/list")
    Observable<NewsListBean> getNewsList(@Query("id")int id,@Query("page")int page);

    @GET("/api/top/show")
    Observable<NewsDetailBean> getNewsDetail(@Query("id") int id);


    @POST("/api/oauth2/reg")
    Observable<RegisterBean> getRegResult(@Query("client_id") String clientId,
                                          @Query("client_secret")String clientSecret,
                                          @Query("email") String email,
                                          @Query("account") String username,
                                          @Query("password") String password);

    @GET("/api/oauth2/login")
    Observable<LoginBean> getLogResult(@Query("client_id") String clientId,
                                       @Query("client_secret")String clientSecret,
                                       @Query("account") String username,
                                       @Query("password") String password
                                      );


    @GET("/tnfs/api/list")
    Observable<ImageListBean> getImageList(@Query("id")int id,@Query("page")int page,@Query("rows")int rows);
}
