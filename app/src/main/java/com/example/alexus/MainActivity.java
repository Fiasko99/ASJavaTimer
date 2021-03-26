package com.example.alexus;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class MainActivity extends AppCompatActivity {
    TextView _textViewCurrentDayTime;
    TextView tvHour;
    TextView tvMinute;
    TextView tvSecond;
    public boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _textViewCurrentDayTime = findViewById(R.id.textViewCurrentDayTime);
        refreshCurrentDateTime();
        tvHour = findViewById(R.id.hour);
        tvMinute = findViewById(R.id.minute);
        tvSecond = findViewById(R.id.second);

    }

    public void addTime(View v) {
        TextView tv = (TextView) findViewById(v.getId());
        Integer num = Integer.parseInt(tv.getText().toString()) + 1;
        String str = num.toString();
        tv.setText((num > 9) ? str : "0" + str);
    }

    public void setTimer(View v) {
        if (v.getId() == R.id.StartBtn) {
            startTimer();
            editEnable(v.getId(), false);
            editEnable(R.id.ClearBtn, true);
        } else if (v.getId() == R.id.ClearBtn) {
            resetTime();
        }
    }

    public void resetTime() {
        TextView hours = (TextView) findViewById(R.id.hour);
        TextView minutes = (TextView) findViewById(R.id.minute);
        TextView seconds = (TextView) findViewById(R.id.second);
        hours.setText("00");
        minutes.setText("00");
        seconds.setText("00");
    }

    public void editEnable(Integer id, Boolean flag) {
        Button btn = (Button) findViewById(id);
        btn.setEnabled(flag);
    }

    public void startTimer() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Integer num;
                if (Integer.parseInt(tvSecond.getText().toString()) > 0) {
                    num = Integer.parseInt(tvSecond.getText().toString()) - 1;
                    tvSecond.setText(num.toString());
                } else {
                    if (Integer.parseInt(tvMinute.getText().toString()) > 0) {
                        num = Integer.parseInt(tvMinute.getText().toString()) - 1;
                        tvMinute.setText(num.toString());
                        tvSecond.setText("59");
                    } else {
                        if (Integer.parseInt(tvHour.getText().toString()) > 0) {
                            num = Integer.parseInt(tvHour.getText().toString()) - 1;
                            tvHour.setText(num.toString());
                            tvMinute.setText("59");
                            tvSecond.setText("59");
                        }
                    }
                }
                startTimer();
            }
        }, 1000);
    }
    private void refreshCurrentDateTime() {
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                LocalTime time = LocalTime.now(ZoneId.of("Europe/Moscow"));
                _textViewCurrentDayTime.setText(time.truncatedTo(ChronoUnit.SECONDS).toString());
                refreshCurrentDateTime();
            }
        }, 1000);
    }
}


