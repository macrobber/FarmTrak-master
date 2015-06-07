package com.primenumberfarms.farmtrak;

import android.content.Intent;
//import android.support.v7.app.ActionBarActivity;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class CattleManagement extends AppCompatActivity {

    private String uid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cattle_management);

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            return;
        }

        this.uid = extras.getString("uid");
        Toast.makeText(getApplicationContext(), this.uid, Toast.LENGTH_SHORT).show();


        TextView txtShowAllCows = (TextView) findViewById(R.id.testView2);
        TextView txtShowCattle = (TextView) findViewById(R.id.searchCattle);
        TextView txtAddCattle = (TextView) findViewById(R.id.AddCattle);

// ************* Block for onClick Listener ***********************
        txtShowAllCows.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ShowAllCattle.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        }));
// ************* Close this block

// ************* Block for onClick Listener ***********************
        txtShowCattle.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchCattle.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }

        }));
// ************* Close this block
// ************* Block for onClick Listener ***********************
        txtAddCattle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CattleAdd.class);
                intent.putExtra("uid", uid);
                startActivity(intent);
            }
        });


// ************* Close this block

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cattle_management, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if(id==android.R.id.home) {
            onBackPressed();
            return true;
        }

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
//        if(id==android.R.id.home) {
//            finish();
//        }


        return super.onOptionsItemSelected(item);
    }
}
