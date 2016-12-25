package com.sandrowinkler.example_apps.splashscreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashActivity extends Activity implements LoadingTaskFinishedListener {

    public static final String NUMBER_OF_STEPS = "steps";
    public static final String SECONDS_BETWEEN_STEPS = "seconds";

    public static final int DEFAULT_NUMBER_OF_STEPS = 10;
    public static final int DEFAULT_SECONDS_BETWEEN_STEPS = 1;

    private ProgressBar progressBar;
    private int numberOfSteps;
    private int secondsBetweenSteps;

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
    }

    private void startLoading() {
        final LoadingTask loadingTask = new LoadingTask(progressBar, this);
        loadingTask.execute(numberOfSteps, secondsBetweenSteps);
    }

    private void initWidgets() {
        progressBar = (ProgressBar) findViewById(R.id.activity_splash_progress_bar);
    }

    private void initFromIntent() {
        final Intent intent = getIntent();
        numberOfSteps = intent.getIntExtra(NUMBER_OF_STEPS, DEFAULT_NUMBER_OF_STEPS);
        secondsBetweenSteps = intent.getIntExtra(SECONDS_BETWEEN_STEPS, DEFAULT_SECONDS_BETWEEN_STEPS);
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
