package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Xml;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class SongsActivity extends AppCompatActivity {

    XmlPullParser parser = Xml.newPullParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_songs);

        new DownloadXmlTask().
                execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=25/xml");
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