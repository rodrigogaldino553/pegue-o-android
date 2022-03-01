package com.rp.android;

import android.app.*;
import android.content.*;
import android.content.Intent;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.rp.android.MainActivity;

public class SobreActivity extends Activity {

  ScrollView scv;
  Button mail, tube, btnFollow, share;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sobre);
    scv = (ScrollView) findViewById(R.id.ScrollView01);
    mail = (Button) findViewById(R.id.btnFeed);
    tube = (Button) findViewById(R.id.btnTube);
    share = (Button) findViewById(R.id.share);
    btnFollow = (Button) findViewById(R.id.insta);
    Toast.makeText(this, "Sobre o app", Toast.LENGTH_SHORT).show();
  }

  public void send(View v) {
    Toast.makeText(this, "Selecione um aplicativo de e-mail", Toast.LENGTH_LONG).show();
    Intent it = new Intent(Intent.ACTION_SEND);
    it.setData(Uri.parse("mail"));
    String[] myMail = {"galdinorodrigo553@Gmail.com"};
    it.putExtra(Intent.EXTRA_EMAIL, myMail);
    it.putExtra(Intent.EXTRA_SUBJECT, "Pegue o Android");
    it.putExtra(Intent.EXTRA_TEXT, "Rtec Play, sobre o Pegue o Android, tenho a dizer");
    it.setType("message/rfc822");
    Intent chooser = Intent.createChooser(it, "Escolha seu serviço de e-mail");
    startActivity(it);
  }

  public void share(View v) {
    Toast.makeText(this, "Desafie seus amigos", Toast.LENGTH_LONG).show();
    Intent it = new Intent(Intent.ACTION_SEND);
    it.setType("text/plain");
    it.putExtra(Intent.EXTRA_TEXT, "Baixe já o jogo mais irritante do android [LINK DO VIDEO]");
    Intent chooser = Intent.createChooser(it, "Compartilhar por: ");
    startActivity(it); // Intent.createChooser((Intent) it, (CharSequence) "compartilhar por: "));
  }

  public void goTube(View v) {
    Intent i = new Intent(Intent.ACTION_VIEW);
    i.setData(Uri.parse("https://youtube.com/c/RTECPLAY"));
    Toast.makeText(this, "Inscreva-se em nosso canal", Toast.LENGTH_LONG).show();
    startActivity(i);
  }

  public void follow(View v) {
    Intent i = new Intent(Intent.ACTION_VIEW);
    Toast.makeText(this, "Segue nois ae valeu", Toast.LENGTH_SHORT).show();
    i.setData(Uri.parse("https://www.instagram.com/rgr205?r=nametag "));
    Intent chooser = Intent.createChooser(i, "Acessar Instagram por: ");
    startActivity(i);
  }

  public void how(View v) {
    AlertDialog.Builder msg = new AlertDialog.Builder(this);
    msg.setTitle("Pegue o Android");
    msg.setMessage(
        "Seja bem-vindo ao pegue o android.\n As regras são simples, apenas click no android para somar pontos. CUIDADO!! se aperta no azul você perde tudo.\n Tire mais pontos que seus amigos e divirta-se");
    msg.setIcon(R.drawable.ic_launcher);
    msg.setPositiveButton(
        "Jogar",
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface img, int num) {
            back();
          }
        });
    msg.create().show();
  }

  public void back() {
    Intent it = new Intent(this, MainActivity.class);
    startActivity(it);
    finish();
  }
  
  @Override
  public void onBackPressed(){
    back();
  }
}
