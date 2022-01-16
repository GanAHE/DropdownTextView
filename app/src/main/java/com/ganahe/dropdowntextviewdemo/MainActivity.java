package com.ganahe.dropdowntextviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.ganahe.dropdowntextview.DropdownTextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private final List<CharSequence> demo4_items = new ArrayList<>();
    private final List<CharSequence> demo5_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.textview_online_help).setOnClickListener(view->{
            Uri uri = Uri.parse("https://dgzc.ganahe.top/ganahe/2022/android_dropdowntextview.html");
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(uri);
            startActivity(intent);
        });

        //手动从array中获取List
        List<CharSequence> demo3List = new ArrayList<>();
        String[] a = getResources().getStringArray(R.array.WIFI_KEYS);
        for(String str:a){
            demo3List.add((CharSequence) str);
        }
        ((DropdownTextView) findViewById(R.id.customDropDownPopWindow_rional_speed)).setArrayItems(demo3List);


        demo4_items.add("GNSSAMS");
        demo4_items.add("DGZC.ganahe.top");
        demo4_items.add("Test for ong");

        ((DropdownTextView) findViewById(R.id.dropdownTextview_demo_4)).setArrayItems(demo4_items);

        demo5_items.add("TianqueROS");
        demo5_items.add("Software");
        demo5_items.add("GanAHE");
        ((DropdownTextView) findViewById(R.id.dropdownTextview_demo_5)).setArrayItems(demo5_items);
    }
}