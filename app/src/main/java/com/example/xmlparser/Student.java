package com.example.xmlparser;

import android.content.Context;
import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

public class Student {

    private String id;
    private String fname;
    private String lname;
    private int age;

    public Student(String id, String fname, String lname, int age){
        this.age = age;
        this.id = id;
        this.fname = fname;
        this.lname = lname;
    }

    public void writeXml(String filename, Context c){

        XmlSerializer xser = Xml.newSerializer();
        try
        {

            FileOutputStream f = c.openFileOutput(filename, Context.MODE_PRIVATE);
            StringWriter writer = new StringWriter();
            xser.setOutput(writer);
            xser.startDocument("UTF-8", true);
            xser.startTag(null, "filiere");
            xser.startTag(null, "students");
            xser.startTag(null, "student");

            xser.startTag(null, "id");
            xser.text(this.fname);
            xser.endTag(null, "id");

            xser.startTag(null, "lname");
            xser.text(this.lname);
            xser.endTag(null, "lname");

            xser.startTag(null, "fname");
            xser.text(this.fname);
            xser.endTag(null, "fname");

            xser.startTag(null, "age");
            xser.text(Integer.toString(this.age));
            xser.endTag(null, "age");

            xser.endTag(null, "student");
            xser.endTag(null, "students");
            xser.endTag(null, "filiere");

            xser.endDocument();
            xser.flush();
            String dataWrite = writer.toString();
            f.write(dataWrite.getBytes());

            f.close();


        }catch (FileNotFoundException
                e) {
            e.printStackTrace();
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalStateException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", fname='" + fname + '\'' +
                ", lname='" + lname + '\'' +
                ", age=" + age +
                '}';
    }
}
