package design.swira.aennyapp.ui.aenny.paymentsmethods;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityPaymentsListBinding;
import design.swira.aennyapp.pojo.aenny.clientpaymentsmethods.ClientsPaymentsMethodsResponse;
import design.swira.aennyapp.ui.aenny.adapters.ClientPaymentsMethodsAdapter;
import design.swira.aennyapp.ui.aenny.adapters.ClientsPaymentsMethodsAdapter;
import design.swira.aennyapp.ui.aenny.clientfavourites.ClientFavouriteListActivity;
import design.swira.aennyapp.ui.aenny.clientfavourites.UpdateClientFavouriteActivity;
import design.swira.aennyapp.ui.aenny.customdialog.MyCustomDialogActivity;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.SwipeHelper;

public class PaymentsListActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityPaymentsListBinding binding;
    PaymentsMethodsViewModel viewModel;
    ClientsPaymentsMethodsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_payments_list);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_payments_list);
        viewModel= ViewModelProviders.of(this).get(PaymentsMethodsViewModel.class);

        binding.imgadd.setOnClickListener(this);
        binding.add.setOnClickListener(this);

        bindData();

        adapter=new ClientsPaymentsMethodsAdapter(this);


    }

    private void bindData() {
        int clientid= Constants.getClientId(PaymentsListActivity.this);
        viewModel.getPaymentsMethodsByClientId(clientid);

        viewModel.clientpaymentsmethodsMutableLiveData2.observe(PaymentsListActivity.this, new Observer<List<ClientsPaymentsMethodsResponse>>() {
            @Override
            public void onChanged(List<ClientsPaymentsMethodsResponse> clientsPaymentsMethodsResponses) {
                if(clientsPaymentsMethodsResponses != null){
                    adapter.SetList(clientsPaymentsMethodsResponses);
                    binding.recycler.setAdapter(adapter);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(PaymentsListActivity.this));
                    binding.pbar.setVisibility(View.GONE);
                    binding.recycler.setVisibility(View.VISIBLE);


                    SwipeHelper swipeHelper = new SwipeHelper(PaymentsListActivity.this, binding.recycler) {
                        @Override
                        public void instantiateUnderlayButton(RecyclerView.ViewHolder viewHolder, List<UnderlayButton> underlayButtons) {
                            underlayButtons.add(new SwipeHelper.UnderlayButton(
                                    "Delete",
                                    0,
                                    Color.parseColor("#ea0000"),
                                    new SwipeHelper.UnderlayButtonClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            // TODO: onDelete

                                            int id = clientsPaymentsMethodsResponses.get(pos).getClient_Payments_Methods_id();
                                            //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                                            Intent i=new Intent(PaymentsListActivity.this, MyCustomDialogActivity.class);
                                            i.putExtra("id",id);
                                            i.putExtra("title","Confirm delete");
                                            i.putExtra("msg","Are you sure you want to delete this client payment ?");
                                            i.putExtra("flag","pay");
                                            startActivity(i);
                                        }
                                    }
                            ));

                            /*underlayButtons.add(new SwipeHelper.UnderlayButton(
                                    "Transfer",
                                    0,
                                    Color.parseColor("#FF9502"),
                                    new SwipeHelper.UnderlayButtonClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            // TODO: OnTransfer
                                        }
                                    }
                            ));*/
                            underlayButtons.add(new SwipeHelper.UnderlayButton(
                                    "Edit",
                                    0,
                                    Color.parseColor("#0c66f0"),
                                    new SwipeHelper.UnderlayButtonClickListener() {
                                        @Override
                                        public void onClick(int pos) {
                                            // TODO: OnUnshare

                                            int id = clientsPaymentsMethodsResponses.get(pos).getClient_Payments_Methods_id();
                                            //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                                            Intent i=new Intent(PaymentsListActivity.this, UpdatePaymentMethodActivity.class);
                                            i.putExtra("id",id);
                                            startActivity(i);

                                        }
                                    }
                            ));
                        }
                    };
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.imgadd){
            addPayments();
        }else if(v.getId() == R.id.add){
            addPayments();
        }
    }

    private void addPayments() {
        Intent i=new Intent(PaymentsListActivity.this,AddPaymentsMethodActivity.class);
        startActivity(i);
    }
}
