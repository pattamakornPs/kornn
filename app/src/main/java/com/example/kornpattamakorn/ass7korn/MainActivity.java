package com.example.kornpattamakorn.ass7korn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText stid;
    private EditText stname;
    private EditText sttel;
    private EditText stemail;
    private Button save;
    private RecyclerView recyclerView;
    List<showRecycle> Listshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stid = findViewById(R.id.editText);
        stname = findViewById(R.id.editText2);
        sttel = findViewById(R.id.editText3);
        stemail = findViewById(R.id.editText4);
        save = findViewById(R.id.button);
        recyclerView = findViewById(R.id.show);
        Listshow = new ArrayList<>();
        LinearLayoutManager li = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(li);
        recyclerView.setHasFixedSize(true);
        showstudent();
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addstudent();
                showstudent();
            }
        });
    }
    public void showstudent(){
        String url = "http://10.51.24.125/ass7mobile/select.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Listshow.clear();
                try {
                    JSONArray array = new JSONArray(response);
                    for(int i = 0;i<array.length();i++){
                        JSONObject kn = array.getJSONObject(i);
                        Listshow.add(new showRecycle(
                                kn.getString("Id_Student"),
                                kn.getString("Name"),
                                kn.getString("Tel"),
                                kn.getString("Email")
                        ));
                        showAdapter kk = new showAdapter(getApplicationContext(),Listshow);
                        recyclerView.setAdapter(kk);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("","");
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void addstudent(){
        String url = "http://10.51.24.125/ass7mobile/insert.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("Success")) {
                    Listshow.clear();
                    Toast.makeText(getApplicationContext(),"Seccess!!!!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"Can not Register",Toast.LENGTH_SHORT).show();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("id",stid.getText().toString());
                param.put("name",stname.getText().toString());
                param.put("tel",sttel.getText().toString());
                param.put("mail",stemail.getText().toString());
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
