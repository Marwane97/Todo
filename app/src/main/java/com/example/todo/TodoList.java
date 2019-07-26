package com.example.todo;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TodoList extends AppCompatActivity {
    private android.support.v7.widget.Toolbar toolbar;
    DatabaseHandler db;
    TextView date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle(" Create Now ");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        date = (TextView) findViewById(R.id.txt_date_add);
        Date dat = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
       String strDate = formatter.format(dat);
       date.setText(strDate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu2,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_save){
            try {
                db = new DatabaseHandler(getApplicationContext());
                Model model = new Model();
                final TextView txt_date_add = (TextView) findViewById(R.id.txt_date_add);
                final EditText edit_txt_title = (EditText) findViewById(R.id.edit_txt_title);
                final  EditText edit_txt_detail = (EditText) findViewById(R.id.edit_txt_detail);
                model.setToday(txt_date_add.getText().toString());
                model.setTitle(edit_txt_title.getText().toString());
                model.setDetail(edit_txt_detail.getText().toString());
                db.addModel(model);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
