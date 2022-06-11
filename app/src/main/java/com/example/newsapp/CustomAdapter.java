package com.example.newsapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<news> news_list = new ArrayList<>();
    Context mContext;


    public CustomAdapter(ArrayList<news> news_list, Context context) {

        this.news_list = news_list;
        this.mContext = context;

    }

    @Override
    public int getCount() {
        return news_list.size();
    }

    @Override
    public Object getItem(int i) {
        return news_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    public void ShareMe(String url){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        String value = url ;
        intent.putExtra(Intent.EXTRA_TEXT, value);
        mContext.startActivity(Intent.createChooser(intent,"Share Via"));
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        if(convertView ==null)
        {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.activity_main,parent,false);
        }
        news temp_news = (news) getItem(i);
        TextView nhead = (TextView) convertView.findViewById(R.id.Heading);
        TextView ndesc = (TextView) convertView.findViewById(R.id.Desc);
        TextView ntime = (TextView) convertView.findViewById(R.id.TimeNews);
        TextView nlink = (TextView) convertView.findViewById(R.id.LinkNews);


        //image
        ImageView nimg = (ImageView) convertView.findViewById(R.id.Nimg);


        Button btnShare = (Button) convertView.findViewById(R.id.share);
        btnShare.setVisibility(View.VISIBLE);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShareMe(temp_news.get_news_link());
            }
        });


        nhead.setText(""+temp_news.get_news_heading());
        ndesc.setText(""+temp_news.get_news_desc());
        ntime.setText(""+temp_news.get_news_time());
        nlink.setText(""+temp_news.get_news_link());

        Glide.with(mContext).load(temp_news.get_news_url()).override(600,400).into(nimg);
        return convertView;
    }
}