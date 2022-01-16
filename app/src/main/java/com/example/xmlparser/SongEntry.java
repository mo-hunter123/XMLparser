package com.example.xmlparser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SongEntry {

    private String idSong;
    private String titleSong;
    private String nameSong;
    private String priceSong;
    private String imageLink;
    private String dateReleased;

    public SongEntry(
            String idSong,
            String titleSong,
            String nameSong,
            String priceSong,
            String imageLink,
            String dateReleased
    ){
        this.dateReleased = dateReleased;
        this.idSong = idSong;
        this.titleSong = titleSong;
        this.nameSong = nameSong;
        this.priceSong = priceSong;
        this.imageLink = imageLink;
    }

    public static List<SongEntry> readFeed(XmlPullParser parser)
    throws XmlPullParserException, IOException
    {
        List<SongEntry> entries = new ArrayList<SongEntry>();
        parser.require(XmlPullParser.START_TAG, null, "feed");
        while (parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            if(name.equals("entry")){
                entries.add(readSong(parser));
            } else {
                skip(parser);
            }
        }

        return entries;
    }

    public static SongEntry readSong(XmlPullParser parser)
    throws XmlPullParserException, IOException
    {
        // on definit <entry> comme etant un START_TAG
        parser.require(XmlPullParser.START_TAG, null, "entry");
        String idSong = null;
        String titleSong = null;
        String nameSong = null;
        String priceSong = null;
        String imageLink = null;
        String dateReleased = null;

        while (parser.next() != XmlPullParser.END_TAG){
            if(parser.getEventType() != XmlPullParser.START_TAG){
                continue;
            }
            String name = parser.getName();
            System.out.println(" n : " + name);
            if(name.equals("title")){
                titleSong = lireTag(parser, "title");
            } else if(name.equals("id")){
                idSong = lireTag(parser, "id");
            } else if(name.equals("name")){
                nameSong = lireTag(parser, "name");
            } else if(name.equals("price")){
                priceSong = lireTag(parser, "price");
            } else if(name.equals("releaseDate")){
                dateReleased = lireTag(parser, "releaseDate");
            } else if(
                    name.equals("image")
                    ) {
                imageLink = lireTag(parser, "image");
            }else {
                skip(parser);
            }

        }
        return new SongEntry(idSong, titleSong, nameSong,priceSong,imageLink,dateReleased);
    }
    public static String readText(XmlPullParser parser)
    throws XmlPullParserException, IOException
    {
        String res = "";
        if(parser.next() == XmlPullParser.TEXT){
            res = parser.getText();
            parser.nextTag();
        }
        return res;
    }
    public static String lireTag(XmlPullParser parser, String tag_name)
    throws  IOException, XmlPullParserException
    {

        parser.require(XmlPullParser.START_TAG, null, tag_name);
        String value = readText(parser);
        parser.require(XmlPullParser.END_TAG, null, tag_name);

        return value;
    }
    public static void skip(XmlPullParser parser)
    throws IOException, XmlPullParserException
    {
        if (parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }

    }

    public String getIdSong() {
        return idSong;
    }

    public String getTitleSong() {
        return titleSong;
    }

    public String getNameSong() {
        return nameSong;
    }

    public String getPriceSong() {
        return priceSong;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDateReleased() {
        return dateReleased;
    }

    @Override
    public String toString() {
        return  idSong;
//                "idSong = '" + idSong + '\'' + "\n" +
//                "titleSong = '" + titleSong + '\'' + "\n" +
//                "nameSong = '" + nameSong + '\'' + "\n" +
//                "priceSong = '" + priceSong + '\'' + "\n" +
//                "imageLink = '" + imageLink + '\'' + "\n" +
//                "dateReleased = '" + dateReleased + '\'' + "\n";
    }
}
