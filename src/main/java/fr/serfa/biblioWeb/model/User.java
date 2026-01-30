package fr.serfa.biblioWeb.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public class User {
    private String nom;
    private int age =0;


    //@JsonCreator // parce que https://stackoverflow.com/a/79854456
    public User() {
    }

    // parce que https://stackoverflow.com/a/79854456  -> permet de faire un post sans "age" dedans
    public User(String nom) {
        this.nom = nom;
    }

    public User(String nom, int age) {this.nom = nom;    this.age = age;}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getTruc() {return "truc machin"; }


    @Override
    public String toString() {
        return "User{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
}
