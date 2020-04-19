package design.swira.aennyapp.ui.aenny.trips;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import design.swira.aennyapp.R;
import design.swira.aennyapp.ui.aenny.adapters.MyPagerAdapter;

public class TripsListActivity extends AppCompatActivity {

    private ViewPager vpager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trips_list);


        tabs=findViewById(R.id.tabs);
        vpager=findViewById(R.id.vpager);







        FragmentManager fm = getSupportFragmentManager();

        MyPagerAdapter adp=new MyPagerAdapter(fm);

        vpager.setAdapter(adp);

        tabs.setupWithViewPager(vpager);
        //tabs.getTabAt(0).setIcon(R.drawable.list);
        //tabs.getTabAt(1).setIcon(R.drawable.grid);
    }
}
