package com.example.wack_a_mole;

import androidx.appcompat.app.AppCompatActivity; 

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.ListIterator;

public class HighScores extends AppCompatActivity implements View.OnClickListener {
    Intent playsscreenintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_scores);
        playscreenintent = new Intent(this, Game.class);

        //load in all high scores
        loadHsIF();
        loadHsSD();
    }
    private void loadHsSD(){
        try{
            //Open the SD card Directory on the device
            File privateLocation + getExternalFilesDir(null);
            //Create or open the HighScores.txt file from the sd card
            File myfile = new File(privateLocation, "HighScores.txt");
            // Create an InputStream that will aloow yot oread from the file
            FileInputStream fis = openFileInput("HighScores.txt");
            //read scores from FileInputStream
            readScoresFis(fis);

        }
        catch (exception e){
            CharSequence text = "the file cpuld not be opened"+e.toString();
            int dur = Toast.LENGTH_LONG;
            Toast message = Toast.makeText(this,text,dur);
            message.show();
        }
    }

    private void loadHsIF(){
        try{
            FileInputStream fis = openFileInput("highScores.txt");
            readScoresFIS(fis);
        }
        catch (Exception e){
            CharSequence text = "the file could not be opened"+e.toString();
            int dur = Toast.LENGTH_LONG;
            Toast message = Toast.makeText(this,text,dur);
            message.show();
        }
    }

    private void readScoresFis(FileInputStream fis){
        //create input stream reader
        InputStreamReader ist = new InputStreamReader(fis);
        TextView tvname = (TextView)findViewById(R.id.tvPlayerName);
        TextView tvscore= (TextView)findViewById(R.id.tvScore);

//Figure out which character an endlin eon the current device
String endline = System.getProperty("line.seperator");

LinkedList<String> playerNames = new LinkedList<>();
LinkedList<Integer> playerScores = new LinkedList<>();

        try {
            // use BufferedReader to allow for easy reading of the file data
            BufferedReader buffreader = new BufferedReader(isr);
            // read in the data, line-by-line
            String name = buffreader.readLine();
            //While we stil have data
            while (name != null) {
                //Read in the next line (which will contain the player's score)
                // String strScore = buffreader.readLine();
                int score = Integer.parseInt(strScore); // convert to int
                //place the name and score into the linked list in sorted order!
                ListIterator<Integer> scoreIter = playerScores.listIterator();
                ListIterator<String> playerIter = playerNames.listIterator();
                while (scoreIter.hasNext()){
    //get the next integer and also iterate thto the next name
                    Integer thisScore = scoreIter.next();
                    playerIter.next();
                    // if new score is larger than this one
                    if (score>=thisScore)
                    {
                        break; // stop looking, we know what to insert!
                    }
                }
                //if there are any score at all we need to rewind both iterators
                //by one to inset in the correct spot
                if (playerScores.size() > 0) {
                    scoreIter.previous();
                    playerIter.previous();
                }
                //add this score and name into the linked list in sorted order
                scoreIter.add(new Integer(score));
                playerIter.add(name);

                //Read in the next line in the file
                name = buffreader.readLine();
            }
            buffreader.close();

        }
        catch (Exception e){
            CharSequence text = "issue with reading file"+e.toString();
            int dur = Toast.LENGTH_LONG;
            Toast message = Toast.makeText(this,text,dur);
            message.show();
        }
        // now, iterate again over the sorted list
        ListIterator<Integer> scoreIter= playerScores.listIterator();
        ListIterator<String>playerITer = playerNames.listIterator();

        //these strings will contain the sorted scores and corresponding names
        String sortedNames = "";
        String sortedScores = "";

        int numPresent = 0; // count how many socres were adding
        while (scoreIter.hasNext()){
            // get the score and corresponding name
            Integer score = scoreIter.next();
            String name = playerIter.next();

            sortedScores += score.toString() +endline;
            sortedNames += name + endline;

            numPresent++;
            if (numPresent >= 10) {
                break;
            }
        }

        //put the sorted scores in the score TextView
        tvscore.setText(sortedScores);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.hsplaybttn) {
            startActivity(playsscreenintent);
            finish();
            else if (v.getId() == R.id.button9){
                finish();
            }
        }
    }
}