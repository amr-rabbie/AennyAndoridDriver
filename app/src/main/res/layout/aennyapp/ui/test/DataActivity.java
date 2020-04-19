package design.swira.aennyapp.ui.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.data.room.Data;
import design.swira.aennyapp.databinding.ActivityDataBinding;
import design.swira.aennyapp.pojo.test.Country;
import design.swira.aennyapp.pojo.test.RowsItem;
import design.swira.aennyapp.ui.test.adapters.DataAdapter;
import design.swira.aennyapp.ui.test.adapters.DataAdapterOffline;
import design.swira.aennyapp.ui.test.adapters.StocksSpinnerAdapter;
import design.swira.aennyapp.utils.Network;

public class DataActivity extends AppCompatActivity {

    ActivityDataBinding binding;
    DataViewModel dataViewModel;

    String country="sa";
    String s="sa";
    private String[] countryArray = {"Egypt Stock", "Uae Stock", "Ksa Stock", "Qatar Stock"};
    private int[] flagsArray = {R.drawable.egyptf, R.drawable.uaef, R.drawable.ksa, R.drawable.qatar};

    StocksSpinnerAdapter cadapter;
    DataAdapter adapter;
    DataAdapterOffline offlineadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_data);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_data);

        dataViewModel= ViewModelProviders.of(this).get(DataViewModel.class);

        //binding.setViewmodel(dataViewModel);
        //binding.setLifecycleOwner(this);

        fillcData();
        //fillData(country);


    }

    private void fillcData() {

        dataViewModel.getcAllData();

        dataViewModel.calldataMutableLiveData.observe(this, new Observer<List<Country>>() {
            @Override
            public void onChanged(List<Country> countries) {
                binding.pbar.setVisibility(View.VISIBLE);
                binding.recycler.setVisibility(View.GONE);
                viewcData(countries);
            }
        });

    }

    private void viewcData(List<Country> countries) {
        cadapter=new StocksSpinnerAdapter(DataActivity.this,R.layout.country_spinner_item,countryArray,flagsArray);
        binding.spinner.setAdapter(cadapter);


        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String c=(String)parent.getItemAtPosition(position);
                switch (c){
                    case "Egypt Stock":
                        s = "eg";
                        break;
                    case "Uae Stock":
                        s = "ae";
                        break;
                    case "Ksa Stock":
                        s = "sa";
                        break;
                    case "Qatar Stock":
                        s = "qa";
                        break;
                }

                if(Network.isNetworkAvailable(DataActivity.this)) {
                    fillData(s);
                }else{

                    dataViewModel.getAlldata();

                    dataViewModel.alldata.observe(DataActivity.this, new Observer<List<Data>>() {
                        @Override
                        public void onChanged(List<Data> data) {
                            viewDataOffline(data);
                        }
                    });



                    AlertDialog.Builder builder=new AlertDialog.Builder(parent.getContext());
                    builder.setIcon(R.drawable.attention);
                    builder.setTitle("Attention!");
                    builder.setMessage("\n\n Network Connection Not Exists!" );
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.create().show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void viewDataOffline(List<Data> data) {
        offlineadapter=new DataAdapterOffline(data);
        //adapter.setList(rowsItems);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(DataActivity.this));
        binding.pbar.setVisibility(View.GONE);
        binding.recycler.setVisibility(View.VISIBLE);
    }

    private void fillData(final String country) {

        dataViewModel.getAllDataOnline(country);

        dataViewModel.alldataMutableLiveData.observe(DataActivity.this, new Observer<List<RowsItem>>() {
            @Override
            public void onChanged(List<RowsItem> rowsItems) {

                dataViewModel.deleteAllData();

                for(int i=0;i<rowsItems.size();i++){
                    RowsItem item = rowsItems.get(i);
                    Data data=new Data(item.getSymbol(), item.getName(), item.getPrice()+"", item.getChangePercentage()+"", country);
                    dataViewModel.insertData(data);
                }

                viewData(rowsItems);
            }
        });

    }

    private void viewData(List<RowsItem> rowsItems) {
        adapter=new DataAdapter(rowsItems);
        //adapter.setList(rowsItems);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(DataActivity.this));
        binding.pbar.setVisibility(View.GONE);
        binding.recycler.setVisibility(View.VISIBLE);
    }
}
