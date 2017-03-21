package com.artlite.bslibrary.helpers.notification;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.artlite.bslibrary.R;
import com.artlite.bslibrary.helpers.abs.BSBaseHelper;

/**
 * Class which provide the notification functional
 */

public final class BSNotificationHelper extends BSBaseHelper {

    /**
     * Notification ID
     */
    private static final int NOTIFICATION_ID = 0x1;

    /**
     * Method which provide the show notification from the activity
     *
     * @param currentActivity  current activity
     * @param anotherActivity  activity which should open when tap on notification
     * @param notificationIcon notification icon
     * @param largeIcon        large notification icon
     * @param title            notification title
     * @param contentText      notification text
     * @param subtext          notification subtext
     */
    public static void showNotification(@Nullable final Activity currentActivity,
                                        @Nullable final Class anotherActivity,
                                        @DrawableRes int notificationIcon,
                                        @DrawableRes int largeIcon,
                                        @Nullable final String title,
                                        @Nullable final String contentText,
                                        @Nullable final String subtext) {
        //Build the intent
        Intent intent = new Intent(currentActivity, anotherActivity);
        PendingIntent pendingIntent = PendingIntent.getActivity(currentActivity, 0, intent, 0);

        //Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(currentActivity);
        builder.setSmallIcon(notificationIcon);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(currentActivity.getResources(), largeIcon));
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        //Set texts to notification
        builder.setContentTitle(title);
        builder.setContentText(contentText);
        builder.setSubText(subtext);

