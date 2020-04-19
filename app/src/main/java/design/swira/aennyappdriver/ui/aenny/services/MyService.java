package design.swira.aennyappdriver.ui.aenny.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.ui.aenny.mainpage.MainDriverActivity;
import design.swira.aennyappdriver.utils.Constants;

public class MyService extends Service {
    private HubConnection hubConnection;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }



    @Override
    public void onCreate() {
        super.onCreate();
        String signalrurl= ApiClient.signalRBaseUrl;
        hubConnection = HubConnectionBuilder.create(signalrurl).build();


        Intent notificationIntent = new Intent(this, MainDriverActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_notify_icon)
                .setContentTitle("New Trip!")
                .setContentText("Client want new trip!")
                .setContentIntent(pendingIntent).build();

        startForeground(1337, notification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        hubConnection.start();

        try{
            String driveridd= String.valueOf(Constants.getClientId(getApplicationContext()));
            hubConnection.send("OnConnectedAsync",driveridd,3);
            Log.i("ConnectionStatus",driveridd + " ,3, " + true );
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        //return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try{
            String driveridd= String.valueOf(Constants.getClientId(getApplicationContext()));
            hubConnection.send("OnDisconnectedAsync",driveridd,3);
            Log.i("ConnectionStatus",driveridd + " ,3, " + false );
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
        hubConnection.stop();
    }

}
