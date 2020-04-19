package design.swira.aennyapp.ui.aenny.adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import design.swira.aennyapp.ui.aenny.intro.Fragment1;
import design.swira.aennyapp.ui.aenny.intro.Fragment2;
import design.swira.aennyapp.ui.aenny.intro.Fragment3;
import design.swira.aennyapp.ui.aenny.trips.CompletedTripsFragment;
import design.swira.aennyapp.ui.aenny.trips.ScduleTripsFragment;

public class MyPagerAdapter2 extends FragmentPagerAdapter {

    Fragment[] frags={new Fragment1(),new Fragment2(),new Fragment3()};

    String[] titles={"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus.\",\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus.\",\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus.","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus.\",\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus.\",\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus.","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus.\",\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus.\",\"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus."};

    public MyPagerAdapter2(FragmentManager fm) {
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
