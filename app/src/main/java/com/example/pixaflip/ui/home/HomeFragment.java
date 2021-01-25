package com.example.pixaflip.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.pixaflip.DisplayPdfActivity;
import com.example.pixaflip.DisplayVideoActivity;
import com.example.pixaflip.MainActivity;
import com.example.pixaflip.R;
import com.spark.submitbutton.SubmitButton;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SubmitButton playVideo=root.findViewById(R.id.playVideo);
        SubmitButton showPdf=root.findViewById(R.id.showPdf);

        playVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //play video in landscape mode.
                Intent intent=new Intent(MainActivity.context, DisplayVideoActivity.class);
                startActivity(intent);
             // Toast.makeText(MainActivity.context,"Play video available in Assets folder",Toast.LENGTH_LONG).show();

            }
        });

        showPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.context, DisplayPdfActivity.class);
                startActivity(intent);
            }
        });



        return root;
    }
}
