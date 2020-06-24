package com.appstone.maybatchtasksample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Information.db";
    private static final int DATABASE_VERSION = 1;
    public static String TABLE_NAME = "CHECKLIST";
    public static String COL_ID = "ID";
    public static String COL_TITLE = "TITLE";
    public static String COL_ITEM = "ITEMS";

    private Context context;


    public static String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_TITLE + " TEXT," + COL_ITEM + " TEXT)";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addValueToDatabase(Items items){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_ITEM,items.itemName);
        cv.put(COL_TITLE,items.title);

        long result = sqLiteDatabase.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(context, "Error!!!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Success!!!", Toast.LENGTH_SHORT).show();
        }

    }

    public ArrayList<Items> getDataFromDatabase(SQLiteDatabase sqLiteDatabase){
        ArrayList<Items> items = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        if(cursor.moveToFirst()){
            do {
                Items items_list = new Items();
                items_list.itemID = cursor.getInt(cursor.getColumnIndex(COL_ID));
                items_list.title = cursor.getString(cursor.getColumnIndex(COL_TITLE));
                items_list.itemName = cursor.getString(cursor.getColumnIndex(COL_ITEM));

            }while (cursor.moveToNext());

            cursor.close();
        }
        return items;
    }
}
