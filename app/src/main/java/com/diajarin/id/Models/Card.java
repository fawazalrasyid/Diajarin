package com.diajarin.id.Models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Card {
    public String name;
    public String img;
    public String id;

    public Card() {
    }

    public Card(String name, String img, String id) {
        this.name = name;
        this.img = img;
        this.id = id;

    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("img", img);
        result.put("id", id);

        return result;
    }

}
