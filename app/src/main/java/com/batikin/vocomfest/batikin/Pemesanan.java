package com.batikin.vocomfest.batikin;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.batikin.vocomfest.batikin.model.PemesananModel;
import com.batikin.vocomfest.batikin.utils.CDM;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Pemesanan extends AppCompatActivity implements CDM {
    private ArrayList<NavItem> navItems = new ArrayList<NavItem>();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    TextView motif;
    TextView model;
    TextView harga;
    TextView desc;

    Button btnNext, btnKembali;

    PemesananModel mPemesananModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        mPemesananModel = getIntent().getExtras().getParcelable("pemesanan");
        getSupportActionBar().setTitle("Pemesanan");

        motif = findViewById(R.id.txtmotif);
        model = findViewById(R.id.txtmodel);
        harga = findViewById(R.id.txtTotalHarga);
        desc = findViewById(R.id.txtDesc);

        motif.setText(mPemesananModel.getMotif());
        model.setText(mPemesananModel.getModel());
        harga.setText("Total Harga : \n" + mPemesananModel.getHarga());

        if (mPemesananModel.getMotif().equalsIgnoreCase("kawung")) {
            desc.setText("Batik ini terinspirasi dari bentuk buah kolang kaling. Bentuk kolang kaling yang lonjong tersebut disusun empat sisi membentuk lingkaran. Motif Kuwung sering diidentikan dengan motif sepuluh sen kuno, karena bentuknya yang bulat dengan lubang ditengahnya. Motif ini berasal dan berkembang di Jawa Tengah dan Jogjakarta. Biasanya motifnya sama, hanya bedanya pada hiasan atau aksennya saja. Batik ini juga termasuk motif batik Indonesia yang paling banyak dipakai.");
        } else if (mPemesananModel.getMotif().equalsIgnoreCase("parang")) {
            desc.setText("Parang berasal dari kata pereng atau miring. Bentuk motifnya berbentuk seperti huruf “S” miring berombak memanjang.Motif Parang ini tersebar di seluruh Jawa, mulai dari Jawa Tegah, Jogjakarta dan Jawa Barat. Biasanya, perbedaannya hanya terletak pada aksen dari batik Motif parang tersebut. Misalkan, di Jogja ada motif Parang Rusak dan Parang Barong, di Jawa Tengah ada Parang Slobog, serta di Jawa Barat ada Parang Klisik.");
        }

        Log.d(TAG, "onCreate: "+mPemesananModel.getMotif()+mPemesananModel.getModel()+mPemesananModel.getKategori()+mPemesananModel.getHarga()+mPemesananModel.getUkuran());
//        Side menu drawer code
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        insertDrawerItem();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mDrawerPane = findViewById(R.id.drawerPane);
        mDrawerList = findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, navItems);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
//        ==============================================================

        ImageView imgModel = findViewById(R.id.imgModel);
        ImageView imgMotif = findViewById(R.id.imgMotifPesan);

        Picasso.with(this).load("https://image.ibb.co/hxmKgc/model_santai.jpg").into(imgModel);
        Picasso.with(this).load("https://image.ibb.co/cKKdrc/batik_jawa_1.jpg").into(imgMotif);

        btnKembali = findViewById(R.id.btnKembali);
        btnNext = findViewById(R.id.btnNext);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pemesanan.super.finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent informasiPengirimanIntent = new Intent(view.getContext(), InformasiPengiriman.class);
                view.getContext().startActivity(informasiPengirimanIntent);
            }
        });
    }

    //Drawer Function
    private void insertDrawerItem() {
        navItems.add(new NavItem("ACCOUNT", R.drawable.person));
        navItems.add(new NavItem("RIWAYAT PEMESANAN", R.drawable.time));
        navItems.add(new NavItem("PENGATURAN", R.drawable.settings));
        navItems.add(new NavItem("BANTUAN", R.drawable.help));
        navItems.add(new NavItem("LOGOUT", R.drawable.power));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.mDrawerToggle.syncState();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerPane);
        return super.onPrepareOptionsMenu(menu);
    }
//    ========================================================================
}
