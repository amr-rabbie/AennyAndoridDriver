package design.swira.aennyapp.ui.aenny.intro;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import design.swira.aennyapp.pojo.aenny.IntroData;

public class IntroViewModel extends AndroidViewModel {

    MutableLiveData<List<IntroData>> intromutableLiveData=new MutableLiveData<>();
    String [] titles={"Ongoing trips","Scheduled trips","Healthy and handcappy"};
    String [] bodies={"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus.","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus.","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus erat turpis, cursus non consectetur sit amet, consectetur sit amet lectus."};

    public IntroViewModel(@NonNull Application application) {
        super(application);
    }

    public void getIntroData(){
        List<IntroData> introDataList=new ArrayList<>();
        for(int i=0;i<titles.length;i++){
            introDataList.add(new IntroData(titles[i],bodies[i]));
        }
        intromutableLiveData.setValue(introDataList);
    }


}
