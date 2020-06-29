package com.example.tiktokbd.Model;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Base64;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.ByteArrayOutputStream;

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
    @ColumnInfo(name = "tik_image")
    private String image;


    private static final float PREFERRED_WIDTH = 250;
    private static final float PREFERRED_HEIGHT = 250;


    public static final int COL_ID = 0;
    public static final int COL_TITLE = 1;
    public static final int COL_IMAGE = 2;

@Ignore
    public Video() {
    }

    public Video(long id, String description, String whoRecommended, String URL, String image) {
        this.id = id;
        this.description = description;
        this.whoRecommended = whoRecommended;
        this.URL = URL;
        this.image = image;
    }

    @Ignore
    public Video(String description, String URL) {
        this.description = description;
        this.URL = URL;
    }

    public long getId() {
        return id;
    }

    public String getImage(){
    return  image;
    }

    public Bitmap getImageAsBitmap() {

        return stringToBitmap(this.image);
    }

    public void setImage(){
    this.image = image;
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

    @Ignore
    public Video(Cursor cursor) {
        this.image = cursor.getString(COL_IMAGE);
    }

@Ignore
    public Video(Bitmap image) {
        this.image = bitmapToString(resizeBitmap(image));
    }


    public String getImageAsString() {
        return this.image;
    }

    private static String bitmapToString(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }

    private static Bitmap stringToBitmap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            return BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static Bitmap resizeBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = PREFERRED_WIDTH / width;
        float scaleHeight = PREFERRED_HEIGHT / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bitmap, 0, 0, width, height, matrix, false);
        bitmap.recycle();
        return resizedBitmap;
    }
}