package com.example.tiktokbd.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "tik")
public class Video {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "tik_id")
    private long id;
    @ColumnInfo(name = "tik_description")
    private String description;
    @ColumnInfo(name = "tik_date")
    private String date;
    @ColumnInfo(name = "tik_who_recommended")
    private String whoRecommended;
    @ColumnInfo(name = "tik_url")
    private String URL;
    @ColumnInfo(name = "tik_topic")
    private String topic;

@Ignore
    public Video() {
    }

    public Video(long id, String description, String whoRecommended, String URL) {
        this.id = id;
        this.description = description;
        this.whoRecommended = whoRecommended;
        this.URL = URL;
        this.topic = topic;
    }

    @Ignore
    public Video(String description, String URL) {
        this.description = description;
        this.URL = URL;
    }

    public long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDate() {
        return date;
    }

    public String getWhoRecommended() {
        return whoRecommended;
    }

    public String getURL() {
        return URL;
    }

    public  String getTopic() {return topic;}

    public void setId(long id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTopic(String topic) {this.topic = topic;}

    public void setWhoRecommended(String whoRecommended) {
        this.whoRecommended = whoRecommended;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}