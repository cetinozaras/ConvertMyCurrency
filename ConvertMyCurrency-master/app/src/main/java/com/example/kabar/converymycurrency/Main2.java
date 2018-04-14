package com.example.kabar.converymycurrency;

import android.content.Intent;
import android.graphics.Color;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.mutespittah.convertmycurrencyfinal.R;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main2 extends AppCompatActivity {

    Map<String, Double> currencyToRate = new HashMap<String, Double>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy =
                    new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        final TextView jsonView = (TextView) findViewById(R.id.textView8);

        final Button btn1=(Button)findViewById(R.id.button2);
        Button b3=(Button) findViewById(R.id.button3);

        final ArrayList<String> arraylist1 = new ArrayList<String>();
        final ArrayList<String> arraylist2=new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraylist1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraylist2);



        final EditText x1=(EditText)findViewById(R.id.editText);

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
        JSONObject realResult = (JSONObject)array.get("rates");//map type
        final Double result = (Double) realResult.get("USD");
        Double result2=(Double) realResult.get("GBP");
        currencyToRate.clear();//currency to rate hashamp // real result ise jsonobject
        currencyToRate.put("EUR", (double) 1);
        for (Object o : realResult.keySet()) {
            String key = (String) o;
            if (key != null) {
                currencyToRate.put(key, (Double) realResult.get(key));
            }
        }

        for (String s : currencyToRate.keySet()) {
            arraylist1.add(s);

        }
        for(String q : currencyToRate.keySet()){
            arraylist2.add(q);
            Log.d("Listeler",q);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinnertoconvertcurrencies=(Spinner) findViewById(R.id.spinner);
        spinnertoconvertcurrencies.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final Spinner spinnertobeconvertedcurrencies=(Spinner) findViewById(R.id.spinner2);
        spinnertobeconvertedcurrencies.setAdapter(adapter2);

        //kullanma
        final Double rate = currencyToRate.get("GBP");

        // jsonView.setText(result.toString());
        //jsonView.setText(result2.toString());
        spinnertoconvertcurrencies.setOnItemSelectedListener(new OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (parent.getId()) {
                    case R.id.spinner:
                        String lang_Name = parent.getItemAtPosition(position).toString();
                        final Double lang_Key = currencyToRate.get(lang_Name);
                        final String lang_Name2 = parent.getItemAtPosition(position).toString();
                        final Double lang_Key2=currencyToRate.get(lang_Name2);
                        Log.d("degeer",""+ Double.toString(lang_Key));
                        btn1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Double firstvalue= Double.parseDouble(x1.getEditableText().toString());
                                NumberFormat nm=NumberFormat.getNumberInstance();
                                Log.d("langkey",""+ Double.toString(lang_Key));
                                Log.d("langkey2",""+ Double.toString(lang_Key2));

                                double result = (lang_Key/lang_Key2) * firstvalue ;
                                Log.d("result","" +result);


                                jsonView.setText(nm.format(result));
                            }
                        });
                        spinnertobeconvertedcurrencies.setOnItemSelectedListener(new OnItemSelectedListener() {

                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                switch (parent.getId()) {
                                    case R.id.spinner2:
                                        String lang_Name = parent.getItemAtPosition(position).toString();
                                        final Double lang_Key=currencyToRate.get(lang_Name);
                                        Log.d("degeer2suan",""+ Double.toString(lang_Key));
                                        final Double lang_Key2=currencyToRate.get(lang_Name2);
                                        btn1.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Double firstvalue= Double.parseDouble(x1.getEditableText().toString());
                                                NumberFormat nm=NumberFormat.getNumberInstance();
                                                Log.d("diger",""+ Double.toString(lang_Key));
                                                Log.d("diger2",""+ Double.toString(lang_Key2));

                                                double result = (lang_Key/lang_Key2) * firstvalue ;


                                                jsonView.setText(nm.format(result));
                                            }
                                        });
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main2.this,Main3.class));
            }
        });
    }
}