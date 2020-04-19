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
import design.swira.aennyapp.pojo.aenny.clientpaymentsmethods.ClientsPaymentsMethodsResponse;
import design.swira.aennyapp.pojo.aenny.paymentsmethods.PaymentsMethodsResponse;
import design.swira.aennyapp.ui.aenny.adapters.PaymentsMethodsAdapter;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.utils.Constants;

public class AddPaymentsMethodActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityAddPaymentsMethodBinding binding;
    PaymentsMethodsViewModel viewModel;
    PaymentsMethodsAdapter adapter;

    int paymentmethodid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_add_payments_method);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_add_payments_method);

        viewModel= ViewModelProviders.of(this).get(PaymentsMethodsViewModel.class);

        bindMyData();

        binding.add.setOnClickListener(this);
    }

    private void bindMyData() {
        viewModel.getAllPaymentsMethods();

        viewModel.paymentsmethodsMutableLiveData.observe(AddPaymentsMethodActivity.this, new Observer<List<PaymentsMethodsResponse>>() {
            @Override
            public void onChanged(List<PaymentsMethodsResponse> paymentsMethodsResponses) {
                adapter=new PaymentsMethodsAdapter(AddPaymentsMethodActivity.this,paymentsMethodsResponses);
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
        int clientid= Constants.getClientId(AddPaymentsMethodActivity.this);

        if(clientid > 0 && paymentmethodid > 0){

            if(paymentmethodid == 1){
                ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse=new ClientsPaymentsMethodsResponse(paymentmethodid, cvv, cardnumber, holdername, expiredate, clientid);
                viewModel.saveClientPaymentMethod(clientsPaymentsMethodsResponse);

                viewModel.clientpaymentsmethodsMutableLiveData.observe(AddPaymentsMethodActivity.this, new Observer<ClientsPaymentsMethodsResponse>() {
                    @Override
                    public void onChanged(ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse) {
                        if(clientsPaymentsMethodsResponse != null){
                            Toast.makeText(AddPaymentsMethodActivity.this, "Add Payments Method Sucessfully", Toast.LENGTH_SHORT).show();
                            Intent iiiii=new Intent(AddPaymentsMethodActivity.this, PaymentsListActivity.class);
                            startActivity(iiiii);
                        }
                    }
                });

            }else if(paymentmethodid == 2 || paymentmethodid == 3){
                if(holdername.isEmpty()){
                    Toast.makeText(AddPaymentsMethodActivity.this, "must enter holder name", Toast.LENGTH_SHORT).show();
                }else if(cardnumber.isEmpty()){
                    Toast.makeText(AddPaymentsMethodActivity.this, "must enter card number", Toast.LENGTH_SHORT).show();
                }else if(expiredate.isEmpty()){
                    Toast.makeText(AddPaymentsMethodActivity.this, "must enter expire date", Toast.LENGTH_SHORT).show();
                }else if(cvv.isEmpty()){
                    Toast.makeText(AddPaymentsMethodActivity.this, "must enter cvv", Toast.LENGTH_SHORT).show();
                }else{
                    ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse=new ClientsPaymentsMethodsResponse(paymentmethodid, cvv, cardnumber, holdername, expiredate, clientid);
                    viewModel.saveClientPaymentMethod(clientsPaymentsMethodsResponse);

                    viewModel.clientpaymentsmethodsMutableLiveData.observe(AddPaymentsMethodActivity.this, new Observer<ClientsPaymentsMethodsResponse>() {
                        @Override
                        public void onChanged(ClientsPaymentsMethodsResponse clientsPaymentsMethodsResponse) {
                            if(clientsPaymentsMethodsResponse != null){
                                Toast.makeText(AddPaymentsMethodActivity.this, "Add Payments Method Sucessfully", Toast.LENGTH_SHORT).show();
                                Intent iiiii=new Intent(AddPaymentsMethodActivity.this, PaymentsListActivity.class);
                                startActivity(iiiii);
                            }
                        }
                    });

                }

            }


        }
    }
}
