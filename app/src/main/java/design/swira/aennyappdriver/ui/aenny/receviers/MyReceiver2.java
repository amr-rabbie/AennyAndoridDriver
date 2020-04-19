package design.swira.aennyappdriver.ui.aenny.receviers;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.ui.aenny.mainpage.MainDriverActivity;
import design.swira.aennyappdriver.utils.Constants;

public class MyReceiver2 extends BroadcastReceiver {

    private HubConnection hubConnection;
    private int intervaltime = 1000;
    private int getnewmsg = 0;
    private String msg;
    private Integer vehicleid;
    private int getassigntovehicl=0;
    private Integer unvehicleid;
    private int getunassigntovehicl=0;
    private Integer scduletripid;
    int getassigntoscdule=0;
    private Integer unscduletripid;
    int getunassigntoscdule=0;

    @Override
    public void onReceive(Context context, Intent intent) {
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
                        String driverid = String.valueOf(Constants.getClientId(context));
                        hubConnection.send("OnConnectedAsync", driverid, 3);
                        Log.i("ConnectionStatus", driverid + " ,3, " + true);
                    } catch (Exception e) {
                        //Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();


            hubConnection.on("ReceiveMessage", (Trip_Id, User_Id_From, UserId_To, Message, UserName, UserTypeFrom, UserTypeTo, MessageTime) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                //Log.i("chatmsg", Trip_Id + " , " + User_Id_From );

                Log.i("recvmsg", Message + MessageTime);
                msg = Message;
                getnewmsg = 1;


                /*if(tripid == Trip_Id){
                    getnewmsg = 1;
                    Log.i("recvmsg",Message + MessageTime);

                }*/

            }, Integer.class, Integer.class, Integer.class, String.class, String.class, Integer.class, Integer.class, String.class);


            hubConnection.on("NotifiedAssignDriverToVehicle", (DriverID, VehicleID) -> {

                Log.i("assigntovechael", DriverID + " "  + VehicleID);
                vehicleid=VehicleID;
                getassigntovehicl = 1;

            }, Integer.class, Integer.class);

            hubConnection.on("NotifiedUnAssignDriverToVehicle", (DriverID, VehicleID) -> {

                Log.i("unassigntovechael", DriverID + " "  + VehicleID);
                unvehicleid=VehicleID;
                getunassigntovehicl = 1;

            }, Integer.class, Integer.class);

            hubConnection.on("NotifiedAssignDriverToScheduledTrip", (DriverID, TripScheduleId) -> {

                Log.i("assigntoscduletrip", DriverID + " "  + TripScheduleId);
                scduletripid=TripScheduleId;
                getassigntoscdule = 1;

            }, Integer.class, Integer.class);


            hubConnection.on("NotifiedUnAssignDriverToScheduledTrip", (DriverID, TripScheduleId) -> {

                Log.i("unassigntoscduletrip", DriverID + " "  + TripScheduleId);
                unscduletripid=TripScheduleId;
                getunassigntoscdule = 1;

            }, Integer.class, Integer.class);




            final int delay2 = intervaltime * 1;

            final Handler handler2 = new Handler();
            //int delay = 1000; //milliseconds

            handler2.postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                public void run() {
                    //do something
                    if (getnewmsg == 1) {


                        //Intent notificationIntent=new Intent(context, MainDriverActivity.class);
                        /*notificationIntent.putExtra("clientid",clientid);
                        notificationIntent.putExtra("clientpickuplong",clientpickuplong);
                        notificationIntent.putExtra("clientpickuplate",clientpickuplate);
                        int rides=healthy+handcap;
                        notificationIntent.putExtra("rides",rides);
                        notificationIntent.putExtra("clientdestlong",clientdestlong);
                        notificationIntent.putExtra("clientdestlate",clientdestlate);*/
                        //PendingIntent pen= PendingIntent.getActivity(context,0,notificationIntent,PendingIntent.FLAG_ONE_SHOT);
                        PendingIntent contentIntent = PendingIntent.getActivity(
                                context.getApplicationContext(),
                                0,
                                new Intent(), // add this
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notify_icon);
                        Notification.Builder builder = new Notification.Builder(context);
                        builder.setTicker("New Message!");
                        builder.setContentTitle("New Message From Client!");
                        builder.setSmallIcon(R.drawable.ic_notify_icon);
                        builder.setContentText("Client Said: " + msg);
                        builder.setLargeIcon(bitmap);
                        builder.setContentIntent(contentIntent);
                        builder.setAutoCancel(false);
                       /* Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notifysnd);
                        builder.setSound(sound);*/
                        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
                        Notification notification;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";
                            notification = builder.setChannelId(CHANNEL_ID).build();
                        } else {
                            notification = builder.build();
                        }
                        NotificationManager nManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";// The id of the channel.
                            //CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
                            CharSequence name = "amr  rabie mohamed";
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            nManager.createNotificationChannel(mChannel);
                        }
                        nManager.notify(1, notification);

