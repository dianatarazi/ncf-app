package com.example.dianatarazi.professorfinder;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.dianatarazi.professorfinder.adapter.ListAdapter;
import com.example.dianatarazi.professorfinder.app.AppController;
import com.example.dianatarazi.professorfinder.model.Faculty;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;



public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String url = "http://studentguide.000webhostapp.com/getdata.php";
    private ProgressDialog pDialog;
    private List<Faculty> facultyList = new ArrayList<Faculty>();
    private ListView listView;
    private ListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);
        adapter = new ListAdapter(this, facultyList);
        listView.setAdapter(adapter);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        //changing action bar color
        //getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1b1b1b")));

        //creating volley request object
        JsonObjectRequest facultyReq = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();



                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            //try {

                                //JSONObject obj = response.getJSONObject(String.valueOf(i));

                            Faculty faculty = new Faculty();
                            faculty.setName(response.optString("members_full_name"));
                            faculty.setThumbnailUrl(response.optString("members_user_image"));
                            faculty.setfacultyPosition(response.optString("members_position"));
                            faculty.setEmail(response.optString("members_email_address"));
                            faculty.setMailLocation(response.optString("members_mail_location"));
                            faculty.setOfficeLocation(response.optString("members_office_location"));


                            // adding faculty to faculty array
                            facultyList.add(faculty);

                            /*} catch (JSONException e) {
                                e.printStackTrace();
                            }*/

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(facultyReq);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        hidePDialog();
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

}
