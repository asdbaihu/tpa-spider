package com.hehemedia.spider.pojo;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by Lenovo on 2017/7/11.
 */
@Component
public class NewsInfo {

    private Integer id;
    private String title;
    private Date newsTime;
    private String url;


    private String source;

    public NewsInfo() {
    }

    public NewsInfo(String title, Date newsTime, String url, String source) {
        this.title = title;
        this.newsTime = newsTime;
        this.url = url;
        this.source = source;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return newsTime;
    }

    public void setDate(Date date) {
        this.newsTime = date;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "NewsInfo{" +
                "title='" + title + '\'' +
                ", date=" + newsTime +
                ", url='" + url + '\'' +
                ", source='" + source + '\'' +
                '}';
    }
}
