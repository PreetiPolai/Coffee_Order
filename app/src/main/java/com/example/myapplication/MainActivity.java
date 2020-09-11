package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
     int noOfCoffees=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee);
    }

    public void submitOrder(View view) {
        CheckBox check = (CheckBox)findViewById(R.id.whippedCream);
        boolean state = check.isChecked();
        CheckBox check1  = (CheckBox)findViewById(R.id.chocolate);
        boolean state1 = check1.isChecked();

        int totalPrice =Price(noOfCoffees);
        if(state){
            totalPrice +=noOfCoffees*10;
        }

        if(state1){
            totalPrice += noOfCoffees*20;
        }


        String message = priceMessage(totalPrice,state,state1);
        Intent i = new Intent(Intent.ACTION_SENDTO);
        i.setData(Uri.parse("mailto:"));
        i.putExtra(Intent.EXTRA_TEXT,message);
        i.putExtra(Intent.EXTRA_SUBJECT,"just java coffee order");
        if(i.resolveActivity(getPackageManager() )!= null){
            startActivity(i);
        }

    }

    private String priceMessage(int totalPrice, boolean state, boolean state1) {
        EditText n = (EditText)findViewById(R.id.name);
         String name = n.getText().toString();
        String message = "NAME:"+name+"\n Quantity : "+noOfCoffees+"\n Add whipped cream ? "+ state +"\n Add Chocolate? "+ state1 +"\n"+"TOTAL: $  "+totalPrice+" \n Thank you!!!";
        return  message;
    }

    private void display(int i) {
        TextView m = (TextView)findViewById(R.id.quantity_text_view);
        m.setText(""+i);
    }

    private void displayMessage(String Message){
        TextView m = (TextView)findViewById(R.id.price_text_view);
        m.setText(Message);

    }

    public void increment(View view) {
        noOfCoffees++;
        display(noOfCoffees);
    }

    public void decrement(View view) {
        if (noOfCoffees > 0)
            noOfCoffees--;
        display(noOfCoffees);
    }
        public int Price(int i){
            return 5*i;
        }
    }

