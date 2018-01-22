package com.example.joel.weekendassignment1;

/**
 * Created by Joel on 1/22/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.joel.weekendassignment1.model.Music;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by joel on 11/26/2017.
 */

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> implements View.OnClickListener{

    private final ArrayList<Music> music;
    private final Context context;
    private final LayoutInflater layoutInflater;
    Long Position;


    public MusicAdapter(Context context, ArrayList<Music> musicArrayList){
        layoutInflater = LayoutInflater.from(context);
        this.context=context;
        this.music = musicArrayList;
    }

    //define interface
    public interface OnRecyclerViewItemClickListener
    {
        void onItemClick(View view, String data);

    }

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view,String.valueOf(view.getTag()));
        }
        else{
            Log.e("CLICK", "ERROR");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        final TextView _ID;
        final TextView name;
        final TextView description;
        final ImageView image;
        final TextView link;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            this._ID = itemView.findViewById(R.id.category_id);
            this.name = itemView.findViewById(R.id.category_name);
            this.image = itemView.findViewById(R.id.category_image);
            this.description = itemView.findViewById(R.id.category_discription);
            this.link = itemView.findViewById(R.id.link);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.llinearLayout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_layout,null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder._ID.setText(music.get(position).getAlbumId());
        holder.name.setText(music.get(position).getAlbumName());
        holder.description.setText(music.get(position).getAlbumDesc());
        holder.itemView.setTag(music.get(position).getMusicFile());
        Picasso.with(context)
                .load(music.get(position).getAlbumThumb())
                .into(holder.image);

    }
    @Override
    public int getItemCount() {
        return music.size();
    }

}
