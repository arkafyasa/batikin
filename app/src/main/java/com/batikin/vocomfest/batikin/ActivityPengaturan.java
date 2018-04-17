package com.batikin.vocomfest.batikin;

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
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ActivityPengaturan extends AppCompatActivity implements View.OnClickListener, CDM {

    private EditText lama;
    private EditText baru;
    private Button ok;
    private FirebaseUser user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengaturan_layout);

        lama = findViewById(R.id.txt_pengaturan_pass_lama);
        baru = findViewById(R.id.txt_pengaturan_pass_baru);
        ok = findViewById(R.id.btn_account_photo);
        user = FirebaseAuth.getInstance().getCurrentUser();
        ok.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        gantiPassword(lama.getText().toString(), baru.getText().toString());
    }

    public void gantiPassword(String lama, final String baru) {
        AuthCredential credential = EmailAuthProvider
                .getCredential(user.getEmail(), lama);

// Prompt the user to re-provide their sign-in credentials
        user.reauthenticate(credential)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            user.updatePassword(baru).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Log.d(TAG, "Password updated");
                                        Toast.makeText(getApplicationContext(), "password berhasil d diubah", Toast.LENGTH_LONG).show();
                                    } else {
                                        Log.d(TAG, "Error password not updated");
                                    }
                                }
                            });
                        } else {
                            Log.d(TAG, "Error auth failed");
                        }
                    }
                });
    }
}
