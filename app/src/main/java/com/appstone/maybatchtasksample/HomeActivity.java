package com.appstone.maybatchtasksample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FloatingActionButton actionButton;
    DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.rc_view_data_from_json);
        actionButton = findViewById(R.id.home_add_item_btn);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,MainActivity.class));

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));
        dbHelper = new DatabaseHelper(this);
        getDataFromDatabase();
    }

    private void getDataFromDatabase() {
        ArrayList<Items> items = dbHelper.getDataFromDatabase(dbHelper.getReadableDatabase());
        CustomAdapter customAdapter = new CustomAdapter(this,items);
        recyclerView.setAdapter(customAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 12345){
            getDataFromDatabase();
        }
    }
}