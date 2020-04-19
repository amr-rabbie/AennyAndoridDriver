package design.swira.aennyapp.ui.aenny.adapters;


/*import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;*/

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import design.swira.aennyapp.ui.aenny.trips.CompletedTripsFragment;
import design.swira.aennyapp.ui.aenny.trips.ScduleTripsFragment;


public class MyPagerAdapter extends FragmentPagerAdapter {

    Fragment[] frags={new ScduleTripsFragment(),new CompletedTripsFragment()};

    String[] titles={"Scheduled","Completed"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return frags[i];
    }

    @Override
    public int getCount() {
        return frags.length;
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
