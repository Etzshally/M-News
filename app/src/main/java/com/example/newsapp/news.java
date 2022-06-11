package com.example.newsapp;

public class news {

    private Integer id;
    private String news_url;
    private String heading;
    private String desc_news;
    private String news_link;
    private String news_time;

    //setters
    public void set_news_id(Integer id) {
        this.id = id;
    }

    public void set_news_heading(String hd) {
        this.heading = hd;
    }

    public void set_news_link(String l) {
        this.news_link = l;
    }

    public void set_news_time(String t) {
        this.news_time = t;
    }

    public void set_news_desc(String d) {
        this.desc_news = d;
    }

    public void set_news_url(String u) {
        this.news_url = u;
    }

    // getters
    public String get_news_heading() {
        return heading;
    }

    public String get_news_desc() {
        return desc_news;
    }

    public String get_news_url() {
        return news_url;
    }

    public String get_news_time() {
        return news_time;
    }

    public String get_news_link() {
        return news_link;
    }

    public Integer get_news_id() {
        return id;
    }


}
