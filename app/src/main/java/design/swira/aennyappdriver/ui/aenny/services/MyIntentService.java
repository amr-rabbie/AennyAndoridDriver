package design.swira.aennyappdriver.ui.aenny.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.ui.aenny.mainpage.MainDriverActivity;
import design.swira.aennyappdriver.utils.Constants;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "design.swira.aennyappdriver.ui.aenny.services.action.FOO";
    private static final String ACTION_BAZ = "design.swira.aennyappdriver.ui.aenny.services.action.BAZ";

    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "design.swira.aennyappdriver.ui.aenny.services.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "design.swira.aennyappdriver.ui.aenny.services.extra.PARAM2";
    private HubConnection hubConnection;
    Context context;

    public MyIntentService(Context context) {
        super("MyIntentService");
        this.context=context;
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            }
        }
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new Mybinder();
    }

    @Override
    public void onCreate() {
        String signalrurl= ApiClient.signalRBaseUrl;
        hubConnection = HubConnectionBuilder.create(signalrurl).build();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopSR();
    }

    public void startSR(){
        hubConnection.start();
        String driveridd= String.valueOf(Constants.getClientId(context));
        hubConnection.send("OnConnectedAsync",driveridd,3);
    }

    public void stopSR(){
        hubConnection.stop();
        String driveridd= String.valueOf(Constants.getClientId(context));
        hubConnection.send("OnDisconnectedAsync",driveridd,3);
    }

    public class Mybinder extends Binder {
        public MyIntentService GetmyInstance(){
            return MyIntentService.this;
        }
    }
}
