package design.swira.aennyappdriver.ui.aenny.notifications;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;


import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.databinding.ActivityNotificationsListBinding;
import design.swira.aennyappdriver.pojo.aenny.notifications.NotificationsResponse;
import design.swira.aennyappdriver.ui.aenny.adapters.NotificationsAdapter;
import design.swira.aennyappdriver.utils.Constants;

public class NotificationsListActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityNotificationsListBinding binding;
    NotificationsViewModel viewModel;
    NotificationsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_notifications_list);

        binding= DataBindingUtil.setContentView(this, R.layout.activity_notifications_list);

        viewModel= ViewModelProviders.of(this).get(NotificationsViewModel.class);



        adapter=new NotificationsAdapter(this);

        bindMyNotifications();


    }

    private void bindMyNotifications() {
        int usertype=2;
        int clientid= Constants.getClientId(NotificationsListActivity.this);
        viewModel.getAllNoficationsByClientId(usertype,clientid);

        viewModel.notifylistMutableLiveData.observe(NotificationsListActivity.this, new Observer<List<NotificationsResponse>>() {
            @Override
            public void onChanged(List<NotificationsResponse> notificationsResponses) {
                if(notificationsResponses != null){
                    if(notificationsResponses.size() > 0) {
                        viewData(notificationsResponses);
                        binding.recycler.setVisibility(View.VISIBLE);
                        binding.pbar.setVisibility(View.GONE);
                    }else{
                        binding.recycler.setVisibility(View.GONE);
                        binding.pbar.setVisibility(View.GONE);
                        binding.notexists.setVisibility(View.VISIBLE);
                    }
                }else{
                    binding.recycler.setVisibility(View.GONE);
                    binding.pbar.setVisibility(View.GONE);
                    binding.notexists.setVisibility(View.VISIBLE);
                }

            }
        });
    }

    private void viewData(List<NotificationsResponse> notificationsResponses) {
        adapter.setList(notificationsResponses);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(NotificationsListActivity.this));
    }

    @Override
    public void onClick(View v) {

    }


    private String getDate(long time) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        String date = DateFormat.format("dd-MM-yyyy", cal).toString();
        return date;


        /*SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = formatter.format(new Date(Long.parseLong(YOUR TIMESTAMP VALUE)));
        txtDate.setText(dateString);*/


        /*String time = DateUtils.formatDateTime(this, 1378798459, DateUtils.FORMAT_SHOW_TIME);
        String date =  DateUtils.formatDateTime(this, 1378798459, DateUtils.FORMAT_SHOW_DATE);*/


        /*Calendar call = Calendar.getInstance();
        call.setTimeInMillis(System.currentTimeMillis());
        Date mdate = call.getTime();
        int mHour = mdate.getHours();
        int mMinute = mdate.getMinutes();*/

        /*long timestampString =  Long.parseLong("yourString");
        String value = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").
                format(new java.util.Date(timestampString * 1000));*/

    }

    public  String getDateCurrentTimeZone(long timestamp) {
        try{
            Calendar calendar = Calendar.getInstance();
            TimeZone tz = TimeZone.getDefault();
            calendar.setTimeInMillis(timestamp * 1000);
            calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date currenTimeZone = (Date) calendar.getTime();
            return sdf.format(currenTimeZone);
        }catch (Exception e) {
        }
        return "";
    }


}
