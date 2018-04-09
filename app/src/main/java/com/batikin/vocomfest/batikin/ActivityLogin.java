package com.batikin.vocomfest.batikin;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.batikin.vocomfest.batikin.utils.CDM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import dmax.dialog.SpotsDialog;

public class ActivityLogin extends AppCompatActivity implements View.OnClickListener, CDM {

    private String email;
    private String password;
    private FirebaseAuth mAuth;
    private EditText emailTxt;
    private EditText passwordTxt;
    private AlertDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        dialog = new SpotsDialog(this);
        Button btnToRegister = findViewById(R.id.btnSignUpToRegister);
        Button btnLogin = findViewById(R.id.btnLogin);
        emailTxt = findViewById(R.id.email);
        passwordTxt = findViewById(R.id.password);
        mAuth = FirebaseAuth.getInstance();
        btnToRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSignUpToRegister:
                goToRegister();
                break;
            case R.id.btnLogin:
                loginProcess(emailTxt.getText().toString(), passwordTxt.getText().toString());
                break;

        }
    }

    private void loginProcess(String email, String password) {
        dialog.show();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    dialog.dismiss();
                    Toast.makeText(ActivityLogin.this, "Please check the form maybe your account are not registred or check the connection",
                            Toast.LENGTH_LONG).show();
                } else {
                    dialog.dismiss();
                    Toast.makeText(ActivityLogin.this, "Log In Success",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivityLogin.this, HomePage.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }

    private void goToRegister() {
        startActivity(new Intent(ActivityLogin.this, ActivityRegister.class));

    }
}
