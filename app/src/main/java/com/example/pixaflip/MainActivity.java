package com.example.pixaflip;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;

import com.example.pixaflip.ui.home.HomeFragment;
import com.example.pixaflip.user.UserDatabase;
import com.example.pixaflip.user.methods;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private UserDatabase dbHandler;

    private String getDate()
    {
        SimpleDateFormat sd=new SimpleDateFormat ("yyyy-MM-ddcHH:mm:ss", Locale.getDefault ());
        Date d=new Date ();
        return sd.format(d);
    }

    private AppBarConfiguration mAppBarConfiguration;
    public static Context context;

    //Add new pdf objects in this list and use this list in pdf adapter of recyclerview
    public static ArrayList<pdf> list =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=this;

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        Menu menu=navigationView.getMenu ();
        MenuItem mt=menu.findItem ( R.id.nav_home );
        MenuItem mt1=menu.findItem ( R.id.nav_gallery );
        MenuItem mt2=menu.findItem ( R.id.nav_slideshow );
        mt.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ()
                                        {
                                            @Override
                                            public boolean onMenuItemClick(MenuItem menuItem)
                                            {
                                                dbHandler=new UserDatabase(MainActivity.this);
                                                dbHandler.Adduser ( "HomeFragment" ,getClass().toString(),getDate());
                                                return false;
                                            }
                                        }
        );
        mt1.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ()
                                         {
                                             @Override
                                             public boolean onMenuItemClick(MenuItem menuItem)
                                             {
                                                 dbHandler=new UserDatabase(MainActivity.this);
                                                 dbHandler.Adduser ( "GalleryFragment" ,getClass().toString(),getDate());
                                                 return false;
                                             }
                                         }
        );
        mt2.setOnMenuItemClickListener ( new MenuItem.OnMenuItemClickListener ()
                                         {
                                             @Override
                                             public boolean onMenuItemClick(MenuItem menuItem)
                                             {
                                                 dbHandler=new UserDatabase(MainActivity.this);
                                                 dbHandler.Adduser ( "FavPdf" ,getClass ().toString (),getDate());
                                                 return false;
                                             }
                                         }
        );





        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);



    }
    private String getDateTime()
    {
        SimpleDateFormat sd=new SimpleDateFormat ("yyyy-MM-ddcHH:mm:ss", Locale.getDefault ());
        Date d=new Date ();
        return sd.format(d);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
