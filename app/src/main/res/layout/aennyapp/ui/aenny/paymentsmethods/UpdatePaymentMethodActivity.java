package design.swira.aennyapp.ui.aenny.paymentsmethods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityAddPaymentsMethodBinding;
import design.swira.aennyapp.databinding.ActivityUpdatePaymentMethodBinding;
import design.swira.aennyapp.pojo.aenny.clientpaymentsmethods.ClientsPaymentsMethodsResponse;
import design.swira.aennyapp.pojo.aenny.paymentsmethods.PaymentsMethodsResponse;
import design.swira.aennyapp.ui.aenny.adapters.PaymentsMethodsAdapter;
import design.swira.aennyapp.utils.Constants;

public class UpdatePaymentMethodActivity extends AppCompatActivity implements View.OnClickListener {


    ActivityUpdatePaymentMethodBinding binding;
    PaymentsMethodsViewModel viewModel;
    PaymentsMethodsAdapter adapter;

    int paymentmethodid;
    int id;
    int clientid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_payment_method);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_payment_method);

        viewModel= ViewModelProviders.of(this).get(PaymentsMethodsViewModel.class);

        bindMyData();

        fillMyData();

        binding.add.setOnClickListener(this);
    }

    private void fillMyData() {
        Intent intent=getIntent();
        if(intent.hasExtra("id")){
            id=(int)intent.getExtras().get("id");

            viewModel.getClientPaymentById(id);
            viewModel.getclientpaymentsmethodsMutableLiveData.observe(UpdatePaymentMethodActivity.this, new Observer<ClientsPaymentsMethodsResponse>() {
                @Override
                public void onChanged(ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse) {
                    if(clientsPaymentsMethodsResponse != null){
                        binding.holdername.setText(clientsPaymentsMethodsResponse.getCardHolderName());
                        binding.cardnumber.setText(clientsPaymentsMethodsResponse.getCardNumber());
                        binding.expiredate.setText(clientsPaymentsMethodsResponse.getExpirationDate());
                        binding.cvv.setText(clientsPaymentsMethodsResponse.getCVV());

                        clientid=clientsPaymentsMethodsResponse.getClientId();

                        paymentmethodid=clientsPaymentsMethodsResponse.getPaymentTypeId();

                        paymentmethodid=paymentmethodid-1;
                        binding.spinner.setSelection(paymentmethodid);
                    }
                }
            });

        }
    }

    private void bindMyData() {
        viewModel.getAllPaymentsMethods();

        viewModel.paymentsmethodsMutableLiveData.observe(UpdatePaymentMethodActivity.this, new Observer<List<PaymentsMethodsResponse>>() {
            @Override
            public void onChanged(List<PaymentsMethodsResponse> paymentsMethodsResponses) {
                adapter=new PaymentsMethodsAdapter(UpdatePaymentMethodActivity.this,paymentsMethodsResponses);
                binding.spinner.setAdapter(adapter);
                binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        paymentmethodid= Integer.parseInt(paymentsMethodsResponses.get(position).getId());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.add){
            saveClientPaymentsMethod();
        }
    }

    private void saveClientPaymentsMethod() {
        String holdername=binding.holdername.getText().toString();
        String cardnumber=binding.cardnumber.getText().toString();
        String expiredate=binding.expiredate.getText().toString();
        String cvv=binding.cvv.getText().toString();
        //int clientid= Constants.getClientId(UpdatePaymentMethodActivity.this);

        if(clientid > 0 && paymentmethodid > 0){
            ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse=new ClientsPaymentsMethodsResponse(paymentmethodid, cvv, cardnumber, holdername, expiredate, clientid,id);
            viewModel.updateClientPaymentById(id,clientsPaymentsMethodsResponse);

            Toast.makeText(UpdatePaymentMethodActivity.this, "Update Payments Method Sucessfully", Toast.LENGTH_SHORT).show();
            Intent iiiii=new Intent(UpdatePaymentMethodActivity.this, PaymentsListActivity.class);
            startActivity(iiiii);

            viewModel.updateclientpaymentsmethodsMutableLiveData.observe(UpdatePaymentMethodActivity.this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    //if(s != null){
                        Toast.makeText(UpdatePaymentMethodActivity.this, "Update Payments Method Sucessfully", Toast.LENGTH_SHORT).show();
                        Intent iiiii=new Intent(UpdatePaymentMethodActivity.this, PaymentsListActivity.class);
                        startActivity(iiiii);
                    ///
                    //Toast.makeText(UpdatePaymentMethodActivity.this, s.toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
