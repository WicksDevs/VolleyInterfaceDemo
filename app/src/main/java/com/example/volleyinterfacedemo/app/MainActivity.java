package com.example.volleyinterfacedemo.app;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;

import org.json.JSONObject;


public class MainActivity extends ActionBarActivity  implements VolleyListener.VolleyInterfaceListener {

    private VolleyListener listener;
    private static final String TAG = MainActivity.class.getName();

    private static final String URL = "http://www.tengountrabajo.com/api/provincias";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listener = new VolleyListener(this);
        listener.StringRequest(URL,getApplicationContext(), Request.Method.GET,null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void JsonObjectRequestFinish(String url, JSONObject response) {


    }

    @Override
    public void StringRequestFinish(String url, String response) {
        Log.d(TAG,response);
    }

    @Override
    public void JsonObjectRequestError(String url, VolleyError error) {

    }

    @Override
    public void StringRequestError(String url, VolleyError error) {

    }
}
