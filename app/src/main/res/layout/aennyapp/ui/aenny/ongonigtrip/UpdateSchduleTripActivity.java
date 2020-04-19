package design.swira.aennyapp.ui.aenny.ongonigtrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;

import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.exceptions.OutOfDateRangeException;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivitySchduleTripBinding;
import design.swira.aennyapp.databinding.ActivityUpdateSchduleTripBinding;
import design.swira.aennyapp.pojo.aenny.newscduletrip.UpdateScduleResponse;
import design.swira.aennyapp.pojo.aenny.scduledtrips.ScduledTripsResponse;
import design.swira.aennyapp.pojo.aenny.schduletrips.UpdateScduleTrip;
import design.swira.aennyapp.pojo.aenny.trip.ResponseTrips;
import design.swira.aennyapp.pojo.aenny.trip.TripsResponse;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.ui.aenny.trips.TripsListActivity;
import design.swira.aennyapp.utils.Constants;
import okhttp3.ResponseBody;

public class UpdateSchduleTripActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityUpdateSchduleTripBinding binding;
    OnGoingViewModel viewModel;


    private int mHour, mMinute;
    String date;
    String time;
    int id;


    ArrayList<String> mylist=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_schdule_trip);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_update_schdule_trip);

        viewModel = ViewModelProviders.of(this).get(OnGoingViewModel.class);

        binding.next.setOnClickListener(this);

        binding.time.setOnClickListener(this);



        intcalenderview();






        binding.date.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {





                Calendar clickedDayCalendar = eventDay.getCalendar();
                //Toast.makeText(SchduleTripActivity.this, "" + clickedDayCalendar.getTime(), Toast.LENGTH_SHORT).show();

                String myFormat = "yyyy-MM-dd";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

                date=sdf.format(clickedDayCalendar.getTime());


            }
        });




        /*Calendar min_date_c = Calendar.getInstance();
        binding.date.setMinimumDate(min_date_c);*/

        bindMyUpdateData();










    }

    private void bindMyUpdateData() {

        Intent intent=getIntent();
        if(intent.hasExtra("id")){
            id=(int)intent.getExtras().get("id");

            viewModel.getTripById(id);

            viewModel.gettripbyidMutableLiveData.observe(UpdateSchduleTripActivity.this, new Observer<TripsResponse>() {
                @Override
                public void onChanged(TripsResponse tripsResponse) {

                    date=tripsResponse.getTripDate();
                    time=tripsResponse.getTripTime();
                    int index = time.indexOf("T");
                    String myt = time.substring(index+1);
                    myt = myt.replaceAll(":00", "");
                    binding.time.setText(myt);

                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd");

                    try {
                        Date d=dateFormat.parse(date);
                        int year = d.getYear();
                        int month = d.getMonth();
                        int day = d.getDate();

                        Calendar calendar = Calendar.getInstance();
                        //calendar.set(year, month, day);
                        calendar.setTime(d);

                        try {
                            binding.date.setDate(calendar);
                        } catch (OutOfDateRangeException e) {
                            e.printStackTrace();
                        }

                    } catch (ParseException e) {
                        e.printStackTrace();
                    }





                }
            });


        }
    }

    private void intcalenderview() {



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



        for(int i=0;i<selectedDates.size();i++){
            Calendar mcalendar = selectedDates.get(i);

            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            String d=sdf.format(mcalendar.getTime());

            //Toast.makeText(SchduleTripActivity.this, "Date: " + d + "\n" + "Time: " + time, Toast.LENGTH_SHORT).show();

            String getori = Constants.getori(UpdateSchduleTripActivity.this);
            String getdes = Constants.getdes(UpdateSchduleTripActivity.this);
            String distance=Constants.getdistance(UpdateSchduleTripActivity.this);
            String cost=Constants.getcost(UpdateSchduleTripActivity.this);
            String getlatitude = Constants.getlatitude(UpdateSchduleTripActivity.this);
            String getlongitude = Constants.getlongitude(UpdateSchduleTripActivity.this);
            String getlatitude2 = Constants.getlatitude2(UpdateSchduleTripActivity.this);
            String getlongitude2 = Constants.getlongitude2(UpdateSchduleTripActivity.this);
            int clientId = Constants.getClientId(UpdateSchduleTripActivity.this);
            int gethealthy = Constants.gethealthy(UpdateSchduleTripActivity.this);
            int gethandcap = Constants.gethandcap(UpdateSchduleTripActivity.this);
            int getpaidtype = Constants.getpaidtype(UpdateSchduleTripActivity.this);
            String getpaid = Constants.getpaid(UpdateSchduleTripActivity.this);
            int triptype=2;


            if(!getori.isEmpty() && !getdes.isEmpty() && !distance.isEmpty() && !cost.isEmpty()){
                // Toast.makeText(SchduleTripActivity.this, "You must save here", Toast.LENGTH_SHORT).show();

                //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost),Double.parseDouble(getlatitude2) , getdes,Double.parseDouble(getlongitude2),getpaidtype, time,Double.parseDouble(getlongitude) , String tripScheduleTime,  String tripDestinationTime,  triptype, gethealthy, getori,  d,  String tripScheduleDate,Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid,   clientId, gethandcap,  String tripDestinationDate);
                //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost),Double.parseDouble(getlatitude2) , getdes,Double.parseDouble(getlongitude2),getpaidtype, d+" "+time,Double.parseDouble(getlongitude) ,   triptype, gethealthy, getori,  d,  Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid,   clientId, gethandcap );
                //TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost), Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2), getpaidtype, d+" "+time, Double.parseDouble(getlongitude), d+" "+time, 0, d+" "+time, "", triptype, gethealthy, getori, "", d, 0, d, Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, 0, clientId, gethandcap, 0, d);
                /*TripsResponse tripsResponse=new TripsResponse(Double.parseDouble(cost), Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2),  d+" "+time, Double.parseDouble(getlongitude),  triptype  , gethealthy, getori,  d,   Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, 1, clientId, gethandcap );
                viewModel.saveTrips(tripsResponse);
                viewModel.savetripMutableLiveData.observe(UpdateSchduleTripActivity.this, new Observer<TripsResponse>() {
                    @Override
                    public void onChanged(TripsResponse tripsResponse) {
                        if(tripsResponse != null){
                            Toast.makeText(UpdateSchduleTripActivity.this, "Your schdule trip Updated sucessfully", Toast.LENGTH_SHORT).show();
                            Constants.saveTripData("","","","","","",UpdateSchduleTripActivity.this);
                            Constants.saveTripData2(0,0,0,"","","",UpdateSchduleTripActivity.this);
                            Intent intt=new Intent(UpdateSchduleTripActivity.this, TripsListActivity.class);
                            startActivity(intt);
                        }
                    }
                });*/



                //ResponseTrips tripsResponse=new ResponseTrips(Double.parseDouble(cost), Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2),  d+" "+time, Double.parseDouble(getlongitude),  triptype  , gethealthy, getori,  d,   Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, 1, clientId, gethandcap ,id );

                design.swira.aennyapp.pojo.aenny.newscduletrip.UpdateScduleTrip updateScduleTrip=new design.swira.aennyapp.pojo.aenny.newscduletrip.UpdateScduleTrip(Double.parseDouble(cost), triptype, gethealthy, getori, d, Double.parseDouble(getlatitude2), getdes, Double.parseDouble(getlongitude2), getpaidtype, d+" "+time, Double.parseDouble(getlongitude), Double.parseDouble(getlatitude), Double.parseDouble(distance), getpaid, id, 1, clientId, gethandcap);

                Log.i("updatesctrip",updateScduleTrip.toString());

                viewModel.updateScduledTrip(id,updateScduleTrip);

               viewModel.upsctripMutableLiveData.observe(UpdateSchduleTripActivity.this, new Observer<UpdateScduleResponse>() {
                   @Override
                   public void onChanged(UpdateScduleResponse updateScduleResponse) {
                       if(updateScduleResponse != null){
                           Toast.makeText(UpdateSchduleTripActivity.this, "Your schdule trip Updated sucessfully", Toast.LENGTH_SHORT).show();
                           Constants.saveTripData("","","","","","",UpdateSchduleTripActivity.this);
                           Constants.saveTripData2(0,0,0,"","","",UpdateSchduleTripActivity.this);
                           Intent intt=new Intent(UpdateSchduleTripActivity.this, TripsListActivity.class);
                           startActivity(intt);
                       }else{
                           Toast.makeText(UpdateSchduleTripActivity.this, "Error when updating scdule trip", Toast.LENGTH_SHORT).show();
                       }
                   }
               });


                //UpdateScduleTrip updateScduleTrip=new UpdateScduleTrip(Double.parseDouble(cost),triptype,gethealthy,getori,d,Double.parseDouble(getlatitude2),getdes,Double.parseDouble(getlongitude2),getpaidtype,d+" "+time,Double.parseDouble(getlongitude),Double.parseDouble(getlatitude),Double.parseDouble(distance),getpaid,id,1,clientId,gethandcap);

                /*viewModel.UpdateScduledTrip(id,updateScduleTrip);
                //viewModel.updateTrip(id,tripsResponse);

                viewModel.upsctripsMutableLiveData.observe(UpdateSchduleTripActivity.this, new Observer<ScduledTripsResponse>() {
                    @Override
                    public void onChanged(ScduledTripsResponse scduledTripsResponse) {
                        if(scduledTripsResponse != null){
                            Toast.makeText(UpdateSchduleTripActivity.this, "Your schdule trip Updated sucessfully", Toast.LENGTH_SHORT).show();
                            Constants.saveTripData("","","","","","",UpdateSchduleTripActivity.this);
                            Constants.saveTripData2(0,0,0,"","","",UpdateSchduleTripActivity.this);
                            Intent intt=new Intent(UpdateSchduleTripActivity.this, TripsListActivity.class);
                            startActivity(intt);
                        }else{
                            Toast.makeText(UpdateSchduleTripActivity.this, "Error when updating scdule trip", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/


                /*Toast.makeText(UpdateSchduleTripActivity.this, "Your schdule trip Updated sucessfully", Toast.LENGTH_SHORT).show();
                Constants.saveTripData("","","","","","",UpdateSchduleTripActivity.this);
                Constants.saveTripData2(0,0,0,"","","",UpdateSchduleTripActivity.this);
                Intent intt=new Intent(UpdateSchduleTripActivity.this, TripsListActivity.class);
                startActivity(intt);*/

                /*viewModel.updatetripMutableLiveData.observe(UpdateSchduleTripActivity.this, new Observer<ResponseBody>() {
                    @Override
                    public void onChanged(ResponseBody Response) {
                        if(Response != null){
                            Toast.makeText(UpdateSchduleTripActivity.this, "Your schdule trip Updated sucessfully", Toast.LENGTH_SHORT).show();
                            Constants.saveTripData("","","","","","",UpdateSchduleTripActivity.this);
                            Constants.saveTripData2(0,0,0,"","","",UpdateSchduleTripActivity.this);
                            Intent intt=new Intent(UpdateSchduleTripActivity.this, TripsListActivity.class);
                            startActivity(intt);
                        }
                    }
                });*/

            }/*else{

                Intent ii=new Intent(UpdateSchduleTripActivity.this,RouteActivity.class);
                ii.putExtra("selectedDates", (Serializable) selectedDates);
                ii.putExtra("selectedDTime",time);
                ii.putExtra("Scduled","yes");
                startActivity(ii);
            }*/

        }




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
        Constants.saveTripData("", "", "", "", "", "", UpdateSchduleTripActivity.this);
        Constants.saveTripData2(0, 0, 0, "", "", "", UpdateSchduleTripActivity.this);
    }





}
