package com.example.slots;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    private String rulesString = "Rules:\n1. Press Start\n2. Wait until all slots show the same image\n3. Press Stop\n\nPoints:\n1. If all images match, 50 points.\n2. Otherwise, 10 points per cherry\n\nCurrent Points: ";
    private TextView rulesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        rulesView = findViewById(R.id.rulesView);
        Intent intent = getIntent();
        rulesView.setText(rulesString + intent.getIntExtra("POINTS", -1));
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
