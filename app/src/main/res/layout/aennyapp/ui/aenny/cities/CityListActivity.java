package design.swira.aennyapp.ui.aenny.cities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityCityListBinding;
import design.swira.aennyapp.pojo.aenny.cities.City;

public class CityListActivity extends AppCompatActivity {

    CitiesViewModel citiesViewModel;
    ActivityCityListBinding binding;
    CitiesListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_city_list);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_city_list);

        citiesViewModel= ViewModelProviders.of(this).get(CitiesViewModel.class);

        citiesViewModel.getAllCitiesOnline();

        citiesViewModel.citylistMutableLiveData.observe(this, new Observer<List<City>>() {
            @Override
            public void onChanged(List<City> cities) {
                
                adapter=new CitiesListAdapter();
                adapter.setList(cities);
                binding.recycler.setAdapter(adapter);
                binding.recycler.setLayoutManager(new LinearLayoutManager(CityListActivity.this));

                //Log.i("city",cities.toString());
            }
        });
    }
}
