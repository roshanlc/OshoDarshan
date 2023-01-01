package com.roshanam.oshodarshan.ui.utils;

import static java.lang.String.*;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roshanam.oshodarshan.R;

import java.util.ArrayList;

public class AlbumsAdapter extends  RecyclerView.Adapter<AlbumsAdapter.ViewHolder> {


        private ArrayList<Album> albums;

        public AlbumsAdapter(ArrayList<Album> albums) {
            this.albums = albums;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view = inflater.inflate(R.layout.list_items,parent,false);
            return new ViewHolder(view);
        }

        @SuppressLint("DefaultLocale")
        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position)
        {
            Album album = albums.get(position);
            String name = album.getName();
            String url = album.getUrl();
            // Set name and url
            holder.name.setText(name);
            holder.url.setText(url);
            holder.count.setText(format("%d.",position+1));
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

    /**
     * Remove all items from the list
     */
    public void clearList(){
            this.albums.clear();
        }

        public  static  class ViewHolder extends RecyclerView.ViewHolder{

            public TextView name;
            public TextView url;
            public Button downloadBtn;
            public TextView count;

            public ViewHolder(View v)
            {
                super(v);
                name = v.findViewById(R.id.nameValue);
                url = v.findViewById(R.id.urlValue);
                count = v.findViewById(R.id.countValue);
                downloadBtn = v.findViewById(R.id.downloadAlbum);
            }
        }

}
