package com.example.android.timetabledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class FacultyDetailsActivity extends AppCompatActivity {
private CircleImageView facultyImage;
private Toolbar toolbar;
private TextView facultyName,phoneNumber,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty_details);
        setupUIViews();
        initToolbar();
        setupDetails();
    }
    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarFacultyDetails);
        facultyImage=(CircleImageView)findViewById(R.id.ivFaculty);
        facultyName=(TextView)findViewById(R.id.tvFacultySelName);
        phoneNumber=(TextView)findViewById(R.id.tvPhoneNumber);
        email=(TextView)findViewById(R.id.tvEmail);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Faculty Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupDetails()
    {
        int faculty_pos=FacultyActivity.sharedPreferences.getInt(FacultyActivity.SEL_FACULTY,0);
        String[] facultyNames=getResources().getStringArray(R.array.faculty_name);
        int[] facultyArray=new int[]{R.array.Suryakant,R.array.Deepak,R.array.Ahuti,R.array.Pinaki,R.array.Chinmay,R.array.Ganesha,R.array.Mamina,R.array.Lk,R.array.Smruti,R.array.Bapi};
        int[] facultyImages=new int[]{R.drawable.suryakant,R.drawable.deepak,R.drawable.ahuti,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.drawable.ganesh,R.mipmap.ic_launcher,R.mipmap.ic_launcher,R.drawable.director,R.drawable.bapi};
        String[] facultyDetails=getResources().getStringArray(facultyArray[faculty_pos]);
        phoneNumber.setText(facultyDetails[0]);
        email.setText(facultyDetails[1]);
        facultyImage.setImageResource(facultyImages[faculty_pos]);
        facultyName.setText(facultyNames[faculty_pos]);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
