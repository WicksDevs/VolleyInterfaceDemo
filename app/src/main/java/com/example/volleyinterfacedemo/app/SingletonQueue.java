package com.example.volleyinterfacedemo.app;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/*
|-------------------------------------------------------------
|   Clase Singleton  para la cola de peticiones de volley
|-------------------------------------------------------------
*/
public class SingletonQueue {
    private static SingletonQueue mInstance = null;
    private RequestQueue mRequestQueue;

    public SingletonQueue(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static SingletonQueue getInstance(Context context) {
        return mInstance = (mInstance == null) ? new SingletonQueue(context) : mInstance;
    }

    public RequestQueue getmRequestQueue() {
        return this.mRequestQueue;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
