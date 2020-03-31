package com.tugasakhir.skripsi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class RegisterActivity extends AppCompatActivity {

    private Button btn_Regist;
    private EditText emailRegist, userRegist, passRegist, phoneRegist;
    private static String URL_Regist = "";
    private ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        loading = findViewById(R.id.loading);
        btn_Regist = findViewById(R.id.btn_register);
        emailRegist = findViewById(R.id.email_regist);
        userRegist = findViewById(R.id.user_regist);
        passRegist = findViewById(R.id.password_regist);
        phoneRegist = findViewById(R.id.phone_regist);

        btn_Regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();

            }
        });
    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_Regist.setVisibility(View.GONE);
        final String email = this.emailRegist.getText().toString().trim();
            final String user = this.userRegist.getText().toString().trim();
            final String pass = this.passRegist.getText().toString().trim();
            final String phone = this.phoneRegist.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Regist,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            if (success.equals("1")) {
                                Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(RegisterActivity.this, "Registrasi Gagal" + e.toString(), Toast.LENGTH_SHORT).show();
                            loading.setVisibility(View.GONE);
                            btn_Regist.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Registrasi Gagal" + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_Regist.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("user", user);
                params.put("password", pass);
                params.put("phone", phone);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

//
//        private void Registrasi () {
//            btnRegist.setVisibility(View.GONE);
//
//
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Regist,
//                    new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                JSONObject jsonObject = new JSONObject(response);
//                                String succes = jsonObject.getString("success");
//
//                                if (succes.equals("1")) {
//                                    Toast.makeText(RegisterActivity.this, "Register Berhasil", Toast.LENGTH_SHORT).show();
//                                }
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                Toast.makeText(RegisterActivity.this, "Register Gagal!!!", Toast.LENGTH_SHORT).show();
//                                btnRegist.setVisibility(View.VISIBLE);
//                            }
//                        }
//                    },
//                    new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(RegisterActivity.this, "Register Gagal!!!", Toast.LENGTH_SHORT).show();
//                            btnRegist.setVisibility(View.VISIBLE);
//                        }
//                    }) {
//                @Override
//                protected Map<String, String> getParams() throws AuthFailureError {
//                    Map<String, String> params = new HashMap<>();
//                    params.put("email", email);
//                    params.put("user", user);
//                    params.put("password", pass);
//                    params.put("phone", phone);
//                    return params;
//                }
//            };
//            RequestQueue requestQueue = Volley.newRequestQueue(this);
//            requestQueue.add(stringRequest);
//        }
//    }