package com.assignmenttp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.assignmenttp.domain.employees.photo.Photo;
import com.assignmenttp.repository.employees.photo.PhotoRepository;
import com.assignmenttp.repository.employees.photo.implem.PhotoReapositoryImpem;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DisplayPhotoTaker extends AppCompatActivity {


    private Set<Photo> photoSet;
    private ArrayAdapter adapter;
    private ListView listView;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_photo_taker);

        PhotoRepository photoRepository = new PhotoReapositoryImpem(this.getApplicationContext());

        photoSet = new HashSet<>();
        try {
            photoSet = photoRepository.findAll();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        Iterator<Photo> photoIterator = photoSet.iterator();
        if (photoSet.size() > 0) {
            names = new String[photoSet.size()];
            // id = new String[personSet.size()];
            int i = 0;

            while (photoIterator.hasNext()) {
                // id[i]= String.valueOf(itPerson.next().getId());
                names[i] = photoIterator.next().getFirstName();
                i++;
            }

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
            //adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2,id);

            listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            //listView.setAdapter(adapter1);
        } else {
            Toast.makeText(DisplayPhotoTaker.this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }
    public void returnToStart(View view) {

        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);

    }
}
