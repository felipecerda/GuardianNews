package com.example.guardiannews;

import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<NewsItem> {

    public NewsAdapter(Activity context, ArrayList<NewsItem> newsItems){
        super(context, 0, newsItems);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.news_list_item, parent, false);
        }

        NewsItem currentNewsItem = getItem(position);

        String title = currentNewsItem.getTitle();
        String section = currentNewsItem.getSection();
        String author = currentNewsItem.getAuthor();

        TextView titleView = listItemView.findViewById(R.id.title);
        titleView.setText(title);

        TextView sectionView = listItemView.findViewById(R.id.section);
        sectionView.setText(section);

        TextView authorView = listItemView.findViewById(R.id.author);
        authorView.setText(author);

        //Formats the date
        DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("LLL dd, yyyy 'at' hh:mm a");
        LocalDateTime localDateTime = currentNewsItem.getDate();
        String formattedDate = FORMATTER.format(localDateTime);

        TextView dateView = listItemView.findViewById(R.id.date_time);
        dateView.setText(formattedDate);

        return listItemView;
    }



}
