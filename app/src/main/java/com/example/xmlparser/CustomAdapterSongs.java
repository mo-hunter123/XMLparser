package com.example.xmlparser;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class CustomAdapterSongs extends BaseAdapter {
    private List<SongEntry> listData;
    private LayoutInflater layoutInflater;
    private Context context;


    public CustomAdapterSongs(Context context, List<SongEntry> list){
        this.listData = list;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return this.listData.size();
    }

    @Override
    public Object getItem(int position) {
        System.out.println("==Pos==  "+position);
        return this.listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null) {
            convertView = layoutInflater.inflate(R.layout.song_item_layout, null);
            holder = new ViewHolder();
            holder.songAvatar= (ImageView) convertView.findViewById(R.id.imageView2);
            holder.title = (TextView) convertView.findViewById(R.id.textView2);
            holder.name = (TextView) convertView.findViewById(R.id.textView3);
            convertView.setTag(holder);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }
        SongEntry song = this.listData.get(position);

        try{
            holder.title.setText(song.getTitleSong());
            holder.name.setText(song.getNameSong());

        } catch (Exception e){
            System.out.println("==Erroro== " + e);
        }
        try{
            Picasso.with(context.getApplicationContext()).
                    load(song.getImageLink())
                    .into(holder.songAvatar);

        } catch (Exception e){
            e.printStackTrace();
        }
        return convertView;
    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    static class ViewHolder{
        ImageView songAvatar;
        TextView title;
        TextView name;
    }
}
