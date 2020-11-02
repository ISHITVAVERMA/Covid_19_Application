package com.example.covid_19_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import Adapter.MyAdapter;
import Model.ListItem;

public class MainActivity extends AppCompatActivity {

    Button Apicall;
    EditText editplace; //This is the text View to take input from the user abut the Country we want to consider
    TextView return_call;
    TextView return_call_confirmed;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }



    public void BuApi(View view) {



        Intent intent=new Intent(this,Country_wise_Status.class);
        startActivity(intent);

        //Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();

    }

    public void BuApi1(View view) {
        Intent intent=new Intent(this,India_activity.class);

        startActivity(intent);
    }



}
