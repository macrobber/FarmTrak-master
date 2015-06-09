package com.primenumberfarms.farmtrak;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;


public class CattleUpdateDetails extends ActionBarActivity {
    private String uid;
    private String Tag;
    private EditText Name;
    private EditText RegNumber;
    private EditText Gender;
    private EditText ElectronicID;
    private EditText Owner;
    private EditText PercentagePure;
    private EditText DryMatterIntake;
    private EditText BirthDate;
    private EditText BirthWeight;
    private EditText WeaningDate;
    private EditText WeaningWeight;
    private EditText YearlingWeight;
    private EditText Breeder;
    private EditText Conception;
    private EditText SirTag;
    private EditText DamTag;
    private EditText HornStatus;
    private EditText Breed;
    private EditText BodyScore;
    private EditText CalvingDate;
    private EditText AmountPaid;
    private EditText AmountSold;
    private EditText ColorMarkings;
    private EditText sTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cattle_update_details);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            return;
        }

        this.uid = extras.getString("uid");
        this.Tag = extras.getString("Tag");

        this.Name = (EditText) this.findViewById(R.id.Name);
        this.RegNumber = (EditText) this.findViewById(R.id.RegNumber);
        this.sTag = (EditText) this.findViewById(R.id.Tag);

// Get these two working then add the others....

        if(this.Tag != null)
        {
            // we know we passed a tag....like...maybe???
            new GetCowDetails().execute(new CattleApiConnector());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cattle_update_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==android.R.id.home) {
            onBackPressed();
            finish();

            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    private class GetCowDetails extends AsyncTask<CattleApiConnector, Long, JSONArray> {
        @Override
        protected JSONArray doInBackground(CattleApiConnector... params) {
            // This is executed in the background thread
            return params[0].GetCowDetails(Tag, uid);
        }

        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
            try {
                JSONObject cow = jArray.getJSONObject(0);
                sTag.setText(cow.getString("Tag"));
                Name.setText(cow.getString("Name"));
                RegNumber.setText(cow.getString("RegNumber"));
                ElectronicID.setText(cow.getString("ElectronicID"));
                Owner.setText(cow.getString("Owner"));
                PercentagePure.setText(cow.getString("PercentagePure"));
                ColorMarkings.setText(cow.getString("Color"));
                DryMatterIntake.setText(cow.getString("DryMatterIntake"));
                BirthDate.setText(cow.getString("BirthDate"));
                BirthWeight.setText(cow.getString("BirthWeight"));
                WeaningDate.setText(cow.getString("WeaningDate"));
                WeaningWeight.setText(cow.getString("WeaningWeight"));
                YearlingWeight.setText(cow.getString("YearlingWeight"));
                Breeder.setText(cow.getString("Breeder"));
                Conception.setText(cow.getString("Conception"));
                SirTag.setText(cow.getString("SirTag"));
                DamTag.setText(cow.getString("DamTag"));
                HornStatus.setText(cow.getString("HornStatus"));
                Breed.setText(cow.getString("Breed"));
                BodyScore.setText(cow.getString("BodyScore"));
                CalvingDate.setText(cow.getString("CalvingDate"));
                AmountPaid.setText(cow.getString("AmountPaid"));
                AmountSold.setText(cow.getString("AmountSold"));
                Gender.setText(cow.getString("Gender"));


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }


        // End
}
