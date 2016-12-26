package com.sandrowinkler.example_apps.splashscreen;

import android.os.AsyncTask;
import android.widget.ProgressBar;

/**
 * Created by winklerrr on 25/12/2016.
 */

public class LoadingTask extends AsyncTask<Void, Integer, Void> {

    public interface LoadingTaskFinishedListener {
        void onLoadingFinished();
    }

    private final ProgressBar progressBar;
    private final LoadingTaskFinishedListener listener;
    private final int numberOfSteps;
    private final double secondsBetweenSteps;

    public LoadingTask(final ProgressBar progressBar, final LoadingTaskFinishedListener listener, final int numberOfSteps, final double secondsBetweenSteps) {
        this.progressBar = progressBar;
        this.listener = listener;
        this.numberOfSteps = numberOfSteps;
        this.secondsBetweenSteps = secondsBetweenSteps;
    }

    @Override
    protected Void doInBackground(Void... params) {
        load();
        return null;
    }

    private void load() {
        for (int currentStep = 0; currentStep < numberOfSteps && !isCancelled(); currentStep++) {
            final int progressInPercent = 100 * currentStep / numberOfSteps;
            publishProgress(progressInPercent);

            try {
                Thread.sleep((long)(secondsBetweenSteps * 1000));
            } catch (InterruptedException ignore) {
            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        progressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        listener.onLoadingFinished();
    }
}
