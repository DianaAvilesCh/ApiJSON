package com.example.apijson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class volley extends AppCompatActivity {

    TextView textUser;
    RequestQueue requestQueue;
    String URL = "https://gorest.co.in/public/v1/users";
    ArrayList<String> lstUser = new ArrayList<String> ();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volley);

        requestQueue = Volley.newRequestQueue(this);

        stringRequest();
    }

    public void stringRequest(){
        TextView txtUser = (TextView)findViewById(R.id.txtListUser2);
        StringRequest request = new StringRequest(Request.Method.GET,
                URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject object = new JSONObject(response);
                            JSONArray JSONlista = object.getJSONArray("data");
                            for(int i=0; i< JSONlista.length();i++){
                                JSONObject user=  JSONlista.getJSONObject(i);
                                lstUser.add(user.getString("name")+ ", "
                                        + user.getString("email")+", "
                                        + user.getString("gender")+", "
                                        + user.getString("status")+";\n\n");
                            }
                            txtUser.setKeyListener(null);
                            txtUser.setText(lstUser.toString());
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(request);
    }
}