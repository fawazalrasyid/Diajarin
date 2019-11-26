package com.diajarin.id.Models;

public class Tes {

    private String id, overview, profile, img, tanyaurl, tipsurl;

    public Tes(String id, String overview, String profile, String img, String tanyaurl, String tipsurl) {
        this.id = id;
        this.overview = overview;
        this.profile = profile;
        this.img = img;
        this.tanyaurl = tanyaurl;
        this.tipsurl = tipsurl;
    }


    public Tes() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTanyaurl() {
        return tanyaurl;
    }

    public void setTanyaurl(String tanyaurl) {
        this.tanyaurl = tanyaurl;
    }

    public String getTipsurl() {
        return tipsurl;
    }

    public void setTipsurl(String tipsurl) {
        this.tipsurl = tipsurl;
    }

}
