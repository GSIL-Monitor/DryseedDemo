package com.dryseed.dryseedapp.framework.retrofit.demo3.api;

import com.dryseed.dryseedapp.framework.retrofit.demo2.entity.NewsEntity;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface NewsApi {

    /*
      ● GET请求 @GET
        eg1:
            @GET("login/")
            Call<LoginResult> getData(@Query("name") String name, @Query("password") String pw);
        eg2:
            @GET()
            Call<ResponseBody>  getBattleRoyaleInfo(@Url String url, @Query("nickname") String nickName);
        eg3:
            @GET("users")
            Call<List<User>> getUsersBySort(@Query("sortby") String sort);
            对应 http://baseurl/users?sortby=username

      ● 如果想用表单
        eg1:
            @FormUrlEncoded
            @POST("/url")
            Call<T> postForm(@FieldMap Map<String , Object> maps);

        eg2:
            @POST("login")
            @FormUrlEncoded
            Call<User> login(@Field("username") String username, @Field("password") String password);

      ● 如果直接用对象 @Body
        @POST("url")
        Call<T> PostBody(@Body Objects objects);

      ● 如果直接多参数 @QueryMap
        @PUT("/url")
        Call<T> queryMap(@QueryMap Map<String, String> maps);
        eg1:
            @POST()
            Call<HttpBase.RetBase> uploadVideo(@Url String url, @Body HttpBase.ReqBase req);
        eg2:
            @POST("user/edit/")
            Call<HttpBase.RetBase> updateUserInfo(@Body HttpBase.ReqBase req);

      ● 如果上传文件 @Part
        @Multipart
        @POST("/url")
        Call<ResponseBody> uploadFlie(
              @Part("description") RequestBody description,
              @Part("files") MultipartBody.Part file
        );
     */

    @GET("api/data/{category}/{count}/{page}")
    Call<NewsEntity> getNews(@Path("category") String category, @Path("count") int count, @Path("page") int page);
    //Call<NewsEntity> getNews(@Path("category") String category, @Path("count") int count, @Path("page") int page);

    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description, @Part MultipartBody.Part file);
}
