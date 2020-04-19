package design.swira.aennyappdriver.ui.aenny.receviers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.ui.aenny.mainpage.MainDriverActivity;
import design.swira.aennyappdriver.ui.aenny.services.MyIntentService;
import design.swira.aennyappdriver.utils.Constants;
import design.swira.aennyappdriver.utils.GpsTracker;

public class MyReceiver extends BroadcastReceiver {

    private HubConnection hubConnection;
    private CountDownTimer counttimer;
    int healthy,handcap;
    int tripcanceled=0;
    private String dateoftrip;
    private String acceptTime;
    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    MyIntentService myIntentService;
    private ServiceConnection con;
    private final int intervaltime = 1000;
    private final int intervaltime2 = 100;
    int clientid;
    double clientpickuplong,clientpickuplate,clientdestlong,clientdestlate;
    int mytrip=0;
    double opendoorcost;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        /*Toast.makeText(context, "New Trip!", Toast.LENGTH_SHORT).show();
        throw new UnsupportedOperationException("Not yet implemented");*/

        String signalrurl = ApiClient.signalRBaseUrl;
        hubConnection = HubConnectionBuilder.create(signalrurl).build();
        hubConnection.start();

        try {


            new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    //timer.setText("00 : " + millisUntilFinished / 1000);
                }

