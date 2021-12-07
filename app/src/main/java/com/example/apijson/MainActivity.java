package com.example.apijson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.apijson.WebService.Asynchtask;
import com.example.apijson.WebService.WebService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://gorest.co.in/public/v1/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtUser = (TextView)findViewById(R.id.txtListUser2);

        ArrayList<String> lstUser = new ArrayList<String> ();
        JSONObject object = new JSONObject(result);
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
    }

    public void btVolley(View view){
        Intent intent = new Intent(MainActivity.this,volley.class);
        startActivity(intent);
    }
}