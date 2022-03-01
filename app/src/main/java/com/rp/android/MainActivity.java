package com.rp.android;

import android.app.*;
import android.content.*;
import android.content.Intent;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.TextView;
import java.util.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.io.BufferedReader;
import java.io.*;

public class MainActivity extends Activity {

  ImageView img;
  TimerTask tempoTwo, timer;
  int score = 0;
  long tempo;
  LinearLayout perdeu, down;
  Button label;
  TextView txt;
  private Timer _time, time;
  int width, height, eye, back;
  String fileName = "rank";

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    width = getResources().getDisplayMetrics().widthPixels - 100;
    height = getResources().getDisplayMetrics().heightPixels - 330;
    label = (Button) findViewById(R.id.btn);
    perdeu = (LinearLayout) findViewById(R.id.linear);
    down = (LinearLayout) findViewById(R.id.bottom);
    img = (ImageView) findViewById(R.id.iv);
    txt = (TextView) findViewById(R.id.txtScore);
    animation();
  }

  @Override
  public void onResume() {
    super.onResume();
  }

  public void WriteBtnClick(String score) {
    try {
      FileOutputStream fileout = openFileOutput(fileName, MODE_PRIVATE);
      OutputStreamWriter outputWriter = new OutputStreamWriter(fileout);
      outputWriter.write(score);
      outputWriter.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void animation() {
    time = new Timer();
    timer =
        new TimerTask() {
          @Override
          public void run() {
            runOnUiThread(
                new Runnable() {

                  public void run() {
                    tempo = getRandom(1000, 4500);
                    tempoTwo =
                        new TimerTask() {
                          @Override
                          public void run() {
                            runOnUiThread(
                                new Runnable() {
                                  public void run() {
                                    tempoTwo.cancel();
                                    Random rand = new Random();
                                    label.setTranslationX(rand.nextInt(width));
                                    label.setTranslationY(rand.nextInt(height));
                                    // tempoTwo.cancel();
                                    eye++;
                                  }
                                });
                          }
                        };
                    time.schedule(tempoTwo, tempo);
                  }
                });
          }
        };
    time.scheduleAtFixedRate(timer, 500, 500);

    if (eye > 20) {
      lose(perdeu);
    }
  }

  public void pegue(View v) {
    eye = 0;
    score++;
    txt.setText(String.valueOf((long) (score)));
  }

  public void lose(View v) {
    Vibrator vibe = (Vibrator) getSystemService(VIBRATOR_SERVICE);
    vibe.vibrate(1000);
    if (score > 50) {
      // time.cancel();
      // timer.cancel();
      WriteBtnClick(String.valueOf((long) (score)));
      Toast.makeText(this, "Parabéns!!", Toast.LENGTH_SHORT).show();
      mostScore();
      score = 0;
    } else {
      score = 0;
      Toast.makeText(this, "Perdeu tudo kkk", Toast.LENGTH_LONG).show();
    }
    txt.setText(String.valueOf((long) (score)));
  }

  public void clickDown(View v) {
    // init();
    // método do linear de baixo é statico
  }

  public void up(View v) {
    PopupMenu popupMenu = new PopupMenu(MainActivity.this, img);
    popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
    popupMenu.setOnMenuItemClickListener(
        new PopupMenu.OnMenuItemClickListener() {
          @Override
          public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
              case R.id.about:
                mudarTela();
                break;
              case R.id.share:
                share();
                break;
            }
            return true;
          }
        });
    popupMenu.show();
  }

  public void mudarTela() {
    time.cancel();
    timer.cancel();
    Intent it = new Intent(this, SobreActivity.class);
    startActivity(it);
    finish();
  }

  @Deprecated
  public int getRandom(int min, int max) {
    Random sort = new Random();
    return sort.nextInt(max - min + 1) + min;
  }

  public void share() {
    Intent intent = new Intent("android.intent.action.SEND");
    intent.setType("text/plain");
    // Na linha seguinte deverá ter o link de compartilhar
    intent.putExtra(Intent.EXTRA_TEXT, "Baixe já o jogo mais irritante do mundo [LINK DO VIDEO]");
    startActivity(intent);
    /*time.cancel();
    timer.cancel();*/
    // finish();
  }

  public void mostScore() {
    // FUNCAO PARA QUANDO O JOGADOR PERDER, MAIS TIVER UM BOM SCORE, MOSTRARA O RESULTADO
    // vai ser direcionado para outra activitu
    time.cancel();
    timer.cancel();
    Intent it = new Intent(this, ScoreActivity.class);
    startActivity(it);
    finish();
  }

  @Override
  public void onBackPressed() {
    back++;
    if (back >= 2) {
      Toast.makeText(this, "Volte sempre", Toast.LENGTH_SHORT).show();
      finish();

    } else {
      Toast.makeText(this, "Clique novamente para sair", Toast.LENGTH_SHORT).show();
      Timer time3 = new Timer();
      TimerTask ti2 =
          new TimerTask() {
            @Override
            public void run() {
              runOnUiThread(
                  new Runnable() {
                    @Override
                    public void run() {
                      back = 0;
                    }
                  });
            }
          };
      time3.schedule(ti2, (int) (2000));
    }
  }
}
