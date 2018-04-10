package com.batikin.vocomfest.batikin;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.batikin.vocomfest.batikin.utils.CDM;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity implements CDM {

    private RecyclerView mRecyclerCategory;
    private RecyclerView.Adapter mAdapterCategory;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<CategoryItem> categoryItems = new ArrayList<CategoryItem>();
    private ArrayList<NavItem> navItems = new ArrayList<NavItem>();
    private CarouselView carouselView;
    private String imageSlider[] = {"https://image.ibb.co/iwaq4n/dummy_batik_bg.jpg",
            "https://image.ibb.co/hV73Pn/slider4.jpg",
            "https://image.ibb.co/jPKuVS/slider2.jpg",
            "https://image.ibb.co/hToA4n/slider3.jpg"};
    Context currentContext;
    TextView txtSlider;

    private FirebaseAuth mAuth;
    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable drawer button
        currentContext = this;

        txtSlider = findViewById(R.id.sliderText);
        username = findViewById(R.id.userName);

        insertData();
        mRecyclerCategory = findViewById(R.id.recyclerCategory);
        mRecyclerCategory.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerCategory.setLayoutManager(mLayoutManager);

        mAdapterCategory = new AdapterCategory(this, categoryItems);
        mRecyclerCategory.setAdapter(mAdapterCategory);
        mRecyclerCategory.setLayoutManager(mLayoutManager);

        carouselView = findViewById(R.id.carouselView);
        carouselView.setPageCount(imageSlider.length);
        carouselView.setImageListener(imageListener);

        insertDrawerItem();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        mDrawerPane = findViewById(R.id.drawerPane);
        mDrawerList = findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, navItems);
        mDrawerList.setAdapter(adapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        startActivity(new Intent(HomePage.this,ActivityAccount.class));
                        break;
                    case 1 :
                        startActivity(new Intent(HomePage.this,ActivityRiwayat.class));
                        break;
                    case 2 :
                        startActivity(new Intent(HomePage.this,ActivityPengaturan.class));
                        break;
                    case 3 :
                        startActivity(new Intent(HomePage.this,ActivityBantuan.class));
                        break;
                    case 4 :
                        logout();
                        break;
                }
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
        //firebase
        mAuth = FirebaseAuth.getInstance();

        //set data from firebase
        username.setText(mAuth.getCurrentUser().getEmail());
    }

    private void insertDrawerItem() {
        navItems.add(new NavItem("ACCOUNT", R.drawable.person));
        navItems.add(new NavItem("RIWAYAT PEMESANAN", R.drawable.time));
        navItems.add(new NavItem("PENGATURAN", R.drawable.settings));
        navItems.add(new NavItem("BANTUAN", R.drawable.help));
        navItems.add(new NavItem("LOGOUT", R.drawable.power));
    }

    private void insertData() {
        categoryItems.add(new CategoryItem("Kemeja", R.drawable.dummy_category));
        categoryItems.add(new CategoryItem("Kaos", R.drawable.dummy_category));
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            Picasso.with(currentContext).load(imageSlider[position]).into(imageView);
        }
    };

    //method tambahan
    private void logout() {
        mAuth.signOut();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            Log.d(TAG, "onNavigationItemSelected: logout succesful");
            startActivity(new Intent(this, ActivityLogin.class));
        } else {
            Log.d(TAG, "onNavigationItemSelected: " + user.getEmail());
        }
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

    @Override
    protected void onStart() {
        super.onStart();
        //cek user sudah login apa belum
        if (mAuth.getCurrentUser() == null) {
            startActivity(new Intent(HomePage.this, ActivityLogin.class));
        }
    }
}
