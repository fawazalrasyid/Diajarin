package com.diajarin.id.Models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Slider {
    public String img;

    public Slider() {
    }

    public Slider(String img) {
        this.img = img;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("img", img);

        return result;
    }

}
