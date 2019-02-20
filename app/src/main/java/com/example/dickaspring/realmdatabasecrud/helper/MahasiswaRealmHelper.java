package com.example.dickaspring.realmdatabasecrud.helper;

import android.util.Log;

import com.example.dickaspring.realmdatabasecrud.model.MahasiswaModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MahasiswaRealmHelper {

    private Realm realm;

    public MahasiswaRealmHelper(Realm realm){
        this.realm = realm;
    }

    public void save(final MahasiswaModel mahasiswaModel){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //check
                if (realm != null){
                    Log.e("Created", "Database was created");
                    Number currentIdNumber = realm.where(MahasiswaModel.class).max("Id");
                    int nextId;
                    if (currentIdNumber == null){
                        nextId = 1;
                    }else{
                        //generate auto increment
                        nextId = currentIdNumber.intValue() + 1;
                    }
                    mahasiswaModel.setId(nextId);
                    MahasiswaModel mMahasiswa = realm.copyToRealm(mahasiswaModel);
                    System.out.println("data : "+mMahasiswa);
                }else{
                    Log.e("sorry", "Database not exists !");
                }
            }
        });
    }

    public List<MahasiswaModel> listMahasiswa(){
        List<MahasiswaModel> mahasiswaModels = new ArrayList<>();
        RealmResults<MahasiswaModel> realmMahasiswa = realm.where(MahasiswaModel.class).findAll();
        for (MahasiswaModel mahasiswaModel : realmMahasiswa){
            mahasiswaModels.add(mahasiswaModel);
        }
        return mahasiswaModels;
    }

    public void update(final Integer Id, final Integer Nim, final String Nama){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                MahasiswaModel mMahasiswaModel = realm.where(MahasiswaModel.class)
                        .equalTo("Id", Id)
                        .findFirst();
                mMahasiswaModel.setNim(Nim);
                mMahasiswaModel.setNama(Nama);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Log.e("success", "success");
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    public void delete(final Integer Id){
        final RealmResults<MahasiswaModel> mahasiswaModels = realm.where(MahasiswaModel.class)
                .equalTo("Id", Id).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mahasiswaModels.deleteFromRealm(0);
            }
        });
    }
}
