package com.example.todo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    ArrayList<Model> models ;
    DatabaseHandler db ;
    ListView listView;
    private long backPressTime;
    private   Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(" To Do List ");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        db = new DatabaseHandler(getApplicationContext());
        listView = (ListView) findViewById(R.id.list_view);
        if(savedInstanceState != null){
            LoadAdapter();
        }
    }
    
 @Override
    protected void onResume() {
        super.onResume();
        LoadAdapter();
    }
    void LoadAdapter(){
        loadArray();
        MyAddapter adapter = new MyAddapter(getApplicationContext(), models, this);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add){
            Intent intent = new Intent(getApplicationContext(),TodoList.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    void loadArray(){
        models = new ArrayList<Model>();
        models =  db.listAll();
    }
}
