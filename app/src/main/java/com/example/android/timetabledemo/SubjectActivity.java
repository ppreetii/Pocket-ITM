package com.example.android.timetabledemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.timetabledemo.Utils.LetterImageView;

public class SubjectActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView listView;
    String[] subjects;
    public static SharedPreferences subjectPreferences;
    public static String SUB_PREF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        setupUIViews();
        initToolbar();
        setupListView();
    }
    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarSubject);
        listView = (ListView)findViewById(R.id.lvSubject);
        subjectPreferences=getSharedPreferences("Subject",MODE_PRIVATE);
    }
    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Subjects");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void setupListView(){
         subjects = getResources().getStringArray(R.array.Subjects);

        SubjectAdapter adapter = new SubjectAdapter(this, R.layout.subject_single_list_item, subjects);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position){
                    case 0: {
                        startActivity(new Intent(SubjectActivity.this, SubjectDetailsActivity.class));
                        subjectPreferences.edit().putString(SUB_PREF, "OB").apply();
                        break;
                    }
                    case 1: {
                        startActivity(new Intent(SubjectActivity.this, SubjectDetailsActivity.class));
                        subjectPreferences.edit().putString(SUB_PREF, "Accounting").apply();
                        break;
                    }
                    case 2: {
                        startActivity(new Intent(SubjectActivity.this, SubjectDetailsActivity.class));
                        subjectPreferences.edit().putString(SUB_PREF, "OperationResearch").apply();
                        break;
                    }
                    case 3: {
                        startActivity(new Intent(SubjectActivity.this, SubjectDetailsActivity.class));
                        subjectPreferences.edit().putString(SUB_PREF, "English").apply();
                        break;
                    }
                    case 4: {
                        startActivity(new Intent(SubjectActivity.this, SubjectDetailsActivity.class));
                        subjectPreferences.edit().putString(SUB_PREF, "VC++").apply();
                        break;
                    }
                    case 5: {
                        startActivity(new Intent(SubjectActivity.this, SubjectDetailsActivity.class));
                        subjectPreferences.edit().putString(SUB_PREF, "AdvJava").apply();
                        break;
                    }
                    default:break;
                }
            }
        });

    }
    public class SubjectAdapter extends ArrayAdapter {

        private int resource;
        private LayoutInflater layoutInflater;
        private String[] subjects = new String[]{};

        public SubjectAdapter(Context context, int resource, String[] objects) {
            super(context, resource, objects);
            this.resource = resource;
            this.subjects = objects;
            layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null){
                holder = new ViewHolder();
                convertView = layoutInflater.inflate(resource, null);
                holder.ivLogo = (LetterImageView)convertView.findViewById(R.id.ivLetterSubject);
                holder.tvSubject = (TextView)convertView.findViewById(R.id.tvSubject);
                convertView.setTag(holder);
            }else{
                holder = (ViewHolder)convertView.getTag();
            }

            holder.ivLogo.setOval(true);
            holder.ivLogo.setLetter(subjects[position].charAt(0));
            holder.tvSubject.setText(subjects[position]);

            return convertView;
        }

        class ViewHolder{
            private LetterImageView ivLogo;
            private TextView tvSubject;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case android.R.id.home : {
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
