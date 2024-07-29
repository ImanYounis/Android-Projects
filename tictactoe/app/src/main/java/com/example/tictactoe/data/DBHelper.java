package com.example.tictactoe.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.tictactoe.data.model.Contact;
import com.example.tictactoe.params.Params;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {
        super(context, Params.DB_NAME, null, Params.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create="CREATE TABLE " +Params.TABLE_NAME+ "(" +Params.KEY_ID+ "INTEGER PRIMARY KEY "
                +Params.KEY_NAME+ "TEXT" +Params.KEY_PHONE+ "TEXT" +")" ;
        Log.d("dblogs","query being run is:"+create);
        sqLiteDatabase.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Params.KEY_NAME, contact.getName());
        cv.put(Params.KEY_PHONE, contact.getPhoneno());

        db.insert(Params.TABLE_NAME,null,cv);
        Log.d("dblogs","data inserted");
        db.close();
    }
    public List<Contact> displayContacts(){
        List<Contact> contacts=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM "+Params.TABLE_NAME;
        Cursor cursor=db.rawQuery(select,null);

        if (cursor.moveToFirst()){
            do{
                Contact c=new Contact();
                c.setId(Integer.parseInt(cursor.getString(0)));
                c.setName(cursor.getString(1));
                c.setPhoneno(cursor.getString(2));
                contacts.add(c);
            }while (cursor.moveToNext());
        }
        return contacts;
    }
    public int updateContact(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Params.KEY_NAME,contact.getName());
        values.put(Params.KEY_PHONE,contact.getPhoneno());
        return db.update(Params.TABLE_NAME,values,Params.KEY_ID+"=?",
                new String[]{String.valueOf(contact.getId())});

    }
    public void deleteContactById(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteContactByNameorwhole(Contact contact){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ID+"=?",new String[]{String.valueOf(contact)});
        db.close();
    }
    public int getCount(){
        String query="SELECT * FROM "+Params.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
    }
}
