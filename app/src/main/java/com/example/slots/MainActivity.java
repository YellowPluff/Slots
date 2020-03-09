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
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView slotOne, slotTwo, slotThree;
    private Drawable cherryImage, pearImage, strawberryImage, grapeImage;
    private Drawable[] images;
    private TextView pointsView;
    private SeekBar speedBar;
    private Button startButton;
    private int points;
    private Handler handler;
    private SlotImageChangerOne imageChangerOne;
    private int nextImageOne = 1;
    private SlotImageChangerTwo imageChangerTwo;
    private int nextImageTwo = 2;
    private SlotImageChangerThree imageChangerThree;
    private int nextImageThree = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFields();
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
        startButton = findViewById(R.id.startStopButton);
        points = 60;
        handler = new Handler();
        imageChangerOne = new SlotImageChangerOne();
        imageChangerTwo = new SlotImageChangerTwo();
        imageChangerThree = new SlotImageChangerThree();
    }

    public void startSlots(View view) {
        if(startButton.getText().toString().equals("Start")) {
            handler.postDelayed(imageChangerOne, 0);
            handler.postDelayed(imageChangerTwo, 0);
            handler.postDelayed(imageChangerThree, 0);
            startButton.setText("Stop");
        } else {
            handler.removeCallbacks(imageChangerOne);
            handler.removeCallbacks(imageChangerTwo);
            handler.removeCallbacks(imageChangerThree);
            startButton.setText("Start");
        }
        /**
         * Todo
         * 1. Get images to randomize when startButton is pressed and to stop when stop button is pressed
         * 2. if (nextImageOne == 2) this means slowOne has a cherry and therefore +10 points
         * 3. Play with speed
         */
//        pointsView.setText("Points: 60");
//        int seekBarValue = speedBar.getProgress();

//        handler.postDelayed(imageChangerOne, 0);
//        handler.postDelayed(imageChangerTwo, 0);
//        handler.postDelayed(imageChangerThree, 0);
    }

    public void rulesClicked(View view) {
        Intent intent = new Intent(this, RulesActivity.class);
        intent.putExtra("POINTS", points);
        startActivity(intent);
    }

    private class SlotImageChangerOne implements Runnable {
        @Override
        public void run() {
            if(handler.hasCallbacks(imageChangerOne)) {
                Drawable imageToDraw = images[nextImageOne];
                nextImageOne = (nextImageOne == 3) ? 0 : nextImageOne+1;
                slotOne.setImageDrawable(imageToDraw);
                handler.postDelayed(imageChangerOne, 500);
            }
        }
    }
    private class SlotImageChangerTwo implements Runnable {
        @Override
        public void run() {
            if(handler.hasCallbacks(imageChangerTwo)) {
                Drawable imageToDraw = images[nextImageTwo];
                nextImageTwo = (nextImageTwo == 3) ? 0 : nextImageTwo+1;
                slotTwo.setImageDrawable(imageToDraw);
                handler.postDelayed(imageChangerTwo, 250);
            }
        }
    }
    private class SlotImageChangerThree implements Runnable {
        @Override
        public void run() {
            if(handler.hasCallbacks(imageChangerThree)) {
                Drawable imageToDraw = images[nextImageThree];
                nextImageThree = (nextImageThree == 3) ? 0 : nextImageThree+1;
                slotThree.setImageDrawable(imageToDraw);
                handler.postDelayed(imageChangerThree, 400);
            }
        }
    }
}
