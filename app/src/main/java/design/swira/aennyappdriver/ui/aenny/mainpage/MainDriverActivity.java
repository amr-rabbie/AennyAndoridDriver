package design.swira.aennyappdriver.ui.aenny.mainpage;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.material.navigation.NavigationView;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.microsoft.signalr.HubConnectionState;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicReference;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.pojo.aenny.notifications.NotificationsResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driver.DriverDataResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverscdule.NextScduleResponse;
import design.swira.aennyappdriver.pojo.aennydriver.driverstatics.DriverStaticsResponse;
import design.swira.aennyappdriver.ui.aenny.adapters.ScdulesListAdapter2;
import design.swira.aennyappdriver.ui.aenny.changepasswordmobile.ChangePasswordActivity;
import design.swira.aennyappdriver.ui.aenny.drivertrips.DriverListTripsActivity;
import design.swira.aennyappdriver.ui.aenny.drivertrips.DriverTripFirstActivity;
import design.swira.aennyappdriver.ui.aenny.drivertrips.StartTripActivity;
import design.swira.aennyappdriver.ui.aenny.drivertrips.TripsViewModel;
import design.swira.aennyappdriver.ui.aenny.notifications.NotificationsListActivity;
import design.swira.aennyappdriver.ui.aenny.notifications.NotificationsViewModel;
import design.swira.aennyappdriver.ui.aenny.receviers.MyReceiver;
import design.swira.aennyappdriver.ui.aenny.receviers.MyReceiver2;
import design.swira.aennyappdriver.ui.aenny.services.MyIntentService;
import design.swira.aennyappdriver.ui.aenny.services.MyService;
import design.swira.aennyappdriver.ui.aenny.settingscreen.MainSettingsActivity;
import design.swira.aennyappdriver.ui.aenny.signin.LoginActivity;
import design.swira.aennyappdriver.ui.aenny.splash.SplashActivity;
import design.swira.aennyappdriver.utils.Constants;
import design.swira.aennyappdriver.utils.GpsTracker;
import design.swira.aennyappdriver.utils.Network;


