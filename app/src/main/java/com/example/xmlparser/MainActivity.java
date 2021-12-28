package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Xml;
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
    TextView tex;

    String s = new String(
            "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
            "<entry>\n" +
            "    <id >1581087034</id>\n" +
            "\n" +
            "\n" +
            "    <title>Shivers - Ed Sheeran</title>\n" +
            "\n" +
            "    <name>Shivers</name>\n" +
            "    <price>1.29$</price>\n" +
            "    <imagelink>\n" +
            "        https://is3-ssl.mzstatic.com/image/thumb/Music125/v4/c5/d8/c6/c5d8c675-63e3-6632-33db-2401eabe574d/190296491412.jpg/55x55bb.png\n" +
            "    </imagelink>\n" +
            "    <date>\n" +
            "        September 9, 2021\n" +
            "    </date>\n" +
            "</entry>");
    InputStream f = new ByteArrayInputStream(s.getBytes());
    XmlPullParser parser = Xml.newPullParser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<SongEntry> entries = new ArrayList();

        SongEntry entre = null;
        try {
            parser.setInput(f, null);
            parser.nextTag();
            entre = SongEntry.readSong(parser);
            tex = findViewById(R.id.text);
            tex.setText(entre.toString());
        } catch (Exception e){
            System.out.println("__Err: => " + e);
        }
    }
}