package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RssOptionsActivity extends AppCompatActivity {

    Button topMus;
    Button topAlb;
    Button topApps;
    Button topMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_options);

        topMus = findViewById(R.id.button3);
        topAlb = findViewById(R.id.button4);
        topApps = findViewById(R.id.button5);
        topMovies = findViewById(R.id.button6);

        Intent intent = new Intent(getApplicationContext(), SongsActivity.class);

        topMus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("LINK", "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=25/xml");
                startActivity(intent);
            }
        });

        topAlb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("LINK", "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topalbums/limit=25/xml");
                startActivity(intent);
            }
        });
        topApps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("LINK", "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=25/xml");
                startActivity(intent);
            }
        });
        topMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("LINK", "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topMovies/xml");
                startActivity(intent);
            }
        });

    }
}