package com.kimopay.channelnewsindonesia.data.response;

import com.google.gson.annotations.SerializedName;
import com.kimopay.channelnewsindonesia.data.model.News;

import java.util.ArrayList;
import java.util.List;

public class ResponseNews {

    @SerializedName("kode")
    private String kode;

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("news_result")
    private ArrayList<News> news_result;

    @SerializedName("news_item")
    private News news_item;

    @SerializedName("news_item_random")
    private News news_item_random;

    public ArrayList<News> getNews_result() {
        return news_result;
    }

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public News getNews_item() {
        return news_item;
    }

    public News getNews_item_random() {
        return news_item_random;
    }
}
