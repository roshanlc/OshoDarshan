package com.roshanam.oshodarshan.ui.utils;

import static java.lang.String.*;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roshanam.oshodarshan.MainActivity;
import com.roshanam.oshodarshan.R;
import com.roshanam.oshodarshan.ui.home.HomeFragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class AlbumsAdapter extends RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {


    private ArrayList<Album> albums;

    public AlbumsAdapter(ArrayList<Album> albums) {
        this.albums = albums;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_items, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albums.get(position);
        String name = album.getName();
        String url = album.getUrl();
        // Set name and url
        holder.name.setText(name);
        holder.url.setText(url);
        holder.count.setText(format("%d.", position + 1));
        holder.downloadBtn.setOnClickListener((view -> {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            Thread workThread = new Thread() {
                @Override
                public void run() {
                    System.out.println("Starting episodes extraction for the album, name="+name+", url = "+url);
                    // Create new album downloader object
                    AlbumDownloader albumDownloader = new AlbumDownloader(album);
                    try {
                        albumDownloader.extractEpisodes();
                    } catch (IOException e) {
                        Log.d("DEBUG", "onBindViewHolder: " + e.getMessage());
                    }
                }
            };

            // Run in background
            executor.submit(workThread);
            executor.shutdown();

        }));

    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView url;
        public Button downloadBtn;
        public TextView count;

        public ViewHolder(View v) {
            super(v);
            name = v.findViewById(R.id.nameValue);
            url = v.findViewById(R.id.urlValue);
            count = v.findViewById(R.id.countValue);
            downloadBtn = v.findViewById(R.id.downloadAlbum);
        }
    }

}
