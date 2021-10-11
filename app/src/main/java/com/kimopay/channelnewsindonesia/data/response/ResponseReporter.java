package com.kimopay.channelnewsindonesia.data.response;

import com.google.gson.annotations.SerializedName;
import com.kimopay.channelnewsindonesia.data.model.Reporter;

import java.util.List;

public class ResponseReporter {

    @SerializedName("kode")
    private String kode;

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("reporters_item")
    private Reporter reporters_item;

    @SerializedName("reporters_result")
    private List<Reporter> reporters_result;

    public String getKode() {
        return kode;
    }

    public String getPesan() {
        return pesan;
    }

    public Reporter getReporters_item() {
        return reporters_item;
    }

    public List<Reporter> getReporters_result() {
        return reporters_result;
    }
}
