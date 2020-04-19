package design.swira.aennyapp.ui.aenny.adapters;

import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.notifications.NotificationsResponse;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationsViewHolder> {

    List<NotificationsResponse> notificationslist;
    Context context;

    public NotificationsAdapter(Context context) {
        this.context = context;
    }

    public void setList(List<NotificationsResponse> notificationslist){
        this.notificationslist=notificationslist;
    }

    @NonNull
    @Override
    public NotificationsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.notify_list_item,parent,false);
        return new NotificationsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsViewHolder holder, int position) {

        NotificationsResponse notification = notificationslist.get(position);

        holder.title.setText(notification.getNotificationTitle());
        holder.body.setText(notification.getNotificationDescription());

        //String notificationTimeStamp = notification.getNotificationTimeStamp();


        //String time = DateUtils.formatDateTime(context, notification.getNotificationTimeStamp(), DateUtils.FORMAT_SHOW_TIME);

        String date = notification.getNotificationTimeStamp();
        String time=notification.getNotificationTimeStamp();


        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");

        SimpleDateFormat dateFormat2 = new SimpleDateFormat(
                "MM/dd/yyyy hh:mm:ss");

        int index = time.indexOf("T");
        String myt = time.substring(index+1);
        myt = myt.replaceAll(":00", "");
        holder.time.setText(myt);


        try {
            Date d=dateFormat.parse(date);
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            String myday=sdf.format(d);
            //holder.date.setText(myday);
            holder.date.setText(myday);

        } catch (ParseException e) {
            e.printStackTrace();
        }



    }

    @Override
    public int getItemCount() {
        return notificationslist.size();
    }

    public class NotificationsViewHolder extends RecyclerView.ViewHolder {
        CardView cview;
        TextView title,body,time,date;
        ImageView img;
        public NotificationsViewHolder(@NonNull View v) {
            super(v);
            cview=v.findViewById(R.id.cview);
            title=v.findViewById(R.id.title);
            body=v.findViewById(R.id.body);
            time=v.findViewById(R.id.time);
            date=v.findViewById(R.id.date);
            img=v.findViewById(R.id.img);



        }
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
