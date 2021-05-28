package com.example.auditionstest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class ListStudent extends AppCompatActivity {

    List<Student> users = new ArrayList<>();
    RecyclerView rcv_user;
    StudentAdapter adapter;
    EditText edt_name,edt_age,edt_id;
    Button btn_post,btn_delete,btn_put;
    String url = "https://60adbf6c80a61f0017331989.mockapi.io/students";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);
    }
}