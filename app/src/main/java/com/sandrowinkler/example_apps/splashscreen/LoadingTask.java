package com.sandrowinkler.example_apps.splashscreen;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by winklerrr on 25/12/2016.
 */

public class LoadingTask extends AsyncTask<Integer, Integer, Void> {

    private final ProgressBar progressBar;
    private final LoadingTaskFinishedListener listener;

    public LoadingTask(final ProgressBar progressBar, final LoadingTaskFinishedListener listener) {
        this.progressBar = progressBar;
        this.listener = listener;
    }

    @Override
    protected Void doInBackground(Integer... params) {
        load(params[0], params[1]);
        return null;
    }

    private void load(final int numberOfSteps, final int secondsBetweenSteps) {
        for (int currentStep = 0; currentStep < numberOfSteps; currentStep++) {
            final int progressInPercent = 100 * currentStep / numberOfSteps;
            publishProgress(progressInPercent);

            try {
                Thread.sleep(secondsBetweenSteps * 1000);
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
