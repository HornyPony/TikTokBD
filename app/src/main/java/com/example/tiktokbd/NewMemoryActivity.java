package com.example.tiktokbd;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tiktokbd.Data.MemoryDbHelper;
import com.example.tiktokbd.Model.Memory;

import java.io.IOException;
import java.io.InputStream;

public class NewMemoryActivity extends AppCompatActivity {
    //TODO: Step 1. add a request code for gallery
    private static final  int GALLERY_REQUEST_CODE = 100;

    //TODO: Step 4. add a camera request code
    private ImageView selectedImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_memory);

        this.selectedImageView = findViewById(R.id.new_memory_selected_image);
    }

    public void openGallery(View view) {
        //TODO: Step 2. launch gallery request
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), GALLERY_REQUEST_CODE);
    }

    public void openCamera(View view) {
        //TODO: Step 5. launch camera activity
    }

    public void cancel(View view) {
        finish();
    }

    public void save(View view) {
        //TODO: Step 9. Update model object
        Memory memory = new Memory();
        Bitmap image = ((BitmapDrawable)selectedImageView.getDrawable()).getBitmap();

        new MemoryDbHelper(this).addMemory(memory);
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
}