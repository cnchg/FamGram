package com.tricloudcommunications.ce.famgram;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseSession;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeViewActivity extends AppCompatActivity {


    ListView userLV;
    ArrayList<String> usersArrayList;
    ArrayAdapter arrayAdapter;

    public void noUserSession(){

        ParseUser.logOut();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        userLV = (ListView) findViewById(R.id.usersListView);
        usersArrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, usersArrayList);
        userLV.setAdapter(arrayAdapter);

        ParseSession.getCurrentSessionInBackground(new GetCallback<ParseSession>() {
            @Override
            public void done(ParseSession object, ParseException e) {

                if (object != null){

                    ParseQuery<ParseUser> userParseQuery = ParseUser.getQuery();
                    userParseQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().getUsername());
                    userParseQuery.findInBackground(new FindCallback<ParseUser>() {
                        @Override
                        public void done(List<ParseUser> objects, ParseException e) {

                            if (e == null){

                                for (ParseObject users : objects){

                                    usersArrayList.add(users.getString("username"));
                                    arrayAdapter.notifyDataSetChanged();
                                }

                            }else {

                                Log.i("ListUserEvent", e.getMessage());
                            }

                        }
                    });

                }else {

                    noUserSession();

                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_view_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.home_view_action_settings) {

            ParseUser.logOut();
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
