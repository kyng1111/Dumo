package com.example.myapplication;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {

    private ArrayList<PersonalData> mList = null;
    private Activity context = null;


    public UsersAdapter(Activity context, ArrayList<PersonalData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView id;
        protected TextView name;
        protected TextView lat;
        protected TextView lon;
        protected TextView isok;
        protected TextView num;


        public CustomViewHolder(View view) {
            super(view);
            this.name = (TextView) view.findViewById(R.id.textView_list_name);
            this.lat = (TextView) view.findViewById(R.id.textView_list_lat);
            this.lon = (TextView) view.findViewById(R.id.textView_list_lon);
            this.isok = (TextView) view.findViewById(R.id.textView_list_isok);
            this.num = (TextView) view.findViewById(R.id.textView_list_num);
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_test, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.name.setText(mList.get(position).getMember_name());
        viewholder.lat .setText(mList.get(position).getMember_lat());
        viewholder.lon .setText(mList.get(position).getMember_lon());
        viewholder.isok.setText(mList.get(position).getMember_isok());
        viewholder.num .setText(mList.get(position).getMember_num());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}

