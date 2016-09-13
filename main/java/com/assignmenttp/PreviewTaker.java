package com.assignmenttp;

import android.content.Intent;
import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.assignmenttp.domain.address.Address;
import com.assignmenttp.domain.employees.photo.Photo;
import com.assignmenttp.repository.employees.photo.PhotoRepository;
import com.assignmenttp.repository.employees.photo.implem.PhotoReapositoryImpem;

public class PreviewTaker extends AppCompatActivity {
    private TextView fName;
    private TextView lName;
    private TextView streetName;
    private TextView postalAddress;
    private TextView pCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview_taker);

        fName =(TextView)findViewById(R.id.firstName);
        lName =(TextView)findViewById(R.id.lastName);
        streetName =(TextView)findViewById(R.id.streetname);
        postalAddress =(TextView)findViewById(R.id.postalAddress);
        pCode =(TextView)findViewById(R.id.code);

        Intent i = getIntent();

        Bundle b = i.getExtras();

        if(b!=null) {
            String fname = (String)b.get("firstName");
            fName.setText(fname);

            String lname =(String)b.get("lastName");
            lName.setText(lname);

            String street=(String)b.get("streetName");
            streetName.setText(street);

            String address=(String)b.get("postalAddress");
            postalAddress.setText(address);

            String code=(String)b.get("postalCode");
            pCode.setText(code);
        }
    }
    public void btnSubmit(View view) throws InterruptedException, java.sql.SQLException {
        PhotoRepository repo=new PhotoReapositoryImpem(this.getApplicationContext());
        Address address=new Address.Builder()
                .streetName(streetName.toString())
                .suburb(postalAddress.toString())
                .postalCode(pCode.toString())
                .build();
        Photo party=new Photo.Builder()
                .first(fName.toString())
                .last(lName.toString())
                .address(address)
                .build();


        Photo inserted = repo.save(party);

        Intent intent = new Intent(this, DisplayPhotoTaker.class);

        startActivity(intent);

    }
}
