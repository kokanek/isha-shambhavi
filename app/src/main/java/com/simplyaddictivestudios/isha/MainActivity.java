package com.simplyaddictivestudios.isha;

import android.media.MediaPlayer;
import android.os.Handler;
import android.os.PowerManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

  int state = 0;
  boolean nextState = false;
  static final int START_INTRUCTIONS=0, PATANGASANA = 1, SHISHU_RIGHT = 2, SHISHU_LEFT = 3, NADI_POSTURE = 4, NADI_FULL = 5, SUKHA_KRIYA = 6,
                    OM_CHANTING_START = 7, AUM_CHANTING = 8, FLUTTER_BREATHING = 9, BANDHAS = 10, ENDING_SHLOKA = 11, END = 12;
  int nadi_count = 0;

  String[] displayStrings = new String[13];

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    displayStrings[START_INTRUCTIONS] = "Starting instructions...";
    displayStrings[PATANGASANA] = "Patangasana for 2 minutes...";
    displayStrings[SHISHU_RIGHT] = "Shishupalasana for right leg...";
    displayStrings[SHISHU_LEFT] = "Shishupalasana for left leg...";
    displayStrings[NADI_POSTURE] = "Nadi shodhana kriya posture...";
    displayStrings[NADI_FULL] = "Nadi shodhana kriya 3 times...";
    displayStrings[SUKHA_KRIYA] = "Sukha kriya for 5 minutes...";
    displayStrings[OM_CHANTING_START] = "prepare for om chanting...";
    displayStrings[AUM_CHANTING] = "Om chanting...";
    displayStrings[FLUTTER_BREATHING] = "flutter breathing...";
    displayStrings[BANDHAS] = "apply bandhas...";
    displayStrings[ENDING_SHLOKA] = "Ending shloka...";
    displayStrings[END] = "End";

    setContentView(R.layout.activity_main);

    CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
    checkBox.setChecked(true);

    state = START_INTRUCTIONS;

    getWindow().clearFlags(android.view.WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

      PowerManager pm = (PowerManager) getSystemService(getApplicationContext().POWER_SERVICE);
      PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
      wl.acquire();
  }

  public void onChecked(View v) {
    CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
    if(checkBox.isChecked()) {
      nextState = false;
    }
    else {
      nextState = true;
    }
  }

  public void onBeginClicked(final View v) throws InterruptedException {
    //Toast.makeText(getApplicationContext(), "clicked", Toast.LENGTH_SHORT).show();
    final MediaPlayer mediaPlayer = MediaPlayer.create(getApplication(), R.raw.relax);
    final Handler handler = new Handler();

    v.setVisibility(GONE);

    final CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
    checkBox.setVisibility(GONE);

    ProgressBar anim = (ProgressBar) findViewById(R.id.progressBarIndeterminate);
    anim.setVisibility(View.VISIBLE);

    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
      public void onCompletion(MediaPlayer mp) {
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setProgress(state*100/12);

        if(nextState) {
          state = NADI_FULL;
          nextState = false;
        }

        TextView tvStatus = (TextView) findViewById(R.id.textView3);
        tvStatus.setText(displayStrings[state].toString());

        switch(state) {
          case START_INTRUCTIONS:
            final MediaPlayer mediaPlayer14 = MediaPlayer.create(getApplication(), R.raw.patangasana2);
            mediaPlayer14.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer14.start();
              }
            }, 2000);
            state = PATANGASANA;
            break;

          case PATANGASANA:
            final MediaPlayer mediaPlayer2 = MediaPlayer.create(getApplication(), R.raw.shishu_right);
            mediaPlayer2.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer2.start();
              }
            }, 120000);
            state = SHISHU_RIGHT;
            break;

          case SHISHU_RIGHT:
            final MediaPlayer mediaPlayer3 = MediaPlayer.create(getApplication(), R.raw.shishu_left);
            mediaPlayer3.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer3.start();
              }
            }, 120000);
            state = SHISHU_LEFT;
            break;

          case SHISHU_LEFT:
            final MediaPlayer mediaPlayer4 = MediaPlayer.create(getApplication(), R.raw.nadi_vibhajana_posture);
            mediaPlayer4.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer4.start();
              }
            }, 120000);
            state = NADI_POSTURE;
            break;

          case NADI_POSTURE:
            final MediaPlayer mediaPlayer5 = MediaPlayer.create(getApplication(), R.raw.nadi_vibhajana_full);
            mediaPlayer5.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer5.start();
              }
            }, 3000);
            if(nadi_count < 2){
              state = NADI_POSTURE;
              nadi_count++;
            }
            else {
              state = NADI_FULL;
            }
            break;

          case NADI_FULL:
            final MediaPlayer mediaPlayer6 = MediaPlayer.create(getApplication(), R.raw.start_sukha_kriya);
            mediaPlayer6.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer6.start();
              }
            }, 3000);
            state = SUKHA_KRIYA;
            break;

          case SUKHA_KRIYA:
            final MediaPlayer mediaPlayer7 = MediaPlayer.create(getApplication(), R.raw.om_chanting_start);
            mediaPlayer7.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer7.start();
              }
            }, 300000);
            state = OM_CHANTING_START;
            break;

          case OM_CHANTING_START:
            final MediaPlayer mediaPlayer8 = MediaPlayer.create(getApplication(), R.raw.aum_chanting);
            mediaPlayer8.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer8.start();
              }
            }, 3000);
            state = AUM_CHANTING;
            break;

          case AUM_CHANTING:
            final MediaPlayer mediaPlayer9 = MediaPlayer.create(getApplication(), R.raw.flutter_breathing);
            mediaPlayer9.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer9.start();
              }
            }, 3000);
            state = FLUTTER_BREATHING;
            break;

          case FLUTTER_BREATHING:
            final MediaPlayer mediaPlayer10 = MediaPlayer.create(getApplication(), R.raw.apply_bandhas);
            mediaPlayer10.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer10.start();
              }
            }, 150000);
            state = BANDHAS;
            break;

          case BANDHAS:
            final MediaPlayer mediaPlayer11 = MediaPlayer.create(getApplication(), R.raw.meditation);
            mediaPlayer11.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer11.start();
              }
            }, 30000);
            state = ENDING_SHLOKA;
            break;

          case ENDING_SHLOKA:
            final MediaPlayer mediaPlayer12 = MediaPlayer.create(getApplication(), R.raw.ending_shloka);
            mediaPlayer12.setOnCompletionListener(this);
            handler.postDelayed(new Runnable() {
              @Override
              public void run() {
                mediaPlayer12.start();
              }
            }, 180000);
            state = END;
            break;

          case END:
            Toast.makeText(getApplicationContext(), "Isha kriya ended!", Toast.LENGTH_SHORT).show();
            //clear everything to default as before starting
            v.setVisibility(View.VISIBLE);
            checkBox.setVisibility(View.VISIBLE);
            progressBar.setProgress(0);

            ProgressBar anim = (ProgressBar) findViewById(R.id.progressBarIndeterminate);
            anim.setVisibility(View.GONE);
            break;
        }
      }
    });

    mediaPlayer.start();
  }

  @Override
    protected void onPause() {
      super.onPause();

      PowerManager pm = (PowerManager) getSystemService(getApplicationContext().POWER_SERVICE);
      PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
      wl.release();
  }
}
