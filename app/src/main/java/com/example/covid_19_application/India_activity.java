package com.example.covid_19_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

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

public class India_activity extends AppCompatActivity {

    TextView loc;
    TextView deaths;
    TextView confirmedCasesIndian;
    TextView discharged;
    TextView total_confirmed_state;
    TextView total_confirmed_case_day_text;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItem;
    String a[] = new String[1000];
    //String a1[] = new String[29];
    //String a2[] = new String[29];



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_india_activity);
        loc=findViewById(R.id.loc);
        deaths=findViewById(R.id.deaths);
        confirmedCasesIndian=findViewById(R.id.confirmedCasesIndian);
        discharged=findViewById(R.id.discharged);
        total_confirmed_state=findViewById(R.id.total_confirmed_state);
        total_confirmed_case_day_text=findViewById(R.id.total_confirmed_case_day);



        String url = "https://api.rootnet.in/covid19-in/stats/latest";
        new India_activity.MyAsyncTaskgetNews().execute(url);


        recyclerView=(RecyclerView)findViewById(R.id.Recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listItem = new ArrayList<>();

        /*for(int i=0;i<50;i++)
        {
            ListItem item=new ListItem(
                    "Item"+(i+1),

                    "Description"+(i+1),
                    "rating"+(i+1)
            );


        }*/

        for(int i=0;i<=1000;i=i+3)
        {
            a[i]="he";
            String one=a[i];
            //String two=a[i+1];
            // String three=a[i+2];
            listItem.add(new ListItem("hello"+a[i],"hello","bye"));
            System.out.println(a[i]);
        }





        listItem.add(new ListItem("Hello","me","bye"));


        adapter=new MyAdapter(this,listItem);
        recyclerView.setAdapter(adapter);



    }



    public class MyAsyncTaskgetNews extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            //before works
        }
        @Override
        protected String  doInBackground(String... params) {
            // TODO Auto-generated method stub
            try {
                String NewsData;
                //define the url we have to connect with
                URL url = new URL(params[0]);
                //make connect with url and send request
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                //waiting for 7000ms for response
                urlConnection.setConnectTimeout(7000);//set timeout to 5 seconds

                try {
                    //getting the response data
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    //convert the stream to string
                    NewsData = ConvertInputToStringNoChange(in);
                    //send to display data
                    publishProgress(NewsData);
                } finally {
                    //end connection
                    urlConnection.disconnect();
                }

            }catch (Exception ex){}
            return null;
        }
        protected void onProgressUpdate(String... progress) {

            try {

                /*
                String s2="";
                String deaths_String="";
                String confirmedCasesIndian_String="";
                String discharged_String="";
                String total_confirmed_state_String="";
                int total_confirmed_case_day=0;
                int deaths_int=0;
                int recovered=0;

                JSONObject json = new JSONObject(progress[0]);
                JSONObject data = json.getJSONObject("data");
                JSONArray region = data.getJSONArray("regional");




                for(int i=0;i<region.length();i++)
                {
                    JSONObject JO = (JSONObject) region.get(i);

                    s2=s2+JO.getString("loc")+"\n";
                    loc.setText(s2);

                    //deaths_String=deaths_String+JO.getString("deaths")+"\n";
                    //deaths.setText(deaths_String);
                    //deaths_int=deaths_int+Integer.parseInt(JO.getString("deaths"));

                    confirmedCasesIndian_String=confirmedCasesIndian_String+JO.getString("confirmedCasesIndian")+"\n";
                    confirmedCasesIndian.setText(confirmedCasesIndian_String);

                    discharged_String=discharged_String+JO.getString("discharged")+"\n";

                    recovered=recovered+Integer.parseInt(JO.getString("discharged"));

                    total_confirmed_state_String=total_confirmed_state_String+JO.getString("totalConfirmed")+"\n";
                    total_confirmed_state.setText(total_confirmed_state_String);

                    total_confirmed_case_day=total_confirmed_case_day+Integer.parseInt(JO.getString("totalConfirmed"));

                }
                //total_confirmed_case_day_text.setText(Integer.toString(total_confirmed_case_day));
                //deaths.setText(Integer.toString(deaths_int));
                //discharged.setText(Integer.toString(recovered));

                //display response data
                //Toast.makeText(getApplicationContext(),"Hello boi",Toast.LENGTH_LONG).show();
                //Toast.makeText(getApplicationContext(),progress[0],Toast.LENGTH_LONG).show();
                */




                JSONObject json=new JSONObject(progress[0]);

                JSONObject data = json.getJSONObject("data");

                JSONObject data1 = data.getJSONObject("summary");

                //JSONObject data2=data1.getJSONObject("total");
                total_confirmed_case_day_text.setText(data1.getString("total"));
                deaths.setText(data1.getString("deaths"));
                discharged.setText(data1.getString("discharged"));

                JSONArray data2 = data.getJSONArray("regional");



                for(int i=0;i<data2.length();i=i+3) {
                    JSONObject JO = (JSONObject) data2.get(i);

                    String city = JO.getString("loc");
                    String confirmed_now=JO.getString("confirmedCasesIndian");
                    String death_now=JO.getString("deaths");


                   //listItem.add(new ListItem("hello","bye","death_now"));

                    //Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();
                    a[i]=city;
                    a[i+1]=confirmed_now;
                    a[i+2]=death_now;

                    System.out.println(a[i]);
                    //System.out.println(a[i+1]);
                    //System.out.println(a[i+2]);

                }









                //Toast.makeText(getApplicationContext(),"hello",Toast.LENGTH_LONG).show();






            } catch (Exception ex) {
            }


        }

        protected void onPostExecute(String  result2){


        }
    }

    // this method convert any stream to string
    public static String ConvertInputToStringNoChange(InputStream inputStream) {

        BufferedReader bureader=new BufferedReader( new InputStreamReader(inputStream));
        String line ;
        String linereultcal="";

        try{
            while((line=bureader.readLine())!=null) {

                linereultcal+=line;

            }
            inputStream.close();


        }catch (Exception ex){}

        return linereultcal;
    }
}