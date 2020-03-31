package com.tugasakhir.skripsi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Context context;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<PojoLogUser> list;
    PojoLogUser pojoLogUser;
    UserLogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleViewUserLog);

        linearLayoutManager = new LinearLayoutManager(this);
        context = this;
        list = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        getData();

    }

    private void getData() {

        StringRequest getRequest = new StringRequest(Request.Method.GET, "", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean status = jsonObject.getBoolean("status");

                    if(status)
                    {
                        JSONArray jsonArray = jsonObject.getJSONArray("datalog");
                        for (int i=0; i<jsonArray.length(); i++)
                        {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            String date = obj.getString("date");
                            String user = obj.getString("user");
                            String information = obj.getString("information");

                            pojoLogUser = new PojoLogUser(date, user, information);
                        }
                    }
                }catch (Exception e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        Volley.newRequestQueue(this).add(getRequest);
    }
}
