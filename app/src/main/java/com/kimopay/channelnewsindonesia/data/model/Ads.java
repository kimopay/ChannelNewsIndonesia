package com.kimopay.channelnewsindonesia.data.model;

import com.google.gson.annotations.SerializedName;

public class Ads {

    @SerializedName("id")
    private String id;

    @SerializedName("image")
    private String image;

    @SerializedName("name")
    private String name;

    @SerializedName("category")
    private String category;

    @SerializedName("link")
    private String link;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getLink() {
        return link;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
