package com.sandrowinkler.example_apps.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashActivity extends Activity implements LoadingTaskFinishedListener {

    public static final String NUMBER_OF_STEPS = "steps";
    public static final String SECONDS_BETWEEN_STEPS = "seconds";

    public static final int DEFAULT_NUMBER_OF_STEPS = 100;
    public static final double DEFAULT_SECONDS_BETWEEN_STEPS = 0.1;

    private ProgressBar progressBar;
    private ImageView iconImageView;

    private LoadingTask loadingTask;
    private int numberOfSteps;
    private double secondsBetweenSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initActivity();
        startLoading();
    }

    private void initActivity() {
        initWidgets();
        initFromIntent();
        addListeners();
    }

    private void startLoading() {
        loadingTask = new LoadingTask(progressBar, this, numberOfSteps, secondsBetweenSteps);
        loadingTask.execute();
    }

    private void initWidgets() {
        progressBar = (ProgressBar) findViewById(R.id.activity_splash_progress_bar);
        iconImageView = (ImageView) findViewById(R.id.activity_splash_icon);
    }

    private void initFromIntent() {
        final Intent intent = getIntent();
        numberOfSteps = intent.getIntExtra(NUMBER_OF_STEPS, DEFAULT_NUMBER_OF_STEPS);
        secondsBetweenSteps = intent.getDoubleExtra(SECONDS_BETWEEN_STEPS, DEFAULT_SECONDS_BETWEEN_STEPS);
    }

    private void addListeners() {
        iconImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                skipLoading();
            }
        });
    }

    private void skipLoading() {
        loadingTask.cancel(true);
        completeSplash();
    }

    @Override
    public void onLoadingFinished() {
        completeSplash();
    }

    private void completeSplash() {
        startMainActivity();
        finish();
    }

    private void startMainActivity() {
        final Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(NUMBER_OF_STEPS, numberOfSteps);
        intent.putExtra(SECONDS_BETWEEN_STEPS, secondsBetweenSteps);
        startActivity(intent);
    }
}
