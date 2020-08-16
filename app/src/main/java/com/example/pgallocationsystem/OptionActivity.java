package com.example.pgallocationsystem;

import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.view.Menu;
import android.view.View;

public class OptionActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    View view,viewcall,viewmessage;
    BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        bottomSheetDialog=new BottomSheetDialog(OptionActivity.this);
        view=getLayoutInflater().inflate(R.layout.contact,null);
        bottomSheetDialog.setContentView(view);
        viewcall=view.findViewById(R.id.lincall);
        viewmessage=view.findViewById(R.id.linmessage);
        viewcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OptionActivity.this,CallUser.class);
                startActivity(intent);

            }
        });
        viewmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(OptionActivity.this,MessageUser.class);
                startActivity(intent);
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState==null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,new Home()).commit();
            navigationView.setCheckedItem(R.id.nav_home);

        }






    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment=null;



        if (id == R.id.nav_home) {
            fragment=new Home();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

        }
        else if (id == R.id.roomtype) {
            fragment=new RoomTypeManagement();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

        }else if (id == R.id.roomcategory) {
            fragment=new RoomCategoryManagement();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

        } else if (id == R.id.roomdetails) {
            fragment=new RoomDetailsManagement();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

        } else if (id == R.id.report) {
            fragment=new ReportManagement();
            getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();

        } else if (id == R.id.contact) {
            bottomSheetDialog.show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
