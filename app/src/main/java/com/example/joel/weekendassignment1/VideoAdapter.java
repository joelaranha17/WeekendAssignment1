package com.example.joel.weekendassignment1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.joel.weekendassignment1.model.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Joel on 1/22/2018.
 */

class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> implements View.OnClickListener {

    private final ArrayList<Video> video;
    private final Context context;
    private final LayoutInflater layoutInflater;

    public VideoAdapter(Context context, ArrayList<Video> videoArrayList) {
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.video = videoArrayList;
    }

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, String data);

    }

    private VideoAdapter.OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public void setOnItemClickListener(VideoAdapter.OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(view, String.valueOf(view.getTag()));
        } else {
            Log.e("CLICK", "ERROR");
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

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
            linearLayout = (LinearLayout) itemView.findViewById(R.id.llinearLayout);
        }
    }

    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_layout, null);
        view.setOnClickListener(this);
        return new VideoAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final VideoAdapter.ViewHolder holder, final int position) {
        holder._ID.setText(video.get(position).getVideoId());
        holder.name.setText(video.get(position).getVideoName());
        holder.description.setText(video.get(position).getVideoDesc());
        holder.itemView.setTag(video.get(position).getVideoFile());
        Picasso.with(context)
                .load(video.get(position).getVideoThumb())
                .into(holder.image);
    }
    @Override
    public int getItemCount() {
        return video.size();
    }

}
