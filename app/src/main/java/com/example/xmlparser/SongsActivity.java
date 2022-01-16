package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SongsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        Bundle extras = getIntent().getExtras();
        new DownloadXmlTask().
                execute(extras.getString("LINK"));
    }
    private class DownloadXmlTask extends AsyncTask<String, Void, String> {
        List<SongEntry> entries;

        @Override
        protected String doInBackground(String... urls) {
            XmlPullParser parser = Xml.newPullParser();

            try {
                InputStream f = downloadUrl(urls[0]);
                parser.setInput(f, null);
                parser.nextTag();
                this.entries = SongEntry.readFeed(parser);

                for (SongEntry e : entries) {
                    System.out.println(e);
                }

            } catch (Exception e) {
                System.out.println("__Err: => " + e);
            }
            return null;
        }
        @Override
        protected void onPreExecute(){

        }

        @Override
        protected void onPostExecute(String result) {
            final ListView listview = findViewById(R.id.listview);
            listview.setAdapter(new CustomAdapterSongs(getApplicationContext(), entries));

            listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Object obj = listview.getAdapter().getItem(position);
                    Uri uri = Uri.parse(obj.toString());
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            });
        }

        private InputStream downloadUrl(String urlString) throws IOException {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            return conn.getInputStream();
        }
    }
}