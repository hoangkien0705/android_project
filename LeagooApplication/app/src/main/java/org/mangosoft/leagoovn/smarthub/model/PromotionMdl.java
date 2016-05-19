package org.mangosoft.leagoovn.smarthub.model;

/**
 * Created by kienhv on 4/8/2016.
 * Object event promotion
 * params thumb: image thumb of event
 */
public class PromotionMdl {
    private String title;
    private int thumb;
    private String time;
    private String description;
    private String url;
    private String address;

    public PromotionMdl() {
    }

    public PromotionMdl(String title, int thumb, String time, String description, String url, String address) {
        this.title = title;
        this.thumb = thumb;
        this.time = time;
        this.description = description;
        this.url = url;
        this.address = address;

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
