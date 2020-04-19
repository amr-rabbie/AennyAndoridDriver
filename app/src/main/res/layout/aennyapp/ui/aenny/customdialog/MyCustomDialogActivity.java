package design.swira.aennyapp.ui.aenny.customdialog;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import butterknife.BindView;
import butterknife.ButterKnife;
import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.pojo.aenny.clientpaymentsmethods.ClientsPaymentsMethodsResponse;
import design.swira.aennyapp.pojo.aenny.trip.TripsResponse;
import design.swira.aennyapp.ui.aenny.clientfavourites.ClientFavouriteListActivity;
import design.swira.aennyapp.ui.aenny.clientfavourites.ClientFavouriteViewModel;
import design.swira.aennyapp.ui.aenny.ongonigtrip.OnGoingViewModel;
import design.swira.aennyapp.ui.aenny.paymentsmethods.PaymentsListActivity;
import design.swira.aennyapp.ui.aenny.paymentsmethods.PaymentsMethodsViewModel;
import design.swira.aennyapp.ui.aenny.trips.TripsListActivity;

public class MyCustomDialogActivity extends AppCompatActivity  {

    ClientFavouriteViewModel clientFavouriteViewModel;
    PaymentsMethodsViewModel paymentsMethodsViewModel;
    OnGoingViewModel onGoingViewModel;
    @BindView(R.id.dtitle)
    TextView dtitle;
    @BindView(R.id.dmsg)
    TextView dmsg;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.cancel)
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_my_custom_dialog);

        ButterKnife.bind(this);



        if (getIntent().hasExtra("id")) {
            int id = (int) getIntent().getExtras().get("id");
            String title = (String) getIntent().getExtras().get("title");
            String msg = (String) getIntent().getExtras().get("msg");
            String flag = (String) getIntent().getExtras().get("flag");

            clientFavouriteViewModel = ViewModelProviders.of(this).get(ClientFavouriteViewModel.class);
            paymentsMethodsViewModel=ViewModelProviders.of(this).get(PaymentsMethodsViewModel.class);
            onGoingViewModel=ViewModelProviders.of(this).get(OnGoingViewModel.class);

            bindData(id, title, msg, flag);

        }


    }

    private void bindData(int id, String title, String msg, String flag) {
        //TextView dtitle =  findViewById(R.id.dtitle);
        dtitle.setText(title);

        //TextView dmsg =  findViewById(R.id.dmsg);
        dmsg.setText(msg);

        //Button ok =  findViewById(R.id.ok);

        //Button cancel =  findViewById(R.id.cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag.equals("fav")) {
                    Intent i = new Intent(MyCustomDialogActivity.this, ClientFavouriteListActivity.class);
                    startActivity(i);
                }else if(flag.equals("pay")){
                    Intent i = new Intent(MyCustomDialogActivity.this, PaymentsListActivity.class);
                    startActivity(i);
                }else if(flag.equals("scdule")){
                    Intent i = new Intent(MyCustomDialogActivity.this, TripsListActivity.class);
                    startActivity(i);
                }
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag.equals("fav")) {
                    clientFavouriteViewModel.deleteClientFavouritebyid(id);
                    clientFavouriteViewModel.deleteclientFavouritebyidMutableLiveData.observe(MyCustomDialogActivity.this, new Observer<ClientFavouriteResponse>() {
                        @Override
                        public void onChanged(ClientFavouriteResponse clientFavouriteResponse) {
                            if (clientFavouriteResponse != null) {
                                Toast.makeText(MyCustomDialogActivity.this, "Client Favourite deleted sucessfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MyCustomDialogActivity.this, ClientFavouriteListActivity.class);
                                startActivity(i);
                            }
                        }
                    });

                }else if(flag.equals("pay")){
                    paymentsMethodsViewModel.deleteClientPaymentbyid(id);
                    paymentsMethodsViewModel.deleteclientpaymentsmethodsMutableLiveData.observe(MyCustomDialogActivity.this, new Observer<ClientsPaymentsMethodsResponse>() {
                        @Override
                        public void onChanged(ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse) {
                            if (clientsPaymentsMethodsResponse != null) {
                                Toast.makeText(MyCustomDialogActivity.this, "Client payment method deleted sucessfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MyCustomDialogActivity.this, PaymentsListActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }else if(flag.equals("scdule")){
                    onGoingViewModel.deleteTripById(id);
                    onGoingViewModel.deletetripbyidMutableLiveData.observe(MyCustomDialogActivity.this, new Observer<TripsResponse>() {
                        @Override
                        public void onChanged(TripsResponse tripsResponse) {
                            if(tripsResponse != null){
                                Toast.makeText(MyCustomDialogActivity.this, "Scdule trip deleted sucessfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(MyCustomDialogActivity.this, TripsListActivity.class);
                                startActivity(i);
                            }
                        }
                    });
                }

            }
        });
    }


}
