package com.example.alpha;

import androidx.appcompat.app.AlertDialog;
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

import java.util.Date;
import java.util.List;


public class StudentRegistrationActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    TextView datalist;
    TextView datalist_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_registration);

        databaseHelper = new DatabaseHelper(StudentRegistrationActivity.this);
        Button delete = findViewById(R.id.delete_data);
        Button insert = findViewById(R.id.insert_data);
        Button update = findViewById(R.id.update_data);
        Button read = findViewById(R.id.refresh_data);
        datalist = findViewById(R.id.all_data_list);
        datalist_count = findViewById(R.id.data_list_count);

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();

            }
        });

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)  {
                ShowInputDialog();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateIdDialog();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog();
            }
        });

    }

    private void refreshData() {
        datalist_count.setText("ALL DATA COUNT : " + databaseHelper.getTotalCount());

        List<StudentModel> studentModelList = databaseHelper.getAllStudents();
        datalist.setText("");
        for (StudentModel studentModel : studentModelList) {
            datalist.append("ID : " + studentModel.getId() + " | First Name : " + studentModel.getfName() + " | Last Name : " + studentModel.getlName() + " | Program of Study : " + studentModel.getProgramofstudy() + " | Year of Study : " + studentModel.getYearofstudy()+ " | Course Unit : " + studentModel.getCourseunit()+ " | Residence : " + studentModel.getResidence() + " \n\n");
        }
    }

    private void showDeleteDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(StudentRegistrationActivity.this);
        View view = getLayoutInflater().inflate(R.layout.delete_dialog, null);
        al.setView(view);
        final EditText id_input = view.findViewById(R.id.id_input);
        Button delete_btn = view.findViewById(R.id.delete_btn);
        final AlertDialog alertDialog = al.show();

        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteStudent(id_input.getText().toString());
                alertDialog.dismiss();
                refreshData();

            }
        });


    }

    private void showUpdateIdDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(StudentRegistrationActivity.this);
        View view = getLayoutInflater().inflate(R.layout.update_id_dialog, null);
        al.setView(view);
        final EditText id_input = view.findViewById(R.id.id_input);
        Button fetch_btn = view.findViewById(R.id.update_id_btn);
        final AlertDialog alertDialog = al.show();
        fetch_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDataDialog(id_input.getText().toString());
                alertDialog.dismiss();
                refreshData();
            }
        });

    }

    private void showDataDialog(final String id) {
        StudentModel studentModel = databaseHelper.getStudent(Integer.parseInt(id));
        AlertDialog.Builder al = new AlertDialog.Builder(StudentRegistrationActivity.this);
        View view = getLayoutInflater().inflate(R.layout.update_dialog, null);
        final EditText fname = view.findViewById(R.id.fname);
        final EditText lname = view.findViewById(R.id.lname);
        final EditText residence = view.findViewById(R.id.resdidence);
        final EditText cu = view.findViewById(R.id.cu);
        final EditText year = view.findViewById(R.id.year);
        final EditText prog = view.findViewById(R.id.prog);

        Button update_btn = view.findViewById(R.id.update_btn);
        al.setView(view);

        fname.setText(studentModel.getfName());
        lname.setText(studentModel.getlName());
        prog.setText(studentModel.getProgramofstudy());
        year.setText(studentModel.getYearofstudy());
        cu.setText(studentModel.getCourseunit());
        residence.setText(studentModel.getResidence());

        final AlertDialog alertDialog = al.show();
        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel studentModel = new StudentModel();
                studentModel.setfName(fname.getText().toString());
                studentModel.setlName(lname.getText().toString());
                studentModel.setId(id);
                studentModel.setProgramofstudy(prog.getText().toString());
                studentModel.setYearofstudy(year.getText().toString());
                studentModel.setCourseunit(cu.getText().toString());
                studentModel.setResidence(residence.getText().toString());

                databaseHelper.updateStudent(studentModel);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }

    private void ShowInputDialog() {
        AlertDialog.Builder al = new AlertDialog.Builder(StudentRegistrationActivity.this);
        View view = getLayoutInflater().inflate(R.layout.insert_dialog, null);
        final EditText fname = view.findViewById(R.id.fname);
        final EditText lname = view.findViewById(R.id.lname);
        final EditText cu = view.findViewById(R.id.cu);
        final EditText residence = view.findViewById(R.id.resdidence);
        final EditText prog = view.findViewById(R.id.prog);
        final EditText year = view.findViewById(R.id.year);

        Button insertBtn = view.findViewById(R.id.insert_btn);
        al.setView(view);

        final AlertDialog alertDialog = al.show();

        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StudentModel studentModel = new StudentModel();
                studentModel.setfName(fname.getText().toString());
                studentModel.setlName(lname.getText().toString());
                studentModel.setProgramofstudy(prog.getText().toString());
                studentModel.setYearofstudy(year.getText().toString());
                studentModel.setCourseunit(cu.getText().toString());
                studentModel.setResidence(residence.getText().toString());
                Date date = new Date();
                studentModel.setCreated_at("" + date.getTime());
                databaseHelper.AddStudnet(studentModel);
                alertDialog.dismiss();
                refreshData();
            }
        });
    }
}
//
//    DBhelper dBhelper;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student_registration);
//        dBhelper = new DBhelper(this);
//
//
//        Button savebtn = (Button) findViewById(R.id.regbtn);
//        TextView logout = (TextView) findViewById(R.id.logout);
//        TextView copyright = (TextView) findViewById(R.id.copyright);
//        copyright.setTextColor(Color.BLUE);
//
//        savebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), StudentDetailsActivity.class);
//
//                EditText fname = (EditText) findViewById(R.id.fname);
//                EditText lname = (EditText) findViewById(R.id.email);
//                EditText progstudy = (EditText) findViewById(R.id.pwd);
//                EditText courseunit = (EditText) findViewById(R.id.cu);
//                EditText residence = (EditText) findViewById(R.id.address);
//                EditText yearstudy = (EditText) findViewById(R.id.year);
//
//                String FFname = fname.getText().toString();
//                String LLname = lname.getText().toString();
//                String ppstudy = progstudy.getText().toString();
//                String cuu = courseunit.getText().toString();
//                String aaddress = residence.getText().toString();
//                String yystudy = yearstudy.getText().toString();
//
//                if (FFname.equals("") || LLname.equals("") || ppstudy.equals("") || cuu.equals("") || aaddress.equals("") || yystudy.equals("")) {
//                    Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
//                    return;
//                } else {
//                    Boolean addStudent = dBhelper.addStudent(FFname,LLname,ppstudy,cuu,aaddress,yystudy);
//                    if (addStudent) {
//                        Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
//                        fname.setText("");
//                        lname.setText("");
//                        progstudy.setText("");
//                        courseunit.setText("");
//                        residence.setText("");
//                        yearstudy.setText("");
//
//                        Intent studentDetails = new Intent(getApplicationContext(), StudentDetailsActivity.class);
//
//                        startActivity(studentDetails);
//                    }
//                }
////                intent.putExtra("Fname", FFname);
////                intent.putExtra("Lname", LLname);
////                intent.putExtra("pstudy", ppstudy);
////                intent.putExtra("cu", cuu);
////                intent.putExtra("address", aaddress);
////                intent.putExtra("ystudy", yystudy);
////                startActivity(intent);
//            }
//        });
//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent logout = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(logout);
//            }
//        });
//
//    }
//}