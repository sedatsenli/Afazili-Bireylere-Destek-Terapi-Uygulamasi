package com.emirer.myapplication.Model;

public class hastaModel  {
    String hastaAdi, hastaSoyadi, hastaEmail, hastaEgzersiz, hastaTekrar, hastaSonuc;


    public hastaModel(String hastaAdi) {

    }

    public hastaModel(String hastaAdi, String hastaSoyadi, String hastaEmail, String hastaEgzersiz, String hastaTekrar, String hastaSonuc){
        this.hastaAdi = hastaAdi;
        this.hastaSoyadi = hastaSoyadi;
        this.hastaEmail = hastaEmail;
        this.hastaEgzersiz = hastaEgzersiz;
        this.hastaTekrar = hastaTekrar;
        this.hastaSonuc = hastaSonuc;

    }

    public String getHastaEgzersiz() {
        return hastaEgzersiz;
    }

    public void setHastaEgzersiz(String hastaEgzersiz) {
        this.hastaEgzersiz = hastaEgzersiz;
    }

    public String getHastaTekrar() {
        return hastaTekrar;
    }

    public void setHastaTekrar(String hastaTekrar) {
        this.hastaTekrar = hastaTekrar;
    }

    public String getHastaSonuc() {
        return hastaSonuc;
    }

    public void setHastaSonuc(String hastaSonuc) {
        this.hastaSonuc = hastaSonuc;
    }

    public String getHastaAdi() {
        return hastaAdi;
    }

    public void setHastaAdi(String hastaAdi) {
        this.hastaAdi = hastaAdi;
    }

    public String getHastaSoyadi() {
        return hastaSoyadi;
    }

    public void setHastaSoyadi(String hastaSoyadi) {
        this.hastaSoyadi = hastaSoyadi;
    }

    public String getHastaEmail() {
        return hastaEmail;
    }

    public void setHastaEmail(String hastaEmail) {
        this.hastaEmail = hastaEmail;
    }

}

