package com.platzi.platzigram.view;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.platzi.platzigram.LoginActivity;
import com.platzi.platzigram.R;

public class CreateAcountActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_acount);
        showToolbar(getResources().getString(R.string.toolbar_title_createaccount), true);

        firebaseAuth = FirebaseAuth.getInstance();


        final TextInputEditText etEmail = (TextInputEditText) findViewById(R.id.email_createAccount);
        final TextInputEditText etName = (TextInputEditText) findViewById(R.id.name_createAccount);

        final TextInputEditText etUsername = (TextInputEditText) findViewById(R.id.user_createAccount);

        final TextInputEditText etPassword = (TextInputEditText) findViewById(R.id.password_createAccount);
        final TextInputEditText etPasswordConf = (TextInputEditText) findViewById(R.id.confirmPassword_createAccount);

        Button btncreateAcount=(Button)findViewById(R.id.joinUs);
        btncreateAcount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = etEmail.getText().toString().trim();
                String user = etUsername.getText().toString().trim();
                String user_name = etName.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmation = etPasswordConf.getText().toString().trim();

                if (email.equals("")){
                    Toast.makeText(CreateAcountActivity.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(confirmation)){

                    if (password.equals("")){
                        Toast.makeText(CreateAcountActivity.this, "Paswword Invalid", Toast.LENGTH_SHORT).show();
                    }
                    return;
                }

                firebaseAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(CreateAcountActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    Toast.makeText(CreateAcountActivity.this, "Auth Failed", Toast.LENGTH_SHORT).show();
                                }else {
                                    startActivity(new Intent(CreateAcountActivity.this, LoginActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });

    }

    public void showToolbar(String tittle, boolean upButton) {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }
    private void declarateInterface(){

    }
}
