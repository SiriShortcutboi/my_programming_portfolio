package com.example.wack_a_mole;

import androidx.appcompat.app.AppCompatActivity; 

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class GameOver extends AppCompatActivity implements View.OnClickListener {
    String playerName;
    int score;
    Intent gamescreenintent;
    Intent hsscreenintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameover);
        Button play = (Button) findViewById(R.id.goButtonPlay);
        play.setOnClickListener(this);
        Button highScores = (Button) findViewById(R.id.goHighScores);
        highScores.setOnClickListener(this);
        TextView message = (TextView) findViewById(R.id.gotvmessage);
        TextView scores = (TextView) findViewById(R.id.gotvGameOver);
        score = getIntent().getExtras().getInt("score");
        playerName = getIntent().getExtras().getString("name");
        message.setText("You hit " + score + " times!");
        scores.setText("Game Over, " + playerName);

        gamescreenintent = new Intent(this, Game.class);
        hsscreenintent = new Intent(this, HighScores.class);
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);

        //load in all high scores
        if(externalMemoryAvailable(this)){
            loadHsSD();
        }
        else{
            loadHsIF();
        }
    }

    private void saveDatatoSD(){
    try{
        //Open the SD card Directory on the device
        File privateLocation = getExternalFilesDir(null);
        //Create or open the HighScores.txt file from the sd card
        File myfile = new File(privateLocation, "HighScores.txt");

        // build a FileOutputStream and write some text data!
        FileOutputStream fos = new FileOutputStream(myfile,true,);
        WriteToFOS(fos);
    }
}

    private void saveDatatoIF{
            try{
        FileOutputStream fos = openFileOutput("HighScores.txt", MODE_APPEND);
        WriteToFOS(fos);
    }
            catch(Exception e){
        CharSequence text = "the file could not be opened" + e.toString();
        int dur = Toast.LENGTH_LONG;
        Toast message = Toast.makeText(this, text, dur);
        message.show();
    }
}
public void WriteToFOS(FileOutputStream fos){
    try {
        // Open the file and assign it to an output stream writer
        OutputStreamWriter osw = new OutputStreamWriter(fos);
        //figure out which character i an endline on the current device
        String endline = System.getProperty("line.seperator");
        // Write the players need to file
        osw.write(playerName + endline);
        osw.write(score+endline);
        // make sure all data has been sent out to the file
        osw.flush();
        // Close the file
        osw.close();
    }
    catch (Exception e) {
        CharSequence text = "could not write to file" + e.toString();
        int dur = Toast.LENGTH_LONG;
        Toast message = Toast.makeText(this, text, dur);
        message.show();
        }
    }
    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.goButtonPlay) {
            startActivity(gamescreenintent);
            finish();
        }
        else if (v.getId() == R.id.goHighScores){
            startActivity(hsscreenintent);
            finish();
    }

}