        //Show the notification
        NotificationManager notificationManager = (NotificationManager) currentActivity.getSystemService(
                Activity.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Method which provide the show notification from the activity
     *
     * @param currentActivity  current activity
     * @param anotherActivity  activity which should open when tap on notification
     * @param notificationIcon notification icon
     * @param title            notification title
     * @param contentText      notification text
     * @param subtext          notification subtext
     */
    public static void showNotification(@Nullable final Activity currentActivity,
                                        @Nullable final Class anotherActivity,
                                        @DrawableRes int notificationIcon,
                                        @Nullable final String title,
                                        @Nullable final String contentText,
                                        @Nullable final String subtext) {

        //Build the intent
        Intent intent = new Intent(currentActivity, anotherActivity);
        PendingIntent pendingIntent = PendingIntent.getActivity(currentActivity, 0, intent, 0);

        //Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(currentActivity);
        builder.setSmallIcon(notificationIcon);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(currentActivity.getResources(), notificationIcon));
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        //Set texts to notification
        builder.setContentTitle(title);
        builder.setContentText(contentText);
        builder.setSubText(subtext);

        //Show the notification
        NotificationManager notificationManager = (NotificationManager) currentActivity.getSystemService(
                Activity.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Method which provide the show notification from the activity
     *
     * @param currentActivity current activity
     * @param anotherActivity activity which should open when tap on notification
     * @param title           notification title
     * @param contentText     notification text
     * @param subtext         notification subtext
     */
    public static void showNotification(@Nullable final Activity currentActivity,
                                        @Nullable final Class anotherActivity,
                                        @Nullable final String title,
                                        @Nullable final String contentText,
                                        @Nullable final String subtext) {

        //Get current application icon ID
        int appIcon = currentActivity.getApplication().getApplicationInfo().icon;

        //Build the intent
        Intent intent = new Intent(currentActivity, anotherActivity);
        PendingIntent pendingIntent = PendingIntent.getActivity(currentActivity, 0, intent, 0);

        //Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(currentActivity);
        builder.setSmallIcon(appIcon);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(currentActivity.getResources(), appIcon));
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        //Set texts to notification
        builder.setContentTitle(title);
        builder.setContentText(contentText);
        builder.setSubText(subtext);

        //Show the notification
        NotificationManager notificationManager = (NotificationManager) currentActivity.getSystemService(
                Activity.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Method which provide the show notification from the activity
     *
     * @param currentActivity current activity
     * @param anotherActivity activity which should open when tap on notification
     * @param title           notification title
     * @param contentText     notification text
     */
    public static void showNotification(@Nullable final Activity currentActivity,
                                        @Nullable final Class anotherActivity,
                                        @Nullable final String title,
                                        @Nullable final String contentText) {

        //Get current application icon ID
        int appIcon = currentActivity.getApplication().getApplicationInfo().icon;

        //Build the intent
        Intent intent = new Intent(currentActivity, anotherActivity);
        PendingIntent pendingIntent = PendingIntent.getActivity(currentActivity, 0, intent, 0);

        //Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(currentActivity);
        builder.setSmallIcon(appIcon);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(currentActivity.getResources(), appIcon));
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        //Set texts to notification
        builder.setContentTitle(title);
        builder.setContentText(contentText);

        //Show the notification
        NotificationManager notificationManager = (NotificationManager) currentActivity.getSystemService(
                Activity.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Method which provide the show notification from the activity
     *
     * @param currentActivity current activity
     * @param anotherActivity activity which should open when tap on notification
     * @param title           notification title
     * @param contentText     notification text
     */
    public static void showNotification(@Nullable final Context currentActivity,
                                        @Nullable final Class anotherActivity,
                                        @DrawableRes int appIcon,
                                        @Nullable final String title,
                                        @Nullable final String contentText) {

        //Get current application icon ID

        //Build the intent
        Intent intent = new Intent(currentActivity, anotherActivity);
        PendingIntent pendingIntent = PendingIntent.getActivity(currentActivity, 0, intent, 0);

        //Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(currentActivity);
        builder.setSmallIcon(appIcon);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(currentActivity.getResources(), appIcon));
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        //Set texts to notification
        builder.setContentTitle(title);
        builder.setContentText(contentText);

        //Show the notification
        NotificationManager notificationManager = (NotificationManager) currentActivity.getSystemService(
                Activity.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Method which provide the show notification from the intent
     *
     * @param currentActivity current activity
     * @param intent          intent which should open when tap on notification
     * @param title           notification title
     * @param contentText     notification text
     */
    public static void showNotification(@Nullable final Context currentActivity,
                                        @Nullable final Intent intent,
                                        @DrawableRes int appIcon,
                                        @Nullable final String title,
                                        @Nullable final String contentText) {

        //Get current application icon ID

        //Build the intent
        PendingIntent pendingIntent = PendingIntent.getActivity(currentActivity, 0, intent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        //Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(currentActivity);
        builder.setSmallIcon(appIcon);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(currentActivity.getResources(), appIcon));
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        //Set texts to notification
        builder.setContentTitle(title);
        builder.setContentText(contentText);

        //Show the notification
        NotificationManager notificationManager = (NotificationManager) currentActivity.getSystemService(
                Activity.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Method which provide the show notification from the intent
     *
     * @param currentActivity current activity
     * @param title           notification title
     * @param contentText     notification text
     */
    public static void showNotification(@Nullable final Context currentActivity,
                                        @Nullable final String title,
                                        @Nullable final String contentText) {

        //Get current application icon ID

        //Build the intent

        //Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(currentActivity);
        builder.setSmallIcon(R.mipmap.ic_notification);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(currentActivity.getResources(),
                R.mipmap.ic_notification));
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        //Set texts to notification
        builder.setContentTitle(title);
        builder.setContentText(contentText);

        //Show the notification
        NotificationManager notificationManager = (NotificationManager) currentActivity.getSystemService(
                Activity.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    /**
     * Method which provide the show notification from the intent
     *
     * @param currentActivity current activity
     * @param title           notification title
     * @param contentText     notification text
     */
    public static void showNotification(@Nullable final Context currentActivity,
                                        @DrawableRes int iconID,
                                        @Nullable final String title,
                                        @Nullable final String contentText) {

        //Get current application icon ID

        //Build the intent

        //Create notification builder
        NotificationCompat.Builder builder = new NotificationCompat.Builder(currentActivity);
        builder.setSmallIcon(iconID);
        builder.setAutoCancel(true);
        builder.setLargeIcon(BitmapFactory.decodeResource(currentActivity.getResources(), iconID));
        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));

        //Set texts to notification
        builder.setContentTitle(title);
        builder.setContentText(contentText);

        //Show the notification
        NotificationManager notificationManager = (NotificationManager) currentActivity.getSystemService(
                Activity.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    ///


}
