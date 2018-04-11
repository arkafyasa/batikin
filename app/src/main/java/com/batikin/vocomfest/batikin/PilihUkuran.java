package com.batikin.vocomfest.batikin;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class PilihUkuran extends AppCompatActivity {
    int imgRes;

    ImageView imgSleeve;
    Button btnKembali,btnNext;
    private ArrayList<NavItem> navItems = new ArrayList<NavItem>();

    ListView mDrawerList;
    RelativeLayout mDrawerPane,btnStandar,btnCustom;
    LinearLayout linearLayoutSize;
    TextView txtS,txtM,txtL,txtXL,txtXXL;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

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

        btnStandar = findViewById(R.id.btnStandar);
        btnCustom = findViewById(R.id.btnCustom);
        linearLayoutSize = findViewById(R.id.linearlayoutSize);
        txtS = findViewById(R.id.txtS);
        txtM = findViewById(R.id.txtM);
        txtL = findViewById(R.id.txtL);
        txtXL = findViewById(R.id.txtXL);
        txtXXL = findViewById(R.id.txtXXL);
        btnKembali = findViewById(R.id.btnKembali);
        btnNext = findViewById(R.id.btnNext);

        btnStandar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                linearLayoutSize.setVisibility(linearLayoutSize.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
            }
        });
        btnCustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment dialogCustom = new CustomSizeDialog(imgRes);
                dialogCustom.show(getSupportFragmentManager(), "CustomSizeDialog");
            }
        });
        txtS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtS.setBackgroundColor(getResources().getColor(R.color.brownLighter));
                txtM.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtXL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtXXL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
            }
        });

        txtM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtS.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtM.setBackgroundColor(getResources().getColor(R.color.brownLighter));
                txtL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtXL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtXXL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
            }
        });

        txtL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtS.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtM.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtL.setBackgroundColor(getResources().getColor(R.color.brownLighter));
                txtXL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtXXL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
            }
        });

        txtXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtS.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtM.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtXL.setBackgroundColor(getResources().getColor(R.color.brownLighter));
                txtXXL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                TextView tvHarga = findViewById(R.id.txtHarga);
                tvHarga.setText("Total Harga : Rp. 185000");
            }
        });

        txtXXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtS.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtM.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtXL.setBackgroundColor(getResources().getColor(R.color.whiteColor));
                txtXXL.setBackgroundColor(getResources().getColor(R.color.brownLighter));
            }
        });

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PilihUkuran.super.finish();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pilihMotifIntent = new Intent(view.getContext(),PilihMotif.class);
                view.getContext().startActivity(pilihMotifIntent);
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

@SuppressLint("ValidFragment")
class CustomSizeDialog extends DialogFragment {
    int imgSleeve;
    ImageView ivSleeve;

    public CustomSizeDialog(int imgRes) {
        this.imgSleeve = imgRes;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogInflate = inflater.inflate(R.layout.dialog_custom_size, null);

        ivSleeve = dialogInflate.findViewById(R.id.sleevePic);
        ivSleeve.setImageResource(imgSleeve);

        builder.setView(dialogInflate)
                .setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        CustomSizeDialog.this.getDialog().cancel(); //temporary, please change it
                    }
                })
                .setNegativeButton("Batalkan", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        CustomSizeDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
