package com.example.slots;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ImageView slotOne, slotTwo, slotThree;
    private Drawable cherryImage, pearImage, strawberryImage, grapeImage;
    private TextView pointsView;
    private SeekBar speedBar;
    private Button startButton, rulesButton;

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
        pointsView = findViewById(R.id.pointsView);
        speedBar = findViewById(R.id.speedSeekBar);
        startButton = findViewById(R.id.startStopButton);
        rulesButton = findViewById(R.id.rulesButton);
    }

    public void startSlots(View view) {
        /**
         * Todo:
         * 1. Make rules activity
         * 2. Get images to randomize when startButton is pressed and to stop when stop button is pressed
         */
//        slotOne.setImageDrawable(grapeImage);
//        slotTwo.setImageDrawable(pearImage);
//        slotThree.setImageDrawable(strawberryImage);
//        pointsView.setText("Points: 60");
//        int seekBarValue = speedBar.getProgress();
//        startButton.setText("Stop");
//        Log.w("fatal", seekBarValue+"");
    }
}
