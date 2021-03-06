package com.sandrowinkler.example_apps.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private LinearLayout layout;
    private EditText stepsEditText;
    private EditText secondsEditText;
    private Button splashAgainButton;

    private int numberOfSteps;
    private int secondsBetweenSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initActivity();
    }

    private void initActivity() {
        initWidgets();
        initFromIntent();
        addListeners();
        updateUi();
    }

    private void initWidgets() {
        layout = (LinearLayout) findViewById(R.id.activity_main_edittexts);
        stepsEditText = (EditText) findViewById(R.id.activity_main_edittext_steps);
        secondsEditText = (EditText) findViewById(R.id.activity_main_edittext_seconds);
        splashAgainButton = (Button) findViewById(R.id.activity_main_button_splash_again);
    }

    private void initFromIntent() {
        final Intent intent = getIntent();
        numberOfSteps = intent.getIntExtra(SplashActivity.NUMBER_OF_STEPS, SplashActivity.DEFAULT_NUMBER_OF_STEPS);
        secondsBetweenSteps = intent.getIntExtra(SplashActivity.SECONDS_BETWEEN_STEPS, SplashActivity.DEFAULT_SECONDS_BETWEEN_STEPS);
    }

    private void addListeners() {
        addButtonOnClickListener();
        addButtonOnLongClickListener();
    }

    private void updateUi() {
        stepsEditText.setText(String.valueOf(numberOfSteps));
        secondsEditText.setText(String.valueOf(secondsBetweenSteps));
    }

    private void addButtonOnClickListener() {
        splashAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadSplashAgain();
            }
        });
    }

    private void addButtonOnLongClickListener() {
        splashAgainButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                showFurtherOptions();
                return true;
            }
        });
    }

    private void loadSplashAgain() {
        updateFromUi();
        startSplashActivity();
        finish();
    }

    private void showFurtherOptions() {
        layout.setVisibility(View.VISIBLE);
    }

    private void updateFromUi() {
        numberOfSteps = Integer.parseInt(stepsEditText.getText().toString());
        secondsBetweenSteps = Integer.parseInt(secondsEditText.getText().toString());
    }

    private void startSplashActivity() {
        final Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra(SplashActivity.NUMBER_OF_STEPS, numberOfSteps);
        intent.putExtra(SplashActivity.SECONDS_BETWEEN_STEPS, secondsBetweenSteps);
        startActivity(intent);
    }
}
