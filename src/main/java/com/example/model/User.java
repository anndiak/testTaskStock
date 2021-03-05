package com.example.model;

public class User {
    private int id;
    private String name;
    private boolean premium;
    private int age;

    public User(int id,String name, boolean premium, int age) {
        this.id = id;
        this.name = name;
        this.premium = premium;
        this.age = age;
    }
    public User(String name, boolean premium, int age) {
        this.name = name;
        this.premium = premium;
        this.age = age;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isElderThen70(){
        return age > 70;
    }
}
