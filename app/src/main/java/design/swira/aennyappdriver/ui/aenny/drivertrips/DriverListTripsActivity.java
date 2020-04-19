package design.swira.aennyappdriver.ui.aenny.drivertrips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import design.swira.aennyappdriver.R;
import design.swira.aennyappdriver.ui.aenny.adapters.TripsFragementAdapter;

public class DriverListTripsActivity extends AppCompatActivity {

    private ViewPager vpager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_list_trips);

        tabs=findViewById(R.id.tabs);
        vpager=findViewById(R.id.vpager);







        FragmentManager fm = getSupportFragmentManager();

        TripsFragementAdapter adp=new TripsFragementAdapter(fm);

        vpager.setAdapter(adp);

        tabs.setupWithViewPager(vpager);
        //tabs.getTabAt(0).setIcon(R.drawable.list);
        //tabs.getTabAt(1).setIcon(R.drawable.grid);
    }
}
