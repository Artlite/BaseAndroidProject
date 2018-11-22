package com.artlite.bslibrary.tasks;


import android.os.Handler;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Class which provide the action with interval
 */
public class BSRepetitiveTask {

    /**
     * Interface which provide the action callback
     */
    public interface OnActionCallback {

        /**
         * Method which provide the action performing
         */
        void onActionPerformed();
    }

    /**
     * Instance of the {@link TimeMeasure}
     */
    public enum TimeMeasure {
        MILLISECONDS(1),
        SECONDS(1000),
        MINUTES(SECONDS.factor * 60),
        HOURS(MINUTES.factor * 60);

        /**
         * {@link Integer} value of the factor
         */
        protected final int factor;

        /**
         * Default constructor
         *
         * @param factor {@link Integer} value of the factor
         */
        TimeMeasure(int factor) {
            this.factor = factor;
        }
    }

    /**
     * {@link Integer} value of the interval in seconds
     */
    protected final int interval;

    /**
     * Instance of the {@link Handler}
     */
    protected final Handler handler;

    /**
     * Instance of the {@link Runnable}
     */
    protected final Runnable runnable;

    /**
     * Instance of the {@link OnActionCallback}
     */
    protected final OnActionCallback callback;

    /**
     * Instance of the {@link TimeMeasure}
     */
    protected final TimeMeasure timeMeasure;

    /**
     * {@link Boolean} value if it need running
     */
    protected boolean isNeedRunning = false;

    /**
     * Constructor which provide the create of the {@link BSRepetitiveTask}
     *
     * @param interval {@link Integer} value of the interval in seconds
     * @param callback instance of the {@link OnActionCallback}
     */
    public BSRepetitiveTask(@IntRange(from = 1, to = Integer.MAX_VALUE) int interval,
                            @Nullable OnActionCallback callback) {
        this(interval, callback, TimeMeasure.SECONDS);
    }

    /**
     * Constructor which provide the create of the {@link BSRepetitiveTask}
     *
     * @param interval    {@link Integer} value of the interval in seconds
     * @param callback    instance of the {@link OnActionCallback}
     * @param timeMeasure instance of the {@link TimeMeasure}
     */
    public BSRepetitiveTask(@IntRange(from = 1, to = Integer.MAX_VALUE) int interval,
                            @Nullable OnActionCallback callback,
                            @NonNull TimeMeasure timeMeasure) {
        if (callback == null) {
            this.interval = 1;
            this.handler = null;
            this.runnable = null;
            this.callback = null;
            this.timeMeasure = TimeMeasure.SECONDS;
        } else {
            this.interval = (interval <= 0) ? 1 : interval;
            this.handler = new Handler();
            this.callback = callback;
            this.timeMeasure = timeMeasure;
            this.runnable = new Runnable() {
                @Override
                public void run() {
                    count();
                }
            };
        }
    }

    /**
     * Method which provide the start the repetitive task
     */
    public void start() {
        this.isNeedRunning = true;
        this.count();
    }

    /**
     * Method which provide the stop the repetitive task
     */
    public void stop() {
        if ((this.handler == null) || (this.callback == null)) return;
        this.isNeedRunning = false;
        this.handler.removeCallbacks(this.runnable);
    }

    /**
     * Method which provide the start counting
     */
    protected void count() {
        if (!this.isNeedRunning) {
            this.stop();
            return;
        }
        if ((this.handler == null) || (this.callback == null)) return;
        this.handler.postDelayed(this.runnable,
                this.interval * this.timeMeasure.factor);
        this.callback.onActionPerformed();
    }

}
