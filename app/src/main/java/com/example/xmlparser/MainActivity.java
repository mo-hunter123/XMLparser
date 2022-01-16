package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    Button wr_xml;
    Button re_musics;

    XmlPullParser parser = Xml.newPullParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        re_musics = findViewById(R.id.button);
        wr_xml = findViewById(R.id.button2);

        re_musics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RssOptionsActivity.class);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    System.out.println("Error=====> " + e);
                }
            }
        });

        wr_xml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), XmlRempActivity.class);
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    System.out.println("Error=====> " + e);
                }
            }
        });
    }
}