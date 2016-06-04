package com.example.luchunyang.jsonformat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);
    }


    /*
    {
        "employees": [
        "hello Android"
        { "firstName":"John" , "lastName":"Doe" },
        { "firstName":"Anna" , "lastName":"Smith" },
        { "firstName":"Peter" , "lastName":"Jones" }
        ]
    }
    */
    public void createBasicJson(View view) throws JSONException {
        JSONObject employees = new JSONObject();
        JSONArray array = new JSONArray();
        //数组里面添加个值
        array.put("hello Android");

        JSONObject people = new JSONObject();
        people.put("firstName","John");
        people.put("lastName","Doe");
        //数组里添加对象
        array.put(people);

        people = new JSONObject();
        people.put("firstName","Anna");
        people.put("lastName","Smith");
        array.put(people);

        people = new JSONObject();
        people.put("firstName","Peter");
        people.put("lastName","Jones");
        array.put(people);

        employees.put("employees",array);

        System.out.println(employees.toString());
    }
}
