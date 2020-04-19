package design.swira.aennyappdriver.ui.aenny.chat;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.data.api.ApiClient;
import design.swira.aennyappdriver.databinding.ActivityChatBinding;
import design.swira.aennyappdriver.pojo.aenny.clients.ClientDataResponse;
import design.swira.aennyappdriver.pojo.aennydriver.chat.ChatResponse;
import design.swira.aennyappdriver.ui.aenny.adapters.ChatAdapter;
import design.swira.aennyappdriver.ui.aenny.drivertrips.StartTripActivity;
import design.swira.aennyappdriver.ui.aenny.drivertrips.TripsViewModel;
import design.swira.aennyappdriver.ui.aenny.mainpage.MainDriverActivity;
import design.swira.aennyappdriver.utils.Constants;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityChatBinding binding;
    private int driverid;
    TripsViewModel viewModel;
    ChatViewModel chatViewModel;
    private String phone;
    private int tripid;
    ChatAdapter chatAdapter;
    private HubConnection hubConnection;
    private int getnewmsg=0;
    private final int intervaltime = 1000;
    private String msgTime;
    private int clientid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        //setContentView(R.layout.activity_chat);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_chat);
        viewModel = ViewModelProviders.of(this).get(TripsViewModel.class);
        chatViewModel= ViewModelProviders.of(this).get(ChatViewModel.class);


        binding.back.setOnClickListener(this);
        binding.call.setOnClickListener(this);
        binding.chatbtn.setOnClickListener(this);


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
                        String driveridd = String.valueOf(Constants.getClientId(ChatActivity.this));
                        hubConnection.send("OnConnectedAsync", driveridd, 3);
                        Log.i("ConnectionStatus", driveridd + " ,3, " + true);
                    } catch (Exception e) {
                        Toast.makeText(ChatActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                }
            }.start();











        } catch (Exception e) {
            e.printStackTrace();
            Log.e("hub error", e.getMessage().toString());
            Toast.makeText(ChatActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }

        //chatAdapter=new ChatAdapter();

        Intent intent=getIntent();
        if(intent.hasExtra("clientid")){
            clientid=(int)intent.getExtras().get("clientid");
            fillDriverData();

        }

        if(intent.hasExtra("tripid")){
            tripid=(int)intent.getExtras().get("tripid");
            fillChatDat();
        }


        try {



        hubConnection.on("ReceiveMessage", (Trip_Id, User_Id_From, UserId_To, Message, UserName, UserTypeFrom,UserTypeTo, MessageTime) -> {
            //Log.i("LocClient",latitude + "," + longitude);
            //Log.i("chatmsg", Trip_Id + " , " + User_Id_From );
            Log.i("recvmsg",Message + MessageTime);
            if(tripid == Trip_Id){
                getnewmsg = 1;

            }

            }, Integer.class, Integer.class, Integer.class,String.class,String.class, Integer.class, Integer.class,String.class);



            final int delay2 = intervaltime * 1;

            final Handler handler2 = new Handler();
            //int delay = 1000; //milliseconds

            handler2.postDelayed(new Runnable() {
                public void run() {
                    //do something
                    if (getnewmsg == 1) {
                        fillChatDat2();
                        getnewmsg = 0;
                    }



                    handler2.postDelayed(this, delay2);
                }
            }, delay2);


        } catch (Exception e) {
            e.printStackTrace();
            Log.e("recvmsg", e.getMessage().toString());
            Toast.makeText(ChatActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
        }




        }

    private void fillChatDat() {
        binding.pbar.setVisibility(View.VISIBLE);
        binding.chatreecycler.setVisibility(View.GONE);
        chatViewModel.getChat(tripid,0,100);
        chatViewModel.chatResponseMutableLiveData.observe(ChatActivity.this, new Observer<List<ChatResponse>>() {
            @Override
            public void onChanged(List<ChatResponse> chatResponse) {
                if(chatResponse != null){
                   // chatAdapter.setList(chatResponse);
                    chatAdapter=new ChatAdapter(chatResponse,ChatActivity.this);
                    binding.chatreecycler.setAdapter(chatAdapter);
                    LinearLayoutManager ll=new LinearLayoutManager(ChatActivity.this);
                    ll.setStackFromEnd(true);
                    binding.chatreecycler.setLayoutManager(ll);
                    binding.pbar.setVisibility(View.GONE);
                    binding.chatreecycler.setVisibility(View.VISIBLE);
                }else{
                    Toast.makeText(ChatActivity.this, "There is no previous data", Toast.LENGTH_SHORT).show();
                    binding.pbar.setVisibility(View.GONE);
                    binding.chatreecycler.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void fillChatDat2() {
        /*binding.pbar.setVisibility(View.VISIBLE);
        binding.chatreecycler.setVisibility(View.GONE);*/
        chatViewModel.getChat(tripid,0,100);
        chatViewModel.chatResponseMutableLiveData.observe(ChatActivity.this, new Observer<List<ChatResponse>>() {
            @Override
            public void onChanged(List<ChatResponse> chatResponse) {
                if(chatResponse != null){
                    //chatAdapter.setList(chatResponse);
                    chatAdapter=new ChatAdapter(chatResponse,ChatActivity.this);
                    binding.chatreecycler.setAdapter(chatAdapter);
                    LinearLayoutManager ll=new LinearLayoutManager(ChatActivity.this);
                    ll.setStackFromEnd(true);
                    binding.chatreecycler.setLayoutManager(ll);
                    /*binding.pbar.setVisibility(View.GONE);
                    binding.chatreecycler.setVisibility(View.VISIBLE);*/
                }else{
                    Toast.makeText(ChatActivity.this, "There is no previous data", Toast.LENGTH_SHORT).show();
                   /* binding.pbar.setVisibility(View.GONE);
                    binding.chatreecycler.setVisibility(View.VISIBLE);*/
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back:
                Intent resultIntent = new Intent();
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
                break;
            case R.id.call:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                startActivity(intent);
                break;
            case R.id.chatbtn:

                String text=binding.chattext.getText().toString();
                if(!text.isEmpty()){
                    int driverid=Constants.getClientId(ChatActivity.this);
                    String drivername=Constants.getClientName(ChatActivity.this);
                    getmsgtime();
                    try {
                        hubConnection.send("SendMessage", tripid, driverid, clientid, text, drivername, 2,1, msgTime);
                        new Handler().postDelayed(new Runnable(){
                            @Override
                            public void run() {
                                fillChatDat2();
                                binding.chattext.setText("");
                                //Toast.makeText(ChatActivity.this, "Message sent", Toast.LENGTH_SHORT).show();
                            }
                        }, intervaltime * 2);
                        Log.i("sendmsg",text + " " + msgTime);
                    }catch (Exception e){
                        Log.e("sendmsg",e.getMessage().toString());
                    }
                }else{
                    Toast.makeText(ChatActivity.this, "Must enter chat message", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void fillDriverData() {
        viewModel.getClientData(clientid);
        viewModel.clientMutableLiveData.observe(ChatActivity.this, new Observer<ClientDataResponse>() {
            @Override
            public void onChanged(ClientDataResponse clientDataResponse) {
                if(clientDataResponse != null){
                    binding.drivername.setText(clientDataResponse.getClientName());
                    phone=clientDataResponse.getClientMobile();
                    binding.driverrating.setRating(clientDataResponse.getClientAvgRate());
                }
            }
        });
    }


    private void getmsgtime() {
        try {
            //dateoftrip = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            msgTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        }catch (Exception e){
            Log.e("acceptTime",e.getMessage().toString());
        }

    }

}
