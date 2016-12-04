package com.platzi.platzigram;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.platzi.platzigram.view.ContainerActivity;
import com.platzi.platzigram.view.CreateAcountActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goCreateAccount(View view) {
        Intent i=new Intent(LoginActivity.this, CreateAcountActivity.class);
        startActivity(i);
    }

    public void login(View view) {
        Intent i=new Intent(LoginActivity.this, ContainerActivity.class);
        startActivity(i);
    }

    public void goPlatzigram(View view) {
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://platzigram.com/"));
        startActivity(i);
    }
}
