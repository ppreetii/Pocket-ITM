package com.example.android.timetabledemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class SubjectDetailsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_details);
        setupUIViews();
        initToolbar();
        setupListview();
    }
    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarSubjectDetails);
        listView = (ListView)findViewById(R.id.lvSubjectDetails);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupListview()
    {
        String subject_seleted=SubjectActivity.subjectPreferences.getString(SubjectActivity.SUB_PREF,null);
        String[] title=getResources().getStringArray(R.array.title);
        String[] syllabus=new String[]{};
        if (subject_seleted.equalsIgnoreCase("OB"))
        {
            syllabus=getResources().getStringArray(R.array.OB);
        }
        else if (subject_seleted.equalsIgnoreCase("Accounting"))
        {
            syllabus=getResources().getStringArray(R.array.Accounting);
        }
        else if (subject_seleted.equalsIgnoreCase("OperationResearch"))
        {
            syllabus=getResources().getStringArray(R.array.OperationResearch);
        }
        else if (subject_seleted.equalsIgnoreCase("English"))
        {
            syllabus=getResources().getStringArray(R.array.English);
        }
        else if (subject_seleted.equalsIgnoreCase("VC"))
        {
            syllabus=getResources().getStringArray(R.array.VC);
        }

        else if (subject_seleted.equalsIgnoreCase("AdvJava"))
        {
            syllabus=getResources().getStringArray(R.array.AdvJava);
        }

        SubjectDetailsAdapter subjectDetailsAdapter=new SubjectDetailsAdapter(this,title,syllabus);
        listView.setAdapter(subjectDetailsAdapter);

    }
    public class SubjectDetailsAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater layoutInflater;
        private TextView title, syllabus;
        private String[] titleArray;
        private String[] syllabusArray;

        public SubjectDetailsAdapter(Context context, String[] title, String[] syllabus){
            mContext = context;
            titleArray = title;
            syllabusArray = syllabus;
            layoutInflater = LayoutInflater.from(context);
        }


        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = layoutInflater.inflate(R.layout.subject_details_single_item, null);
            }

            title = (TextView)convertView.findViewById(R.id.tvSubjectTitle);
            syllabus = (TextView)convertView.findViewById(R.id.tvSyllabus);

            title.setText(titleArray[position]);
            syllabus.setText(syllabusArray[position]);


            return convertView;

        }
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
