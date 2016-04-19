package com.example.luchunyang.jsonformat;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by luchunyang on 16/4/19.
 */
public class Student {
    public int id;
    public String nickName;
    public int age;
    public String email;
    public ArrayList<String> books;
    public HashMap<String, String> booksMap;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", books=" + books +
                ", booksMap=" + booksMap +
                '}';
    }
}
