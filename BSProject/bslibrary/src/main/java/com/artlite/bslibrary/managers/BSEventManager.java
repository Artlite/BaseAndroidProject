package com.artlite.bslibrary.managers;

import android.content.Context;
import android.util.Log;
import android.util.LruCache;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dlernatovich on 2/17/2017.
 */

public final class BSEventManager extends BSBaseManager {

    /**
     * Instance of the {@link BSEventManager}
     */
    private static BSEventManager instance;

    /**
     * Instance of the {@link LruCache}
     */
    private final LruCache<String, List<WeakReference<OnEventCallback>>> events;

    /**
     * Method which provide the initialization of {@link BSEventManager}
     *
     * @param context instance of {@link Context}
     * @return initialization result
     * @warning should be initializing in application singleton
     */
    public static void init(@Nullable final Context context) {
        if (isNull(instance)) {
            instance = new BSEventManager(context);
        } else {
            Log.e(TAG, "EventManager is already created");
        }
    }

    /**
     * Method which provide the instance of {@link BSEventManager}
     *
     * @return instance of {@link BSEventManager}
     */
    @Nullable
    protected static BSEventManager getInstance() {
        if (isNull(instance)) {
            Log.e(TAG, "EventManager should be initialized the Application singleton");
        }
        return instance;
    }

    /**
     * Default constructor
     */
    private BSEventManager(@Nullable final Context context) {
        super(context);
        this.events = new LruCache<>(K_CACHE_SIZE);
    }

    /**
     * Method which provide the registering of the {@link OnEventCallback}
     *
     * @param owner    instance of owner {@link Object}
     * @param callback instance of {@link OnEventCallback}
     * @return registering results
     */
    public static boolean register(@Nullable final Object owner,
                                   @Nullable final OnEventCallback callback) {
        if (nullValidate(owner, callback)) {
            return register(owner.getClass(), callback);
        }
        return false;
    }

    /**
     * Method which provide the registering of the {@link OnEventCallback}
     *
     * @param owner    instance of owner {@link Class}
     * @param callback instance of {@link OnEventCallback}
     * @return registering results
     */
    public static boolean register(@Nullable final Class owner,
                                   @Nullable final OnEventCallback callback) {
        if (nullValidate(owner, callback)) {
            return register(getClassEvent(owner), callback);
        }
        return false;
    }

    /**
     * Method which provide the registering of the {@link OnEventCallback}
     *
     * @param event    instance of owner {@link Class}
     * @param callback instance of {@link OnEventCallback}
     * @return registering results
     */
    public static boolean register(@Nullable final String event,
                                   @Nullable final OnEventCallback callback) {
        //Validate instance
        if (!hasInstance()) {
            return false;
        }
        //Register Event
        synchronized (instance.events) {
            if (nullValidate(event, callback)) {
                if (isNull(instance.events.get(event))) {
                    instance.events.put(event, new ArrayList<WeakReference<OnEventCallback>>());
                }
                final List<WeakReference<OnEventCallback>> callbacks = instance.events.get(event);
                if (callbacks != null) {
                    callbacks.add(new WeakReference<OnEventCallback>(callback));
                }
                return true;
            }
            return false;
        }
    }

    /**
     * Method which provide the sending of the {@link Event}
     *
     * @param owner owner class
     * @param code  event code
     * @param <T>   object types
     * @return sending result
     */
    public static <T extends Object> void send(@Nullable final Class owner, int code) {
        send(owner, code, null);
    }

    /**
     * Method which provide the sending of the {@link Event}
     *
     * @param owner  owner class
     * @param code   event code
     * @param object instance of object
     * @param <T>    object types
     * @return sending result
     */
    public static <T extends Object> void send(@Nullable final Class owner,
                                               int code,
                                               @Nullable final T object) {
        send(owner, new Event(code, object));
    }

    /**
     * Method which provide the sending of the {@link Event}
     *
     * @param owner owner class
     * @param event instance of {@link Event}
     * @param <T>   object types
     * @return sending result
     */
    public static <T extends Object> void send(@Nullable final Class owner,
                                               @Nullable final Event event) {
        if (nullValidate(owner)) {
            send(getClassEvent(owner), event);
        }
    }

    /**
     * Method which provide the sending of the {@link Event}
     *
     * @param event event value
     * @param code  event code
     * @param <T>   object types
     * @return sending result
     */
    public static <T extends Object> void send(@Nullable final String event, int code) {
        send(event, code, null);
    }

