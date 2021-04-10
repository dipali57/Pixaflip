package com.example.pixaflip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.pixaflip.Data.MyFav;
import com.example.pixaflip.Data.MyDbHandler;
import com.example.pixaflip.ui.slideshow.FavAdapter;
import com.example.pixaflip.ui.slideshow.SlideshowFragment;
import com.example.pixaflip.user.UserDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.example.pixaflip.MainActivity.context;

public class DisplayPdfActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    static List<pdf> list;
    ToggleButton toggleButton;
    PDFAdapter adapter;

    private UserDatabase dbHandler;

    private String getDateTime()
    {
        SimpleDateFormat sd=new SimpleDateFormat ("yyyy-MM-ddcHH:mm:ss", Locale.getDefault ());
        Date d=new Date();
        return sd.format(d);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pdf);
        recyclerView = (RecyclerView) findViewById(R.id.Myrecycler);
        toggleButton = (ToggleButton)findViewById(R.id.FavLike);

        boolean connected = false;
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network
            connected = true;
        }
        else
            connected = false;



        MyDbHandler db = new MyDbHandler(DisplayPdfActivity.this);

        list = new ArrayList<>();
        list.add(new pdf("Subconsious mind","https://rgu.ac/rgi_pdf/power-subconscious-mind.pdf"));
        list.add(new pdf("The secret", "https://www.thesecret.tv/wp-content/uploads/2015/04/The-Science-of-Getting-Rich.pdf"));
        list.add(new pdf("Self Belief","https://static1.squarespace.com/static/5753d2be45bf211ddd8db268/t/576075e027d4bd87de91b67e/1465939432968/61._Self-Belief.pdf"));
        list.add(new pdf("Magic of Faith ","http://www.unfetteredmind.org/practices/TheMagicOfFaith2017.pdf"));
        list.add(new pdf("The heart","http://www.divinebalance.eu/images/stories/algemeen/folders/The%20Heart.Seat%20of%20the%20Soul.pdf"));



        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PDFAdapter(list,this, db);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new PDFAdapter.ItemClickListener() {
            @Override
            public void onItemClick(int pos) {

                dbHandler= new UserDatabase(DisplayPdfActivity.this);
                //  String pname pos.get
                //  int x=getAda

                String name = list.get(pos).getPdfName();

                Log.d("pdfname",name);

                Toast.makeText ( context,"name of pdf is"+name,Toast.LENGTH_SHORT ).show ();
                // model m=new model();
                //String from2 = m.setFrom1 ( "DisplayPdfActivity" );
                // Sdb.tring name = list.get(pos).getPdfName();

                dbHandler.Adduser( "DisplayPdfActivity",name,getDateTime () );
                // String timestamp2 =m.setTimestamp ( getDateTime () );
                // model pro = new model(from2,to2,timestamp2);
                // db.Adduser (m);

                Intent intent = new Intent(DisplayPdfActivity.this,ActivityPdf.class);
                intent.putExtra("position",pos);
                startActivity(intent);
            }

            @Override
            public void ontogclick(int pos,boolean state) {

                MyDbHandler db = new MyDbHandler(DisplayPdfActivity.this);
                if(state){
                    int x = pos;
                    System.out.println(db.isExist(list.get(x).getPdfName()));
                    if(!db.isExist(list.get(x).getPdfName())){
                        String url = list.get(x).getPdfUrl();
                        String name = list.get(x).getPdfName();
                        MyFav pro = new MyFav(name,url);
                        db.addFavourite(pro);
                    }

                }else{
                    if(db.isExist(list.get(pos).getPdfName())){
                        String name = list.get(pos).getPdfName();
                        db.deleteById(name);
                    }

                }
            }
        });

        //create recycler view in this activity and use it to display pdf files.
    }
}

