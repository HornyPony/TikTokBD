package com.example.tiktokbd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class NewMemoryActivity extends AppCompatActivity {
    Bitmap imageAsBitmap;
    String imageAsString;
    private ImageView selectedImageView;
    //TODO: Step 1. add a request code for gallery
    private static final  int GALLERY_REQUEST_CODE = 100;

    //TODO: Step 4. add a camera request code


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memory);
        this.selectedImageView = findViewById(R.id.new_memory_selected_image);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       Intent returnIntent = new Intent();
        if (imageAsBitmap != null){
            returnIntent.putExtra("image", imageAsBitmap);
            setResult(Activity.RESULT_OK,returnIntent);
            finish();

    }


    }

    public void openGallery(View view) {
        //TODO: Step 2. launch gallery request
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
    }



    public void cancel(View view) {
        finish();
    }

    public void save(View view) {
        //TODO: Step 9. Update model object
          imageAsBitmap = ((BitmapDrawable)selectedImageView.getDrawable()).getBitmap();
        //imageAsString = bitmapToString(imageAsBitmap);
finish();

    }

    //TODO: Step 3. handle activity result
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == GALLERY_REQUEST_CODE) {
            try {
                Uri selectedImage = data.getData();
                InputStream imageStream = getContentResolver().openInputStream(selectedImage);
                selectedImageView.setImageBitmap(BitmapFactory.decodeStream(imageStream));
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
    }

    private static  String bitmapToString(Bitmap bitmap) {

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] b = baos.toByteArray();
        return Base64.encodeToString(b, Base64.DEFAULT);
    }


}