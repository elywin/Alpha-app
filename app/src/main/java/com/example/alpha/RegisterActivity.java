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

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dBhelper = new DatabaseHelper(this);

        TextView lgbtn = (TextView) findViewById(R.id.lgbtn);
        Button regbtn = (Button) findViewById(R.id.regbtn);
        lgbtn.setTextColor(Color.BLUE);
        TextView copyright = (TextView) findViewById(R.id.copyright);
        copyright.setTextColor(Color.BLUE);

        lgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText email = (EditText) findViewById(R.id.email);
                EditText pwd = (EditText) findViewById(R.id.pwd);
                EditText cpwd = (EditText) findViewById(R.id.cpwd);

                String em = email.getText().toString();
                String ps = pwd.getText().toString();
                String cps = cpwd.getText().toString();

                if (em.equals("") || ps.equals("") || cps.equals("")) {
                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    if (ps.equals(cps)) {
                        Boolean checkusername = dBhelper.CheckUsername(em);
                        if (checkusername) {
                            Boolean insert = dBhelper.Insert(em, ps);
                            if (insert) {
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                                email.setText("");
                                pwd.setText("");
                                cpwd.setText("");

                                Intent register = new Intent(getApplicationContext(), MainActivity.class);

                                startActivity(register);
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email already taken", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_SHORT).show();
                    }
                }
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