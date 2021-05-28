package com.example.auditionstest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListStudent extends AppCompatActivity {

    List<Student> students = new ArrayList<>();
    RecyclerView rcvStudent;
    StudentAdapter adapter;
    EditText edt_name,edt_age,edt_id;
    Button btn_post,btn_delete,btn_put;
    String url = "https://60adbf6c80a61f0017331989.mockapi.io/students";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
        rcvStudent = findViewById(R.id.rcvListStd);

        adapter = new StudentAdapter(this, (ArrayList<Student>) students);
        rcvStudent.setAdapter(adapter);
        rcvStudent.setLayoutManager(new GridLayoutManager(this,1));
        getJsonObjectArray(url,students);
    }
    private void getJsonObjectArray(String url, List<Student> students) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse(JSONArray response) {
                        for(int i =0 ; i<response.length();i++){
                            try {
                                JSONObject jsonObject = (JSONObject) response.get(i);
                                Student student = new Student();
                                student.setId(jsonObject.getString("id").toString());
                                student.setAge(jsonObject.getInt("age"));
                                student.setName(jsonObject.getString("name"));
                                students.add(student);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListStudent.this,
                                "Error by get Json Array!", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
    private void getData(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url,new Response.Listener<String>(){

            @Override
            public void onResponse(String response) {
                Toast.makeText(ListStudent.this,
                        response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ListStudent.this,
                        "Error make by API server!", Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    private void PostAPI(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ListStudent.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListStudent.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("name",edt_name.getText().toString());
                params.put("age",edt_age.getText().toString());

                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void PutAPI(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url + "/" + edt_id.getText().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ListStudent.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListStudent.this, "Error by Put data!", Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("name",edt_name.getText().toString());
                params.put("age",edt_age.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void Delete(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url +"/" + edt_id.getText().toString(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ListStudent.this, "Successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ListStudent.this, "Error by Post data!", Toast.LENGTH_SHORT).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}