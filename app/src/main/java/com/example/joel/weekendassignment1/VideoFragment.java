package com.example.joel.weekendassignment1;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.joel.weekendassignment1.model.Music;
import com.example.joel.weekendassignment1.model.Video;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Joel on 1/22/2018.
 */

public class VideoFragment extends Fragment {

    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private RecyclerView.Adapter adapter;
    private ArrayList<Video> videoArrayList;
    private Video video;
    String url = "http://rjtmobile.com/ansari/rjt_music/music_app/video_list.php";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.video_fragment, container, false);
        recyclerView = view.findViewById(R.id.video_listView);
        videoArrayList = new ArrayList<>();

        callnetwork();
        return view;
    }

    private void callnetwork() {
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("MYTEST", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray albums = jsonObject.getJSONArray("Albums");
                    for (int i = 0; i < albums.length(); i++) {
                        JSONObject item = albums.getJSONObject(i);
                        Video ls = new Video(
                                item.getString("VideoId"),
                                item.getString("VideoName"),
                                item.getString("VideoDesc"),
                                item.getString("VideoThumb"),
                                item.getString("VideoFile"));
                        Log.d("HAHA", "HAHA" + ls);
                        videoArrayList.add(ls);
                    }

                    adapter = new VideoAdapter(getContext(), videoArrayList);
                    recyclerView.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(sr);
    }
}