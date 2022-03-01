package com.rp.android;

import android.app.*;
import android.content.*;
import android.content.Intent;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.TextView;
import java.io.*;

public class ScoreActivity extends Activity {
  
  String fileName = "rank";
  TextView txt;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_score);
    
    txt = (TextView)findViewById(R.id.viewScore);
    ReadBtnClick();
    Toast.makeText(this, "Parabéns!!", Toast.LENGTH_SHORT).show();
  }

  @Override
  public void onBackPressed() {
    Intent it = new Intent(this, MainActivity.class);
    startActivity(it);
    Toast.makeText(this, "Boa sorte", Toast.LENGTH_SHORT).show();
    finish();
  }

  public void ReadBtnClick() {
    // reading text from file
    try {
      FileInputStream fileIn = openFileInput(fileName);
      InputStreamReader inputRead = new InputStreamReader(fileIn);

      // init bufferreader and stringbuilder to convert retrieved data into string
      BufferedReader bufferedReader = new BufferedReader(inputRead);
      StringBuilder stringBuilder = new StringBuilder();
      String line = null;
      while ((line = bufferedReader.readLine()) != null) {
        stringBuilder.append(line);
      }
      // close the file
      fileIn.close();
      inputRead.close();

      //Toast.makeText(getBaseContext(), "File Read successfully!", Toast.LENGTH_SHORT).show();

      //displayName.setText("Name: " + stringBuilder.toString());
      txt.setText(stringBuilder.toString());

    } catch (java.io.IOException e) {
      e.printStackTrace();
    }
    
    
  }
}
