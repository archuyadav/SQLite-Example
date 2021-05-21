package com.example.sqliteex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Name, Contact, dob;
    Button insert, update, delete, viewData;
Helper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name=findViewById(R.id.Name);
        Contact=findViewById(R.id.contact);
        dob=findViewById(R.id.dob);
        insert=findViewById(R.id.insert);
        update=findViewById(R.id.update);
        delete=findViewById(R.id.delete);
        viewData=findViewById(R.id.view);
helper=new Helper(this);
insert.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String nameText=Name.getText().toString();
        String contactText=Contact.getText().toString();
        String dobText=dob.getText().toString();

        Boolean checkInsert=helper.insert(nameText,contactText,dobText);
        if (checkInsert==true)
            Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
  else
            Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_SHORT).show();
    }
});

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText=Name.getText().toString();
                String contactText=Contact.getText().toString();
                String dobText=dob.getText().toString();

                Boolean checkupdate=helper.update(nameText,contactText,dobText);
                if (checkupdate==true)
                    Toast.makeText(MainActivity.this, " Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Not Updated", Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameText=Name.getText().toString();
//                String contactText=Contact.getText().toString();
//                String dobText=dob.getText().toString();

                Boolean checkDelete=helper.delete(nameText);
                if (checkDelete==true)
                    Toast.makeText(MainActivity.this, " Entry Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Not Deleted", Toast.LENGTH_SHORT).show();
            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=helper.viewdata();
                if (res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                return;
                }
                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Contact :"+res.getString(1)+"\n");
                    buffer.append("Date of Birth :"+res.getString(2)+"\n\n");
                }
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();

            }
        });



    }
}