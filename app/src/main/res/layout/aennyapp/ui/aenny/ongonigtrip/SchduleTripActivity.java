package design.swira.aennyapp.ui.aenny.ongonigtrip;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivitySchduleTripBinding;
import design.swira.aennyapp.pojo.aenny.MyDates;
import design.swira.aennyapp.pojo.aenny.newscduletrip.AddScduleTrip;
import design.swira.aennyapp.pojo.aenny.newscduletrip.TripDateTimeListItem;
import design.swira.aennyapp.pojo.aenny.trip.TripsResponse;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.utils.Constants;

public class SchduleTripActivity extends AppCompatActivity implements View.OnClickListener  {

    ActivitySchduleTripBinding binding;
    OnGoingViewModel viewModel;


    private int mHour, mMinute;
    String date;
    String time;


    ArrayList<String> mylist=new ArrayList<>();









    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_schdule_trip);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_schdule_trip);

        viewModel = ViewModelProviders.of(this).get(OnGoingViewModel.class);

        binding.next.setOnClickListener(this);

        binding.time.setOnClickListener(this);



        intcalenderview();






        binding.date.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {


                Date today = new Date();
                long todaytime = today.getTime();




                Calendar clickedDayCalendar = eventDay.getCalendar();
                //Toast.makeText(SchduleTripActivity.this, "" + clickedDayCalendar.getTime(), Toast.LENGTH_SHORT).show();



                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                date=sdf.format(clickedDayCalendar.getTime());


            }
        });










    }

    private void intcalenderview() {

        Calendar min_date_c = Calendar.getInstance();
        binding.date.setMinimumDate(min_date_c);



        List<EventDay> events = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        events.add(new EventDay(calendar, R.drawable.ic_ellipse_4));

        //CalendarView calendarView = (CalendarView) findViewById(R.id.calendarView);
        binding.date.setEvents(events);



    }




    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next:
                gettimeanddate();
                break;
            case R.id.time:
                getTime();
                break;
        }
    }

    private void gettimeanddate() {

        List<Calendar> selectedDates = binding.date.getSelectedDates();

        String myt = binding.time.getText().toString();

        if (selectedDates.size() <= 0) {
            Toast.makeText(SchduleTripActivity.this, "You must select at least one day", Toast.LENGTH_SHORT).show();
        } else if (myt.isEmpty()) {
            Toast.makeText(SchduleTripActivity.this, "You must select time ", Toast.LENGTH_SHORT).show();
        } else {

           /* for (int i = 0; i < selectedDates.size(); i++) {
                Calendar mcalendar = selectedDates.get(i);

                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                String d = sdf.format(mcalendar.getTime());*/

            //Toast.makeText(SchduleTripActivity.this, "Date: " + d + "\n" + "Time: " + time, Toast.LENGTH_SHORT).show();

            String getori = Constants.getori(SchduleTripActivity.this);
            String getdes = Constants.getdes(SchduleTripActivity.this);
            String distance = Constants.getdistance(SchduleTripActivity.this);
            String cost = Constants.getcost(SchduleTripActivity.this);
            String getlatitude = Constants.getlatitude(SchduleTripActivity.this);
            String getlongitude = Constants.getlongitude(SchduleTripActivity.this);
            String getlatitude2 = Constants.getlatitude2(SchduleTripActivity.this);
            String getlongitude2 = Constants.getlongitude2(SchduleTripActivity.this);
            int clientId = Constants.getClientId(SchduleTripActivity.this);
            int gethealthy = Constants.gethealthy(SchduleTripActivity.this);
            int gethandcap = Constants.gethandcap(SchduleTripActivity.this);
            int getpaidtype = Constants.getpaidtype(SchduleTripActivity.this);
            String getpaid = Constants.getpaid(SchduleTripActivity.this);
            int triptype = 2;


            if (!getori.isEmpty() && !getdes.isEmpty() && !distance.isEmpty() && !cost.isEmpty()) {
                // Toast.makeText(SchduleTripActivity.this, "You must save here", Toast.LENGTH_SHORT).show();

                //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost),Double.parseDouble(getlatitude2) , getdes,Double.parseDouble(getlongitude2),getpaidtype, time,Double.parseDouble(getlongitude) , String tripScheduleTime,  String tripDestinationTime,  triptype, gethealthy, getori,  d,  String tripScheduleDate,Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid,   clientId, gethandcap,  String tripDestinationDate);
                //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost),Double.parseDouble(getlatitude2) , getdes,Double.parseDouble(getlongitude2),getpaidtype, d+" "+time,Double.parseDouble(getlongitude) ,   triptype, gethealthy, getori,  d,  Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid,   clientId, gethandcap );
                //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost), Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2), getpaidtype, d+" "+time, Double.parseDouble(getlongitude), d+" "+time, 0, d+" "+time, "", triptype, gethealthy, getori, "", d, 0, d, Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, 0, clientId, gethandcap, 0, d);

               /* TripsResponse tripsResponse = new TripsResponse(Double.parseDouble(cost), Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2), d + " " + time, Double.parseDouble(getlongitude), triptype, gethealthy, getori, d, Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, 1, clientId, gethandcap);
                viewModel.saveTrips(tripsResponse);
                viewModel.savetripMutableLiveData.observe(SchduleTripActivity.this, new Observer<TripsResponse>() {
                    @Override
                    public void onChanged(TripsResponse tripsResponse) {
                        if (tripsResponse != null) {
                            Toast.makeText(SchduleTripActivity.this, "Your schdule trip saved sucessfully", Toast.LENGTH_SHORT).show();
                            Constants.saveTripData("", "", "", "", "", "", SchduleTripActivity.this);
                            Constants.saveTripData2(0, 0, 0, "", "", "", SchduleTripActivity.this);
                            Intent intt = new Intent(SchduleTripActivity.this, MainClientActivity.class);
                            startActivity(intt);
                        }
                    }
                });*/

                List<TripDateTimeListItem> dateTimeList = new ArrayList<>();


                for (int i = 0; i < selectedDates.size(); i++) {
                    Calendar mcalendar2 = selectedDates.get(i);

                    String myFormat2 = "yyyy-MM-dd";
                    SimpleDateFormat sdf2 = new SimpleDateFormat(myFormat2, Locale.US);

                    String d2 = sdf2.format(mcalendar2.getTime());

                    //TripDateTimeListItem dateTimeListitem = new TripDateTimeListItem(d2,d2+ " " + time);

                    dateTimeList.add(new TripDateTimeListItem(d2, d2 + " " + time));
                }

                AddScduleTrip addScduleTrip=new AddScduleTrip(
                        Double.parseDouble(cost), triptype, gethealthy, getori, Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2), getpaidtype, Double.parseDouble(getlongitude), Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, triptype, 1, clientId, gethandcap, dateTimeList);

                Log.i("AddScduleTrip",addScduleTrip.toString());


                viewModel.addScduleTrip(addScduleTrip);

                viewModel.addScduleTripMutableLiveData.observe(SchduleTripActivity.this, new Observer<AddScduleTrip>() {
                    @Override
                    public void onChanged(AddScduleTrip addScduleTrip) {
                        if(addScduleTrip != null){
                            Toast.makeText(SchduleTripActivity.this, "Your schdule trip saved sucessfully", Toast.LENGTH_SHORT).show();
                            Constants.saveTripData("", "", "", "", "", "", SchduleTripActivity.this);
                            Constants.saveTripData2(0, 0, 0, "", "", "", SchduleTripActivity.this);
                            Intent intt = new Intent(SchduleTripActivity.this, MainClientActivity.class);
                            startActivity(intt);
                        }
                    }
                });


            } else {


                Intent ii = new Intent(SchduleTripActivity.this, RouteActivity.class);
                ii.putExtra("selectedDates", (Serializable) selectedDates);
                ii.putExtra("selectedDTime", time);
                ii.putExtra("Scduled", "yes");
                startActivity(ii);

            }

        }

        //}


    }




    private void getTime() {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        binding.time.setText(hourOfDay + ":" + minute);
                        time=hourOfDay + ":" + minute;
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Constants.saveTripData("", "", "", "", "", "", SchduleTripActivity.this);
        Constants.saveTripData2(0, 0, 0, "", "", "", SchduleTripActivity.this);
    }
}
