package com.example.media_and_search;

import androidx.appcompat.app.AppCompatActivity;

import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //...
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = this.getIntent();
        if (intent.getAction().compareTo(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH) == 0) {

            String mediaFocus = intent.getStringExtra(MediaStore.EXTRA_MEDIA_FOCUS);
            String query = intent.getStringExtra(SearchManager.QUERY);

            // Some of these extras may not be available depending on the search mode
            String album = intent.getStringExtra(MediaStore.EXTRA_MEDIA_ALBUM);
            String artist = intent.getStringExtra(MediaStore.EXTRA_MEDIA_ARTIST);
            String genre = intent.getStringExtra("android.intent.extra.genre");
            String playlist = intent.getStringExtra("android.intent.extra.playlist");
            String title = intent.getStringExtra(MediaStore.EXTRA_MEDIA_TITLE);

            // Determine the search mode and use the corresponding extras
            if (mediaFocus == null) {
                // 'Unstructured' search mode (backward compatible)
                playUnstructuredSearch(query);

            } else if (mediaFocus.compareTo("vnd.android.cursor.item/*") == 0) {
                if (query.isEmpty()) {
                    // 'Any' search mode
                    playResumeLastPlaylist();
                } else {
                    // 'Unstructured' search mode
                    playUnstructuredSearch(query);
                }

            } else if (mediaFocus.compareTo(MediaStore.Audio.Genres.ENTRY_CONTENT_TYPE) == 0) {
                // 'Genre' search mode
                playGenre(genre);

            } else if (mediaFocus.compareTo(MediaStore.Audio.Artists.ENTRY_CONTENT_TYPE) == 0) {
                // 'Artist' search mode
                playArtist(artist, genre);

            } else if (mediaFocus.compareTo(MediaStore.Audio.Albums.ENTRY_CONTENT_TYPE) == 0) {
                // 'Album' search mode
                playAlbum(album, artist);

            } else if (mediaFocus.compareTo("vnd.android.cursor.item/audio") == 0) {
                // 'Song' search mode
                playSong(album, artist, genre, title);

            } else if (mediaFocus.compareTo(MediaStore.Audio.Playlists.ENTRY_CONTENT_TYPE) == 0) {
                // 'Playlist' search mode
                playPlaylist(album, artist, genre, playlist, title);
            }
        }
    }

    public void playMedia(Uri file) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(file);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    public void playSearchArtist(String artist) {
        Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
        intent.putExtra(MediaStore.EXTRA_MEDIA_FOCUS,
                MediaStore.Audio.Artists.ENTRY_CONTENT_TYPE);
        intent.putExtra(MediaStore.EXTRA_MEDIA_ARTIST, artist);
        intent.putExtra(SearchManager.QUERY, artist);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
