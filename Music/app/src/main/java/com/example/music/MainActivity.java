package com.example.music;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void playMedia(Uri file) {
        Intent viewMediaIntent = new Intent();
        viewMediaIntent.setAction(android.content.Intent.ACTION_VIEW);
        viewMediaIntent.setDataAndType(file, "audio/*");
        viewMediaIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(viewMediaIntent);
    }

    public void onClickMusic(View view) {

        String filename="https://www.soundhelix.com/examples/mp3/SoundHelix-Song-8.mp3";
        Uri uri=Uri.parse(filename);
        playMedia(uri);
    }


}
