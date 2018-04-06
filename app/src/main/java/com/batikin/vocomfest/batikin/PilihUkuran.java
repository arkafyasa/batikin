package com.batikin.vocomfest.batikin;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class PilihUkuran extends AppCompatActivity {
    int imgRes;

    ImageView imgSleeve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_ukuran);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pilih Ukuran");
        imgSleeve = findViewById(R.id.imgSleeve);
        imgRes = getIntent().getExtras().getInt("sleeve");
        if(imgRes != 0){
            imgSleeve.setImageResource(imgRes);
        }
    }
}
