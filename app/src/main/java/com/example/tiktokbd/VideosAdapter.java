package com.example.tiktokbd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.tiktokbd.Model.Video;

import java.util.ArrayList;

public class VideosAdapter extends RecyclerView.Adapter<VideosAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<Video> videos;
    private MainActivity mainActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView tikTokImageView;
        public TextView descriptionTextView;
        public TextView whoRecommendedTextView;
        public TextView URLTextView;


        public MyViewHolder(View view) {
            super(view);
            tikTokImageView = view.findViewById(R.id.tikTokImageView);
            descriptionTextView = view.findViewById(R.id.descriptionTextView);
            whoRecommendedTextView = view.findViewById(R.id.whoRecommendedTextView);
            URLTextView = view.findViewById(R.id.urlTextView);
        }
    }


    public VideosAdapter(Context context, ArrayList<Video> videos, MainActivity mainActivity) {
        this.context = context;
        this.videos = videos;
        this.mainActivity = mainActivity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.film_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {


        final Video video = videos.get(position);

        holder.descriptionTextView.setText(video.getDescription());
//        holder.dateTextView.setText(video.getDate());
        holder.whoRecommendedTextView.setText(video.getWhoRecommended());
        holder.URLTextView.setText(video.getURL());
        holder.tikTokImageView.setImageBitmap(video.getImageAsBitmap());


        holder.itemView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {

                mainActivity.addAndEditVideos(true, video, position);
            }
        });

    }

    @Override
    public int getItemCount() {

        return videos.size();
    }


}