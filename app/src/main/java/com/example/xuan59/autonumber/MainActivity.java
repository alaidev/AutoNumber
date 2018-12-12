package com.example.xuan59.autonumber;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.showLoading)
    Button showLoading;

    @BindViews({R.id.auto_number, R.id.auto_number1, R.id.auto_number2})
    List<AutoNumberView> autoNumberView;

    @BindView(R.id.number_value)
    SeekBar numberValue;

    @BindView(R.id.auto_speed)
    SeekBar autoSpeed;

    @BindView(R.id.value)
    TextView value;

    @BindView(R.id.speed)
    TextView speed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        autoNumberView.get(0).setNumber((int) (Math.random() * 500 + 1000));
        autoNumberView.get(1).setNumber((int) (Math.random() * 500 + 1000));
        autoNumberView.get(2).setNumber((int) (Math.random() * 500 + 1000));
        showLoading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (AutoNumberView auto : autoNumberView) {
                    auto.startAnimation();
                }
            }
        });
        numberValue.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                value.setText("设置值:" + progress + "* Math.random() * 1000");
                for (AutoNumberView auto : autoNumberView) {
                    auto.setNumber((int) ((Math.random() * 1000) * progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        autoSpeed.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                speed.setText("设置速度:" + progress + "* 100");
                for (AutoNumberView auto : autoNumberView) {
                    auto.setAutoSpeed(100 * progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
