package com.example.dmc.sensorexa1;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
TextView tvCount;
Button btShowSenList;
ListView lvSenList;
ArrayAdapter<String>adapter;
SensorManager sensorManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCount=(TextView)findViewById(R.id.textCount);
        btShowSenList=(Button)findViewById(R.id.btnShowList);
        lvSenList=(ListView)findViewById(R.id.listVIewSensor);

        sensorManager=(SensorManager)
                getSystemService(SENSOR_SERVICE);

        List<Sensor> list=sensorManager.
                getSensorList(Sensor.TYPE_ALL);

        adapter=new ArrayAdapter<String>(
                MainActivity.this,
                android.R.layout.simple_list_item_1
        );

        for (Sensor temp: list){
            String name=temp.getName();
            String vendor=temp.getVendor();
            int version=temp.getVersion();

            String info = "Name:"+name+"\n"+
                    "Vendor:"+vendor+"\n"+
                    "Version:"+version;
            adapter.add(info);
        }

        int count=adapter.getCount();
        tvCount.setText("Sensor Count:"+count);

        lvSenList.setAdapter(adapter);

        btShowSenList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lvSenList.setVisibility(View.VISIBLE);
            }
        });
    }
}
