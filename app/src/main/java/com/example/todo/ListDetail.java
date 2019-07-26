package com.example.todo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class ListDetail extends AppCompatActivity {
    private Toolbar toolbar;
    EditText title;
    EditText details;
    static int id;
    TextView date;
    DatabaseHandler db;
    Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_detail);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(" To Do List Details");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        title = (EditText) findViewById(R.id.edit_title);
        details = (EditText) findViewById(R.id.edit_text_det);
        date = (TextView) findViewById(R.id.txt_date);
        Bundle bundle = getIntent().getExtras();

        String titl = bundle.getString("TITLE");
        String detail = bundle.getString("DETAIL");
        id = bundle.getInt("ID");
        String dat = bundle.getString("TODAY");
       title.setText(titl);
       details.setText(detail);
       date.setText(dat);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu3,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_update){
            try {
                db = new DatabaseHandler(getApplicationContext());
                model = new Model();
                model.setTitle(title.getText().toString());
                model.setDetail(details.getText().toString());
                model.setId(id);
                db.updateModel(model);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }catch (Exception e){
               e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
