package com.roshanam.oshodarshan.ui.utils;

import static java.lang.String.format;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.roshanam.oshodarshan.R;

import java.util.ArrayList;

public class QuotesAdapter extends RecyclerView.Adapter<QuotesAdapter.ViewHolder>{

    private ArrayList<String> quotes;

    public QuotesAdapter(ArrayList<String> quotes) {
        this.quotes = quotes;
    }

    @NonNull
    @Override
    public QuotesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.quotes_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String quote = quotes.get(position);
        System.out.println(quote); // TODO: remove later
        holder.quote.setText(quote);
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
        return quotes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView quote;


        public ViewHolder(View v) {
            super(v);
            quote = v.findViewById(R.id.quote);

        }
    }
}
