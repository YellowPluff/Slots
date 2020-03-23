package com.example.slots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView slotOne, slotTwo, slotThree;
    private Drawable cherryImage, pearImage, strawberryImage, grapeImage;
    private Drawable[] images;
    private TextView pointsView;
    private SeekBar speedBar;
    private int speedOne;
    private int speedTwo;
    private int speedThree;
    private Button startButton;
    private int points;
    private Handler handler;
    private SlotImageChangerOne imageChangerOne;
    private int nextImageOne = 1;
    private SlotImageChangerTwo imageChangerTwo;
    private int nextImageTwo = 2;
    private SlotImageChangerThree imageChangerThree;
    private int nextImageThree = 3;
    private boolean isRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFields();
        dealWithBundle(savedInstanceState);
        speedBarListener();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopSpinners();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(isRunning) {
            startSlots(slotOne);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("POINTS", points);
        bundle.putBoolean("RUNNING", isRunning);
    }

    private void dealWithBundle(Bundle bundle) {
        if(bundle == null) {
            points = 0;
            isRunning = false;
        } else {
            points = bundle.getInt("POINTS");
            isRunning = bundle.getBoolean("RUNNING");
            pointsView.setText("Points: " + points);
        }
    }

    private void initFields() {
        slotOne = findViewById(R.id.slotViewOne);
        slotTwo = findViewById(R.id.slotViewTwo);
        slotThree = findViewById(R.id.slotViewThree);
        cherryImage = getDrawable(R.drawable.cherry);
        pearImage = getDrawable(R.drawable.pear);
        strawberryImage = getDrawable(R.drawable.strawberry);
        grapeImage = getDrawable(R.drawable.grape);
        images = new Drawable[]{cherryImage, pearImage, strawberryImage, grapeImage};
        pointsView = findViewById(R.id.pointsView);
        speedBar = findViewById(R.id.speedSeekBar);
        speedOne = 250;
        speedTwo = 80;
        speedThree = 120;
        startButton = findViewById(R.id.startStopButton);
        points = 0;
        handler = new Handler();
        imageChangerOne = new SlotImageChangerOne();
        imageChangerTwo = new SlotImageChangerTwo();
        imageChangerThree = new SlotImageChangerThree();
        isRunning = false;
    }

    public void startSlots(View view) {
        if(startButton.getText().toString().equals("Start")) {
            isRunning = true;
            handler.postDelayed(imageChangerOne, 0);
            handler.postDelayed(imageChangerTwo, 0);
            handler.postDelayed(imageChangerThree, 0);
            startButton.setText("Stop");
        } else {
            isRunning = false;
            stopSlotsWithPoints();
            startButton.setText("Start");
        }
    }

    private void stopSpinners() {
        handler.removeCallbacks(imageChangerOne);
        handler.removeCallbacks(imageChangerTwo);
        handler.removeCallbacks(imageChangerThree);
    }

    private void stopSlotsWithPoints() {
        stopSpinners();
        int pointsToAdd = 0;
        if((nextImageOne == nextImageTwo) && (nextImageOne == nextImageThree)) {
            pointsToAdd = 50;
        } else {
            if(nextImageOne == 1) pointsToAdd = pointsToAdd + 10;
            if(nextImageTwo == 1) pointsToAdd = pointsToAdd + 10;
            if(nextImageThree == 1) pointsToAdd = pointsToAdd + 10;
        }
        points = points + pointsToAdd;
        pointsView.setText("Points: " + points);
    }

    public void rulesClicked(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        intent.putExtra("POINTS", points);
        startActivity(intent);
    }

    public void speedBarListener() {
        speedBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (progress) {
                    default:
                    case 10:
                        setNewSpeed(1);
                        break;
                    case 9:
                        setNewSpeed(1.2);
                        break;
                    case 8:
                        setNewSpeed(1.4);
                        break;
                    case 7:
                        setNewSpeed(1.6);
                        break;
                    case 6:
                        setNewSpeed(1.8);
                        break;
                    case 5:
                        setNewSpeed(2.0);
                        break;
                    case 4:
                        setNewSpeed(2.2);
                        break;
                    case 3:
                        setNewSpeed(2.4);
                        break;
                    case 2:
                        setNewSpeed(2.6);
                        break;
                    case 1:
                        setNewSpeed(2.8);
                        break;
                    case 0:
                        setNewSpeed(3.0);
                        break;
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });
    }

    private void setNewSpeed(double v) {
        speedOne = (int) (300 * v);
        speedTwo = (int) (80 * v);
        speedThree = (int) (120 * v);
    }

    private class SlotImageChangerOne implements Runnable {
        @Override
        public void run() {
            Drawable imageToDraw = images[nextImageOne];
            nextImageOne = (nextImageOne == 3) ? 0 : nextImageOne+1;
            slotOne.setImageDrawable(imageToDraw);
            handler.postDelayed(imageChangerOne, speedOne);
        }
    }
    private class SlotImageChangerTwo implements Runnable {
        @Override
        public void run() {
            Drawable imageToDraw = images[nextImageTwo];
            nextImageTwo = (nextImageTwo == 3) ? 0 : nextImageTwo+1;
            slotTwo.setImageDrawable(imageToDraw);
            handler.postDelayed(imageChangerTwo, speedTwo);
        }
    }
    private class SlotImageChangerThree implements Runnable {
        @Override
        public void run() {
            Drawable imageToDraw = images[nextImageThree];
            nextImageThree = (nextImageThree == 3) ? 0 : nextImageThree+1;
            slotThree.setImageDrawable(imageToDraw);
            handler.postDelayed(imageChangerThree, speedThree);
        }
    }
}
