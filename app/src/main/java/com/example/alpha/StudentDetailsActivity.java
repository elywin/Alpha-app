package com.example.alpha;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class StudentDetailsActivity extends AppCompatActivity {
    ContentValues val=new ContentValues();
    ListView list;
    String arruser[],arrpass[];
    SQLiteDatabase dBhelper;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);
        list = (ListView)findViewById(R.id.listView1);
        dBhelper = openOrCreateDatabase("student.db", SQLiteDatabase.CREATE_IF_NECESSARY,null);
        Cursor c = dBhelper.query("student",null,null,null,null,null,null);
        arruser=new String[c.getCount()];
        arrpass=new String[c.getCount()];
        c.moveToFirst();
        for(int i=0;i<arruser.length;i++){
            arruser[i]=c.getString(0);
            arrpass[i]=c.getString(1);

        }

        ArrayAdapter<String>app =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        list.setAdapter(app);

    }

}
//
//
//
//
//
//                Button backbtn = (Button) findViewById(R.id.backbtn);
//        TextView logout = (TextView) findViewById(R.id.logout);
//        TextView copyright = (TextView) findViewById(R.id.copyright);
//        copyright.setTextColor(Color.BLUE);
//        Button callbtn = (Button) findViewById(R.id.callbtn);
//
//        Intent intent = getIntent();
//        TextView fn = findViewById(R.id.fname2);
//        TextView ln = findViewById(R.id.lnamev);
//        TextView ps = findViewById(R.id.pstudyv);
//        TextView ys = findViewById(R.id.ystdyv);
//        TextView cu = findViewById(R.id.cuv);
//        TextView ad = findViewById(R.id.resiv);
//
//        String fnn = intent.getExtras().getString("Fname");
//        String lnn = intent.getExtras().getString("Lname");
//        String pss = intent.getExtras().getString("pstudy");
//        String cuu = intent.getExtras().getString("cu");
//        String add = intent.getExtras().getString("address");
//        String yss = intent.getExtras().getString("ystudy");
//
//
//        fn.setText(fnn);
//        ln.setText(lnn);
//        ps.setText(pss);
//        ys.setText(yss);
//        cu.setText(cuu);
//        ad.setText(add);
//
//        backbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), StudentRegistrationActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(logout);
//            }
//        });
//
//        copyright.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(Intent.ACTION_VIEW);
//
//                intent.setData(Uri.parse("https://elywin.github.io/"));
//
//                startActivity(intent);
//            }
//        });
//        callbtn.setOnClickListener(new View.OnClickListener() {
//             @Override
//             public void onClick(View v) {
//                   Intent launch = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:123456798"));
//                   startActivity(launch);
//    }
//});



//    }
//
//}