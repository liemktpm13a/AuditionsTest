package com.example.auditionstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button btnLogin;
    private EditText inputEmail, inputPw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin);
        inputEmail = findViewById(R.id.inputEmail);
        inputPw = findViewById(R.id.inputPw);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String email = inputEmail.getText().toString();
           final String pass = inputPw.getText().toString();
           if(TextUtils.isEmpty(email)){
               Toast.makeText(getApplicationContext(),"Enter email",Toast.LENGTH_SHORT).show();
               return;
           }
           if(TextUtils.isEmpty(pass)){
               Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
               return;
           }
           auth.signInWithEmailAndPassword(email,pass)
                   .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                if (pass.length() < 6) {
                                    inputPw.setError("Phải lớn hơn 6 ký tự");
                                } else {
                                    Toast.makeText(MainActivity.this, "Sai email", Toast.LENGTH_LONG).show();
                                }
                            }
                            else{
                                Intent intent = new Intent(MainActivity.this,ListStudent.class);
                                startActivity(intent);
                                finish();
                            }
                       }
                   });
            }
        });

    }
}