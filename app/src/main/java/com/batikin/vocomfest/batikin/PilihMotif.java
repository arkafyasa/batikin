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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.batikin.vocomfest.batikin.model.PemesananModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PilihMotif extends AppCompatActivity {

    private ArrayList<NavItem> navItems = new ArrayList<NavItem>();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    Spinner spinnerPulau;
    ImageView img1,img2;
    TextView overlay1,overlay2;
    Button btnNext,btnKembali;
    TextView tvHarga;
    static String motif = "parang";
    PemesananModel mPemesananModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_motif);
        mPemesananModel = getIntent().getExtras().getParcelable("pemesanan");
        getSupportActionBar().setTitle("Pilih Motif");
         tvHarga = findViewById(R.id.txtHarga);

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

        String arraySpinner[] = {"Jawa"};
        spinnerPulau = findViewById(R.id.spinnerMotif);
        ArrayAdapter<String> spinnerPulauAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, arraySpinner);
        spinnerPulauAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPulau.setAdapter(spinnerPulauAdapter);

        img1 = findViewById(R.id.imgMotif1);
        img2 = findViewById(R.id.imgMotif2);
        overlay1 = findViewById(R.id.txtMotif1);
        overlay2 = findViewById(R.id.txtMotif2);

        Picasso.with(this).load("https://image.ibb.co/cKKdrc/batik_jawa_1.jpg").into(img1);
        Picasso.with(this).load("https://image.ibb.co/gHytPx/batik_jawa_2.jpg").into(img2);

        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overlay1.setVisibility(View.VISIBLE);
                overlay2.setVisibility(View.GONE);
                tvHarga.setText("Total Harga : Rp. 50000");
                mPemesananModel.setHarga(String.valueOf(Integer.valueOf(mPemesananModel.getHarga())+50000) );
                motif = "kawung";

            }
        });

        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overlay1.setVisibility(View.GONE);
                overlay2.setVisibility(View.VISIBLE);
                tvHarga.setText("Total Harga : Rp. 60000");
                mPemesananModel.setHarga(String.valueOf(Integer.valueOf(mPemesananModel.getHarga())+60000) );
                motif = "parang";
            }
        });

        btnKembali = findViewById(R.id.btnKembali);
        btnNext = findViewById(R.id.btnNext);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PilihMotif.super.finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pemesananIntent = new Intent(view.getContext(),Pemesanan.class);
                mPemesananModel.setMotif(motif);
                pemesananIntent.putExtra("pemesanan", mPemesananModel);
                view.getContext().startActivity(pemesananIntent);
            }
        });
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
