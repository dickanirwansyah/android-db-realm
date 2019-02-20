package com.example.dickaspring.realmdatabasecrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dickaspring.realmdatabasecrud.helper.MahasiswaRealmHelper;
import com.example.dickaspring.realmdatabasecrud.model.MahasiswaModel;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //deklarasi
    Button btnActionSimpan;
    Button btnActionTampil;
    EditText edtNama;
    EditText edtNim;
    String requestNama;
    Integer requestNim;
    Realm realm;
    MahasiswaRealmHelper mahasiswaRealmHelper;
    MahasiswaModel mahasiswaModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inisialisasi
        btnActionSimpan = (Button) findViewById(R.id.btnSimpan);
        btnActionTampil = (Button) findViewById(R.id.btnTampil);
        edtNama = (EditText) findViewById(R.id.nama);
        edtNim = (EditText) findViewById(R.id.nim);

        //setup realm
        Realm.init(MainActivity.this);
        RealmConfiguration configuration = new RealmConfiguration
                .Builder()
                .build();
        realm = Realm.getInstance(configuration);

        btnActionSimpan.setOnClickListener(this);
        btnActionTampil.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnActionSimpan){
            //requestNim = Integer.parseInt(edtNim.getText().toString());

            //requestNim = Integer.parseInt(stringNim);
            final String stringNim = edtNim.getText().toString();
            requestNim = Integer.parseInt(stringNim);
            requestNama = edtNama.getText().toString();

            if (!requestNim.equals("") && !requestNama.isEmpty()){
                mahasiswaModel = new MahasiswaModel();
                mahasiswaModel.setNama(requestNama);
                mahasiswaModel.setNim(requestNim);

                mahasiswaRealmHelper = new MahasiswaRealmHelper(realm);
                mahasiswaRealmHelper.save(mahasiswaModel);
                Toast.makeText(MainActivity.this, "DATA INSERT !", Toast.LENGTH_SHORT).show();

                edtNim.setText("");
                edtNama.setText("");
            }else{
                Toast.makeText(MainActivity.this, "sorry field masih ada yang kosong ", Toast.LENGTH_SHORT).show();
            }
        }else if(v == btnActionTampil){
            startActivity(new Intent(MainActivity.this, MahasiswaActivity.class));
        }
    }
}
