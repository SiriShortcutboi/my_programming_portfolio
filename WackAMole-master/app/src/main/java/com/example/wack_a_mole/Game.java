package com.example.wack_a_mole;

import androidx.appcompat.app.AppCompatActivity; 

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Random;

public class Game extends AppCompatActivity implements View.OnClickListener {

    // Set up an array of integers to hold the Button IDs of the 'moles'
    ArrayList<Integer> myButtonIDs = new ArrayList<>();
    //The Handler will be used to run a timer in our game
    protected Handler taskhandler = new Handler();
    //The isComplete variable  will tell us when time is up!
    protected Boolean isComplete = false;
    Button currentMole;
    //Use the current time as the start time for the game
    long startTime = System.currentTimeMillis();
    //Keep track of how many times the user has hit the mole\
    int score = 0;
    //settings
    //The following variables used to configure the game.
    //establish default game configuration settings here!
    String playername = "Default";
    int difficultylevel =2; // 1 = hard, 2 = ,medium, 3 = easy
    int numMoles = 8;       // any value between 3 and 8
    int duration = 20;      // any value up to 30 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);

        /*Retrieve game options from incoming intent
        Bundle bun = getIntent().getExtras();
        playername = bun.getString("name");
        difficultylevel = bun.getInt("name");
        duration = bun.getInt("duration");
        difficultylevel = prefs.getInt("difficulty",1); // 1 = hard, 2 = ,medium, 3 = easy
        numMoles = 8;       // any value between 3 and 8
        duration = 20;
*/

        //Get a reference to the shared preferences for our applications
        SharedPreferences prefs = getSharedPreferences("whackSettings",MODE_PRIVATE);
        playername = prefs.getString("name", "Default");
        difficultylevel = prefs.getInt("difficulty", 1);
        numMoles = prefs.getInt("numMoles",8);
        duration = prefs.getInt("duration",20);

        initButtons(); //initialize all 8 buttons
        setNewMole(); // set one mole as the current mole
        setTimer(difficultylevel * 1000); // Start the timer
    }

    @Override
    public void onClick(View v) {
        if (isComplete) {
            return;
        }
        if (v== currentMole) {
            score++;
            TextView tvscore = (TextView)findViewById(R.id.gamescoretv);
            tvscore.setText("score: "+score);
            setNewMole();
        }

    }
//This method is called when the game is completed
    public void gameOver()
    {
        //set the isComplete variable to true to stop the timer
        isComplete = true;
        TextView tvscore = (TextView)findViewById(R.id.gamescoretv);
        tvscore.setText("Game Over! \n Score; "+score);
        setNewMole();
        //Create a new intent for the GameOver Screen and pass the number of
        // hits for the player and the player's name
        Intent gameoverintent = new Intent(this, GameOver.class);
        gameoverintent.putExtra("score",score);
        gameoverintent.putExtra("name",playername);
        startActivity(gameoverintent);
        finish(); // Start the new activity with our Intent
    }
    //this method will choose a new button as the current mole
    // ** this method is provided complete as part of the activity starter.**
    public void setNewMole() {
        Random generator = new Random(); // Create a random number generator

        int randomitem = generator.nextInt(numMoles);

        int newButtonId = myButtonIDs.get(randomitem);
        if (currentMole != null){
                currentMole.setVisibility(View.INVISIBLE);
        }
        Button newMole = (Button)findViewById(newButtonId);
        newMole.setVisibility(View.VISIBLE);
        currentMole=newMole;
    }

    // This method will retrieve all mole button Ids and place them into
    // our array of integer Button IDs
    public void initButtons() {
        ViewGroup group = (ViewGroup)findViewById(R.id.GameLayout);
        View v;
        //now we can look through all the controls and find just the buttons
        for(int i =0;i < group.getChildCount();i++){
            v = group.getChildAt(i);
            if (v instanceof Button){
                v.setOnClickListener(this); //set the OnClickListener for the button
                //if the game is not over
                if(!isComplete){
                    myButtonIDs.add(v.getId()); // Add the Button ids
                    v.setVisibility(View.INVISIBLE);
                }
            }
        }
    }

    //This method will create the timer that will allow us to switch current moles
    protected void setTimer(long time) {
        //get the time that we want our timer to last from the input parameter
        final long elapse = time;
        //Create a new "runnable" task - this will create a timer feature
        Runnable t = new Runnable() {
            @Override
            public void run() {
                onTimerTask(); //Change the currrent mole on the screen
                // if the game is not complete
                if (!isComplete) {
                    // create the new timer task to go off when the next mole should be shown
                    taskhandler.postDelayed(this, elapse);
                }
            }
        };
        taskhandler.postDelayed(t, elapse);
    }
        //This method will change the current mole whenever the timer goes off
        protected void onTimerTask(){
        //Calculate our ending time based on the duration setting
        long endtime = startTime+(duration * 1000);

        // if the ending time is greater than the current time, keep the game going
        if (endtime> System.currentTimeMillis()) {
            setNewMole(); // set a new mole on the screen
        }
        else{
            gameOver(); // if the ending time is less than the current time, the game is over
        }

    }


}
