package com.example.tiktokbd.Data;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tiktokbd.Model.Video;

@Database(entities = {Video.class}, version = 3)
public abstract class TikAppDatabase extends RoomDatabase {

    public abstract TikTokDAO getTikTokDAO();
}
