package com.assignmenttp.repository.employees.photo.implem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.assignmenttp.config.database.DBConstants;
import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.employees.photo.Photo;
import com.assignmenttp.repository.employees.photo.PhotoRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/06/07.
 */
public class PhotoReapositoryImpem extends SQLiteOpenHelper implements PhotoRepository {
    public static final String TABLE_NAME="photo";
    private SQLiteDatabase db;

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_FIRSTNAME="firstName";
    public static final String COLUMN_LASTNAME="lastName";
    public static final String COLUMN_POSTAL="postalCode";
    public static final String COLUMN_STREETNAME="streetName";
    public static final String COLUMN_SUBURB="suburb";


    //create database
    private static final String DATABASE_CREATE= " CREATE TABLE "
            + TABLE_NAME +"("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + COLUMN_FIRSTNAME +" TEXT  , "
            + COLUMN_LASTNAME +" TEXT , "
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

    public PhotoReapositoryImpem(Context context){
        super(context, DBConstants.DATABASE_NAME, null, DBConstants.DATABASE_VERSION);
    }


    public void open()throws SQLException {
        db = this.getWritableDatabase();
    }

    public void close(){
       // this.close();
    }

    @Override
    public Photo findByid(Long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{
                        COLUMN_ID,
                        COLUMN_FIRSTNAME,
                        COLUMN_LASTNAME,
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
            final Photo photo = new Photo.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                    .first(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                    .last(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                    .address(address)
                    .build();

            return  photo;
        }
        else {
            return null;
        }
    }

    @Override
    public Photo save(Photo entity) throws SQLException{

        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
        values.put(COLUMN_POSTAL, entity.getPostalCode());
        values.put(COLUMN_STREETNAME, entity.getStreetName());
        values.put(COLUMN_SUBURB, entity.getSuburb());

        long id = db.insertOrThrow(TABLE_NAME, null, values);

        Photo insertParty = new Photo.Builder()
                .copy(entity)
                .id(new Long(id))
                .build();
        return insertParty;

    }
    @Override
    public Photo update(Photo entity) throws SQLException{
        open();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, entity.getId());
        values.put(COLUMN_FIRSTNAME, entity.getFirstName());
        values.put(COLUMN_LASTNAME, entity.getLastName());
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
    public Photo delete(Photo entity) throws SQLException{
        open();
        db.delete(
                TABLE_NAME,
                COLUMN_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Set<Photo> findAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        Set<Photo > photo = new HashSet<>();
        open();
        Cursor cursor = db.query(TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{

                final Address address = new Address.Builder()
                        .postalCode(cursor.getString(cursor.getColumnIndex(COLUMN_POSTAL)))
                        .streetName(cursor  .getString(cursor.getColumnIndex(COLUMN_STREETNAME)))
                        .suburb(cursor.getString(cursor.getColumnIndex(COLUMN_SUBURB)))
                        .build();

                final Photo photos = new Photo.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)))
                        .first(cursor.getString(cursor.getColumnIndex(COLUMN_FIRSTNAME)))
                        .last(cursor.getString(cursor.getColumnIndex(COLUMN_LASTNAME)))
                        .address(address)
                        .build();
                photo.add(photos);
            }while(cursor.moveToNext());
            return photo;
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
