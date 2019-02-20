package com.example.dickaspring.realmdatabasecrud.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MahasiswaModel extends RealmObject {

    @PrimaryKey
    private Integer Id;
    private Integer Nim;
    private String Nama;

    public Integer getId(){
        return Id;
    }

    public void setId(Integer Id){
        this.Id = Id;
    }

    public Integer getNim(){
        return Nim;
    }

    public void setNim(Integer Nim){
        this.Nim = Nim;
    }

    public String getNama(){
        return Nama;
    }

    public void setNama(String Nama){
        this.Nama = Nama;
    }
}