                public void onFinish() {
                    //timer.setText("done!");
                    try {
                        String driveridd = String.valueOf(Constants.getClientId(context));
                        hubConnection.send("OnConnectedAsync", driveridd, 3);
                        Log.i("ConnectionStatus", driveridd + " ,3, " + true);
                    } catch (Exception e) {
                        //Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();


            hubConnection.on("NotifiedCurrentLongAndLattForDriver", (Client_Id, Client_Pickup_Long, Client_Pickup_Latt, Trip_Destination_Long, Trip_Destination_Latt, Healty_number, Handicapped_Number, Trip_Time) -> {
                int driverid = Constants.getClientId(context);
                hubConnection.send("GetCurrentLongAndLattForDriver", Client_Id, Client_Pickup_Long, Client_Pickup_Latt, driverid, longitude, latitude);


                Log.i("Client_Id", Client_Id + "," + driverid);


                clientid = Client_Id;
                clientpickuplong = Client_Pickup_Long;
                clientpickuplate = Client_Pickup_Latt;
                clientdestlong = Trip_Destination_Long;
                clientdestlate = Trip_Destination_Latt;
                healthy = Healty_number;
                handcap = Handicapped_Number;

                Log.i("LocClient", Client_Id + " " + Client_Pickup_Long + "," + Client_Pickup_Latt + " " + Trip_Destination_Long + "," + Trip_Destination_Latt + " " + Healty_number + Handicapped_Number);

            }, Integer.class, Double.class, Double.class, Double.class, Double.class, Integer.class, Integer.class, Double.class);


            hubConnection.on("NotifiedNearestDriverLongAndLattForDriver", (Client_Id1, Client_Pickup_Long1, Client_Pickup_Latt1) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                Log.i("FinResult", Client_Id1 + " , " + Client_Pickup_Long1 + "," + Client_Pickup_Latt1);


                //ss="New Trip coming!";

                mytrip = 1;

                //viewNewTrip();


            }, Integer.class, Double.class, Double.class);


            final int delay2 = intervaltime * 1;

            final Handler handler2 = new Handler();
            //int delay = 1000; //milliseconds

            handler2.postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                public void run() {
                    //do something
                    if (mytrip == 1) {
                        /*lll3.setVisibility(View.GONE);
                        lll4.setVisibility(View.VISIBLE);
                        startTimer();*/

                       /* Intent intent=new Intent(context,MainDriverActivity.class);
                        intent.putExtra("clientid",clientid);
                        intent.putExtra("clientpickuplong",clientpickuplong);
                        intent.putExtra("clientpickuplate",clientpickuplate);
                        int rides=healthy+handcap;
                        intent.putExtra("rides",rides);
                        intent.putExtra("clientdestlong",clientdestlong);
                        intent.putExtra("clientdestlate",clientdestlate);
                        context.startActivity(intent);*/


                        /*Intent notificationIntent = new Intent(context, MainDriverActivity.class);

                        notificationIntent.putExtra("clientid",clientid);
                        notificationIntent.putExtra("clientpickuplong",clientpickuplong);
                        notificationIntent.putExtra("clientpickuplate",clientpickuplate);
                        int rides=healthy+handcap;
                        notificationIntent.putExtra("rides",rides);
                        notificationIntent.putExtra("clientdestlong",clientdestlong);
                        notificationIntent.putExtra("clientdestlate",clientdestlate);

                        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1001,
                                notificationIntent, PendingIntent.FLAG_ONE_SHOT);

                        Notification notification = new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.ic_notify_icon)
                                .setContentTitle("New Trip!")
                                .setContentText("Client want new trip!")
                                .setContentIntent(pendingIntent).build();

                        //startForeground(1337, notification);

                        NotificationManager notificationManager =
                                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

                        notificationManager.notify(1001, notification);*/






                        Intent notificationIntent=new Intent(context,MainDriverActivity.class);
                        notificationIntent.putExtra("clientid",clientid);
                        notificationIntent.putExtra("clientpickuplong",clientpickuplong);
                        notificationIntent.putExtra("clientpickuplate",clientpickuplate);
                        int rides=healthy+handcap;
                        notificationIntent.putExtra("rides",rides);
                        notificationIntent.putExtra("clientdestlong",clientdestlong);
                        notificationIntent.putExtra("clientdestlate",clientdestlate);
                        PendingIntent pen= PendingIntent.getActivity(context,0,notificationIntent,PendingIntent.FLAG_ONE_SHOT);
                        Bitmap bitmap= BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_notify_icon);
                        Notification.Builder builder=new Notification.Builder(context);
                        builder.setTicker("New Trip!");
                        builder.setContentTitle("New Trip!");
                        builder.setSmallIcon(R.drawable.ic_notify_icon);
                        builder.setContentText("There is new trip for you !");
                        builder.setLargeIcon(bitmap);
                        builder.setContentIntent(pen);
                        builder.setAutoCancel(false);
                       /* Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notifysnd);
                        builder.setSound(sound);*/
                        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
                        Notification notification;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";
                            notification = builder.setChannelId(CHANNEL_ID).build();
                        }else {
                            notification = builder.build();
                        }
                        NotificationManager nManager=(NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";// The id of the channel.
                            //CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
                            CharSequence name = "amr  rabie mohamed";
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            nManager.createNotificationChannel(mChannel);
                        }

                       /* new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                //lll3.setVisibility(View.GONE);
                                // lll4.setVisibility(View.VISIBLE);
                            }
                        }, intervaltime * 5);*/

                        nManager.notify(1,notification);

                        mytrip = 0;
                    }

                    if (tripcanceled == 1) {
                        /*lll3.setVisibility(View.VISIBLE);
                        lll4.setVisibility(View.GONE);*/
                        Toast.makeText(context, "Trip canceled by client", Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "Open door cost for client : " + opendoorcost, Toast.LENGTH_SHORT).show();
                        tripcanceled = 0;
                    }

                    handler2.postDelayed(this, delay2);
                }
            }, delay2);

            try {

                hubConnection.on("NotifiedDriverClientCancleTrip", (DoorOpenCost) -> {


                    Log.i("TripCanceled", "TripCanceled");

                    tripcanceled = 1;

                    opendoorcost = DoorOpenCost;

                    Log.i("clientcancel", DoorOpenCost + "," + tripcanceled);


                }, Double.class);
            } catch (Exception e) {
                Log.e("clientcancel", e.getMessage().toString());
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("hub error", e.getMessage().toString());
            //Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


    }
}
