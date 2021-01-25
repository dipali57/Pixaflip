package com.example.pixaflip;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;


public class pdfopener extends AppCompatActivity {


    PDFView myPDFViewer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfopener);
        myPDFViewer=(PDFView)findViewById(R.id.PDFView);
        String getItem=getIntent().getStringExtra("pdfFileName");
        if(getItem.equals("Unit-I"))
        {
            myPDFViewer.fromAsset("Unit-I.pdf").load();
        }
        if(getItem.equals("Unit-II"))
        {
            myPDFViewer.fromAsset("Unit-II.pdf").load();
        }
        if(getItem.equals("Unit-III"))
        {
            myPDFViewer.fromAsset("Unit-III.pdf").load();
        }
        if(getItem.equals("Unit-IV"))
        {
            myPDFViewer.fromAsset("Unit-IV.pdf").load();
        }
        if(getItem.equals("Unit-V"))
        {
            myPDFViewer.fromAsset("Unit-V.pdf").load();
        }

    }
}