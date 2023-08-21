package com.example.mothapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonTask.JsonTaskListener {
    private final String JSON_URL = "https://mobprog.webug.se/json-api?login=a22cazlu";
    private final List<Moth> name = new ArrayList<>();
    private final List<Moth> family = new ArrayList<>();
    private final List<Moth> location = new ArrayList<>();
    private final List<Moth> size = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new JsonTask(this).execute(JSON_URL);
    }
    @Override
    public void onPostExecute(String json) {
        Log.d("MainActivity", json);
        Gson gson = new Gson();
        Moth[] temp = gson.fromJson(json,Moth[].class);

        for (int i = 0; i < temp.length; i++){
            Log.d("MAIN", "Hittade ett berg: "+temp[i].getFamily());
            name.add(temp[i]);
        }

        Adapter adapter = new Adapter(this, name, new Adapter.OnClickListener(){
            @Override
            public void onClick(Moth item) {
                Toast.makeText(MainActivity.this, item.getName(), Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView view = findViewById(R.id.recycler_view);
        view.setLayoutManager(new LinearLayoutManager(this));
        view.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){
        switch (item.getItemId()){
            case R.id.about:
                //Toast.makeText(this, "About are pressed", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, About.class);
                startActivity(i);
                break;
            case R.id.start:
                Toast.makeText(this, "You are already here", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}