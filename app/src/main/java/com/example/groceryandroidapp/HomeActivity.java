package com.example.groceryandroidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        firebaseAuth =  FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
        if (firebaseAuth.getCurrentUser() != null){
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Please wait you are already logged in !",  Toast.LENGTH_SHORT).show();
        }

    }

    public void login(View view) {
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
    }

    public void register(View view) {
        startActivity(new Intent(HomeActivity.this, RegisterActivity.class));

    }
}