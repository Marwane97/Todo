package com.example.todo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAddapter extends ArrayAdapter<Model> {
    private Context context;
    private ArrayList<Model> models;
    private  Activity activity;
    private  MyAddapter adapter;
    DatabaseHandler db;
    public MyAddapter(Context context, ArrayList<Model> models, Activity activity) {
        super(context, R.layout.list_item, models);
        this.context = context;
        this.models = models;
        this.activity = activity;
        this.db = new DatabaseHandler(context);
        this.adapter = this;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item,null,true);
        TextView txt = view.findViewById(R.id.txt_list);
        txt.setText(models.get(position).getTitle());

         LinearLayout linear = (LinearLayout) view.findViewById(R.id.linear);
        ImageButton image = (ImageButton) view.findViewById(R.id.image);

        final String date = models.get(position).getToday();
        final String title = models.get(position).getTitle();
        final String detail = models.get(position).getDetail();
        final int id = models.get(position).getId();
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              db.deleteModel(id);
              models.remove(position);
                adapter.notifyDataSetChanged();
                Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show();
            }
        });
        linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ListDetail.class);
                intent.putExtra("TODAY",date);
                intent.putExtra("TITLE",title);
                intent.putExtra("DETAIL",detail);
                intent.putExtra("ID",id);
                activity.startActivity(intent);
            }
        });
        return view;
    }
}
