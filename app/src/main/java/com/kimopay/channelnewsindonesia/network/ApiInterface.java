package com.kimopay.channelnewsindonesia.network;

import com.kimopay.channelnewsindonesia.data.response.ResponseCategory;
import com.kimopay.channelnewsindonesia.data.response.ResponseNews;
import com.kimopay.channelnewsindonesia.data.response.ResponseReporter;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    //category
    @GET("category/getCategoryAll.php")
    Call<ResponseCategory> getCategoryAll();

    @GET("category/getCategoryId.php")
    Call<ResponseCategory> getCategoryId(@Query("id") String id);

    //news
    @GET("news/getNewsRandom.php")
    Call<ResponseNews> getNewsRandom();

    @GET("news/getNewsId.php")
    Call<ResponseNews> getNewsId(@Query("id") String id);

    @GET("news/getNewsAll.php")
    Call<ResponseNews> getNewsAll(@Query("record_awal") String record_awal,
                                  @Query("jumlah_record") String jumlah_record);

    //reporter
    @GET("reporters/getReporterId.php")
    Call<ResponseReporter> getReporterId(@Query("id") String id);

}
