package com.example.tiktokbd.Data;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tiktokbd.Model.Video;

import java.util.List;

@Dao
public interface TikTokDAO {

    @Insert
    public long addTikTok(Video video);

    @Update
    public  void  updateTikTok(Video video);

    @Delete
    public void deleteTikTok(Video video);

    @Query("select * from tik")
    public List<Video> getAllVideos();

    @Query("select * from tik where tik_id ==:videoId ")
    public Video getVideo(long videoId);
}
