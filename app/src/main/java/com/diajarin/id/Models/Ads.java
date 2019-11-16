package com.diajarin.id.Models;

public class Ads {

    String banner, internsial;

    public Ads() {
    }

    public Ads(String banner, String internsial) {
        this.banner = banner;
        this.internsial = internsial;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getInternsial() {
        return internsial;
    }

    public void setInternsial(String internsial) {
        this.internsial = internsial;
    }

}
