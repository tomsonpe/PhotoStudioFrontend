package com.assignmenttp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddPhotoTaker extends AppCompatActivity {
    private EditText firstName;
    private EditText lastName;
    private EditText streetName;
    private EditText postalAddress;
    private EditText postalCode;

    private Button previewButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo_taker);

        firstName =(EditText)findViewById(R.id.editText);
        lastName = (EditText)findViewById(R.id.editText2);
        streetName = (EditText)findViewById(R.id.editText3);
        postalAddress = (EditText)findViewById(R.id.editText4);
        postalCode = (EditText)findViewById(R.id.editText5);

        previewButton = (Button)findViewById(R.id.btnPreview);

        previewButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), PreviewTaker.class);
                i.putExtra("firstName",firstName.getText().toString());
                i.putExtra("lastName",lastName.getText().toString());
                i.putExtra("StreetName",streetName.getText().toString());
                i.putExtra("PostalAddress",postalAddress.getText().toString());
                i.putExtra("PostalCode",postalCode.getText().toString());
                startActivity(i);

            }
        });
    }
}
