package com.whatever.ghosts.ghosts_n_whatever;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.whatever.ghosts.fragments.QRScanner;
import com.whatever.ghosts.model.Crypt;
import com.whatever.ghosts.model.Location;
import com.whatever.ghosts.model.Village;

public class AddLocationActivity extends AppCompatActivity implements QRScanner.IQRReadValue {

    TextView tvQRCode;
    Button btnAddLocation;

    String codeValue, locationType;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);



        tvQRCode = (TextView) findViewById(R.id.activity_addLocation_tvQRCode);
        btnAddLocation = (Button) findViewById(R.id.activity_addLocation_btnAdd);

        btnAddLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Location location;
                if (locationType.equals("Crypt")){
                    location = new Crypt();
                    location.Name = codeValue;
                } else {
                    location = new Village();
                    location.Name = codeValue;
                }

                DatabaseReference myRef = database.getReference("Games").child(MyApp.gameID).child("Locations").child(locationType).push();
                myRef.setValue(location);
                finish();
            }
        });
    }

    @Override
    public void readValue(String text) {
        tvQRCode.setText(text);
        codeValue = text;
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.activity_addLocation_rbnCrypt:
                if (checked)
                    Toast.makeText(AddLocationActivity.this, "Crypt!!", Toast.LENGTH_SHORT).show();
                locationType = "Crypt";
                    break;
            case R.id.activity_addLocation_rbnVillage:
                if (checked)
                    Toast.makeText(AddLocationActivity.this, "Village!!", Toast.LENGTH_SHORT).show();
                locationType = "Village";
                    break;
        }
    }

}
