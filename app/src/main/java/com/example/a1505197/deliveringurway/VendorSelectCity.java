package com.example.a1505197.deliveringurway;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import de.hdodenhof.circleimageview.CircleImageView;

public class VendorSelectCity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    String city;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_select_city);
        Spinner spinner=findViewById(R.id.city_spinner);
        spinner.setSelection(0);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.city_list_spinner, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

       // spinnerCreated();
    }



//    public void spinnerCreated() {
//
//
//}

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       city=parent.getItemAtPosition(position).toString();
       // Toast.makeText(getApplicationContext(),city,Toast.LENGTH_SHORT).show();

        CircleImageView ArrowCity=(CircleImageView)findViewById(R.id.arrow_city);
        ArrowCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(city.equals("Bhubaneswar")){
                    Intent intent=new Intent(v.getContext(),VendorInfo2.class);
                    startActivity(intent);
                }
            }
        });


//    void takeNameandNumber(String s){
//            if(city.equals("Bhubaneswar")){
//                Intent intent=new Intent(this,VendorInfo2.class);
//                startActivity(intent);
//            }
//        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
//