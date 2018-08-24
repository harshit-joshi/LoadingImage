package com.example.harshitjoshi.loadingimages;

public class LoadModel
{
    private String mImageUrl;
    private String mCreator;
    private int mLikes;

    public LoadModel(String mImageUrl, String mCreator, int mLikes)
     {
        this.mImageUrl = mImageUrl;
        this.mCreator = mCreator;
        this.mLikes = mLikes;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getmCreator() {
        return mCreator;
    }

    public int getmLikes() {
        return mLikes;
    }
}
