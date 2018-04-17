package com.batikin.vocomfest.batikin;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.batikin.vocomfest.batikin.model.PemesananModel;
import com.batikin.vocomfest.batikin.model.Pengiriman;
import com.batikin.vocomfest.batikin.model.User;
import com.batikin.vocomfest.batikin.utils.CDM;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;

public class InformasiPengiriman extends AppCompatActivity implements CDM {
    private ArrayList<NavItem> navItems = new ArrayList<NavItem>();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    //add this
    private FirebaseUser user;
    private DatabaseReference mDatabase;
    private DatabaseReference mDatabasePengirian;
    private FirebaseAuth mAuth;
    private EditText etNama;
    private EditText etNomorTelp;
    private EditText etEmail;
    private EditText etAlamatPengirian;
    private Spinner provinsi;
    private Spinner kotakab;
    private Spinner kec;
    private EditText kodePos;
    private Spinner pengirimans;
    static SecureRandom rnd = new SecureRandom();
    PemesananModel mPemesananModel;
    static String noTransaksi = "";
    //
    Button btnNext, btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi_pengiriman);
        getSupportActionBar().setTitle("Informasi Pengiriman");


        //add this
        mPemesananModel = getIntent().getExtras().getParcelable("pemesanan");
        etNama = findViewById(R.id.etNama);
        etNomorTelp = findViewById(R.id.etNomorTelp);
        etEmail = findViewById(R.id.etEmail);
        etAlamatPengirian = findViewById(R.id.etAlamatPengiriman);
        provinsi = findViewById(R.id.spinnerProvinsi);
        kotakab = findViewById(R.id.spinnerKota);
        kec = findViewById(R.id.spinnerKecamatan);
        pengirimans = findViewById(R.id.spinnerPengiriman);
        kodePos = findViewById(R.id.etKodePos);
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        //

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

//      Spinner Provinsi
        String arrayProvinsiSpinner[] = {"Jawa Timur", "Jawa Barat", "Jawa Tengah", "Kalimantan Timur", "Kalimantan Barat", "Sumatera Utara", "Sumatera Barat"};
        Spinner spinnerProvinsi = findViewById(R.id.spinnerProvinsi);
        ArrayAdapter<String> spinnerProvinsiAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayProvinsiSpinner);
        spinnerProvinsiAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProvinsi.setAdapter(spinnerProvinsiAdapter);
//      =========================
//      Spinner Kabupaten
        String arrayKotaSpinner[] = {"Malang", "Surabaya", "Bandung", "Jakarta"};
        Spinner spinnerKota = findViewById(R.id.spinnerKota);
        ArrayAdapter<String> spinnerKotaAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayKotaSpinner);
        spinnerKotaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKota.setAdapter(spinnerKotaAdapter);
//      =========================
//      Spinner Kecamatan
        String arrayKecamatanSpinner[] = {"Lowokwaru", "Klojen", "Kedungkandang", "Blimbing"};
        Spinner spinnerKecamatan = findViewById(R.id.spinnerKecamatan);
        ArrayAdapter<String> spinnerKecamatanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayKecamatanSpinner);
        spinnerKecamatanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerKecamatan.setAdapter(spinnerKecamatanAdapter);
//      =========================
//      Spinner Pengiriman
        String arrayPengirimanSpinner[] = {"JNE", "Tiki", "Pos Indonesia", "Ninja"};
        Spinner spinnerPengiriman = findViewById(R.id.spinnerPengiriman);
        ArrayAdapter<String> spinnerPengirimanAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayPengirimanSpinner);
        spinnerPengirimanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerPengiriman.setAdapter(spinnerPengirimanAdapter);
//      =========================
//      Button Action
        btnKembali = findViewById(R.id.btnKembali);
        btnNext = findViewById(R.id.btnNext);
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InformasiPengiriman.super.finish();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesananAndaIntent = new Intent(view.getContext(), PesananAnda.class);
                insert();
                pesananAndaIntent.putExtra("pemesanan", mPemesananModel);
                pesananAndaIntent.putExtra("notransaksi",noTransaksi);
                pesananAndaIntent.putExtra("pengiriman",pengirimans.getSelectedItem().toString());
                view.getContext().startActivity(pesananAndaIntent);
            }
        });
//      =========================
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

    public void insert() {
        //getUID
        String uid = user.getUid();
        noTransaksi = randomKey(8);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Pemesanan").child(uid);

        mDatabasePengirian = FirebaseDatabase.getInstance().getReference().child("Pengiriman").child(uid);


        HashMap<String, PemesananModel> hashMap = new HashMap<>();
        hashMap.put(noTransaksi, mPemesananModel);

        HashMap<String, Pengiriman> hashMap2 = new HashMap<>();

        Pengiriman pengiriman = new Pengiriman(
                etNama.getText().toString(),
                etNomorTelp.getText().toString(),
                etEmail.getText().toString(),
                etAlamatPengirian.getText().toString(),
                provinsi.getSelectedItem().toString(),
                kotakab.getSelectedItem().toString(),
                kec.getSelectedItem().toString(),
                kodePos.getText().toString(),
                pengirimans.getSelectedItem().toString(),
                "belum");
        hashMap2.put(noTransaksi, pengiriman);

        mDatabase.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "onComplete: sukses");
                }else{
                    Log.d(TAG, "onComplete: Maaf gagal");
                }
            }
        });

        mDatabasePengirian.setValue(hashMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Log.d(TAG, "onComplete: sukses");
                }else{
                    Log.d(TAG, "onComplete: belum berhasil");
                }
            }
        });

    }

    public String randomKey(int len) {
        //membuat nomor transaksi
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }
}
