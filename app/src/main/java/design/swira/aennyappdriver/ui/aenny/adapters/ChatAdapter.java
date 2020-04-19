package design.swira.aennyappdriver.ui.aenny.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.pojo.aennydriver.chat.ChatResponse;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {
    List<ChatResponse> list;
    Context context;

    public ChatAdapter(List<ChatResponse> list,Context context) {
        this.list = list;
        this.context=context;
    }

    public void setList(List<ChatResponse> list){
        this.list=list;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.chat_item,parent,false);
        return new ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {


        ChatResponse chatResponse = list.get(position);


        String time=chatResponse.getTimeStamp();

        int index = time.indexOf("T");
        String myt = time.substring(index + 1);
        myt = myt.replaceAll(":00", "");




        if(chatResponse.getUserType() == 1){
           /* //holder.chattext.setBackgroundColor(Color.parseColor("#b5b4ba"));
            holder.chattext.setBackgroundColor(Color.GRAY);
            //holder.chattext.setTextColor(Color.parseColor("#3a3a3a"));
            holder.chattext.setTextColor(Color.BLACK);
            holder.chattext.setGravity(Gravity.LEFT);
            holder.chattime.setGravity(Gravity.LEFT);*/
            holder.otherchattext.setText(chatResponse.getMessage());
            holder.otherchattime.setText(myt);
            holder.otherchattext.setVisibility(View.VISIBLE);
            holder.otherchattime.setVisibility(View.VISIBLE);
        }else if(chatResponse.getUserType() == 2){
           /* //holder.chattext.setBackgroundColor(Color.parseColor("##35af6a"));
            holder.chattext.setBackgroundColor(Color.GREEN);
            //holder.chattext.setTextColor(Color.parseColor("#ffffff"));
            holder.chattext.setTextColor(Color.WHITE);
            holder.chattext.setGravity(Gravity.RIGHT);
            holder.chattime.setGravity(Gravity.RIGHT);*/
            holder.youchattext.setText(chatResponse.getMessage());
            holder.youchattime.setText(myt);
            holder.youchattext.setVisibility(View.VISIBLE);
            holder.youchattime.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView otherchattext,otherchattime;
        TextView youchattext,youchattime;
        public ChatViewHolder(@NonNull View v) {
            super(v);
            otherchattext=v.findViewById(R.id.otherchattext);
            otherchattime=v.findViewById(R.id.otherchattime);
            youchattext=v.findViewById(R.id.youchattext);
            youchattime=v.findViewById(R.id.youchattime);
        }
    }
}
