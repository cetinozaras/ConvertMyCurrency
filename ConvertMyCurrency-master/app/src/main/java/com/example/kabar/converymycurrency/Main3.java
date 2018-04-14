package com.example.kabar.converymycurrency;

import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.example.mutespittah.convertmycurrencyfinal.R;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Main3 extends AppCompatActivity {
LineGraphSeries <DataPoint> series;
    Main2 cem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        JSONParser parser = new JSONParser();
        URL url = null;
        try {
            url = new URL("http://api.fixer.io/latest");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            connection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        InputStream stream = null;
        try {
            stream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        reader = new BufferedReader(new InputStreamReader(stream));
        StringBuffer buffer = new StringBuffer();
        String line = "";
        try {
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Object obj = null;
        try {
            obj = parser.parse(buffer.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONObject array = (JSONObject) obj;
        JSONObject realResult = (JSONObject)array.get("rates");
        Log.d("tag",""+array);




        double x;
        double y;
        x=0;
        y=0;
        GraphView graph=(GraphView) findViewById(R.id.graph);
        series=new LineGraphSeries<DataPoint>();
        for(int i=0 ; i < 100 ;i++){
            x=x+0.12;
            y=0;
            series.appendData(new DataPoint(x,y),true,100);
        }
        graph.addSeries(series);
    }
}
