package com.assignmenttp.repository.customer.birthday.implem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.assignmenttp.config.database.DBConstants;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.customer.religion.Religion;
import com.assignmenttp.repository.customer.birthday.ReligionRepository;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/05/13.
 */
public class ReligionRepositoryImplem extends SQLiteOpenHelper implements ReligionRepository {
    public static final String TABLE_NAME="religion";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME="firstName";
    public static final String COLUMN_POSTAL="postalCode";
    public static final String COLUMN_STREETNAME="streetName";
    public static final String COLUMN_SUBURB="suburb";


    //create database
    private static final String DATABASE_CREATE= " CREATE TABLE "
            + TABLE_NAME +"("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_FIRSTNAME +" TEXT NOT NULL , "
            + COLUMN_POSTAL +" TEXT NOT NULL, "
            + COLUMN_STREETNAME +" TEXT NOT NULL, "
            + COLUMN_SUBURB +" TEXT NOT NULL );";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(this.getClass().getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public ReligionRepositoryImplem(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }


    public void open()throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close(){
  //      this.close();
    }

    @Override
    public Religion findByid(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_FIRSTNAME,
                        COLUMN_POSTAL,
                        COLUMN_STREETNAME,
                        COLUMN_SUBURB},
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if(cursor.moveToNext()){
            final Address address = new Address.Builder()
                    .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTAL)))
                    .streetName(cursor.getString(cursor.getColumnIndex(COLUMN_STREETNAME)))
                    .suburb(cursor.getString(cursor.getColumnIndex(COLUMN_SUBURB)))
                    .build();
            final Religion religion = new Religion.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .address(address)
                    .build();

            return  religion;
        }
        else {
            return null;
        }
    }

    @Override
    public Religion save(Religion entity) throws SQLException {

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FIRSTNAME, entity.getName());
        values.put(COLUMN_POSTAL, entity.getPostalCode());
        values.put(COLUMN_STREETNAME, entity.getStreetName());
        values.put(COLUMN_SUBURB, entity.getSuburb());

        long id = db.insertOrThrow(TABLE_NAME, null, values);

        Religion insertReligion = new Religion.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertReligion;

    }
    @Override
    public Religion update(Religion entity) throws SQLException{
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FIRSTNAME, entity.getName());
        values.put(COLUMN_POSTAL, entity.getPostalCode());
        values.put(COLUMN_STREETNAME, entity.getStreetName());
        values.put(COLUMN_SUBURB, entity.getSuburb());

        //update method
        db.update(
                TABLE_NAME,
                values,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }
    @Override
    public Religion delete(Religion entity) throws SQLException{
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<Religion> findAll() throws SQLException{
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Religion > religions = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{

                final Address address = new Address.Builder()
                        .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTAL)))
                        .streetName(cursor  .getString(cursor.getColumnIndex(COLUMN_STREETNAME)))
                        .suburb(cursor.getString(cursor.getColumnIndex(COLUMN_SUBURB)))
                        .build();

                final Religion religion = new Religion.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .address(address)
                        .build();
                religions.add(religion);
            }while(cursor.moveToNext());
            return religions;
        }
        return null;
    }
    @Override
    public int deleteAll() throws SQLException{
        open();
        int rowsDeleted = db.delete(TABLE_NAME, null, null);
        close();
        return rowsDeleted;
    }
}
