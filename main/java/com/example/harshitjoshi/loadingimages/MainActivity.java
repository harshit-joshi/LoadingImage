package com.example.harshitjoshi.loadingimages;

import android.app.DownloadManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoadAdapter.OnItemClickListener{
    public static final String EXTRA_URL="imageUrl";
    public static final String EXTRA_CREATOR="creatorName";
    public static final String EXTRA_LIKES="likeCount";

    private RecyclerView mRecyclerView;
    private LoadAdapter mLoadAdapter;
    private ArrayList<LoadModel> mLoadModel;
    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView=findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mLoadModel=new ArrayList<>();
        mRequestQueue= Volley.newRequestQueue(this);
        parseJSON();
    }

    private void parseJSON() {
        String url="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=happy%20animals&image_type=photo&pretty=true";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {
                try {
                    JSONArray jsonArray=response.getJSONArray("hits");
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject hit= jsonArray.getJSONObject(i);
                        String creatorName=hit.getString("user");
                        String imageUrl=hit.getString("webformatURL");
                        int likes=hit.getInt("likes");
                        mLoadModel.add(new LoadModel(imageUrl,creatorName,likes));
                    }
                    mLoadAdapter=new LoadAdapter(MainActivity.this,mLoadModel);
                    mRecyclerView.setAdapter(mLoadAdapter);
                    mLoadAdapter.setOnItemClickListener(MainActivity.this);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position)
    {
        Intent detailIntent=new Intent(this,DetailActivity.class);
        LoadModel clickedItem =mLoadModel.get(position);
        detailIntent.putExtra(EXTRA_URL,clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR,clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_LIKES,clickedItem.getmLikes());
        startActivity(detailIntent);
    }
}
