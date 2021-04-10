package com.example.pixaflip.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pixaflip.ActivityPdf;
import com.example.pixaflip.Data.MyDbHandler;

import com.example.pixaflip.DisplayCovidActivity;
import com.example.pixaflip.DisplayPdfActivity;
import com.example.pixaflip.DisplayVideoActivity;
import com.example.pixaflip.MainActivity;
import com.example.pixaflip.R;

import com.example.pixaflip.ShowStates;
import com.example.pixaflip.user.UserDatabase;
import com.example.pixaflip.user.methods;
import com.spark.submitbutton.SubmitButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HomeFragment extends Fragment
{
    private HomeViewModel homeViewModel;


    private UserDatabase dbHandler;


    private String getDateTime()
    {
        SimpleDateFormat sd=new SimpleDateFormat ("yyyy-MM-ddcHH:mm:ss", Locale.getDefault ());
        Date d=new Date ();
        return sd.format(d);
    }

    // PDFAdapter.ItemClickListener itemClickListener;

    // MyDbHandler db;

    public interface ItemClickListener {
        void onItemClick(int pos);
        // void ontogclick
        void ontogclick(String time,String aname);
    }


    public String fun()
    {
        String s1="DisplayCovidActivity";
        return s1;
        // DisplayPdfActivity pd=new DisplayPdfActivity (s1);
    }



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SubmitButton playVideo=root.findViewById(R.id.playVideo);
        SubmitButton showPdf=root.findViewById(R.id.showPdf);
        SubmitButton viewReport=root.findViewById(R.id.Report);
        //SubmitButton viewStatewise=root.findViewById(R.id.Statewise);


        //addCourseBtn = root.findViewById(R.id.playVideo);

        //SubmitButton addCourseBtn = root.findViewById(R.id.playVideo);
        //dbHandler = new MyDbHandler (HomeFragment.this);


        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbHandler = new UserDatabase(getContext ());
                //dbHandler.adduseract ( "DisplayVideoActivity", "", getDateTime () );
                // MyFav pro = new MyFav(name,url);
                methods m=new methods();
                String from = m.setFrom ( "HomeFragment" );
                String to=m.setTo ( "DisplayVideoActivity" );
                String timestamp =m.setTimestamp ( getDateTime () );
                // model pro = new model(from,to,timestamp);
                dbHandler.adduseract(m);
                // model m=new model();
                // dbHandler.adduseract ( "DisplayVideoActivity", "DisplayPdfActivity", getDateTime () );


                //play video in landscape mode.
                Intent intent=new Intent(MainActivity.context, DisplayVideoActivity.class);
                startActivity(intent);
                // Toast.makeText(MainActivity.context,"Play video available in Assets folder",Toast.LENGTH_LONG).show();

            }
        });

        showPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dbHandler = new UserDatabase(getContext ());
                //dbHandler.adduseract ( "DisplayVideoActivity", "", getDateTime () );
                // MyFav pro = new MyFav(name,url);
                methods m1=new methods();
                String from = m1.setFrom ( "HomeFragment" );
                String to=m1.setTo( "DisplayPdfActivity" );
                String timestamp =m1.setTimestamp ( getDateTime () );
                // model pro = new model(from,to,timestamp);
                dbHandler.adduseract (m1);
                Intent intent=new Intent(MainActivity.context, DisplayPdfActivity.class);
                startActivity(intent);
            }
        });

        viewReport.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                dbHandler = new UserDatabase(getContext ());
                //dbHandler.adduseract ( "DisplayVideoActivity", "", getDateTime () );
                // MyFav pro = new MyFav(name,url);
                methods m2=new methods();
                String from= m2.setFrom ( "HomeFragment" );
                String to=m2.setTo( "DisplayCovidActivity" );
                String timestamp=m2.setTimestamp ( getDateTime () );
                // model pro = new model(from,to,timestamp);
                dbHandler.adduseract (m2);

                Intent intent=new Intent(MainActivity.context,DisplayCovidActivity.class);
                startActivity(intent);
            }

        });
        return root;

    }

}