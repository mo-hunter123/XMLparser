package com.example.xmlparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class XmlRempActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml_remp);

        Student s = new Student("UID12", "Mohammed", "Ahrrass", 21);
        s.writeXml("test.xml", getApplicationContext());
    }
}