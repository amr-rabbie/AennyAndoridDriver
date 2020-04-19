package design.swira.aennyapp.ui.test3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityMdataBinding;
import design.swira.aennyapp.pojo.test3.Data;
import design.swira.aennyapp.pojo.test3.MResponse;
import design.swira.aennyapp.pojo.test3.ResultsItem;
import design.swira.aennyapp.ui.test3.adapters.MDataAdapter;

public class MDataActivity extends AppCompatActivity {

    ActivityMdataBinding binding;
    MDataViewModel mDataViewModel;
    MDataAdapter mDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mdata);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_mdata);

        mDataViewModel= ViewModelProviders.of(this).get(MDataViewModel.class);


        binding.pbar.setVisibility(View.VISIBLE);
        binding.recycler.setVisibility(View.GONE);
        fillMData();

    }

    private void fillMData() {

        String ts="1";
        String apikey="ff99b2451d5cd421f8e1d63b593e50d6";
        String hash="47b2c7d86a52d3663db5c0e996d4a887";

        mDataViewModel.getMAllDataOnline(ts,apikey,hash);

        mDataViewModel.mdataMutableLiveData.observe(MDataActivity.this, new Observer<MResponse>() {
            @Override
            public void onChanged(MResponse mResponse) {
                Data data = mResponse.getData();
                List<ResultsItem> results = data.getResults();
                viewMData(results);
            }
        });
    }

    private void viewMData(List<ResultsItem> results) {
        mDataAdapter=new MDataAdapter(results);
        //mDataAdapter.setList(results);
        binding.recycler.setAdapter(mDataAdapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(MDataActivity.this));
        binding.pbar.setVisibility(View.GONE);
        binding.recycler.setVisibility(View.VISIBLE);


    }
}
