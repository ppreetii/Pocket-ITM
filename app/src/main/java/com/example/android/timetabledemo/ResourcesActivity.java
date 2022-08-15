package com.example.android.timetabledemo;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResourcesActivity extends AppCompatActivity {

    ArrayList<String> urlArray;
    ArrayList<String>nameArray;
    private Toolbar toolbar;
    private ListView listView;
    private ProgressDialog progressDialog;
    private DownloadManager downloadManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Wait a moment...");

          setupUIViews();
          initToolbar();
          setupListView();

          listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                  downloadManager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                  Uri uri=Uri.parse(urlArray.get(i));
                  DownloadManager.Request request=new DownloadManager.Request(uri);
                  request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
                  Long reference =downloadManager.enqueue(request);
                  Toast.makeText(ResourcesActivity.this, "File will be stored in DOWNLOAD folder of your phone.Check the progress there.", Toast.LENGTH_LONG).show();
              }
          });


    }

    private void setupListView() {
        progressDialog.show();
        urlArray=new ArrayList<>();
        nameArray=new ArrayList<>();
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference().child("download");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    for (DataSnapshot child:dataSnapshot.getChildren()){
                        notesUrl notes=child.getValue(notesUrl.class);
                        nameArray.add(notes.getName());
                        urlArray.add(notes.getUrl());
                    }
                       // arr.add(child.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ArrayAdapter aa=new ArrayAdapter(ResourcesActivity.this,android.R.layout.simple_list_item_1,nameArray);
        listView.setAdapter(aa);
        aa.notifyDataSetChanged();
        progressDialog.dismiss();
    }

    private void setupUIViews(){
        toolbar = (Toolbar)findViewById(R.id.ToolbarNotes);
        listView = (ListView)findViewById(R.id.lvNotes);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Resources");
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
