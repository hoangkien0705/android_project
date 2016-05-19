package org.mangosoft.leagoovn.smarthub.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.annotation.Nullable;

import org.mangosoft.leagoovn.smarthub.R;
import org.mangosoft.leagoovn.smarthub.components.EventBase;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;
import rx.subjects.Subject;

/**
 * Created by thanghx on 4/1/16.
 */
public class MainService extends Service {
    private static final EventBus<EventBase> appBus = EventBus.createSimple();
    private ServiceHandler mServiceHandler;

    /**
     * Get application Global Event Bus
     *
     * @return Return an Singleton EventBus
     */
    public static EventBus<EventBase> getEventBus() {
        return appBus;
    }

    @Override
    public void onCreate() {
        // Start up the thread running the service.  Note that we create a
        // separate thread because the service normally runs in the process's
        // main thread, which we don't want to block.  We also make it
        // background priority so CPU-intensive work will not disrupt our UI.
        HandlerThread thread = new HandlerThread(getString(R.string.service_name), Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();

        // Get the HandlerThread's Looper and use it for our Handler
        mServiceHandler = new ServiceHandler(thread.getLooper());

    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // For each start request, send a message to start a job and deliver the
        // start ID so we know which request we're stopping when we finish the job
        Message msg = mServiceHandler.obtainMessage();
        msg.arg1 = startId;
        if (intent != null) msg.setData(intent.getExtras());
        mServiceHandler.sendMessage(msg);
        // If we get killed, after returning from here, restart
        return START_STICKY;
    }

    /**
     * Return the communication channel to the service.  May return null if
     * clients can not bind to the service.  The returned
     * {@link IBinder} is usually for a complex interface
     * that has been <a href="{@docRoot}guide/components/aidl.html">described using
     * aidl</a>.
     * <p/>
     * <p><em>Note that unlike other application components, calls on to the
     * IBinder interface returned here may not happen on the main thread
     * of the process</em>.  More information about the main thread can be found in
     * <a href="{@docRoot}guide/topics/fundamentals/processes-and-threads.html">Processes and
     * Threads</a>.</p>
     *
     * @param intent The Intent that was used to bind to this service,
     *               as given to {@link Context#bindService
     *               Context.bindService}.  Note that any extras that were included with
     *               the Intent at that point will <em>not</em> be seen here.
     * @return Return an IBinder through which clients can call on to the
     * service.
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class EventBus<T> {
        private final Subject<T, T> subject;

        private EventBus() {
            this(PublishSubject.<T>create().toSerialized());
        }

        private EventBus(Subject<T, T> subject) {
            this.subject = subject;
        }

        public static <T> EventBus<T> createSimple() {
            return new EventBus<>();//PublishSubject is created in constructor
        }

        public static <T> EventBus<T> createRepeating(int numberOfEventsToRepeat) {
            return new EventBus<>(ReplaySubject.<T>createWithSize(numberOfEventsToRepeat));
        }

        public static <T> EventBus<T> createWithLatest() {
            return new EventBus<>(BehaviorSubject.<T>create());
        }

        public <E extends T> void post(E event) {
            subject.onNext(event);
        }

        public <E extends T> Observable<E> observeEvents(Class<E> eventClass) {
            return subject.ofType(eventClass);//pass only events of specified type, filter all other
        }
    }

    // Handler that receives messages from the thread
    private final class ServiceHandler extends Handler {
        public ServiceHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            // Normally we would do some work here, like download a file.
            // For our sample, we just sleep for 5 seconds.
            try {
                Thread.sleep(50000);
            } catch (InterruptedException e) {
                // Restore interrupt status.
                Thread.currentThread().interrupt();
            }
            // Stop the service using the startId, so that we don't stop
            // the service in the middle of handling another job
            stopSelf(msg.arg1);
        }
    }

}