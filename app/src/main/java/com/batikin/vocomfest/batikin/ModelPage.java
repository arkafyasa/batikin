package com.batikin.vocomfest.batikin;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.batikin.vocomfest.batikin.utils.CDM;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class ModelPage extends AppCompatActivity implements CDM{

    RecyclerView modelRecycler;
    List<ModelItem> modelItemList;
    private ArrayList<NavItem> navItems = new ArrayList<NavItem>();
    AdapterModel adapterModel;
    String modelName;

    private FirebaseAuth mAuth;
    ListView modelDrawerList;
    RelativeLayout modelDrawerPane;
    private ActionBarDrawerToggle modelDrawerToggle;
    private DrawerLayout modelDrawerLayout;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // set drawer button enabled
        modelName = getIntent().getExtras().getString("category");
        username = findViewById(R.id.userName);
        if (modelName != null) {
            getSupportActionBar().setTitle(modelName);
        }
        modelRecycler = findViewById(R.id.recyclerModel);
        modelItemList = new ArrayList<>();
        adapterModel = new AdapterModel(this, modelItemList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        modelRecycler.setLayoutManager(mLayoutManager);
        modelRecycler.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(2), true));
        modelRecycler.setItemAnimator(new DefaultItemAnimator());
        modelRecycler.setAdapter(adapterModel);

        insertData();
        insertDrawerItem();
        modelDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        modelDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        modelDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapterModel = new DrawerListAdapter(this, navItems);
        modelDrawerList.setAdapter(adapterModel);

        modelDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0 :
                        startActivity(new Intent(ModelPage.this,ActivityAccount.class));
                        break;
                    case 1 :
                        startActivity(new Intent(ModelPage.this,ActivityRiwayat.class));
                        break;
                    case 2 :
                        startActivity(new Intent(ModelPage.this,ActivityPengaturan.class));
                        break;
                    case 3 :
                        startActivity(new Intent(ModelPage.this,ActivityBantuan.class));
                        break;
                    case 4:
                        logout();
                        break;
                }
            }
        });

        modelDrawerToggle = new ActionBarDrawerToggle(this, modelDrawerLayout, R.string.drawer_open, R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

        modelDrawerLayout.setDrawerListener(modelDrawerToggle);
        mAuth = FirebaseAuth.getInstance();

        //set data from firebase
        username.setText(mAuth.getCurrentUser().getEmail());
    }

    public void insertData(){
        modelItemList.add(new ModelItem("Kemeja anak","https://image.ibb.co/eacgux/model_anak.jpg"));
        modelItemList.add(new ModelItem("Kemeja formal","https://image.ibb.co/jvqegc/model_formal.jpg"));
        modelItemList.add(new ModelItem("Kemeja santai","https://image.ibb.co/hxmKgc/model_santai.jpg"));
        modelItemList.add(new ModelItem("Kemeja slimfit","https://image.ibb.co/iXNxZx/model_slimfit.jpg"));
    }

    private void insertDrawerItem() {
        navItems.add(new NavItem("ACCOUNT", R.drawable.person));
        navItems.add(new NavItem("RIWAYAT PEMESANAN", R.drawable.time));
        navItems.add(new NavItem("PENGATURAN", R.drawable.settings));
        navItems.add(new NavItem("BANTUAN", R.drawable.help));
        navItems.add(new NavItem("LOGOUT", R.drawable.power));
    }

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
    //Size Configuration

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    //search item
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.choice, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);

        SearchManager searchManager = (SearchManager) ModelPage.this.getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView = null;
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(ModelPage.this.getComponentName()));
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = modelDrawerLayout.isDrawerOpen(modelDrawerPane);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (this.modelDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        this.modelDrawerToggle.syncState();
    }
}
