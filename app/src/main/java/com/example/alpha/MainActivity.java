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
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dBhelper = new DBhelper(this);

        Button loginbtn = (Button) findViewById(R.id.loginbtn);
        TextView regbtn = (TextView) findViewById(R.id.regbtn);
        regbtn.setTextColor(Color.BLUE);
        TextView copyright = (TextView) findViewById(R.id.copyright);
        copyright.setTextColor(Color.BLUE);


        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText email = (EditText) findViewById(R.id.email);
                EditText pwd = (EditText) findViewById(R.id.pwd);

                String em = email.getText().toString();
                String ps = pwd.getText().toString();

                if (em.equals("") || ps.equals("")) {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Boolean checkLogin = dBhelper.CheckLogin(em,ps);
                    if (checkLogin) {
                        Intent login = new Intent(getApplicationContext(), StudentRegistrationActivity.class);

                        startActivity(login);
                    }
                }
            }
        });

        copyright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("https://elywin.github.io/"));
                startActivity(intent);
            }
        });
    }
}