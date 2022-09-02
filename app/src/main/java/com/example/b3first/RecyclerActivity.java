package com.example.b3first;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RecyclerActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    String[] countries = new String[]{"india","pakistan","USA","UK","india","pakistan","USA","UK","india","pakistan","USA","UK","india","pakistan","USA","UK"
    ,"india","pakistan","USA","UK","india","pakistan","USA","UK","india","pakistan","USA","UK","india","pakistan","USA","UK",
            "india","pakistan","USA","UK","india","pakistan","USA","UK","india","pakistan","USA","UK","india","pakistan","USA","UK",
            "india","pakistan","USA","UK","india","pakistan","USA","UK","india","pakistan","USA","UK","india","pakistan","USA","UK"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView = findViewById(R.id.recyclerView);
        CountriesAdapter cAdapter = new CountriesAdapter(countries);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(cAdapter);

        Button cMenuButton = findViewById(R.id.btnContext);
        registerForContextMenu(cMenuButton);
    }


    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
         super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.recycler_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         super.onOptionsItemSelected(item);
         switch (item.getItemId()){
             case R.id.misettings:
                 Toast.makeText(this, "opening settings", Toast.LENGTH_SHORT).show();
                 break;
             case R.id.milogout:
                 Toast.makeText(this, "logging out", Toast.LENGTH_SHORT).show();

                 break;
         }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.recycler_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
         super.onContextItemSelected(item);
        switch (item.getItemId()){
            case R.id.misettings:
                Toast.makeText(this, "context opening settings", Toast.LENGTH_SHORT).show();
                break;
            case R.id.milogout:
                Toast.makeText(this, "context logging out", Toast.LENGTH_SHORT).show();

                break;
        }
        return true;
    }
}