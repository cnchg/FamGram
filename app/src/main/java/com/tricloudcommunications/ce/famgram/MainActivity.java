package com.tricloudcommunications.ce.famgram;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class MainActivity extends AppCompatActivity {

    RelativeLayout signUpLayout;
    RelativeLayout signInLayout;
    EditText logInUserNameEditText;
    EditText loginPasswordEdtText;
    Button logInButton;
    TextView signUpTextView;

    EditText signUpUserNameEditText;
    EditText signUpPasswordEditText;
    EditText signUpEmailEditText;
    EditText signUpPhoneNumberEditText;
    Button signUpButton;
    TextView logInTextView;

    public void userSignIn(View view){

        if (logInUserNameEditText.length() < 1 || loginPasswordEdtText.length() < 1 ){

            Toast.makeText(getApplicationContext(), "Please enter a username and password", Toast.LENGTH_LONG).show();
        }else{

            Toast.makeText(getApplicationContext(),"Awsome you completed the form", Toast.LENGTH_LONG).show();

        }

    }

    public void userSignUp(View view){

        String signUpUserName = String.valueOf(signUpUserNameEditText.getText()).trim(); //Remove white spaces from the begining and end of string
        String signUpPassword = String.valueOf(signUpPasswordEditText.getText()).trim(); //Remove white spaces from the begining and end of string
        String signUpEmail = String.valueOf(signUpEmailEditText.getText()).trim(); //Remove white spaces from the begining and end of string
        String signUpPhoneNumber = String.valueOf(signUpPhoneNumberEditText.getText()).trim(); //Remove white spaces from the begining and end of string

        if (signUpUserNameEditText.length() < 1 || signUpPasswordEditText.length() < 1 || signUpEmailEditText.length() < 1 || signUpPhoneNumberEditText.length() < 1){

            Toast.makeText(getApplicationContext(), "Please enter a username, password, email, and phone number", Toast.LENGTH_LONG).show();

        }else {

            ParseUser userSignUp = new ParseUser();
            userSignUp.setUsername(signUpUserName);
            userSignUp.setPassword(signUpPassword);
            userSignUp.setEmail(signUpEmail);
            userSignUp.put("phoneNumber", signUpPhoneNumber);
            userSignUp.signUpInBackground(new SignUpCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null){

                        Toast.makeText(getApplicationContext(), "Awesome, you account is setup", Toast.LENGTH_LONG).show();

                    }else{

                        Toast.makeText(getApplicationContext(), "Failed: " + e.toString(), Toast.LENGTH_LONG).show();

                    }
                }
            });


        }
    }

    public void logInNow(View view){

        signUpLayout.setVisibility(View.INVISIBLE);
        signInLayout.setVisibility(View.VISIBLE);
        //signUpLayout.animate().translationXBy(1000f).setDuration(2000);//Transition the image in from right to left

        Log.i("UserEvent", "User Clicked Sign In");

    }

    public void signUpNow(View view){

        signInLayout.setVisibility(View.INVISIBLE);
        signUpLayout.setVisibility(View.VISIBLE);
        //signUpLayout.animate().translationXBy(1000f).setDuration(2000);//Transition the image in from right to left

        Log.i("UserEvent", "User Clicked Sign Up");

    }

    public void notLoggedIn(){

        signInLayout.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove Action/Title Bar and makes full screen
        //Source http://stackoverflow.com/questions/2862528/how-to-hide-app-title-in-android
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //Stops the keyboard from popping up,on load.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        signInLayout = (RelativeLayout) findViewById(R.id.signInLayout);
        signUpLayout = (RelativeLayout) findViewById(R.id.signUpLayout);
        logInUserNameEditText = (EditText) findViewById(R.id.loginUserNameEditText);
        loginPasswordEdtText = (EditText) findViewById(R.id.loginPasswordEditText);
        logInButton = (Button) findViewById(R.id.logInButton);
        signUpTextView = (TextView) findViewById(R.id.logInTextView);
        signUpUserNameEditText = (EditText) findViewById(R.id.signupUserNameEditText);
        signUpPasswordEditText = (EditText) findViewById(R.id.signupPasswordEditText);
        signUpEmailEditText = (EditText) findViewById(R.id.signupEmailEditText);
        signUpPhoneNumberEditText = (EditText) findViewById(R.id.signupPhoneNumberEditText);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        logInTextView = (TextView) findViewById(R.id.logInTextView);



        //Check if the user is already logged in.
        if (ParseUser.getCurrentUser() != null){

            Log.i("curentUser", "User is logged in " + ParseUser.getCurrentUser().getUsername());

        }else {

            notLoggedIn();

            Log.i("curentUser", "User is Not logged in ");

        }


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
