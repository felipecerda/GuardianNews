package com.example.guardiannews;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context mContext;
    private List<NewsItem> mNewsList;

    public NewsAdapter(Context context, List<NewsItem> newsItems){
        mContext = context;
        mNewsList = newsItems;
    }

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView titleView;
        private TextView sectionView;
        private TextView authorView;
        private TextView dateView;
        private View parentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.parentView = itemView;
            titleView = itemView.findViewById(R.id.title);
            sectionView = itemView.findViewById(R.id.section);
            authorView = itemView.findViewById(R.id.author);
            dateView = itemView.findViewById(R.id.date_time);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
        // Get the current news that was clicked on
        final NewsItem currentNewsItem = mNewsList.get(position);

        holder.titleView.setText(currentNewsItem.getTitle());
        holder.sectionView.setText(currentNewsItem.getSection());
        holder.authorView.setText(currentNewsItem.getAuthor());

        //Formats the date
        SimpleDateFormat FORMATTER = new SimpleDateFormat("LLL dd, yyyy 'at' HH:mm");
        Date localDateTime = currentNewsItem.getDate();
        String formattedDate = FORMATTER.format(localDateTime);

        holder.dateView.setText(formattedDate);

        // Set an OnClickListener to open a website with more information about the selected article
        holder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri newsItemUri = Uri.parse(currentNewsItem.getUrl());

                // Create a new intent to view the news URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsItemUri);

                // Send the intent to launch a new activity
                mContext.startActivity(websiteIntent);
            }
        });
    }

    /**
     *  Clear all data (a list of {@link NewsItem} objects)
     */
    public void clearAll() {
        mNewsList.clear();
        notifyDataSetChanged();
    }

    /**
     * Add  a list of {@link NewsItem}
     * @param newsList is the list of news, which is the data source of the adapter
     */
    public void addAll(List<NewsItem> newsList) {
        mNewsList.clear();
        mNewsList.addAll(newsList);
        notifyDataSetChanged();
    }

}
