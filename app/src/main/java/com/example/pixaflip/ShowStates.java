package com.example.pixaflip;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pixaflip.user.UserDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ShowStates extends AppCompatActivity implements StateAdapter.OnItemClickListener{


    public static final String EXTRA_LOCATION = "Location";
    public static final String EXTRA_CONFIRMEDI = "ConfirmedCasesI";
    public static final String EXTRA_CONFIRMEDF = "ConfirmedCasesF";
    public static final String EXTRA_DISCHARGED = "Discharged";
    public static final String EXTRA_DEATHS = "Deaths";
    public static final String EXTRA_TOTAL = "Total";
    // creating variables for
    // our ui components.
    private RecyclerView courseRV;

    // variable for our adapter
    // class and array list
    private StateAdapter adapter;
    private ArrayList<MyState> courseModalArrayList;

    // below line is the variable for url from
    // where we will be querying our data.
    String url = "https://api.rootnet.in/covid19-in/stats/latest";
    private ProgressBar progressBar;

    String status;

    private String getDateTime()
    {
        SimpleDateFormat sd=new SimpleDateFormat ("yyyy-MM-ddcHH:mm:ss", Locale.getDefault ());
        Date d=new Date();
        return sd.format(d);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_states);

        // initializing our variables.
        courseRV = findViewById(R.id.idRVCourses);
        progressBar = findViewById(R.id.idPB);

        // below line we are creating a new array list
        courseModalArrayList = new ArrayList<>();
        getData();

        // calling method to
        // build recycler view.
        buildRecyclerView();
    }

    private void getData() {
        // creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(ShowStates.this);
        // in this case the data we are getting is in the form
        // of array so we are making a json array request.
        // below is the line where we are making an json array
        // request and then extracting data from each json object.

        JsonObjectRequest jsonArrayRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        courseRV.setVisibility(View.VISIBLE);
                        try {
                            JSONObject obj=response.getJSONObject("data");
                            JSONArray dataObject = obj.getJSONArray("regional");


                            for (int i = 0; i < dataObject.length(); i++) {
                                // creating a new json object and
                                // getting each object from our json array.

                                // we are getting each json object.
                                JSONObject responseObj = dataObject.getJSONObject(i);

                                // now we get our response from API in json object format.
                                // in below line we are extracting a string with
                                // its key value from our json object.
                                // similarly we are extracting all the strings from our json object.

                                MyState hero = new MyState(responseObj.getString("loc"),responseObj.getString("confirmedCasesIndian"),
                                        responseObj.getString("confirmedCasesForeign"),
                                        responseObj.getString("discharged"),
                                        responseObj.getString("deaths"),
                                        responseObj.getString("totalConfirmed"));

                                courseModalArrayList.add(hero);


                                buildRecyclerView();

                                adapter.setOnItemClickListener(ShowStates.this);

                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ShowStates.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {

        // initializing our adapter class.
        adapter = new StateAdapter(courseModalArrayList, ShowStates.this);

        // adding layout manager
        // to our recycler view.
        LinearLayoutManager manager = new LinearLayoutManager(this);
        courseRV.setHasFixedSize(true);

        // setting layout manager
        // to our recycler view.
        courseRV.setLayoutManager(manager);

        // setting adapter to
        // our recycler view.
        courseRV.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        UserDatabase db = new UserDatabase(ShowStates.this);

        // String name =  pos.getLoc;MyState modal = courseModalArrayList.get(position);
        //        holder.Location.setText(modal.getLoc());
        String name = courseModalArrayList.get(position).getLoc ();

        db.Adduser( "ShowStates",name,getDateTime() );

        Intent detailIntent = new Intent(this, DetailActivity.class);
        MyState clickedItem = courseModalArrayList.get(position);
        detailIntent.putExtra(EXTRA_LOCATION, clickedItem.getLoc());
        detailIntent.putExtra(EXTRA_CONFIRMEDI, clickedItem.getConfirmedCasesIndian());
        detailIntent.putExtra(EXTRA_CONFIRMEDF, clickedItem.getConfirmedCasesForeign());
        detailIntent.putExtra(EXTRA_DISCHARGED, clickedItem.getDischarged());
        detailIntent.putExtra(EXTRA_DEATHS, clickedItem.getDeaths());
        detailIntent.putExtra(EXTRA_TOTAL, clickedItem.getTotalConfirmed());
        startActivity(detailIntent);
    }
}
