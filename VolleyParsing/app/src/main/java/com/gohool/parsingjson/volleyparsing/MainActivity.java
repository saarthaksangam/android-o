package com.gohool.parsingjson.volleyparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private final static String URL_String = "http://magadistudio.com/complete-android-developer-course-source-files/string.html";
    private final static String URL = "http://netflixroulette.net/api/api.php?director=Quentin%20Tarantino";
    private final static  String URL_EQ = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-01-02";

    private final static String URL_M = "http://www.omdbapi.com/?s=samurai&page=2";
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         queue = Volley.newRequestQueue(this);

       // getStringObject(URL_String);

        //getJsonObject(URL_EQ);
        getMovies(URL_M);

        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET,
                URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject movieObject = response.getJSONObject(i);
                        Log.d("Items: ", movieObject.getString("show_title") + "/"
                        + "Released: " + movieObject.getString("release_year"));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }


                //Log.d("Response: ", response.toString());



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                VolleyLog.d("Error", error.getMessage() );

            }
        });
        queue.add(arrayRequest);
    }



    //Get Movies
    private void getMovies(String url) {
        JsonObjectRequest movies = new JsonObjectRequest(Request.Method.GET,
                url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray moviesArray = response.getJSONArray("Search");

                    Log.d("Movies Num: ", String.valueOf(moviesArray.length()));

                    for (int i = 0; i< moviesArray.length(); i++) {
                        JSONObject movieObj = moviesArray.getJSONObject(i);

                        Log.d("Titles: ", movieObj.getString("Title") + "\n" +
                         "Poster: " + movieObj.getString("Poster"));



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(movies);
    }
    private void getJsonObject(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {


                   // Log.d("Object: ", response.getString("type").toString());

                    JSONObject metadata = response.getJSONObject("metadata");

                    Log.d("Metadata: ", metadata.toString());
                    Log.d("MetaInfo: ", metadata.getString("generated"));
                    Log.d("MetaInfo: ", metadata.getString("url"));
                    Log.d("MetaInfo: ", metadata.getString("title"));


                    /// jsonArray
                    JSONArray features = response.getJSONArray("features");

                    for (int i = 0; i < features.length(); i++) {

                        //Get objects
                        JSONObject propertiesObj = features.getJSONObject(i).getJSONObject("properties");

                        Log.d("Place:", propertiesObj.getString("place"));


                    }






                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(jsonObjectRequest);


    }


    public void getStringObject(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("My String: ", response.toString());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);






    }


}
