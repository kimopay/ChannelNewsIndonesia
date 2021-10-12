package com.kimopay.channelnewsindonesia.data.response;

import com.google.gson.annotations.SerializedName;
import com.kimopay.channelnewsindonesia.data.model.Ads;

import java.util.List;

public class ResponseAds {

    @SerializedName("kode")
    private String kode;

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("ads_result")
    private List<Ads> ads_result;

    @SerializedName("ads_category")
    private List<Ads> ads_category;

    @SerializedName("ads_item")
    private Ads ads_item;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<Ads> getAds_result() {
        return ads_result;
    }

    public List<Ads> getAds_category() {
        return ads_category;
    }

    public Ads getAds_item() {
        return ads_item;
    }
}
