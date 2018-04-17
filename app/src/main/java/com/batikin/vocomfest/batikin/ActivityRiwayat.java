package com.batikin.vocomfest.batikin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.batikin.vocomfest.batikin.model.PemesananModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityRiwayat extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DatabaseReference mDatabaseRiwayat;
    private FirebaseUser mCurrentUser;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_layout);


        mCurrentUser = FirebaseAuth.getInstance().getCurrentUser();
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_riwayat);
        mRecyclerView.setHasFixedSize(true);
        mDatabaseRiwayat = FirebaseDatabase.getInstance().getReference().child("Pemesanan").child(mCurrentUser.getUid());
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<PemesananModel, PemesananViewHolder>
                firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<PemesananModel, PemesananViewHolder>
                (PemesananModel.class,R.layout.riwayat_item,PemesananViewHolder.class,mDatabaseRiwayat) {
            @Override
            protected void populateViewHolder(PemesananViewHolder viewHolder, PemesananModel model, int position) {
                viewHolder.setHarga(model.getHarga());
                viewHolder.setMotif(model.getMotif());
                viewHolder.setDesc(model.getModel(),model.getKategori(),model.getUkuran());
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class PemesananViewHolder extends RecyclerView.ViewHolder {
        View mView;
        private TextView motivText;
        private TextView hargatext;
        private TextView desc;

        public PemesananViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
            motivText = mView.findViewById(R.id.motif_text);
            hargatext = mView.findViewById(R.id.harga_text);
            desc = mView.findViewById(R.id.model_ukuran_pengiriman_text);
        }

        public void setMotif(String text){
            motivText.setText(text);
        }

        public void setHarga(String text){
            hargatext.setText("Rp. "+text);
        }

        public void setDesc(String model, String kategori, String ukuran){
            desc.setText("Model :"+model +"\n"
                        +"Kategori : "+kategori+"\n"
                        +"ukuran : "+ukuran);
        }
    }
}
