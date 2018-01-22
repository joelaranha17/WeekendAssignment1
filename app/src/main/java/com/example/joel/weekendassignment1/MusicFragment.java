package com.example.joel.weekendassignment1;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.joel.weekendassignment1.model.Music;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Joel on 1/22/2018.
 */

public class MusicFragment extends Fragment {

    private RecyclerView recyclerView;
    private  MusicAdapter musicAdapter;
    private RecyclerView.Adapter adapter;
    private ArrayList<Music> musicArrayList;
    private Music music;
    String url = "http://rjtmobile.com/ansari/rjt_music/music_app/music_album_category.php?&album_type=new";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.music_fragment, container, false);
        recyclerView = view.findViewById(R.id.music_listView);
        musicArrayList = new ArrayList<>();

        callnetwork();
        return view;
    }
    private void callnetwork() {
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        String tag_json_obj = "json_obj_req";

        StringRequest sr = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.i("MYTEST", response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray albums = jsonObject.getJSONArray("Albums");
                    for (int i = 0; i < albums.length(); i++) {
                        JSONObject item = albums.getJSONObject(i);
                        Music ls = new Music(
                                item.getString("AlbumId"),
                                item.getString("AlbumName"),
                                item.getString("AlbumDesc"),
                                item.getString("AlbumThumb"),
                                item.getString("MusicFile"));
                        Log.d("HAHA","HAHA" +ls);
                        musicArrayList.add(ls);
                    }

                    adapter = new MusicAdapter(getContext(), musicArrayList);
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
