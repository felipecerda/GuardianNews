package com.example.guardiannews;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsItem>> {

    /** Tag for log messages */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public NewsLoader(Context context, String url){
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public List<NewsItem> loadInBackground() {
        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of news items.
        Log.i(LOG_TAG,"Before QueryUtils");
        List<NewsItem> newsItems = QueryUtils.fetchNewsItemData(mUrl);
        return newsItems;
    }

}
