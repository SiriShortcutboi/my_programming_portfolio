package com.example.wack_a_mole;

import android.content.Intent; 
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class WmtitleScreen extends AppCompatActivity implements View.OnClickListener {
    Intent playintent;
    Intent optionIntent;
    Intent idiotPlayIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wmtitle_screen);
        playintent = new Intent(this, Game.class);
        optionIntent = new Intent(this, options.class);
        idiotPlayIntent = new Intent(this, options.class);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.playbutton){
            startActivity(playintent);
            finish(); }
     else if(v.getId() == R.id.startOptions){
         startActivity(optionIntent);
            finish(); }
        else if (v.getId() == R.id.startOptions){
            startActivity(idiotPlayIntent);
            finish(); }
    }
}
