package com.example.tiktokbd;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tiktokbd.Data.TikAppDatabase;
import com.example.tiktokbd.Model.Video;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private VideosAdapter videosAdapter;
    TikAppDatabase tikToksAppDatabase;
    private ArrayList<Video> videos = new ArrayList<>();
    private RecyclerView recyclerView;
    private Spinner topicSpinner;
    private ArrayList spinnerArrayList;
    private ArrayAdapter spinnerAdapter;
    String topic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        tikToksAppDatabase = Room.databaseBuilder(getApplicationContext(), TikAppDatabase.class, "VideosDB").fallbackToDestructiveMigration().allowMainThreadQueries().build();
        videos.addAll(tikToksAppDatabase.getTikTokDAO().getAllVideos());

        videosAdapter = new VideosAdapter(this, videos, MainActivity.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(videosAdapter);


        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addAndEditVideos(false, null, -1);
            }


        });


    }

    public void addAndEditVideos(final boolean isUpdate, final Video video, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.add_video, null);


        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilderUserInput.setView(view);

        TextView newCarTitle = view.findViewById(R.id.newCarTitle);
        final EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);
        final EditText whoRecommendedEditText = view.findViewById(R.id.whoRecommendedEditText);
        final EditText urlEditText = view.findViewById(R.id.urlEditText);
        final Button addPhotoButton = view.findViewById(R.id.addPhotoButton);
//        tv = findViewById(R.id.topicTextView);
//        topicSpinner = view.findViewById(R.id.topicSpinner);
/*
        spinnerArrayList = new ArrayList();
        spinnerArrayList.add("Unknown");
        spinnerArrayList.add("Memes");
        spinnerArrayList.add("LifeHacks");
        spinnerArrayList.add("Trends");
        spinnerArrayList.add("Adapt");
        spinnerArrayList.add("Aesthetics");
        spinnerArrayList.add("Web design");
        spinnerArrayList.add("Coding");
        spinnerArrayList.add("Creative ideas");
        spinnerArrayList.add("What to read");
        spinnerArrayList.add("What to watch");
        spinnerArrayList.add("Bots");
        spinnerArrayList.add("WebSites");
        spinnerArrayList.add("Recipes");
*/


        /*spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        topicSpinner.setAdapter(spinnerAdapter);
        topicSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedTopic = (String) parent.getItemAtPosition(position);
                if (!(TextUtils.isEmpty(selectedTopic))) {
                    if (selectedTopic.equals("Memes")) {
                        topic = "Memes";
                    } else if (selectedTopic.equals("LifeHacks")) {
                        topic = "LifeHacks";
                    } else if (selectedTopic.equals("Trends")) {
                        topic = "Trends";
                    } else if (selectedTopic.equals("Adapt")) {
                        topic = "Adapt";
                    } else if (selectedTopic.equals("Aesthetics")) {
                        topic = "Aesthetics";
                    } else if (selectedTopic.equals("Web design")) {
                        topic = "Web design";
                    } else if (selectedTopic.equals("Coding")) {
                        topic = "Coding";
                    } else if (selectedTopic.equals("Creative ideas")) {
                        topic = "Creative ideas";
                    } else if (selectedTopic.equals("What to read")) {
                        topic = "What to read";
                    } else if (selectedTopic.equals("What to watch")) {
                        topic = "What to watch";
                    } else {
                        topic = "Unknown";
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                topic = "Unknown";
            }

        });
*/

        newCarTitle.setText(!isUpdate ? "Add Video" : "Edit Video");

        if (isUpdate && video != null) {
            descriptionEditText.setText(video.getDescription());
            whoRecommendedEditText.setText(video.getWhoRecommended());
            urlEditText.setText(video.getURL());
//             tv.setText(topic);

        }

        alertDialogBuilderUserInput
                .setCancelable(false)
                .

                        setPositiveButton(isUpdate ? "Update" : "Save", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {

                            }
                        })
                .

                        setNegativeButton(isUpdate ? "Delete" : "Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialogBox, int id) {

                                        if (isUpdate) {

                                            deleteVideo(video, position);
                                        } else {

                                            dialogBox.cancel();

                                        }

                                    }
                                });


        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).

                setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if (TextUtils.isEmpty(descriptionEditText.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Please, describe this TikTok", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(whoRecommendedEditText.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Please, tell me who recommended U this TikTok", Toast.LENGTH_SHORT).show();
                            return;
                        } else if (TextUtils.isEmpty(urlEditText.getText().toString())) {
                            Toast.makeText(MainActivity.this, "Please, enter the link to the TikTok", Toast.LENGTH_SHORT).show();
                            return;
                        } /*else if (topic.equals("Unknown")) {
                            Toast.makeText(MainActivity.this, "Please, choose a topic", Toast.LENGTH_LONG).show();
                        }*/ else {
                            alertDialog.dismiss();
                        }


                        if (isUpdate && v != null) {

                            updateVideo(descriptionEditText.getText().toString(), whoRecommendedEditText.getText().toString(),
                                    urlEditText.getText().toString(), position);
                        } else {

                            createVideo(descriptionEditText.getText().toString(), whoRecommendedEditText.getText().toString(),
                                    urlEditText.getText().toString());
                        }
                    }
                });
    }

    private void deleteVideo(Video video, int position) {

        videos.remove(position);
        tikToksAppDatabase.getTikTokDAO().deleteTikTok(video);
        videosAdapter.notifyDataSetChanged();
    }

    private void updateVideo(String description, String whoRecommended, String url, int position) {

        Video video = videos.get(position);

        video.setDescription(description);
        video.setWhoRecommended(whoRecommended);
        video.setURL(url);
        video.setTopic(topic);

        tikToksAppDatabase.getTikTokDAO().updateTikTok(video);

        videos.set(position, video);

        videosAdapter.notifyDataSetChanged();


    }

    private void createVideo(String description, String whoRecommended, String url) {

        long id = tikToksAppDatabase.getTikTokDAO().addTikTok(new Video(0, description, whoRecommended, url));


        Video video = tikToksAppDatabase.getTikTokDAO().getVideo(id);

        if (video != null) {

            videos.add(0, video);
            videosAdapter.notifyDataSetChanged();

        }

    }


}