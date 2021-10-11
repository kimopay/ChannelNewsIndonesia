package com.kimopay.channelnewsindonesia.data.model;

import com.google.gson.annotations.SerializedName;

public class News {

    @SerializedName("id")
    private String id;

    @SerializedName("title")
    private String title;

    @SerializedName("image")
    private String image;

    @SerializedName("description")
    private String description;

    @SerializedName("view")
    private String view;

    @SerializedName("category_id")
    private String category_id;

    @SerializedName("reporter_id")
    private String reporter_id;

    @SerializedName("created_at")
    private String created_at;

    @SerializedName("updated_at")
    private String updated_at;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getView() {
        return view;
    }

    public String getCategory_id() {
        return category_id;
    }

    public String getReporter_id() {
        return reporter_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }
}
