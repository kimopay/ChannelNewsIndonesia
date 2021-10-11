package com.kimopay.channelnewsindonesia.data.response;

import com.google.gson.annotations.SerializedName;
import com.kimopay.channelnewsindonesia.data.model.Category;

import java.util.List;

public class ResponseCategory {

    @SerializedName("kode")
    private String kode;

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("category_item")
    private Category category_item;

    @SerializedName("category_result")
    private List<Category> category_result;

    public Category getCategory_item() {
        return category_item;
    }

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public List<Category> getCategory_result() {
        return category_result;
    }
}
