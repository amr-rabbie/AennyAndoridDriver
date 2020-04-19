package design.swira.aennyapp.ui.aenny.intro;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.IntroData;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.ui.aenny.clientfavourites.OnLocationFavouriteClick;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.IntroViewHolder> implements RecyclerView.OnItemTouchListener {

    List<IntroData> introDataList;
    OnIntroClick onIntroClick;

    public IntroAdapter(OnIntroClick onIntroClick) {
        this.onIntroClick = onIntroClick;
    }

    public void setList(List<IntroData> introDataList){
        this.introDataList=introDataList;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @NonNull
    @Override
    public IntroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.intro_item,parent,false);

        /*v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int)v.getTag();
                onIntroClick.onListClick(pos);
            }
        });*/



        /*v.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int pos = (int)v.getTag();
                onIntroClick.onListClick(pos);
            }
        });*/





        return new IntroViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull IntroViewHolder holder, int position) {
        holder.title.setText(introDataList.get(position).getTitle());
        holder.body.setText(introDataList.get(position).getBody());

        holder.cview.setTag(position);

        //onIntroClick.onListClick(position);

        if(position == 0){
            holder.img2.setImageResource(R.drawable.ic_white_line);
            holder.img1.setImageResource(R.mipmap.rounded_rectangle_3);
            holder.img3.setImageResource(R.drawable.ic_white_line);
        }
        else if(position == 1){
            holder.img2.setImageResource(R.mipmap.rounded_rectangle_3);
            holder.img1.setImageResource(R.drawable.ic_white_line);
            holder.img3.setImageResource(R.drawable.ic_white_line);
        }
        else if(position == 2){
            holder.img2.setImageResource(R.drawable.ic_white_line);
            holder.img1.setImageResource(R.drawable.ic_white_line);
            holder.img3.setImageResource(R.mipmap.rounded_rectangle_3);
        }
    }

    @Override
    public int getItemCount() {
        return introDataList.size();
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
        int index = (int)rv.getTag();
        onIntroClick.onListClick(index);
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    public class IntroViewHolder extends RecyclerView.ViewHolder {
        TextView title,body;
        LinearLayout cview;
        ImageView img1,img2,img3;
        public IntroViewHolder(@NonNull View v) {
            super(v);
            title=v.findViewById(R.id.title);
            body=v.findViewById(R.id.body);
            cview=v.findViewById(R.id.cview);

            img1=v.findViewById(R.id.img1);
            img2=v.findViewById(R.id.img2);
            img3=v.findViewById(R.id.img3);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
