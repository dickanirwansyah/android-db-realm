package com.example.dickaspring.realmdatabasecrud;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dickaspring.realmdatabasecrud.helper.MahasiswaRealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ActivityDetail extends AppCompatActivity {

    //activity detail
    private EditText edtNIM;
    private EditText edtNama;
    private Button btnUpdate;
    private Button btnDelete;
    private Button btnCancel;

    //request id,nim,nama
    Integer id;
    String nama, nim;
    Realm realm;
    MahasiswaRealmHelper mahasiswaRealmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        edtNIM = (EditText) findViewById(R.id.edtNim);
        edtNama = (EditText) findViewById(R.id.edtNama);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        nim = getIntent().getStringExtra("nim");
        nama = getIntent().getStringExtra("nama");

        edtNIM.setText(nim);
        edtNama.setText(nama);

        //setUp realm
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder().build();
        realm = Realm.getInstance(configuration);
        mahasiswaRealmHelper = new MahasiswaRealmHelper(realm);

        //cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityDetail.this, MainActivity.class));
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mahasiswaRealmHelper.update(id, Integer.parseInt(edtNIM.getText().toString()), edtNama.getText().toString());
                Toast.makeText(ActivityDetail.this,"data berhasil di update ", Toast.LENGTH_SHORT).show();
                edtNama.setText("");
                edtNIM.setText("");
                finish();
            }
        });

        /** masih ada bug
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mahasiswaRealmHelper.delete(id);
                Toast.makeText(ActivityDetail.this, "data berhasil di delete : "+id, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(ActivityDetail.this, MainActivity.class));
                //finish();
            }
        });
         **/
    }
}
