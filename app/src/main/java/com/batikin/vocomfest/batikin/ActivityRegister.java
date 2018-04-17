package com.batikin.vocomfest.batikin;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.batikin.vocomfest.batikin.model.User;
import com.batikin.vocomfest.batikin.utils.CDM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;

import dmax.dialog.SpotsDialog;

public class ActivityRegister extends AppCompatActivity implements CDM, View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private String nama;
    private String pass;
    private String email;
    TextInputLayout txtmail;
    TextInputLayout txtPass;
    TextInputLayout txtNama;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        dialog = new SpotsDialog(this);
        txtmail = findViewById(R.id.email_register);
        txtPass = findViewById(R.id.password_register);
        txtNama = findViewById(R.id.nama_register);
        Button register = findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(this);

    }

    private void signUp(final String email, String password, final String nama) {
        dialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            user = mAuth.getCurrentUser();
                            //updateUI(user);
                            storeToUserDB(nama, email);
                            dialog.dismiss();
                            Toast.makeText(ActivityRegister.this, "user" + nama + "telah terbuat",
                                    Toast.LENGTH_LONG).show();
                            startActivity(new Intent(ActivityRegister.this, ActivityLogin.class));
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            dialog.dismiss();
                            Toast.makeText(ActivityRegister.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }


    public void storeToUserDB(String nama, String email) {
        //getUID
        String uid = user.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        //get
        HashMap<String, User> hashMap = new HashMap<>();
        hashMap.put(uid, new User(nama, email, "default"));

        mDatabase.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    FirebaseAuth.getInstance().signOut();
                    Toast.makeText(ActivityRegister.this, "Registration Success",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ActivityRegister.this, ActivityLogin.class);

                    //addflags untuk menset agar ketika di BACK maka tidak kembali ke halaman awal
                    //tapi langsung keluar apps
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRegister:
                signUp(txtmail.getEditText().getText().toString(),
                        txtPass.getEditText().getText().toString(),
                        txtNama.getEditText().getText().toString());
                break;

        }
    }
}
