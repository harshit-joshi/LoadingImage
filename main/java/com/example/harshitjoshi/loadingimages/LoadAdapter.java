package com.example.harshitjoshi.loadingimages;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LoadAdapter extends RecyclerView.Adapter<LoadAdapter.LoadViewHolder> {
    private Context context;
    private ArrayList<LoadModel> mLoadList;
    private OnItemClickListener mClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mClickListener = listener;
    }

    public LoadAdapter(Context context, ArrayList<LoadModel> mLoadList) {
        this.context = context;
        this.mLoadList = mLoadList;
    }

    @NonNull
    @Override
    public LoadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sinlgle_item, parent, false);
        return new LoadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoadViewHolder holder, int position) {
        LoadModel currentItem = mLoadList.get(position);
        String imageUrl = currentItem.getmImageUrl();
        String creator = currentItem.getmCreator();
        int likes = currentItem.getmLikes();
        holder.mTextViewCreator.setText(creator);
        holder.mTextViewLikes.setText("Likes :" + likes);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);


    }

    @Override
    public int getItemCount() {
        return mLoadList.size();
    }

    public class LoadViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewLikes;
        public TextView mTextViewCreator;

        public LoadViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image_view);
            mTextViewLikes = itemView.findViewById(R.id.likes);
            mTextViewCreator = itemView.findViewById(R.id.text_view_creator);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            mClickListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
