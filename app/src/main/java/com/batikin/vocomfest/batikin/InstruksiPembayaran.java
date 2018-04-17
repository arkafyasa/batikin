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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.batikin.vocomfest.batikin.model.PemesananModel;

import java.util.ArrayList;

public class InstruksiPembayaran extends AppCompatActivity {
    private ArrayList<NavItem> navItems = new ArrayList<NavItem>();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    TextView txtNoTrans;
    TextView ttxTotalBiaya;
    TextView txtPengiriman;

    PemesananModel mPemesananModel;
    static String noTransaksi = "";
    Button btnNext,btnKembali;
    static String pengiriman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruksi_pembayaran);
        getSupportActionBar().setTitle("Instruksi Pembayaran");

        mPemesananModel = getIntent().getExtras().getParcelable("pemesanan");
        noTransaksi = getIntent().getExtras().getString("notransaksi");
        pengiriman = getIntent().getExtras().getString("pengiriman");

        txtNoTrans = findViewById(R.id.txtNoTranss);
        ttxTotalBiaya = findViewById(R.id.txtTotalBiayah);
        txtPengiriman = findViewById(R.id.txtPengirimanLewat);

        txtNoTrans.setText("No. Transaksi :"+noTransaksi);
        txtPengiriman.setText(pengiriman);
        ttxTotalBiaya.setText("Rp. "+mPemesananModel.getHarga());
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
//      Button Action
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent homepageIntent = new Intent(view.getContext(),HomePage.class);
                view.getContext().startActivity(homepageIntent);
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