                        getnewmsg = 0;
                    }

                    if(getassigntovehicl == 1){
                        PendingIntent contentIntent = PendingIntent.getActivity(
                                context.getApplicationContext(),
                                0,
                                new Intent(), // add this
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notify_icon);
                        Notification.Builder builder = new Notification.Builder(context);
                        builder.setTicker("New Message!");
                        builder.setContentTitle("Vehicle Assign!");
                        builder.setSmallIcon(R.drawable.ic_notify_icon);
                        builder.setContentText(vehicleid + " Vehicle Assigned to you");
                        builder.setLargeIcon(bitmap);
                        builder.setContentIntent(contentIntent);
                        builder.setAutoCancel(false);
                       /* Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notifysnd);
                        builder.setSound(sound);*/
                        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
                        Notification notification;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";
                            notification = builder.setChannelId(CHANNEL_ID).build();
                        } else {
                            notification = builder.build();
                        }
                        NotificationManager nManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";// The id of the channel.
                            //CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
                            CharSequence name = "amr  rabie mohamed";
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            nManager.createNotificationChannel(mChannel);
                        }
                        nManager.notify(1, notification);

                        getassigntovehicl = 0;
                    }


                    if(getunassigntovehicl == 1){
                        PendingIntent contentIntent = PendingIntent.getActivity(
                                context.getApplicationContext(),
                                0,
                                new Intent(), // add this
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notify_icon);
                        Notification.Builder builder = new Notification.Builder(context);
                        builder.setTicker("New Message!");
                        builder.setContentTitle("Vehicle Un Assign!");
                        builder.setSmallIcon(R.drawable.ic_notify_icon);
                        builder.setContentText(unvehicleid + " Vehicle Un Assigned to you");
                        builder.setLargeIcon(bitmap);
                        builder.setContentIntent(contentIntent);
                        builder.setAutoCancel(false);
                       /* Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notifysnd);
                        builder.setSound(sound);*/
                        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
                        Notification notification;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";
                            notification = builder.setChannelId(CHANNEL_ID).build();
                        } else {
                            notification = builder.build();
                        }
                        NotificationManager nManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";// The id of the channel.
                            //CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
                            CharSequence name = "amr  rabie mohamed";
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            nManager.createNotificationChannel(mChannel);
                        }
                        nManager.notify(1, notification);

                        getunassigntovehicl = 0;
                    }


                    if(getassigntoscdule == 1){
                        PendingIntent contentIntent = PendingIntent.getActivity(
                                context.getApplicationContext(),
                                0,
                                new Intent(), // add this
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notify_icon);
                        Notification.Builder builder = new Notification.Builder(context);
                        builder.setTicker("New Message!");
                        builder.setContentTitle("Scdule Trip Assign!");
                        builder.setSmallIcon(R.drawable.ic_notify_icon);
                        builder.setContentText(scduletripid + " Scdule Trip Assigned to you");
                        builder.setLargeIcon(bitmap);
                        builder.setContentIntent(contentIntent);
                        builder.setAutoCancel(false);
                       /* Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notifysnd);
                        builder.setSound(sound);*/
                        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
                        Notification notification;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";
                            notification = builder.setChannelId(CHANNEL_ID).build();
                        } else {
                            notification = builder.build();
                        }
                        NotificationManager nManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";// The id of the channel.
                            //CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
                            CharSequence name = "amr  rabie mohamed";
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            nManager.createNotificationChannel(mChannel);
                        }
                        nManager.notify(1, notification);

                        getassigntoscdule = 0;
                    }


                    if(getunassigntoscdule == 1){
                        PendingIntent contentIntent = PendingIntent.getActivity(
                                context.getApplicationContext(),
                                0,
                                new Intent(), // add this
                                PendingIntent.FLAG_UPDATE_CURRENT);
                        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_notify_icon);
                        Notification.Builder builder = new Notification.Builder(context);
                        builder.setTicker("New Message!");
                        builder.setContentTitle("Scdule Trip Un Assign!");
                        builder.setSmallIcon(R.drawable.ic_notify_icon);
                        builder.setContentText(unscduletripid + " Scdule Trip Un Assigned to you");
                        builder.setLargeIcon(bitmap);
                        builder.setContentIntent(contentIntent);
                        builder.setAutoCancel(false);
                       /* Uri sound = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notifysnd);
                        builder.setSound(sound);*/
                        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
                        Notification notification;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";
                            notification = builder.setChannelId(CHANNEL_ID).build();
                        } else {
                            notification = builder.build();
                        }
                        NotificationManager nManager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            String CHANNEL_ID = "my_channel_01";// The id of the channel.
                            //CharSequence name = getString(R.string.channel_name);// The user-visible name of the channel.
                            CharSequence name = "amr  rabie mohamed";
                            int importance = NotificationManager.IMPORTANCE_HIGH;
                            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
                            nManager.createNotificationChannel(mChannel);
                        }
                        nManager.notify(1, notification);

                        getunassigntoscdule = 0;
                    }




                    handler2.postDelayed(this, delay2);
                }
            }, delay2);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("hub error", e.getMessage().toString());
            //Toast.makeText(context, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }
    }
}