    /**
     * Method which provide the sending of the {@link Event}
     *
     * @param event  event value
     * @param code   event code
     * @param object instance of object
     * @param <T>    object types
     * @return sending result
     */
    public static <T extends Object> void send(@Nullable final String event,
                                               int code,
                                               @Nullable final T object) {
        send(event, new Event(code, object));
    }

    /**
     * Method which provide the sending of the {@link Event}
     *
     * @param event event value
     * @param event instance of {@link Event}
     * @param <T>   object types
     * @return sending result
     */
    public static <T extends Object> void send(@Nullable final String event,
                                               @Nullable final Event object) {
        //Validate instance
        if (!hasInstance()) {
            return;
        }
        //Execute sending
        if (nullValidate(event, object)) {
            final Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    synchronized (instance.events) {
                        if (nullValidate(instance.events.get(event))) {
                            final List<WeakReference<OnEventCallback>> references = instance
                                    .events.get(event);
                            final List<WeakReference<OnEventCallback>> missing = new ArrayList<>();
                            for (final WeakReference<OnEventCallback> reference : references) {
                                if (!execute(reference, object)) {
                                    missing.add(reference);
                                }
                            }
                            references.removeAll(missing);
                        }
                    }
                }
            };
            new Thread(runnable).start();
        }
    }

    /**
     * Method which provide the getting of the class event
     *
     * @param aClass
     * @return
     */
    @NonNull
    private static String getClassEvent(@NonNull final Class aClass) {
        return String.format("class_event_%s", aClass.getName());
    }

    /**
     * Method which provide the executing {@link OnEventCallback}
     *
     * @param reference instance of {@link WeakReference<OnEventCallback>}
     * @param event     instance of {@link Event}
     * @return executing result
     */
    private static boolean execute(@Nullable final WeakReference<OnEventCallback> reference,
                                   @Nullable final Event event) {
        if (nullValidate(reference, event)) {
            instance.getHandler().post(new Runnable() {
                @Override
                public void run() {
                    reference.get().onEvent(event);
                }
            });
            return true;
        }
        return false;
    }

    /**
     * Method which provide the {@link BSEventManager} instance validation
     *
     * @return validation result
     */
    private static boolean hasInstance() {
        return getInstance() != null;
    }

    //==============================================================================================
    //                                          CLASSES
    //==============================================================================================

    //==============================================================================================
    //                                           EVENT
    //==============================================================================================

    /**
     * Event class
     */
    public static class Event {
        private static final String TAG = Event.class.getName();
        private int code;
        private WeakReference<Object> object;

        /**
         * Constructor which provide the create {@link Event} from
         *
         * @param code event code
         */
        public Event(int code) {
            this(code, null);
        }

        /**
         * Constructor which provide the create {@link Event} from
         *
         * @param code   event code
         * @param object object instance
         */
        public Event(int code, @Nullable final Object object) {
            this.code = code;
            if (nullValidate(object)) {
                this.object = new WeakReference<Object>(object);
            }
        }

        /**
         * Method which provide the getting of the event code
         *
         * @return
         */
        public int getCode() {
            return code;
        }

        /**
         * Method which provide the getting of the {@link Object}
         *
         * @param <T> object type
         * @return object
         */
        @Nullable
        public <T extends Object> T getObject() {
            final String methodName = "T getObject()";
            try {
                return (T) object.get();
            } catch (Exception ex) {
                Log.e(TAG, methodName, ex);
            }
            return null;
        }

        /**
         * Method which provide the equaling of the {@link Event}
         *
         * @param object instance of equaling {@link Object}
         * @return equaling results
         */
        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Event event = (Event) object;
            return code == event.code;

        }

        /**
         * Method which provide the getting of the hash code
         *
         * @return
         */
        @Override
        public int hashCode() {
            return code;
        }

        /**
         * Method which provide the generating to {@link String} value
         *
         * @return object {@link String} value
         */
        @Override
        public String toString() {
            return "Event{" +
                    "code=" + code +
                    ", object=" + object +
                    '}';
        }
    }

    //==============================================================================================
    //                                          CALLBACK
    //==============================================================================================

    /**
     * Callback of {@link BSEventManager}
     */
    public interface OnEventCallback {
        /**
         * Method which provide the event performing
         *
         * @param event instance of {@link Event}
         */
        void onEvent(@NonNull final Event event);
    }
}
