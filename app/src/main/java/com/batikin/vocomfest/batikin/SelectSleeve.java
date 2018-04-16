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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.batikin.vocomfest.batikin.model.PemesananModel;
import com.batikin.vocomfest.batikin.utils.CDM;

import java.util.ArrayList;

public class SelectSleeve extends AppCompatActivity implements CDM {
    String modelName;
    private ArrayList<NavItem> navItems = new ArrayList<NavItem>();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    PemesananModel mPemesananModel ;

    LinearLayout btnShortSleeve,btnLongSleeve,btnAddDesign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_sleeve);

        mPemesananModel = getIntent().getExtras().getParcelable("pemesanan");

        Log.d(TAG, "onCreate: "+mPemesananModel.getModel());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Pilih Model");

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

        btnShortSleeve = findViewById(R.id.short_sleeve);
        btnLongSleeve = findViewById(R.id.long_sleeve);
        btnAddDesign = findViewById(R.id.add_design);

        btnShortSleeve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shortSleeveIntent = new Intent(view.getContext(),PilihUkuran.class);
                shortSleeveIntent.putExtra("sleeve",R.drawable.short_sleeve);
                mPemesananModel.setModel("Lengan Pendek");
                shortSleeveIntent.putExtra("pemesanan", mPemesananModel);
                view.getContext().startActivity(shortSleeveIntent);
            }
        });

        btnLongSleeve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent longSleeveIntent = new Intent(view.getContext(),PilihUkuran.class);
                longSleeveIntent.putExtra("sleeve",R.drawable.long_sleeve);
                mPemesananModel.setModel("Lengan Pendek");
                longSleeveIntent.putExtra("pemesanan", mPemesananModel);
                view.getContext().startActivity(longSleeveIntent);
            }
        });

        btnAddDesign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addDesignIntent = new Intent(view.getContext(),AddDesign.class);
                view.getContext().startActivity(addDesignIntent);
            }
        });
    }
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
}
