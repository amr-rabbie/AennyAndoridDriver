package design.swira.aennyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import design.swira.aennyapp.ui.aenny.cities.CityListActivity;
import design.swira.aennyapp.ui.test2.DataActivity;
import design.swira.aennyapp.ui.test3.MDataActivity;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Intent i=new Intent(MainActivity.this, DataActivity.class);
        startActivity(i);*/

        Intent i=new Intent(MainActivity.this, NDrawerActivity.class);
        startActivity(i);


    }

}