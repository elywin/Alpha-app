package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentDetailsActivity extends AppCompatActivity {
    DBhelper dBhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        dBhelper = new DBhelper(this);
        Bundle extras = getIntent().getExtras();
        {
            int Value = extras.getInt("id");

            if (Value > 0) {
                //means this is the view part not the add contact part.
                Cursor rs = dBhelper.getAllStudents(Value);
                id_To_Update = Value;
                rs.moveToFirst();

                String stuname = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STUNAME));
                String stuphone = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STUPHONE));
                String stuemail = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STUEMAIL));
                String stustreet = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STUSTREET));
                String stuplace = rs.getString(rs.getColumnIndex(DBHelper.CONTACTS_COLUMN_STUCITY));

                if (!rs.isClosed()) {
                    rs.close();
                }

                Button backbtn = (Button) findViewById(R.id.backbtn);
        TextView logout = (TextView) findViewById(R.id.logout);
        TextView copyright = (TextView) findViewById(R.id.copyright);
        copyright.setTextColor(Color.BLUE);
        Button callbtn = (Button) findViewById(R.id.callbtn);

        Intent intent = getIntent();
        TextView fn = findViewById(R.id.fname2);
        TextView ln = findViewById(R.id.lnamev);
        TextView ps = findViewById(R.id.pstudyv);
        TextView ys = findViewById(R.id.ystdyv);
        TextView cu = findViewById(R.id.cuv);
        TextView ad = findViewById(R.id.resiv);

        String fnn = intent.getExtras().getString("Fname");
        String lnn = intent.getExtras().getString("Lname");
        String pss = intent.getExtras().getString("pstudy");
        String cuu = intent.getExtras().getString("cu");
        String add = intent.getExtras().getString("address");
        String yss = intent.getExtras().getString("ystudy");


        fn.setText(fnn);
        ln.setText(lnn);
        ps.setText(pss);
        ys.setText(yss);
        cu.setText(cuu);
        ad.setText(add);

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), StudentRegistrationActivity.class);
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
        callbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                   Intent launch = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:123456798"));
                   startActivity(launch);
    }
});



    }

}