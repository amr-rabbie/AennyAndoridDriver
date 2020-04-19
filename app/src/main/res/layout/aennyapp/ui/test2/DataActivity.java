package design.swira.aennyapp.ui.test2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityData2Binding;
import design.swira.aennyapp.pojo.test2.WResponse;
import design.swira.aennyapp.utils.Network;

public class DataActivity extends AppCompatActivity {

    ActivityData2Binding binding;
    WDataViewModel wDataViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_data2);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_data2);

        wDataViewModel= ViewModelProviders.of(this).get(WDataViewModel.class);

        binding.setViewmodel(wDataViewModel);
        binding.setLifecycleOwner(this);

        if(Network.isNetworkAvailable(this)) {
            fillWData();
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setIcon(R.drawable.attention);
            builder.setTitle("Attention!");
            builder.setMessage("Network Not Exists!");


            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //do nothing
                }
            });

            builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // do nothing
                }
            });


            builder.create().show();
        }
    }

    private void fillWData() {
        String q="London,uk";
        String appid="b6907d289e10d714a6e88b30761fae22";

        wDataViewModel.getWAllDataOnline(q,appid);

        wDataViewModel.wdataMutableLiveData.observe(DataActivity.this, new Observer<WResponse>() {
            @Override
            public void onChanged(WResponse wResponse) {
                viewWData(wResponse);
            }
        });


    }

    private void viewWData(WResponse wResponse) {
        binding.city.setText(wResponse.getName());
        binding.lattidue.setText(wResponse.getCoord().getLat()+"");
        binding.longttidue.setText(wResponse.getCoord().getLon()+"");
        binding.desc.setText(wResponse.getWeather().get(0).getDescription());

        String iconurl="http://openweathermap.org/img/w/" + wResponse.getWeather().get(0).getIcon() + ".png";

        Glide
                .with(DataActivity.this)
                .load(iconurl)
                .centerCrop()
                //.placeholder(R.drawable.load)
                .into(binding.img);

        binding.temp.setText(wResponse.getMain().getTemp()+"");
        binding.humdity.setText(wResponse.getMain().getHumidity()+"");
        binding.pressure.setText(wResponse.getMain().getPressure()+"");

        binding.wind.setText(wResponse.getWind().getSpeed()+"");
        binding.clouds.setText(wResponse.getClouds().getAll()+"");

        binding.mintemp.setText(wResponse.getMain().getTempMin()+"");
        binding.maxtemp.setText(wResponse.getMain().getTempMax()+"");

        Toast.makeText(DataActivity.this, wResponse.getSys().getSunrise() + wResponse.getSys().getSunset() + wResponse.getWeather().get(0).getMain(), Toast.LENGTH_SHORT).show();
    }
}
