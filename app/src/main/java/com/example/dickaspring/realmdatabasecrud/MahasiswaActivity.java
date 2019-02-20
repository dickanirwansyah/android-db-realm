package com.example.dickaspring.realmdatabasecrud;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.dickaspring.realmdatabasecrud.adapter.MahasiswaAdapter;
import com.example.dickaspring.realmdatabasecrud.helper.MahasiswaRealmHelper;
import com.example.dickaspring.realmdatabasecrud.model.MahasiswaModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MahasiswaActivity extends AppCompatActivity {

    //deklarasi
    private Realm realm;
    private MahasiswaRealmHelper mahasiswaRealmHelper;
    private RecyclerView recyclerView;
    private MahasiswaAdapter mahasiswaAdapter;
    List<MahasiswaModel> mahasiswaModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mahasiswa);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        RealmConfiguration realmConfiguration = new RealmConfiguration
                .Builder().build();
        realm = Realm.getInstance(realmConfiguration);

        mahasiswaRealmHelper = new MahasiswaRealmHelper(realm);
        mahasiswaModels = new ArrayList<>();
        mahasiswaModels = mahasiswaRealmHelper.listMahasiswa();

        show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mahasiswaAdapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        mahasiswaAdapter = new MahasiswaAdapter(this, mahasiswaModels);
        recyclerView.setAdapter(mahasiswaAdapter);
    }
}
