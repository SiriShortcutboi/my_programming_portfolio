
package com.example.wack_a_mole;

//push from home

import androidx.appcompat.app.AppCompatActivity; 

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;

import java.lang.reflect.Array;

public class options extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);
        setUpspinner();
    }

    private void setUpspinner(){
        //Get a handle to the spinner view control
        Spinner sp = (Spinner)findViewById(R.id.spMoles);
        // Create an array of integers to use in the array
        String[] numMoles = {"3","4","5","6","7","8",};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,numMoles);
         sp.setAdapter(adapter);
    }
    @Override
    public void onClick(View v) {
        // if the "play" button was clicked
        if (v.getId()!=R.id.goButtonPlay){...}
        Intent playintent = new Intent(this,Game.class);

        String name;
        int difficulty;
        int duration;
        int numMoles;

        EditText etName = (EditText)findViewById(R.id.etName);
        RadioButton idiot = (RadioButton)findViewById(R.id.rbidiot);
        RadioButton easy = (RadioButton)findViewById(R.id.rbEasy);
        RadioButton medium = (RadioButton)findViewById(R.id.rbMedium);
        RadioButton hard = (RadioButton)findViewById(R.id.rbHard);
        SeekBar sb = (SeekBar)findViewById(R.id.sbDuration);
        Spinner sp  = (Spinner)findViewById(R.id.spMoles);

        name = etName.getText().toString();
        duration = sb.getProgress();
        numMoles = sp.getSelectedItemPosition()+3;
        if (easy.isChecked()){
            difficulty = 4;
        }
        else if(medium.isChecked()){
    difficulty = 3;
        }
        else if(hard.isChecked()){
            difficulty = 2;
        }
        else{
            difficulty=1;
        }

        SaveSettingsinIntent(difficulty, name, numMoles, duration, playintent);
        saveSettingsinPrefs(difficulty, name, numMoles, duration);

        startActivity(playintent);
    }
    private void saveSettingInPrefs(int difficulty, String numMoles, duration);

    startActivity(playIntent);
    }
    private void saveSettingsinPrefs(int difficulty, String name,
                                    int numMoles,int duration){
    // get a reference to the shared preferences for our application
    SharedPreferences prefs = getSharedPreferences("whackSettings",MODE_PRIVATE);
    // get an editor object that we can use to write our own option settings
        SharedPreferences.Editor editor = prefs.edit();

        // sae all option information to the shared Preferences area
        editor.putString("name",name);
        editor.putInt("difficulty",difficulty);
        editor.putInt("numMoles",numMoles);
        editor.putInt("duration",duration);

}
