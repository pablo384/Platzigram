package com.platzi.platzigram;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.platzi.platzigram.view.ContainerActivity;
import com.platzi.platzigram.view.CreateAcountActivity;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

//        if (firebaseAuth.getCurrentUser() != null){
//            goHome();
//        }

        final TextInputEditText etemail = (TextInputEditText)findViewById(R.id.username);
        final TextInputEditText etpassword = (TextInputEditText)findViewById(R.id.password);
        final ProgressBar progressBar=(ProgressBar) findViewById(R.id.progressBar_LoginActivity);
        final TextInputLayout ltext1 = (TextInputLayout) findViewById(R.id.layoutEmail);
        final TextInputLayout ltext2 = (TextInputLayout) findViewById(R.id.layoutPassword);

        final Button btnlogin=(Button)findViewById(R.id.login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etemail.getText().toString().trim();
                String password = etpassword.getText().toString().trim();

                etemail.setVisibility(View.INVISIBLE);
                etpassword.setVisibility(View.INVISIBLE);
                btnlogin.setVisibility(View.INVISIBLE);
                ltext1.setVisibility(View.INVISIBLE);
                ltext2.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);

                if (email.equals("")){
                    Toast.makeText(LoginActivity.this, "Proporcione un nombre de Usuario Valido", Toast.LENGTH_SHORT).show();
                    etemail.setVisibility(View.VISIBLE);
                    etpassword.setVisibility(View.VISIBLE);
                    btnlogin.setVisibility(View.VISIBLE);
                    ltext1.setVisibility(View.VISIBLE);
                    ltext2.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    return;
                }
                if (password.equals("")){
                    etemail.setVisibility(View.VISIBLE);
                    etpassword.setVisibility(View.VISIBLE);
                    btnlogin.setVisibility(View.VISIBLE);
                    ltext1.setVisibility(View.VISIBLE);
                    ltext2.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Proporcione una COntrasena Valida", Toast.LENGTH_SHORT).show();
                    return;

                }


                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Login Failed",
                                            Toast.LENGTH_LONG).show();
                                    etemail.setVisibility(View.VISIBLE);
                                    etpassword.setVisibility(View.VISIBLE);
                                    btnlogin.setVisibility(View.VISIBLE);
                                    ltext1.setVisibility(View.VISIBLE);
                                    ltext2.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.INVISIBLE);
                                }else {
                                    etemail.setVisibility(View.VISIBLE);
                                    etpassword.setVisibility(View.VISIBLE);
                                    btnlogin.setVisibility(View.VISIBLE);
                                    ltext1.setVisibility(View.VISIBLE);
                                    ltext2.setVisibility(View.VISIBLE);
                                    progressBar.setVisibility(View.INVISIBLE);
                                    goHome();
                                }
                            }
                        });


            }
        });
    }

    public void goCreateAccount(View view) {
        Intent i=new Intent(LoginActivity.this, CreateAcountActivity.class);
        startActivity(i);
    }

    public void goHome() {
        Intent i=new Intent(LoginActivity.this, ContainerActivity.class);
        startActivity(i);
        finish();
    }

    public void goPlatzigram(View view) {
        Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse("http://platzigram.com/"));
        startActivity(i);
    }
}
