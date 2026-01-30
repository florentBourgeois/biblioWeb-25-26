package fr.serfa.biblioWeb.model;

public class User {
    private String nom;
    private int age;

    public User(String nom, int age) {this.nom = nom;    this.age = age;}

    public String getNom() {
        return nom;
    }

    public String getTruc() {return "truc machin"; }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "User{" +
                "nom='" + nom + '\'' +
                ", age=" + age +
                '}';
    }
}
