package com.sandrowinkler.example_apps.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends Activity {

    private LinearLayout layout;
    private EditText stepsEditText;
    private EditText secondsEditText;
    private Button splashAgainButton;
    private ImageView iconImageView;
    private ImageView infoIconImageView;

    private Toast infoToast;

    private int numberOfSteps;
    private double secondsBetweenSteps;

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
        iconImageView = (ImageView) findViewById(R.id.activity_main_icon);
        infoIconImageView = (ImageView) findViewById(R.id.activity_main_info_icon);
    }

    private void initFromIntent() {
        final Intent intent = getIntent();
        numberOfSteps = intent.getIntExtra(SplashActivity.NUMBER_OF_STEPS, SplashActivity.DEFAULT_NUMBER_OF_STEPS);
        secondsBetweenSteps = intent.getDoubleExtra(SplashActivity.SECONDS_BETWEEN_STEPS, SplashActivity.DEFAULT_SECONDS_BETWEEN_STEPS);
    }

    private void addListeners() {
        addButtonOnClickListener();
        addIconOnClickListener();
        addInfoIconOnClickListener();
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

    private void addIconOnClickListener() {
        iconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleFurtherOptions();
            }
        });
    }

    private void addInfoIconOnClickListener() {
        infoIconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showInfoToast();
            }
        });
    }

    private void loadSplashAgain() {
        updateFromUi();
        startSplashActivity();
        finish();
    }

    private void toggleFurtherOptions() {
        if (areFurtherOptionsVisible()) {
            layout.setVisibility(View.INVISIBLE);
        } else {
            layout.setVisibility(View.VISIBLE);
        }
    }

    private void showInfoToast() {
        if (infoToast != null) {
            infoToast.cancel();
        }

        infoToast = Toast.makeText(MainActivity.this, R.string.activity_main_toast_info, Toast.LENGTH_SHORT);
        infoToast.show();
    }

    private boolean areFurtherOptionsVisible() {
        return layout.getVisibility() == View.VISIBLE;
    }

    private void updateFromUi() {
        numberOfSteps = Integer.parseInt(stepsEditText.getText().toString());
        secondsBetweenSteps = Double.parseDouble(secondsEditText.getText().toString());
    }

    private void startSplashActivity() {
        final Intent intent = new Intent(this, SplashActivity.class);
        intent.putExtra(SplashActivity.NUMBER_OF_STEPS, numberOfSteps);
        intent.putExtra(SplashActivity.SECONDS_BETWEEN_STEPS, secondsBetweenSteps);
        startActivity(intent);
    }
}
