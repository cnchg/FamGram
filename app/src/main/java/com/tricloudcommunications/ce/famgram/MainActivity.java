package com.tricloudcommunications.ce.famgram;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.onesignal.OneSignal;
import com.parse.LogInCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import com.parse.SignUpCallback;

import com.parse.ParseInstallation;
import com.parse.ParsePushBroadcastReceiver;
import com.parse.ParseAnalytics;




public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnKeyListener {

    Integer currentMode = 1; //1=logIn Screen; 2=signUp Screen; 3=reset Password Screen
    RelativeLayout contentMainLayout;
    RelativeLayout signUpLayout;
    RelativeLayout signInLayout;
    RelativeLayout passwordResetLayout;
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
    ImageButton logOutImageButton;
    EditText passwordResetEmailEditText;

    public void userSignIn(View view){

        String logInUserName = String.valueOf(logInUserNameEditText.getText()).trim(); //Remove white spaces from the begining and end of string
        String logInPassword = String.valueOf(loginPasswordEdtText.getText()).trim(); //Remove white spaces from the begining and end of string

        if (logInUserNameEditText.getText().toString().matches("") || loginPasswordEdtText.getText().toString().matches("") ){

            Toast.makeText(getApplicationContext(), "Please enter a username and password", Toast.LENGTH_LONG).show();

        }else{

            //Login: how to log  users in
            ParseUser.logInInBackground(logInUserName, logInPassword, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if (e == null && user !=null){

                        goHome();

                        //Toast.makeText(getApplicationContext(),"You have successfully logged in", Toast.LENGTH_LONG).show();
                        //signInLayout.setVisibility(View.INVISIBLE);
                        //logOutImageButton.setVisibility(View.VISIBLE);
                        //Log.i("LogInStatus", "You have successfully logged in");

                    }else {

                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();

                        //Log.i("LogInStatus", "Failed- Error: " + e.getMessage());

                    }

                }
            });

        }

    }

    public void userSignUp(View view){

        String signUpUserName = String.valueOf(signUpUserNameEditText.getText()).trim(); //Remove white spaces from the begining and end of string
        String signUpPassword = String.valueOf(signUpPasswordEditText.getText()).trim(); //Remove white spaces from the begining and end of string
        String signUpEmail = String.valueOf(signUpEmailEditText.getText()).trim(); //Remove white spaces from the begining and end of string
        String signUpPhoneNumber = String.valueOf(signUpPhoneNumberEditText.getText()).trim(); //Remove white spaces from the begining and end of string

        if (signUpUserNameEditText.getText().toString().matches("") || signUpPasswordEditText.getText().toString().matches("") || signUpEmailEditText.getText().toString().matches("") || signUpPhoneNumberEditText.getText().toString().matches("")){

            Toast.makeText(MainActivity.this, "Please enter a username, password, email, and phone number", Toast.LENGTH_LONG).show();

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

                        goHome();

                        //Toast.makeText(MainActivity.this, "Awesome, you account is setup", Toast.LENGTH_LONG).show();
                        //signUpLayout.setVisibility(View.INVISIBLE);
                        //logOutImageButton.setVisibility(View.VISIBLE);

                    }else{

                        Toast.makeText(MainActivity.this, e.getMessage() + " Try using another username and email.", Toast.LENGTH_LONG).show();
                        //Log.i("Sign In Error:", e.getMessage());

                    }
                }
            });

        }
    }

    public void passwordReset(View view){

        String passwordResetEmail = String.valueOf(passwordResetEmailEditText.getText()).trim(); //Remove white spaces from the begining and end of string

        if (passwordResetEmailEditText.getText().toString().matches("")){

            Toast.makeText(MainActivity.this, "Please enter your email address", Toast.LENGTH_LONG).show();
        }else{

            ParseUser.requestPasswordResetInBackground(passwordResetEmail, new RequestPasswordResetCallback() {
                @Override
                public void done(ParseException e) {
                    if (e == null){

                        passResetComplete();
                        Toast.makeText(MainActivity.this, "Please check your email for password reset instructions", Toast.LENGTH_LONG).show();

                    }else{

                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                        //Log.i("Password Reset Error:", e.getMessage());
                    }
                }
            });

        }

        //Log.i("UserEvent", "Password reset button clicked, Send Email");
    }

    public void logInNow(View view){

        currentMode = 1;
        signUpLayout.setVisibility(View.INVISIBLE);
        signInLayout.setVisibility(View.VISIBLE);
        logInUserNameEditText.setText("");
        loginPasswordEdtText.setText("");
        loginPasswordEdtText.setOnKeyListener(MainActivity.this);
        //signUpLayout.animate().translationXBy(1000f).setDuration(2000);//Transition the image in from right to left

        Log.i("UserEvent", "User Clicked Sign In");

    }

    public void logOutNow(View view){

        currentMode = 1;
        ParseUser.logOut();
        logOutImageButton.setVisibility(View.INVISIBLE);
        signInLayout.setVisibility(View.VISIBLE);
        logInUserNameEditText.setText("");
        loginPasswordEdtText.setText("");
        loginPasswordEdtText.setOnKeyListener(MainActivity.this);
        //signInLayout.animate().alpha(1f).setDuration(500);//makes the images visible

        Log.i("UserEvent", "User Clicked Log Out");

    }

    public void resetPasswordNow(View view){

        currentMode = 3;
        signInLayout.setVisibility(View.INVISIBLE);
        passwordResetLayout.setVisibility(View.VISIBLE);
        passwordResetEmailEditText.setText("");
        passwordResetEmailEditText.setOnKeyListener(MainActivity.this);

        Log.i("UserEvent", "User Clicked Rest Password");

    }

    public void signUpNow(View view){

        currentMode = 2;
        signInLayout.setVisibility(View.INVISIBLE);
        signUpLayout.setVisibility(View.VISIBLE);
        signUpUserNameEditText.setText("");
        signUpPasswordEditText.setText("");
        signUpEmailEditText.setText("");
        signUpPhoneNumberEditText.setText("");
        signUpPhoneNumberEditText.setOnKeyListener(MainActivity.this);
        //signUpLayout.animate().translationXBy(1000f).setDuration(2000);//Transition the image in from right to left

        Log.i("UserEvent", "User Clicked Sign Up");

    }

    public void returnToLoginNow(View view){

        currentMode = 1;
        passwordResetLayout.setVisibility(View.INVISIBLE);
        signInLayout.setVisibility(View.VISIBLE);
        logInUserNameEditText.setText("");
        loginPasswordEdtText.setText("");
        loginPasswordEdtText.setOnKeyListener(MainActivity.this);

        Log.i("UserEvent", "User Clicked return to login");

    }

    public void notLoggedIn(){

        currentMode = 1;
        signInLayout.setVisibility(View.VISIBLE);
        logInUserNameEditText.setText("");
        loginPasswordEdtText.setText("");
        loginPasswordEdtText.setOnKeyListener(MainActivity.this);

    }

    public void passResetComplete(){

        currentMode = 1;
        passwordResetLayout.setVisibility(View.INVISIBLE);
        signInLayout.setVisibility(View.VISIBLE);
        logInUserNameEditText.setText("");
        loginPasswordEdtText.setText("");
        loginPasswordEdtText.setOnKeyListener(MainActivity.this);

    }

    public void goHome(){

        Intent i = new Intent(MainActivity.this, HomeViewActivity.class);
        startActivity(i);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.content_main){

            //Hide the keyboard if the user clicks anywhere on the background
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN){

            switch(currentMode){

                case 1:
                    userSignIn(v);
                    break;
                case 2:
                    userSignUp(v);
                    break;
                case 3:
                    passwordReset(v);
                    break;

                default:
            }

        }

        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // remove Action/Title Bar and makes full screen
        //Source http://stackoverflow.com/questions/2862528/how-to-hide-app-title-in-android
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Set the Application View
        setContentView(R.layout.activity_main);

        //Stops the keyboard from popping up,on load.
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        contentMainLayout = (RelativeLayout) findViewById(R.id.content_main);
        signInLayout = (RelativeLayout) findViewById(R.id.signInLayout);
        signUpLayout = (RelativeLayout) findViewById(R.id.signUpLayout);
        passwordResetLayout = (RelativeLayout) findViewById(R.id.resetPasswordLayout);
        logInUserNameEditText = (EditText) findViewById(R.id.loginUserNameEditText);
        loginPasswordEdtText = (EditText) findViewById(R.id.loginPasswordEditText);
        signUpUserNameEditText = (EditText) findViewById(R.id.signupUserNameEditText);
        signUpPasswordEditText = (EditText) findViewById(R.id.signupPasswordEditText);
        signUpEmailEditText = (EditText) findViewById(R.id.signupEmailEditText);
        signUpPhoneNumberEditText = (EditText) findViewById(R.id.signupPhoneNumberEditText);
        passwordResetEmailEditText = (EditText) findViewById(R.id.passwordResetEmailEditText);
        logInButton = (Button) findViewById(R.id.logInButton);
        signUpButton = (Button) findViewById(R.id.signUpButton);
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        logInTextView = (TextView) findViewById(R.id.logInTextView);
        logOutImageButton = (ImageButton) findViewById(R.id.logOutImageButton);

        contentMainLayout.setOnClickListener(MainActivity.this);

        //Check if the user is already logged in.
        if (ParseUser.getCurrentUser() != null){

            goHome();
            //logOutImageButton.setVisibility(View.VISIBLE);
            Log.i("curentUser", "User is logged in " + ParseUser.getCurrentUser().getUsername());

        }else {

            notLoggedIn();

            Log.i("curentUser", "User is Not logged in ");

        }


        ParseAnalytics.trackAppOpenedInBackground(getIntent());

        //Onsignal
        OneSignal.startInit(this).init();
        // Call syncHashedEmail anywhere in your app if you have the user's email.
        // This improves the effectiveness of OneSignal's "best-time" notification scheduling feature.
        //OneSignal.syncHashedEmail(ParseUser.getCurrentUser().getEmail());

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
