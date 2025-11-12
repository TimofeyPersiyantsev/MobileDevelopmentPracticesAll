package com.example.applicationallpz.models;

public class User {
    private String name;
    private int age;
    private String email;
    private String department;

    public User(String name, int age, String email, String department) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.department = department;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
    public String getEmail() { return email; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return name + " (" + age + " лет) - " + department;
    }
}