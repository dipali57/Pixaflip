package com.example.pixaflip.ui.slideshow;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pixaflip.ItemClickListener;
import com.example.pixaflip.R;
import com.example.pixaflip.Data.MyFav;
import com.example.pixaflip.Data.MyDbHandler;

import java.util.List;

public class SlideshowFragment extends Fragment {

    public static List<MyFav> list;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recyclerViewfav);

        MyDbHandler db = new MyDbHandler(getActivity());
        final List<MyFav> list = db.getAll();


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ItemClickListener itemClickListener = new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Intent intent = new Intent(getActivity().getApplication(),FavPdf.class);
                intent.putExtra("url",list.get(position).getUrl());
                //intent.putExtra("position",position);
                startActivity(intent);
            }
        };
        FavAdapter adapter = new FavAdapter(list,getActivity(),itemClickListener);
        recyclerView.setAdapter(adapter);

        //create recycler view in this activity and use it to display pdf files.

        return root;
    }
}