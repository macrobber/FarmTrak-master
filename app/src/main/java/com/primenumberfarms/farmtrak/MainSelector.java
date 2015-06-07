package com.primenumberfarms.farmtrak;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.json.JSONArray;
import org.json.JSONObject;


public class MainSelector extends ActionBarActivity {

    private String email;
    private String pw;
    private String uid;
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    FrameLayout progressBarHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_selector);



        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);

        Bundle extras = getIntent().getExtras();
            if(extras == null) {
                return;
            }

        this.email = extras.getString("Email");
        this.pw = extras.getString("PW");

//        Toast.makeText(getApplicationContext(), sPW, Toast.LENGTH_SHORT).show();
//        TextView textView1 = (TextView) findViewById(R.id.testView1);
//        TextView textView2 = (TextView) findViewById(R.id.testView2);
//        textView1.setText(sEmail);
//        textView1.setText(this.email);

        //this.Name = (EditText) this.findViewById(R.id.Name);
        //this.Brand = (EditText) this.findViewById(R.id.Brand);
//        this.RegNumber = (EditText) this.findViewById(R.id.RegNumber);


        inAnimation = new AlphaAnimation(0f, 1f);
        inAnimation.setDuration(2000);
        progressBarHolder.setAnimation(inAnimation);
        progressBarHolder.setVisibility(View.VISIBLE);


        if(this.email != null)
        {
            // we know we passed a tag....like...maybe???
            new MyLogin().execute(new CattleApiConnector());

        }
//        textView2.setText(sPW);

//        uid = textView2.getText().toString();


//        String modifyText;
//        modifyText = textView2.getText().toString();

//        Toast.makeText(getApplicationContext(), this.uid, Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(), uid, Toast.LENGTH_SHORT).show();


/*  ******************************************************************************
    Below is the click code per each image
********************************************************************************* */
/*
        ImageView imgCattle = (ImageView) findViewById(R.id.imageView4);
        imgCattle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Intent intent = new Intent(getApplicationContext(), CattleManagement.class);

                                startActivity(intent); // spawn the activity
                                } // closes OnClick public void
                   }  // closes setonclick listener
                );  // closes setonclick listener

*/
/*  ******************************************************************************
    End of the click code per each image
********************************************************************************* */


//        Toast.makeText(getApplicationContext(), "Yup...you clicked it.", Toast.LENGTH_SHORT).show();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_selector, menu);
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

        return super.onOptionsItemSelected(item);
    }

    private class MyLogin extends AsyncTask<CattleApiConnector, Long, JSONArray> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(200);
            progressBarHolder.setAnimation(inAnimation);
            progressBarHolder.setVisibility(View.VISIBLE);
        }

        @Override
        protected JSONArray doInBackground(CattleApiConnector... params) {
            // This is executed in the background thread
//            return params[0].GetCowDetails(uid);
               return params[0].MyLogin(email, pw, uid);
        }

 /*
        @Override void onPreExecute() {
            super.onPreExecute();
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(200);
            progressBarHolder.setAnimation(inAnimation);
            progressBarHolder.setVisibility(View.VISIBLE);
        }
*/

        @Override
        protected void onPostExecute(JSONArray jArray) {
            // once do in background is done - it sends the result to the main thread...here
//            JSONObject cow = jArray.getJSONObject(0);
            try {
                JSONObject cow = jArray.getJSONObject(0);
                outAnimation = new AlphaAnimation(1f, 0f);
                outAnimation.setDuration(200);
                progressBarHolder.setAnimation(outAnimation);
                progressBarHolder.setVisibility(View.GONE);



                uid = (cow.getString("uid"));


                ImageView imgCattle = (ImageView) findViewById(R.id.imageView4);
                imgCattle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), CattleManagement.class);

                        intent.putExtra("uid", uid);
                        startActivity(intent); // spawn the activity
                    } // closes OnClick public void
                }  // closes setonclick listener
                );  // closes setonclick listener

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}
