package com.example.volleyinterfacedemo.app;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;

public class VolleyListener {

    // For debug
    private static final String TAG = VolleyListener.class.getName();

    // Listener
    private VolleyInterfaceListener listener;

    public VolleyListener(Activity activity) {
        this.listener = (VolleyInterfaceListener) activity;
    }

    // FragmentActivity support library
    public VolleyListener(FragmentActivity fragmentActivity) {
        this.listener = (VolleyInterfaceListener) fragmentActivity;
    }

    // FragmentActivity support library
    public VolleyListener(Fragment fragment) {
        this.listener = (VolleyInterfaceListener) fragment;
    }

    /*
    |-----------------------------------------------------------------------------------------------
    |   JsonObjectRequest()
    |-----------------------------------------------------------------------------------------------
    | Params:
    | url = url of the web sevrices
    | context = Context of activity or fragment..... etc....
    | method = GET,POST.....
    | Description:
    | This method executes a request type JsonObject
    |
    */
    public void JsonObjectRequest(final String url,Context context,int method) {

        // Listener for success response
        Response.Listener<JSONObject> successListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                listener.JsonObjectRequestFinish(url,jsonObject);
            }
        };

        //Listener for error response
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(TAG,volleyError.toString());
                listener.JsonObjectRequestError(url,volleyError);
            }
        };

        // The request
        JsonObjectRequest jr = new JsonObjectRequest(method,url,null,successListener,errorListener);

        // Add request to VolleyQueue
        SingletonQueue.getInstance(context).getmRequestQueue().add(jr);
    }

    /*
    |-----------------------------------------------------------------------------------------------
    |   StringRequest()
    |-----------------------------------------------------------------------------------------------
    | Params:
    | url = url of the web sevrices
    | context = Context of activity or fragment..... etc....
    | method = GET,POST.....
    | params = Map Collection of pair (key,val)
    | Description:
    | This method executes a request type String
    |
    */
    public void StringRequest(final String url,final Context context,int method,final Map<String,String> params) {

        // Listener for success response
        Response.Listener<String> successListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                listener.StringRequestFinish(url,s);
            }
        };

        //Listener for error response
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                listener.StringRequestError(url,volleyError);
            }
        };

        // The request
        StringRequest sr = new StringRequest(method,url,successListener,errorListener) {
            @Override
            protected Map<String, String> getParams(){
                return params;
            }
        };

        // Add request to VolleyQueue
        SingletonQueue.getInstance(context).getmRequestQueue().add(sr);
    }

   /*
    |-----------------------------------------------------------------------------------------------
    |                                       VolleyInterfaceListener
    |-----------------------------------------------------------------------------------------------
    */

    public interface VolleyInterfaceListener {
        // For success..
        public void JsonObjectRequestFinish(String url, JSONObject response);
        public void StringRequestFinish(String url, String response);
        // For errors..
        public void JsonObjectRequestError(String url, VolleyError error);
        public void StringRequestError(String url, VolleyError error);

    }
}
