package com.tricloudcommunications.ce.famgram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.util.Log;
import android.widget.Switch;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Start with: Create Objects, Query Objects, Update Objects
        /**
         //Posting Objects to the server
         ParseObject score = new ParseObject("Score");
         score.put("userName", "Chandler");
         score.put("score", 86);
         score.saveInBackground(new SaveCallback() {
        @Override
        public void done(ParseException e) {

        if (e == null){

        Log.i("SaveInBackground", "Successful");

        }else {

        Log.i("SaveInBackground", "Failed. Error: " + e.toString());

        }

        }
        });


         //Read data from the server
         ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
         query.getInBackground("51mGXYtZwc", new GetCallback<ParseObject>() {
        @Override
        public void done(ParseObject object, ParseException e) {

        if (e == null && object != null){

        //We can also update objects on the server
        object.put("score", 200);
        object.saveInBackground();

        Log.i("ObjectValue", object.getString("userName"));
        Log.i("ObjectValue", Integer.toString(object.getInt("score")));

        }


        }
        });
         */


        //Challange: Create a Tweet class; with a username and tweet objects; save on Parse; then query it; and update the tweet object content
        /**
         ParseObject myTweetObject = new ParseObject("Tweet");
         myTweetObject.put("userName", "Chandler");
         myTweetObject.put("tweets", "I like parse-server. I seems really cool. Can't wait to build!");
         myTweetObject.saveInBackground(new SaveCallback() {
        @Override
        public void done(ParseException e) {

        if (e == null){

        Log.i("SaveInBackgroud", "Successfully saved");

        }else{

        Log.i("SaveInBackground", "Failed. Error: " + e.toString());

        }

        }
        });


         //Query the Tweet Class
         ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Tweet");
         query.getInBackground("qcTz8lVRA1", new GetCallback<ParseObject>() {
        @Override
        public void done(ParseObject object, ParseException e) {

        if (e == null && object != null){

        Log.i("ObjectValue", object.getString("userName"));
        Log.i("ObjectValue", object.getString("tweets"));

        }

        }
        });


         //Update the Tweet Class
         ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Tweet");
         query.getInBackground("qcTz8lVRA1", new GetCallback<ParseObject>() {
        @Override
        public void done(ParseObject object, ParseException e) {

        if (e == null && object != null){

        object.put("tweets", "I wounder if we will be using Parse for the rest of the projects in this course");
        object.saveInBackground();

        Log.i("ObjectValue", object.getString("userName"));
        Log.i("ObjectValue", object.getString("tweets"));

        }

        }
        });


         //Advance Query: Get all the objects in a Class
         ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Score");
         query.whereEqualTo("userName", "Milan");
         query.setLimit(1);

         query.findInBackground(new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> objects, ParseException e) {

        if(e == null){

        Log.i("FindInBackGround", "Retrieved " + objects.size() + " Objects");

        if (objects.size() > 0){

        for (ParseObject object : objects){

        Log.i("FindInBackGround IO ", object.getString("userName"));
        Log.i("FindInBackGround IO ", Integer.toString(object.getInt("score")));
        }

        }

        }

        }
        });


         //Challange: Add 50 points to any score that is higher that 200
         ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Score");
         query.whereGreaterThan("score", 200);

         query.findInBackground(new FindCallback<ParseObject>() {
        @Override
        public void done(List<ParseObject> objects, ParseException e) {

        if (e == null && objects != null){

        Log.i("FindInBackground", "Count users with over 200 points in score " + objects.size());

        for (ParseObject object : objects){

        Log.i("Score Before Increment ", object.getString("userName") + " " + Integer.toString(object.getInt("score")));

        //One method to increase the score is to increment the value:
        //object.increment("score", 50);
        //object.saveInBackground();

        //Here is another way to do it:
        object.put("score", object.getInt("score")+50);
        object.saveInBackground();

        Log.i("Score After Increment ", object.getString("userName") + " " + Integer.toString(object.getInt("score")));

        }

        }

        }
        });
         */

        //ParseUser method: Signing up and Loging In users
        /**
         ParseUser user = new ParseUser();
         user.setUsername("chandler");
         user.setPassword("letmein");
         user.setEmail("chandleretienne@gmail.com");
         user.put("phoneNumber", "6784623915");
         user.signUpInBackground(new SignUpCallback() {
        @Override
        public void done(ParseException e) {

        if (e == null){

        Log.i("SingUpInBackground", "Successful signup");

        }else{

        Log.i("SingUpInBackground", "Failed- Error: " + e.toString());

        }

        }
        });


         //Login: how to log  users in
         ParseUser.logInInBackground("chandler", "letmein", new LogInCallback() {
        @Override
        public void done(ParseUser user, ParseException e) {

        if (e == null && user !=null){

        Log.i("LogInStatus", "You have successfully logged in");

        }else {

        Log.i("LogInStatus", "Failed- Error: " + e.toString());

        }

        }
        });


         //Check if the user is already logged in.
         if (ParseUser.getCurrentUser() != null){

         Log.i("curentUser", "User is logged in " + ParseUser.getCurrentUser().getUsername());

         }else {

         Log.i("curentUser", "User is Not logged in ");

         }

        //How to log out
        ParseUser.logOut();
        */


        //Check if the user is already logged in.
        if (ParseUser.getCurrentUser() != null){

            Log.i("curentUser", "User is logged in " + ParseUser.getCurrentUser().getUsername());

        }else {

            Log.i("curentUser", "User is Not logged in ");

        }


        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
