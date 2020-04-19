package design.swira.aennyappdriver.ui.aenny.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import design.swira.aennyappdriver.ui.aenny.drivertrips.CompletedTripsFragment;
import design.swira.aennyappdriver.ui.aenny.drivertrips.ScduleTripsFragment;

public class TripsFragementAdapter  extends FragmentPagerAdapter {

    Fragment[] frags={new ScduleTripsFragment(),new CompletedTripsFragment()};

    String[] titles={"Scheduled","Completed"};

    public TripsFragementAdapter(FragmentManager fm) {
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
