package com.example.web_search;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;

public class SearchActivity extends Activity {
    public void searchWeb(String query) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.putExtra(SearchManager.QUERY, query);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
