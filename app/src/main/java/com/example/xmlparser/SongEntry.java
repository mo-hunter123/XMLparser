package com.example.xmlparser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

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
            if(name.equals("title")){
                titleSong = lireTag(parser, "title");
            } else if(name.equals("id")){
                idSong = lireTag(parser, "id");
            } else if(name.equals("name")){
                nameSong = lireTag(parser, "name");
            } else if(name.equals("price")){
                priceSong = lireTag(parser, "price");
            } else if(name.equals("date")){
                dateReleased = lireTag(parser, "date");
            } else if(name.equals("imagelink")) {
                imageLink = lireTag(parser, "imagelink");
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

    }

    @Override
    public String toString() {
        return
                "idSong = '" + idSong + '\'' + "\n" +
                "titleSong = '" + titleSong + '\'' + "\n" +
                "nameSong = '" + nameSong + '\'' + "\n" +
                "priceSong = '" + priceSong + '\'' + "\n" +
                "imageLink = '" + imageLink + '\'' + "\n" +
                "dateReleased = '" + dateReleased + '\'' + "\n";
    }
}
