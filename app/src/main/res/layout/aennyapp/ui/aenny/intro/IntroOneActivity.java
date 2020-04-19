package design.swira.aennyapp.ui.aenny.intro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import design.swira.aennyapp.R;
import design.swira.aennyapp.databinding.ActivityIntroOneBinding;
import design.swira.aennyapp.pojo.aenny.IntroData;
import design.swira.aennyapp.ui.aenny.adapters.MyPagerAdapter2;
import design.swira.aennyapp.ui.aenny.signin.LoginActivity;

public class IntroOneActivity extends AppCompatActivity implements View.OnClickListener, OnIntroClick {

    ActivityIntroOneBinding binding;
    IntroViewModel introViewModel;
    IntroAdapter introAdapter;
    @BindView(R.id.img1)
    ImageView img1;
    @BindView(R.id.img2)
    ImageView img2;
    @BindView(R.id.img3)
    ImageView img3;

    private ViewPager vpager;
    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_intro_one);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_intro_one);

        introViewModel = ViewModelProviders.of(this).get(IntroViewModel.class);

        introAdapter = new IntroAdapter(this);

        bindData();

        binding.getstart.setOnClickListener(this);

        tabs = findViewById(R.id.tabs);
        vpager = findViewById(R.id.vpager);

        FragmentManager fm = getSupportFragmentManager();

        MyPagerAdapter2 adp = new MyPagerAdapter2(fm);

        vpager.setAdapter(adp);

        tabs.setupWithViewPager(vpager);

    }

    public void bindData() {
        introViewModel.getIntroData();
        introViewModel.intromutableLiveData.observe(IntroOneActivity.this, new Observer<List<IntroData>>() {
            @Override
            public void onChanged(List<IntroData> introData) {
                introAdapter.setList(introData);
                binding.introrecycler.setAdapter(introAdapter);
                SnapHelper snapHelper = new PagerSnapHelper();
                binding.introrecycler.setLayoutManager(new LinearLayoutManager(IntroOneActivity.this, LinearLayoutManager.HORIZONTAL, false));
                snapHelper.attachToRecyclerView(binding.introrecycler);

                /*binding.introrecycler.setOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);

                        if (img1.getVisibility() == 0) {
                            img1.setVisibility(View.VISIBLE);
                            img2.setVisibility(View.INVISIBLE);
                            img3.setVisibility(View.INVISIBLE);
                        }else if(newState == 1){
                            img1.setVisibility(View.INVISIBLE);
                            img2.setVisibility(View.VISIBLE);
                            img3.setVisibility(View.INVISIBLE);
                        }else if(newState == 2){
                            img1.setVisibility(View.INVISIBLE);
                            img2.setVisibility(View.INVISIBLE);
                            img3.setVisibility(View.VISIBLE);
                        }

                    }
                });*/

            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.getstart) {
            Intent i = new Intent(IntroOneActivity.this, LoginActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onListClick(int index) {
        if (index == 0) {
            img1.setVisibility(View.VISIBLE);
            img2.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.INVISIBLE);
        }else if(index == 1){
            img1.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.VISIBLE);
            img3.setVisibility(View.INVISIBLE);
        }else if(index == 2){
            img1.setVisibility(View.INVISIBLE);
            img2.setVisibility(View.INVISIBLE);
            img3.setVisibility(View.VISIBLE);
        }
    }
}
