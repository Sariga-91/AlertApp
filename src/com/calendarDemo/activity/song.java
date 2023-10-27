package com.calendarDemo.activity;


import com.example.demos.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;

public class song extends Activity{
    MediaPlayer m;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song);
       // m=MediaPlayer.create(getApplicationContext(), R.raw.gaji);
        //create a folder raw inside res folder. and put 1 .mp3 song
        //m.start();
      
        AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(song.this);                    
         dlgAlert.setTitle("Remainder !");
         dlgAlert.setMessage("Your time is up !");
         dlgAlert.setPositiveButton("OK",new DialogInterface.OnClickListener() {
         public void onClick(DialogInterface dialog, int whichButton) {

            // m.stop();
             dialog.cancel();
         }
         });
         dlgAlert.show();
     }
}