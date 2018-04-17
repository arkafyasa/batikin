package com.batikin.vocomfest.batikin;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.batikin.vocomfest.batikin.model.PemesananModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PesananAnda extends AppCompatActivity {
    private ArrayList<NavItem> navItems = new ArrayList<NavItem>();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    Button btnNext,btnKembali;
    PemesananModel mPemesananModel;
    static String noTransaksi = "";
    ImageView imgKategori;
    ImageView imgMotif;
    ImageView imgModels;
    TextView totalTextView;
    static String pengiriman;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan_anda);

        mPemesananModel = getIntent().getExtras().getParcelable("pemesanan");
        noTransaksi = getIntent().getExtras().getString("notransaksi");
        pengiriman = getIntent().getExtras().getString("pengiriman");

        imgKategori = findViewById(R.id.imgKategories);
        imgModels = findViewById(R.id.imgModel);
        imgMotif = findViewById(R.id.imgMotifPesan);
        totalTextView = findViewById(R.id.txtTotalBayarr);

        if (mPemesananModel.getKategori().equalsIgnoreCase("Lengan Pendek")) {
            imgKategori.setImageResource(getResources().getIdentifier("com.batikin.vocomfest.batikin:drawable/short_sleeve.png", null, null));
        } else if (mPemesananModel.getKategori().equalsIgnoreCase("Lengan Panjang")) {
            imgKategori.setImageResource(getResources().getIdentifier("com.batikin.vocomfest.batikin:drawable/long_sleeve.png", null, null));
        } else {

        }
        if (mPemesananModel.getModel().equalsIgnoreCase("Kemeja anak")) {
            Picasso.with(this).load("https://image.ibb.co/eacgux/model_anak.jpg").into(imgModels);
        } else if (mPemesananModel.getModel().equalsIgnoreCase("Kemeja formal")) {
            Picasso.with(this).load("https://image.ibb.co/jvqegc/model_formal.jpg").into(imgModels);
        } else if (mPemesananModel.getModel().equalsIgnoreCase("Kemeja santai")) {
            Picasso.with(this).load("https://image.ibb.co/hxmKgc/model_santai.jpg").into(imgModels);
        } else if (mPemesananModel.getModel().equalsIgnoreCase("Kemeja slimfit")) {
            Picasso.with(this).load("https://image.ibb.co/iXNxZx/model_slimfit.jpg").into(imgModels);
        }

        if (mPemesananModel.getMotif().equalsIgnoreCase("kawung")) {
            Picasso.with(this).load("https://image.ibb.co/cKKdrc/batik_jawa_1.jpg").into(imgMotif);
        } else if (mPemesananModel.getMotif().equalsIgnoreCase("parang")) {
            Picasso.with(this).load("https://image.ibb.co/gHytPx/batik_jawa_2.jpg").into(imgMotif);
        }


        getSupportActionBar().setTitle("Pesanan Anda");
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

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.drawer_open, R.string.drawer_close) {

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

//        ImageView imgModel = findViewById(R.id.imgModel);
//        ImageView imgMotif = findViewById(R.id.imgMotifPesan);
//
//        Picasso.with(this).load("https://image.ibb.co/hxmKgc/model_santai.jpg").into(imgModel);
//        Picasso.with(this).load("https://image.ibb.co/cKKdrc/batik_jawa_1.jpg").into(imgMotif);

        //      Button Action
        btnKembali = findViewById(R.id.btnKembali);
        btnNext = findViewById(R.id.btnNext);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PesananAnda.super.finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent instruksiPembayaranIntent = new Intent(view.getContext(),InstruksiPembayaran.class);
                instruksiPembayaranIntent.putExtra("pemesanan", mPemesananModel);
                instruksiPembayaranIntent.putExtra("pengiriman",pengiriman);
                instruksiPembayaranIntent.putExtra("notransaksi",noTransaksi);
                view.getContext().startActivity(instruksiPembayaranIntent);
            }
        });
//      =========================
    }
    //Drawer Function
    private void insertDrawerItem() {
        navItems.add(new NavItem("ACCOUNT",R.drawable.person));
        navItems.add(new NavItem("RIWAYAT PEMESANAN",R.drawable.time));
        navItems.add(new NavItem("PENGATURAN",R.drawable.settings));
        navItems.add(new NavItem("BANTUAN",R.drawable.help));
        navItems.add(new NavItem("LOGOUT",R.drawable.power));
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
