package com.example.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private Context context;
    private  static  int DATABASE_VERSION = 1;
    private static  String TABLE_NAME = "todo";
    private static  String DATABASE_NAME = "todoList";
    private static String KEY_TITLE = "title";
    private static String  KEY_ID = "id";
    private static String KEY_DETAIL = "detail";
    private static String KEY_DATE = "date";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT, " + KEY_DETAIL + " TEXT, " + KEY_DATE + " DATE)";
        db.execSQL(CREATE_ITEM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    public ArrayList<Model> listAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        ArrayList<Model> array = new ArrayList<Model>();
        if (cursor.moveToFirst()){
            do{
                Model model = new Model();
                model.setTitle(cursor.getString(1));
                model.setId(Integer.valueOf(cursor.getString(0)));
                model.setDetail(cursor.getString(2));
                model.setToday(cursor.getString(3));
                array.add(model);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return  array;
    }
    public void addModel(Model model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE,model.getToday());
        values.put(KEY_DETAIL,model.getDetail());
        values.put(KEY_TITLE,model.getTitle());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public int updateModel(Model model){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TITLE,model.getTitle());
        values.put(KEY_DETAIL,model.getDetail());
        return db.update(TABLE_NAME,values,KEY_ID + " =?",new String[]{String.valueOf(model.getId())});
    }
    void deleteModel(int modelId){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,KEY_ID + " =? ",new String[]{String.valueOf(modelId)});
        db.close();
    }
}
