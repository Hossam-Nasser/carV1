package com.example.car;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;
    private CarsAdapter adapter;
    private DatabaseAccess db;

    private static final int ADD_CAR_REQ_CODE=1;
    private static final int EDIT_CAR_REQ_CODE=1;
    public static final String CAR_KAY ="CAR_KEY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView)findViewById(R.id.main_RecyclerView);

        floatingActionButton = findViewById(R.id.main_float);


        db = DatabaseAccess.getInstance(this);



//        try {
            db.open();
            ArrayList<CarModel> cars = db.getallcar();
            db.close();
            adapter =new CarsAdapter(cars);

//        }catch (Exception e){
//            e.getMessage();
//        }


        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager lm = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(lm);
        recyclerView.setHasFixedSize(true);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewCarActivity.class);
                startActivityForResult(intent,ADD_CAR_REQ_CODE);
            }
        });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        return super.onOptionsItemSelected(item);
    }
}

