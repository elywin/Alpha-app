package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class StudentRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);
        Button savebtn = (Button) findViewById(R.id.regbtn);
        TextView logout = (TextView) findViewById(R.id.logout);
        TextView copyright = (TextView) findViewById(R.id.copyright);
        copyright.setTextColor(Color.BLUE);

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentDetailsActivity.class);

                EditText fname = (EditText) findViewById(R.id.fname);
                EditText lname = (EditText) findViewById(R.id.email);
                EditText progstudy = (EditText) findViewById(R.id.pwd);
                EditText courseunit = (EditText) findViewById(R.id.cu);
                EditText residence = (EditText) findViewById(R.id.address);
                EditText yearstudy = (EditText) findViewById(R.id.year);

                String FFname = fname.getText().toString();
                String LLname = lname.getText().toString();
                String ppstudy = progstudy.getText().toString();
                String cuu = courseunit.getText().toString();
                String aaddress = residence.getText().toString();
                String yystudy = yearstudy.getText().toString();

                intent.putExtra("Fname", FFname);
                intent.putExtra("Lname", LLname);
                intent.putExtra("pstudy", ppstudy);
                intent.putExtra("cu", cuu);
                intent.putExtra("address", aaddress);
                intent.putExtra("ystudy", yystudy);
                startActivity(intent);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(logout);
            }
        });

        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);

                intent.setData(Uri.parse("https://elywin.github.io/"));

                startActivity(intent);
            }
        });
    }
}