package com.diajarin.id.Models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Tips {
    public String judul;
    public String thumbnail;
    public String url;

    public Tips() {
    }

    public Tips(String judul, String thumbnail, String url) {
        this.judul = judul;
        this.thumbnail = thumbnail;
        this.url = url;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", judul);
        result.put("img", thumbnail);
        result.put("url", url);

        return result;
    }

}