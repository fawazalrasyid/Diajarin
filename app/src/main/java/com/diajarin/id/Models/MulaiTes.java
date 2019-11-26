package com.diajarin.id.Models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class MulaiTes {
    public String name;
    public String url;

    public MulaiTes() {
    }

    public MulaiTes(String name, String url) {
        this.name = name;
        this.url = url;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("url", url);

        return result;
    }

}