public class MainDriverActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.notifybtn)
    Button notifybtn;
    @BindView(R.id.notifyimg)
    ImageView notifyimg;
    @BindView(R.id.notify)
    FrameLayout notify;
    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawer)
    DrawerLayout drawer;
    @BindView(R.id.spinner)
    Spinner spinner;
    @BindView(R.id.tripsno)
    TextView tripsno;
    @BindView(R.id.kilosno)
    TextView kilosno;
    @BindView(R.id.hoursno)
    TextView hoursno;
    @BindView(R.id.gainsum)
    TextView gainsum;
    @BindView(R.id.online)
    Button online;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.ll1)
    LinearLayout ll1;
    @BindView(R.id.recycler)
    RecyclerView recycler;
    @BindView(R.id.ll2)
    LinearLayout ll2;
   /* @BindView(R.id.l3)
    LinearLayout l3;*/
    @BindView(R.id.accept)
    Button accept;
    @BindView(R.id.timer)
    TextView timer;
    @BindView(R.id.reject)
    Button reject;
    /*@BindView(R.id.l4)
    LinearLayout l4;*/
    private AppBarConfiguration mAppBarConfiguration;

    String ss;
    int mytrip=0;
    double opendoorcost;

    DrawerLayout drawerLayout;
    /*Toolbar toolbar;
    NavigationView navigationView;*/
    ActionBarDrawerToggle toggle;
    TextView clientname, clientemail, clientmobil, clientcityarea, tripssno, genderage;
    ImageView editimg, backimg;
    CircleImageView driverimg;
    TextView msg;

    NotificationsViewModel notificationsViewModel;
    MainDriverViewModel mainDriverViewModel;

    double clientpickuplong,clientpickuplate,clientdestlong,clientdestlate;


    String[] filterstypes = new String[]{"All Time", "This month", "This week", "Today"};

    private final Paint grayscalePaint = new Paint();

    ColorMatrix cm = new ColorMatrix();

    TripsViewModel tripsViewModel;
    //ScdulesListAdapter2 scdulesListAdapter;
    ScdulesListAdapter2 scdulesListAdapter;

    HubConnection hubConnection;

    private GpsTracker gpsTracker;
    private double latitude;
    private double longitude;
    MyIntentService myIntentService;
    private ServiceConnection con;
    private final int intervaltime = 1000;
    private final int intervaltime2 = 100;
    int clientid;

    LinearLayout lll3,lll4;
    private CountDownTimer counttimer;
    int healthy,handcap;
    int tripcanceled=0;
    private String dateoftrip;
    private String acceptTime;
    private int rides;
    private int tripid;
    private int gettripid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_driver);
        ButterKnife.bind(this);


        Intent intent = getIntent();
        if (intent.hasExtra("clientid")) {
            clientid = (int) intent.getExtras().get("clientid");
        }

        if (intent.hasExtra("clientpickuplong")) {
            clientpickuplong = (Double) intent.getExtras().get("clientpickuplong");
        }

        if (intent.hasExtra("clientpickuplate")) {
            clientpickuplate = (Double) intent.getExtras().get("clientpickuplate");
        }

        if (intent.hasExtra("rides")) {
            rides = (int) intent.getExtras().get("rides");
        }

        if (intent.hasExtra("clientdestlong")) {
            clientdestlong = (Double) intent.getExtras().get("clientdestlong");
        }

        if (intent.hasExtra("clientdestlate")) {
            clientdestlate = (Double) intent.getExtras().get("clientdestlate");
        }





        getLocation();

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
                        String driveridd = String.valueOf(Constants.getClientId(MainDriverActivity.this));
                        hubConnection.send("OnConnectedAsync", driveridd, 3);
                        Log.i("ConnectionStatus", driveridd + " ,3, " + true);
                    } catch (Exception e) {
                        //Toast.makeText(MainDriverActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();

            hubConnection.on("NotifiedDriverTripId", (Trip_Id, Trip_Pickup, Trip_Destination) -> {
                //Log.i("LocClient",latitude + "," + longitude);
                Log.i("tripid", Trip_Id + " , " );

                tripid=Trip_Id;


                //ss="New Trip coming!";

                gettripid = 1;

                //viewNewTrip();


            }, Integer.class,String.class,String.class);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("hub error", e.getMessage().toString());
            //Toast.makeText(MainDriverActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }


        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel.class);
        mainDriverViewModel = ViewModelProviders.of(this).get(MainDriverViewModel.class);
        tripsViewModel = ViewModelProviders.of(this).get(TripsViewModel.class);


        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawerOpen, R.string.drawerClose);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);



        View header = ((NavigationView) findViewById(R.id.navigationView)).getHeaderView(0);

        clientname = header.findViewById(R.id.clientname);
        clientemail = header.findViewById(R.id.clientemail);
        clientmobil = header.findViewById(R.id.clientmobil);
        clientcityarea = header.findViewById(R.id.clientcityarea);
        editimg = header.findViewById(R.id.editimg);
        backimg = header.findViewById(R.id.backimg);
        editimg = header.findViewById(R.id.editimg);
        driverimg = header.findViewById(R.id.driverimg);
        tripssno = header.findViewById(R.id.tripsno);
        genderage = header.findViewById(R.id.genderage);

        lll3=findViewById(R.id.lll3);
        lll4=findViewById(R.id.lll4);


        backimg.setOnClickListener(this);
        notify.setOnClickListener(this);
        notifybtn.setOnClickListener(this);
        notifyimg.setOnClickListener(this);
        editimg.setOnClickListener(this);
        online.setOnClickListener(this);

        accept.setOnClickListener(this);
        reject.setOnClickListener(this);

        //scdulesListAdapter=new ScdulesListAdapter2(this);
        scdulesListAdapter = new ScdulesListAdapter2(this);

        if (!Network.isNetworkAvailable(this)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.warning);
            builder.setTitle("Warnning!");
            builder.setMessage("Network not exists,pleae connect to internet");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(MainDriverActivity.this, SplashActivity.class);
                    startActivity(i);
                }
            });
            builder.create().show();
        }

        bindMycomingTrip();

        bindMyNotifications();

        fillHeaderData();

        fillSpinners();

        cm.setSaturation(0);
        grayscalePaint.setColorFilter(new ColorMatrixColorFilter(cm));

        Intent i = new Intent(MainDriverActivity.this, MyService.class);
       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(i);
        } else {
            startService(i);
        }*/

        //startService(i);
        //stopService(i);


        /*Intent i2 = new Intent(MainDriverActivity.this, MyIntentService.class);

        con=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyIntentService.Mybinder mybinder=(MyIntentService.Mybinder)service;
                myIntentService = mybinder.GetmyInstance();
                myIntentService.startSR();
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                myIntentService=null;

            }
        };

        bindService(i2,con,BIND_AUTO_CREATE);*/
        //unbindService(con);


        Intent iii=new Intent(MainDriverActivity.this, MyReceiver.class);
        sendBroadcast(iii);

        Intent iiii=new Intent(MainDriverActivity.this, MyReceiver2.class);
        sendBroadcast(iiii);

        /*Calendar cal = Calendar.getInstance();

        Intent intent = new Intent(this, MyService.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);

        AlarmManager alarm = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
// Start every minute
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 60*1000, pintent);*/


        //startTimer();



        if(clientid > 0 && clientpickuplong > 0 && clientpickuplate > 0 && clientdestlong > 0 && clientdestlate > 0 && rides > 0){
            //startdelaytime();
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    lll3.setVisibility(View.GONE);
                    lll4.setVisibility(View.VISIBLE);
                }
            }, intervaltime * 3);
            startTimer2();
        }else {





            try {




                hubConnection.on("NotifiedCurrentLongAndLattForDriver", (Client_Id, Client_Pickup_Long, Client_Pickup_Latt, Trip_Destination_Long, Trip_Destination_Latt, Healty_number, Handicapped_Number, Trip_Time) -> {
                    int driverid = Constants.getClientId(MainDriverActivity.this);
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
                    public void run() {
                        //do something
                        if (mytrip == 1) {
                            //startdelaytime();

                            startTimer();
                            mytrip = 0;
                        }

                        if (tripcanceled == 1) {
                            lll3.setVisibility(View.VISIBLE);
                            lll4.setVisibility(View.GONE);
                            Toast.makeText(MainDriverActivity.this, "Trip canceled by client", Toast.LENGTH_SHORT).show();
                            Toast.makeText(MainDriverActivity.this, "Open door cost for client : " + opendoorcost, Toast.LENGTH_SHORT).show();
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

           /*if(mytrip == 1){
               lll3.setVisibility(View.GONE);
               lll4.setVisibility(View.VISIBLE);
               startTimer();
               mytrip=0;
           }*/




            /*final int delay=intervaltime * 15;

            final Handler handler = new Handler();


            //int delay = 1000; //milliseconds

            handler.postDelayed(new Runnable(){
                public void run(){
                    //do something

                    getLocation();
                    int driverid=Constants.getClientId(MainDriverActivity.this);
                    hubConnection.send("GetCurrentLongAndLattForSpecificDriver",clientid,driverid,longitude,latitude);

                    Log.i("DriverLoc",driverid + " " + longitude +","+latitude );
                    handler.postDelayed(this, delay);
                }
            }, delay);*/


                //hubConnection.send("NotifiDriverArrived", clientid);

            } catch (Exception e) {
                e.printStackTrace();
                Log.e("hub error", e.getMessage().toString());
                //Toast.makeText(MainDriverActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void startTimer2() {
        counttimer=new CountDownTimer(19000, 1000) {

            public void onTick(long millisUntilFinished) {


                timer.setText("00 : " + millisUntilFinished / 1000);

            }

            public void onFinish() {
                //timer.setText("done!");
                try{
                    gettimeandDate();
                    int driverid=Constants.getClientId(MainDriverActivity.this);
                    hubConnection.send("StatusTripFromDriver",clientid,driverid,2,clientpickuplong,clientpickuplate,acceptTime,longitude,latitude);
                    Log.i("tripstatus","2");
                    //hubConnection.send("GetMatchedDriver",clientid,gethealthy,gethandcap,clientpickuplong,clientpickuplate);
                }catch (Exception e){
                    //Toast.makeText(MainDriverActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
                lll3.setVisibility(View.VISIBLE);
                lll4.setVisibility(View.GONE);
            }
        }.start();
    }

    private void startdelaytime() {
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

            }
        }, intervaltime * 3);
    }

    private void viewNewTrip() {
        lll3.setVisibility(View.GONE);
        lll4.setVisibility(View.VISIBLE);
        startTimer();
    }

    private void startTimer() {


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                lll3.setVisibility(View.GONE);
                lll4.setVisibility(View.VISIBLE);
            }
        }, intervaltime * 3);







        //Glide.with(MainDriverActivity.this).load(R.drawable.pbarimg).into(accept);

        counttimer=new CountDownTimer(19000, 1000) {

            public void onTick(long millisUntilFinished) {


                    timer.setText("00 : " + millisUntilFinished / 1000);

            }

            public void onFinish() {
                //timer.setText("done!");
                try{
                    gettimeandDate();
                    int driverid=Constants.getClientId(MainDriverActivity.this);
                    hubConnection.send("StatusTripFromDriver",clientid,driverid,2,clientpickuplong,clientpickuplate,acceptTime,longitude,latitude);
                    Log.i("tripstatus","2");
                    //hubConnection.send("GetMatchedDriver",clientid,gethealthy,gethandcap,clientpickuplong,clientpickuplate);
                }catch (Exception e){
                    //Toast.makeText(MainDriverActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
                lll3.setVisibility(View.VISIBLE);
                lll4.setVisibility(View.GONE);
            }
        }.start();
    }

    private void bindMycomingTrip() {
        int driverid = Constants.getClientId(MainDriverActivity.this);
        //tripsViewModel.getNextScduleTripsForDriver(driverid);
        tripsViewModel.getNextScduleTripsForDriver(driverid);
        /*tripsViewModel.ScduleResponselistMutableLiveData2.observe(MainDriverActivity.this, new Observer<List<NextScduleResponse>>() {
            @Override
            public void onChanged(List<NextScduleResponse> scduleResponses) {
                if(scduleResponses != null){
                    viewMyNextTrip(scduleResponses);
                }else{
                    ll1.setVisibility(View.VISIBLE);
                    ll2.setVisibility(View.GONE);
                }
            }
        });*/

        tripsViewModel.ScduleResponselistMutableLiveData2.observe(MainDriverActivity.this, new Observer<NextScduleResponse>() {
            @Override
            public void onChanged(NextScduleResponse nextScduleResponse) {
                if (nextScduleResponse != null) {
                    List<NextScduleResponse> nextScduleResponseList = new ArrayList<>();
                    nextScduleResponseList.add(nextScduleResponse);
                    viewMyNextTrip(nextScduleResponseList);
                } else {
                    ll1.setVisibility(View.VISIBLE);
                    ll2.setVisibility(View.GONE);
                }
            }
        });


        /*tripsViewModel.ScduleResponselistMutableLiveData2.observe(MainDriverActivity.this, new Observer<ScduleResponse>() {
            @Override
            public void onChanged(List<ScduleResponse> scduleResponses) {
                if (scduleResponses != null) {
                    if (scduleResponses.size() > 0) {

                        Date d;


                        List<ScduleResponse> nexttripslist = new ArrayList<>();


                        SimpleDateFormat dateFormat = new SimpleDateFormat(
                                "yyyy-MM-dd");
                        List<Date> dates = new ArrayList<>();
                        for (int i = 0; i < scduleResponses.size(); i++) {
                            ScduleResponse tripsResponse = scduleResponses.get(i);
                            String date = tripsResponse.getTripTime();
                            try {
                                Date dd = dateFormat.parse(date);
                                dates.add(dd);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }

                        Date now = new Date();

                        List<Date> datess = new ArrayList<>();
                        for(int i=0;i<dates.size();i++){
                            if( (dates.get(i).after(now)) || (dates.get(i).equals(now))){
                                datess.add(dates.get(i));
                            }
                        }

                        Date earliest = getNearestDate(datess, now);

                        for (int i = 0; i < scduleResponses.size(); i++) {
                            ScduleResponse tripsResponse = scduleResponses.get(i);
                            String date = tripsResponse.getTripTime();
                            try {
                                Date ddd = dateFormat.parse(date);

                                if (earliest.equals(ddd)) {
                                    ScduleResponse trip = scduleResponses.get(i);
                                    nexttripslist.add(trip);
                                }


                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                        }


                        //viewData(scduledtripslist);
                        viewMyNextTrip(nexttripslist);
                    }
                } else {
                    ll1.setVisibility(View.VISIBLE);
                    ll2.setVisibility(View.GONE);
                }
            }

        });*/
    }

    public static Date getNearestDate(List<Date> dates, Date currentDate) {
        long minDiff = -1, currentTime = currentDate.getTime();
        Date minDate = null;
        for (Date date : dates) {
            long diff = Math.abs(currentTime - date.getTime());
            if ((minDiff == -1) || (diff < minDiff)) {
                minDiff = diff;
                minDate = date;
            }
        }
        return minDate;
    }

    /*private void viewMyNextTrip(List<NextScduleResponse> scduleResponses) {
        scdulesListAdapter.setList(scduleResponses);
        recycler.setAdapter(scdulesListAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(MainDriverActivity.this));
        ll1.setVisibility(View.GONE);
        ll2.setVisibility(View.VISIBLE);
    }*/

    private void viewMyNextTrip(List<NextScduleResponse> scduleResponses) {
        scdulesListAdapter.setList(scduleResponses);
        recycler.setAdapter(scdulesListAdapter);
        recycler.setLayoutManager(new LinearLayoutManager(MainDriverActivity.this));
        ll1.setVisibility(View.GONE);
        ll2.setVisibility(View.VISIBLE);
    }

    private void fillSpinners() {
        ArrayAdapter<String> filtersArray = new ArrayAdapter<String>(MainDriverActivity.this, android.R.layout.simple_spinner_item, filterstypes);
        filtersArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(filtersArray);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int DurationType = 1;
                if (position == 0) {
                    DurationType = 1;
                } else if (position == 1) {
                    DurationType = 2;
                } else if (position == 2) {
                    DurationType = 3;
                } else if (position == 3) {
                    DurationType = 4;
                }
                int driverid = Constants.getClientId(MainDriverActivity.this);

                mainDriverViewModel.getDriverStatics(driverid, DurationType);

                mainDriverViewModel.driverStaticsResponseMutableLiveData.observe(MainDriverActivity.this, new Observer<DriverStaticsResponse>() {
                    @Override
                    public void onChanged(DriverStaticsResponse driverStaticsResponse) {
                        if (driverStaticsResponse != null) {
                            tripsno.setText(driverStaticsResponse.getTripsTotalNumber() + " Trips");
                            kilosno.setText(driverStaticsResponse.getTripsTotalKM() + " Kilometers");
                            hoursno.setText(driverStaticsResponse.getTripsTotalHours() + " Hours");
                            gainsum.setText(driverStaticsResponse.getTripsTotalFees() + " Saudi Riyal");
                        } else {
                            tripsno.setText("0" + " Trips");
                            kilosno.setText("0" + " Kilometers");
                            hoursno.setText("0" + " Hours");
                            gainsum.setText("0" + " Saudi Riyal");
                        }
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void fillHeaderData() {
        clientname.setText(Constants.getClientName(MainDriverActivity.this));
        clientmobil.setText(Constants.getClientMobile(MainDriverActivity.this));
        clientemail.setText(Constants.getClientEmail(MainDriverActivity.this));

        int clientId = Constants.getClientId(MainDriverActivity.this);

        mainDriverViewModel.getDriverData(clientId);

        mainDriverViewModel.driverDataResponseMutableLiveData.observe(this, new Observer<DriverDataResponse>() {
            @Override
            public void onChanged(DriverDataResponse driverDataResponse) {
                if (driverDataResponse != null) {
                    clientname.setText(driverDataResponse.getDriverName());
                    clientmobil.setText(driverDataResponse.getDriverMobile());
                    clientemail.setText(driverDataResponse.getDriverEmail());

                    String opeloc = driverDataResponse.getCityName() + "," + driverDataResponse.getAreaName() + "," + driverDataResponse.getDriverAddress();
                    clientcityarea.setText(opeloc);

                    tripssno.setText(driverDataResponse.getDriverNoOfTrips() + " Completed trips");

                    String agegender = driverDataResponse.getDriverBirthDate() + "," + driverDataResponse.getGenderName();

                    genderage.setText(agegender);

                    String imagepath = "http://aenee.app.192-185-7-211.hgws27.hgwin.temp.domains/" + driverDataResponse.getDriverImage();

                    Glide
                            .with(MainDriverActivity.this)
                            .load(imagepath)
                            .centerCrop()
                            //.placeholder(R.drawable.loading_spinner)
                            .into(driverimg);


                }
            }
        });

       /* mainClientViewModel.getClientData(clientId);

        mainClientViewModel.clientMutableLiveData.observe(MainClientActivity.this, new Observer<ClientDataResponse>() {
            @Override
            public void onChanged(ClientDataResponse clientDataResponse) {
                if (clientDataResponse != null) {
                    String cityName = clientDataResponse.getCityName();
                    String areaName = clientDataResponse.getAreaName();

                    String cityarea = cityName + "," + areaName;
                    clientcityarea.setText(cityarea);

                    clientname.setText(clientDataResponse.getClientName() + "");
                    clientmobil.setText(clientDataResponse.getClientMobile() + "");
                    clientemail.setText(clientDataResponse.getClientEmail() + "");

                }
            }
        });*/
    }


    private void bindMyNotifications() {
        int usertype = 2;
        int clientid = Constants.getClientId(MainDriverActivity.this);
        notificationsViewModel.getAllNoficationsByClientId(usertype, clientid);

        notificationsViewModel.notifylistMutableLiveData.observe(MainDriverActivity.this, new Observer<List<NotificationsResponse>>() {
            @Override
            public void onChanged(List<NotificationsResponse> notificationsResponses) {
                if (notificationsResponses != null) {

                    if (notificationsResponses.size() > 0) {
                        String notes = String.valueOf(notificationsResponses.size());
                        notifybtn.setText(notes);
                        //notifybtn.setTextColor(Color.RED);
                    } else {
                        notifybtn.setText("0");
                        //notifybtn.setTextColor(Color.GRAY);
                    }

                } else {
                    notifybtn.setText("0");
                    //notifybtn.setTextColor(Color.GRAY);
                }

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.whereto:
                //showPlaces();
                Intent i7 = new Intent(MainClientActivity.this, RouteActivity.class);
                //i.putExtra("latitude2", latitude);
                //i.putExtra("longitude2", longitude);
                //i.putExtra("des", whereto.getText().toString());
                startActivity(i7);
                break;
            case R.id.scdule:
                Intent i = new Intent(MainClientActivity.this, SchduleTripActivity.class);
                startActivityForResult(i, 1);
                break;
            case R.id.homeimg:
                Intent i2 = new Intent(MainClientActivity.this, AddClientFavouriteDialogActivity.class);
                i2.putExtra("notes", "home");
                startActivityForResult(i2, 5);
                break;
            case R.id.hospitalimg:
                Intent i3 = new Intent(MainClientActivity.this, AddClientFavouriteDialogActivity.class);
                i3.putExtra("notes", "hospital");
                startActivityForResult(i3, 6);
                break;
            case R.id.schoolimg:
                Intent i4 = new Intent(MainClientActivity.this, AddClientFavouriteDialogActivity.class);
                i4.putExtra("notes", "school");
                startActivityForResult(i4, 7);
                break;
            case R.id.locationimg:
                Intent i5 = new Intent(MainClientActivity.this, AddClientFavouriteDialogActivity.class);
                i5.putExtra("notes", "location");
                startActivityForResult(i5, 8);
                break;*/
            case R.id.editimg:
                Intent i11 = new Intent(MainDriverActivity.this, ChangePasswordActivity.class);
                //i11.putExtra("notes", "location");
                startActivity(i11);
                break;
            case R.id.backimg:
                drawerLayout.closeDrawers();
                break;
            case R.id.notify:
                Intent int11 = new Intent(MainDriverActivity.this, NotificationsListActivity.class);
                //i11.putExtra("notes", "location");
                startActivity(int11);
                break;
            case R.id.notifybtn:
                Intent int111 = new Intent(MainDriverActivity.this, NotificationsListActivity.class);
                //i11.putExtra("notes", "location");
                startActivity(int111);
                break;
            case R.id.notifyimg:
                Intent int112 = new Intent(MainDriverActivity.this, NotificationsListActivity.class);
                //i11.putExtra("notes", "location");
                startActivity(int112);
                break;
            case R.id.accept:
                try{

                    gettimeandDate();
                    getLocation();
                    int driverid=Constants.getClientId(MainDriverActivity.this);
                    //hubConnection.send("GetCurrentLongAndLattForSpecificDriver",clientid,driverid,longitude,latitude);
                    Log.i("DriverLoc",driverid + " " + longitude +","+latitude );

                    //int driverid=Constants.getClientId(MainDriverActivity.this);
                    hubConnection.send("StatusTripFromDriver",clientid,driverid,1,clientpickuplong,clientpickuplate,acceptTime,longitude,latitude);
                    Log.i("tripstatus","1");
                }catch (Exception e){
                    //Toast.makeText(MainDriverActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }




                counttimer.cancel();
                startmyTrip();
                /*Intent intent=new Intent(MainDriverActivity.this, StartTripActivity.class);
                intent.putExtra("clientid",clientid);
                intent.putExtra("clientpickuplong",clientpickuplong);
                intent.putExtra("clientpickuplate",clientpickuplate);
                if(rides <= 0) {
                    rides = healthy + handcap;
                }
                intent.putExtra("rides",rides);
                intent.putExtra("clientdestlong",clientdestlong);
                intent.putExtra("clientdestlate",clientdestlate);
                intent.putExtra("acceptTime",acceptTime);
                startActivity(intent);*/
                //Toast.makeText(MainDriverActivity.this, "accept!", Toast.LENGTH_SHORT).show();
                break;
            case R.id.reject:
                try{
                    gettimeandDate();
                    getLocation();
                    int driveridd=Constants.getClientId(MainDriverActivity.this);
                    hubConnection.send("StatusTripFromDriver",clientid,driveridd,2,clientpickuplong,clientpickuplate,acceptTime,longitude,latitude);
                    Log.i("tripstatus","2");
                    //hubConnection.send("GetMatchedDriver",clientid,gethealthy,gethandcap,clientpickuplong,clientpickuplate);
                }catch (Exception e){
                    //Toast.makeText(MainDriverActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
                //Toast.makeText(MainDriverActivity.this, "reject!", Toast.LENGTH_SHORT).show();
                counttimer.cancel();
                lll3.setVisibility(View.VISIBLE);
                lll4.setVisibility(View.GONE);
                break;
            case R.id.online:

                int driveriddd = Constants.getClientId(MainDriverActivity.this);

                if (online.getText().equals("Offline")) {

                    ll.setLayerType(View.LAYER_TYPE_HARDWARE, grayscalePaint);
                    online.setLayerType(View.LAYER_TYPE_NONE, null);
                    online.setBackgroundResource(R.drawable.ic_green_corners);
                    online.setText("Online");
                    mainDriverViewModel.updatedriverstatus(driveriddd, false);
                    Toast.makeText(MainDriverActivity.this, "You are now offline", Toast.LENGTH_SHORT).show();
                } else {
                    online.setBackgroundResource(R.drawable.ic_red_corners);
                    online.setText("Offline");
                    ll.setLayerType(View.LAYER_TYPE_NONE, null);
                    mainDriverViewModel.updatedriverstatus(driveriddd, true);
                    Toast.makeText(MainDriverActivity.this, "You are now online", Toast.LENGTH_SHORT).show();
                }

        }

    }

    private void startmyTrip() {


        final int delay2 = intervaltime * 1;

        final Handler handler2 = new Handler();
        //int delay = 1000; //milliseconds

        handler2.postDelayed(new Runnable() {
            public void run() {
                //do something
                if (gettripid == 1 && tripid > 0) {

                    Intent intent=new Intent(MainDriverActivity.this, StartTripActivity.class);
                    intent.putExtra("clientid",clientid);
                    intent.putExtra("clientpickuplong",clientpickuplong);
                    intent.putExtra("clientpickuplate",clientpickuplate);
                    if(rides <= 0) {
                        rides = healthy + handcap;
                    }
                    intent.putExtra("rides",rides);
                    intent.putExtra("handcap",handcap);
                    intent.putExtra("healthy",healthy);
                    intent.putExtra("clientdestlong",clientdestlong);
                    intent.putExtra("clientdestlate",clientdestlate);
                    intent.putExtra("acceptTime",acceptTime);
                    intent.putExtra("tripid",tripid);
                    //Toast.makeText(MainDriverActivity.this, "Trip id : " + tripid, Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    gettripid = 0;
                    tripid=0;
                }



                handler2.postDelayed(this, delay2);
            }
        }, delay2);






    }

    private void gettimeandDate() {
        try {
            //dateoftrip = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            //acceptTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
            acceptTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH).format(new Date());
            //Calendar startDate = getStartDate();
            //Calendar endDate = getEndDate();




        }catch (Exception e){
            Log.e("acceptTime",e.getMessage().toString());
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            /*case R.id.changepass:

                Intent i = new Intent(MainClientActivity.this, UpdateProfileActivity.class);
                startActivity(i);
                break;
            case R.id.changemob:
                Intent ii = new Intent(MainClientActivity.this, ChangeMobileActivity.class);
                startActivity(ii);
                break;
            case R.id.myprofile:
                Intent ii3 = new Intent(MainClientActivity.this, UpdateProfileActivity.class);
                startActivity(ii3);
                break;
            case R.id.addlongtrip:
                Intent iii = new Intent(MainClientActivity.this, AddLongTripActivity.class);
                startActivity(iii);
                break;*/
            case R.id.settings:
                Intent iiii = new Intent(MainDriverActivity.this, MainSettingsActivity.class);
                startActivity(iiii);
                break;
            /*case R.id.payments:
                Intent iiiii = new Intent(MainClientActivity.this, PaymentsListActivity.class);
                startActivity(iiiii);
                break;
            case R.id.favourites:
                Intent i2 = new Intent(MainClientActivity.this, ClientFavouriteListActivity.class);
                startActivity(i2);
                break;*/
            case R.id.mytrips:
                Intent i4 = new Intent(MainDriverActivity.this, DriverListTripsActivity.class);
                startActivity(i4);
                break;

            case R.id.invite:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "My friend please see this wonderfull app!");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;

            default:
                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        /*if (!shouldAllowBack()) {
            doSomething();
        } else {
            super.onBackPressed();
        }*/
    }

    public void setGrayedOut(boolean grayedOut) {
        if (grayedOut) {
            ll.setLayerType(View.LAYER_TYPE_HARDWARE, grayscalePaint);
        } else {
            ll.setLayerType(View.LAYER_TYPE_NONE, null);
        }
    }

    public void getLocation() {

        latitude = 33.312805;
        longitude = 44.361488;

        if (!Network.isNetworkAvailable(MainDriverActivity.this)) {
            latitude = 33.312805;
            longitude = 44.361488;
            return;
        } else {

            gpsTracker = new GpsTracker(MainDriverActivity.this);
            if (gpsTracker.canGetLocation()) {
                latitude = gpsTracker.getLatitude();
                longitude = gpsTracker.getLongitude();

            } else {
                gpsTracker.showSettingsAlert();
            }
        }


        //Toast.makeText(RouteActivity.this, "latitude: " + latitude + " - longitude: " + longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
