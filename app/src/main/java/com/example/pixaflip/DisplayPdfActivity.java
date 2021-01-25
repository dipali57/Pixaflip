package com.example.pixaflip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;



import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.String;

public class DisplayPdfActivity extends AppCompatActivity {

    ListView pdfListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pdf);

        //create recycler view in this activity and use it to display pdf files.

        pdfListView=(ListView)findViewById(R.id.myPDFList);
        String[] pdfFiles={"Unit-I","Unit-II","Unit-III","Unit-IV","Unit-V"};
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,pdfFiles)
        {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view=super.getView(position, convertView, parent);
                TextView mytext=(TextView)view.findViewById(android.R.id.text1);
                return view;
            }
        };
        pdfListView.setAdapter(adapter);
        pdfListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                String item=pdfListView.getItemAtPosition(i).toString();
                Intent start=new Intent(getApplicationContext(),pdfopener.class);
                start.putExtra("pdfFileName",item);
                startActivity(start);
            }
        });



    }
}
