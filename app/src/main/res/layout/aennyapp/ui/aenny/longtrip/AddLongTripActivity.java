package design.swira.aennyapp.ui.aenny.longtrip;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityAddLongTripBinding;
import design.swira.aennyapp.pojo.aenny.longtrip.LongTrip;
import design.swira.aennyapp.ui.aenny.regsister.CompleteRegsisterActivity;
import design.swira.aennyapp.utils.Constants;

public class AddLongTripActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityAddLongTripBinding binding;
    LongTripViewModel viewModel;
    Calendar myCalendar;
    Calendar myCalendar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_add_long_trip);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_long_trip);
        viewModel= ViewModelProviders.of(this).get(LongTripViewModel.class);

        binding.send.setOnClickListener(this);
        binding.startdate.setOnClickListener(this);
        binding.enddate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.send){
            saveLongTrip();
        }else if(v.getId() == R.id.startdate){
            getstartdate();
        }else if(v.getId() == R.id.enddate){
            getenddate();
        }

    }

    private void saveLongTrip() {
        int clientId = Constants.getClientId(AddLongTripActivity.this);
        String clientMobile = Constants.getClientMobile(AddLongTripActivity.this);

        String startdate=binding.startdate.getText().toString();
        String enddate=binding.enddate.getText().toString();
        String tripinfo=binding.tripinfo.getText().toString();

        if(startdate.isEmpty()){
            Toast.makeText(AddLongTripActivity.this, "must select trip start date", Toast.LENGTH_SHORT).show();
            binding.startdate.setError("must select trip start date");
        }else if(enddate.isEmpty()){
            Toast.makeText(AddLongTripActivity.this, "must select trip end date", Toast.LENGTH_SHORT).show();
            binding.enddate.setError("must select trip end date");
        }else if(tripinfo.isEmpty()){
            Toast.makeText(AddLongTripActivity.this, "must select trip info", Toast.LENGTH_SHORT).show();
            binding.tripinfo.setError("must select trip info");
        }else{
            LongTrip longTrip=new LongTrip(enddate,clientMobile,startdate,tripinfo,clientId);
            viewModel.saveLongTrip(longTrip);
            viewModel.longTripMutableLiveData.observe(AddLongTripActivity.this, new Observer<LongTrip>() {
                @Override
                public void onChanged(LongTrip longTrip) {
                    if(longTrip != null){
                        Toast.makeText(AddLongTripActivity.this, "Long trip data send secessfully", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(AddLongTripActivity.this, "Long trip data send failed!,try again", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void getstartdate() {

         myCalendar  = Calendar.getInstance();

        //EditText edittext= (EditText) findViewById(R.id.Birthday);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatestartdate();
            }

        };


      /* new DatePickerDialog(AddLongTripActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();*/


        DatePickerDialog dialog = new DatePickerDialog(AddLongTripActivity.this, date,
                myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        dialog.show();
    }

    private void updatestartdate() {

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        binding.startdate.setText(sdf.format(myCalendar.getTime()));
    }


    private void getenddate() {

        myCalendar2  = Calendar.getInstance();

        //EditText edittext= (EditText) findViewById(R.id.Birthday);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar2.set(Calendar.YEAR, year);
                myCalendar2.set(Calendar.MONTH, monthOfYear);
                myCalendar2.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateenddate();
            }

        };

        /*new DatePickerDialog(AddLongTripActivity.this, date, myCalendar2
                .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                myCalendar2.get(Calendar.DAY_OF_MONTH)).show();*/

        DatePickerDialog dialog = new DatePickerDialog(AddLongTripActivity.this, date,
                myCalendar2
                        .get(Calendar.YEAR), myCalendar2.get(Calendar.MONTH),
                myCalendar2.get(Calendar.DAY_OF_MONTH));

        dialog.getDatePicker().setMinDate(Calendar.getInstance().getTimeInMillis());
        dialog.show();
    }

    private void updateenddate() {

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        binding.enddate.setText(sdf.format(myCalendar2.getTime()));
    }

}
