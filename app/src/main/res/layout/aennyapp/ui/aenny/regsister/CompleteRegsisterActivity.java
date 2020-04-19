package design.swira.aennyapp.ui.aenny.regsister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import design.swira.aennyapp.R;
import design.swira.aennyapp.data.room.aeeny.DisTypesIds;
import design.swira.aennyapp.databinding.ActivityCompleteRegsisterBinding;
import design.swira.aennyapp.pojo.aenny.DisabilitiesTypesidData;
import design.swira.aennyapp.pojo.aenny.areas.Areas;
import design.swira.aennyapp.pojo.aenny.cities.City;
import design.swira.aennyapp.pojo.aenny.client_disablity.ClientDisability;
import design.swira.aennyapp.pojo.aenny.clients.Client;
import design.swira.aennyapp.pojo.aenny.disability_types.DisabilityTypes;
import design.swira.aennyapp.pojo.aenny.genders.Genders;
import design.swira.aennyapp.ui.aenny.adapters.AreasAdapter;
import design.swira.aennyapp.ui.aenny.adapters.CitiesAdapter;
import design.swira.aennyapp.ui.aenny.adapters.DisabilityTypesAdapter;
import design.swira.aennyapp.ui.aenny.adapters.GendersAdapter;
import design.swira.aennyapp.ui.aenny.cities.CitiesListAdapter;
import design.swira.aennyapp.ui.aenny.signin.LoginActivity;
import design.swira.aennyapp.ui.aenny.terms.TermssActivity;
import design.swira.aennyapp.utils.Constants;
import design.swira.aennyapp.utils.Network;

public class CompleteRegsisterActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityCompleteRegsisterBinding binding;
     Calendar myCalendar ;
    boolean calanderDate = false;
    private String currentDateandTime;
    RegsisterViewModel regsisterViewModel;

    CitiesAdapter citiesAdapter;
    AreasAdapter areasAdapter;
    GendersAdapter gendersAdapter;
    DisabilityTypesAdapter disabilityTypesAdapter;
    AlertDialog.Builder builder;
    int disabled;
    int cityid;
    int areaid;
    int genderid;
    List<DisabilitiesTypesidData> distypesid=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_complete_regsister);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_complete_regsister);

        regsisterViewModel= ViewModelProviders.of(this).get(RegsisterViewModel.class);



        bindData();

        binding.birthdate.setOnClickListener(this);
        binding.disabilitytypes.setOnClickListener(this);
        //binding.disabled.setOnClickListener(this);
        binding.regsister.setOnClickListener(this);
        binding.seenimg.setOnClickListener(this);
        binding.seenimgagain.setOnClickListener(this);

        binding.disimg.setOnClickListener(this);
        binding.heaimg.setOnClickListener(this);

        binding.distxt.setOnClickListener(this);
        binding.heatxt.setOnClickListener(this);

        binding.terms.setOnClickListener(this);
        binding.signin.setOnClickListener(this);





    }

    private void bindData() {

        if(getIntent().hasExtra("name")) {

            String name = getIntent().getStringExtra("name");
            String mob = getIntent().getStringExtra("mob");
            String password = getIntent().getStringExtra("password");

            if (!name.isEmpty()) {
                binding.name.setText(name);
            }

            if (!mob.isEmpty()) {
                binding.mobile.setText(mob);
            }

            if (!password.isEmpty()) {
                binding.password.setText(password);
                binding.passwordagain.setText(password);
            }

        }

        regsisterViewModel.getCities();
        //regsisterViewModel.getAreas();
        regsisterViewModel.getGenders();
        regsisterViewModel.getDisabilityTypes();

        regsisterViewModel.citiesMutableLiveData.observe(CompleteRegsisterActivity.this, new Observer<List<City>>() {
            @Override
            public void onChanged(final List<City> cities) {
                citiesAdapter =new CitiesAdapter(CompleteRegsisterActivity.this,cities);
                binding.livingcity.setAdapter(citiesAdapter);

                binding.livingcity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        cityid=cities.get(position).getCityId();
                        regsisterViewModel.getAreasByCityid(cityid);
                        Log.i("city",cityid + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        regsisterViewModel.areasbycityMutableLiveData.observe(CompleteRegsisterActivity.this, new Observer<List<Areas>>() {
            @Override
            public void onChanged(final List<Areas> areas) {
                areasAdapter =new AreasAdapter(CompleteRegsisterActivity.this,areas);
                binding.livingarea.setAdapter(areasAdapter);

                binding.livingarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        areaid=areas.get(position).getAreaId();
                        Log.i("area",areaid + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        /*regsisterViewModel.areasMutableLiveData.observe(CompleteRegsisterActivity.this, new Observer<List<Areas>>() {
            @Override
            public void onChanged(final List<Areas> areas) {
                areasAdapter =new AreasAdapter(CompleteRegsisterActivity.this,areas);
                binding.livingarea.setAdapter(areasAdapter);

                binding.livingarea.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        areaid=areas.get(position).getAreaId();
                        Log.i("area",areaid + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });*/

        regsisterViewModel.gendersMutableLiveData.observe(CompleteRegsisterActivity.this, new Observer<List<Genders>>() {
            @Override
            public void onChanged(final List<Genders> genders) {
                gendersAdapter=new GendersAdapter(CompleteRegsisterActivity.this,genders);
                binding.gender.setAdapter(gendersAdapter);

                binding.gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        genderid=genders.get(position).getGenderId();
                        Log.i("gender",genderid + "");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });

        regsisterViewModel.disabilitytypesMutableLiveData.observe(CompleteRegsisterActivity.this, new Observer<List<DisabilityTypes>>() {
            @Override
            public void onChanged(final List<DisabilityTypes> disabilityTypes) {
                /*final String [] names;
                List<String> nameslist = new ArrayList<>();

                for(int i=0;i<disabilityTypes.size();i++){
                    DisabilityTypes types = disabilityTypes.get(i);
                    nameslist.add(types.getDisabilityTypeName());
                }
                names= (String[]) nameslist.toArray();

                //final String[] items = {"Español", "Inglés", "Francés"};

                builder =
                        new AlertDialog.Builder(CompleteRegsisterActivity.this);
                builder.setTitle("Disability types")
                        .setMultiChoiceItems(names, null,
                                new DialogInterface.OnMultiChoiceClickListener() {
                                    public void onClick(DialogInterface dialog, int item, boolean isChecked) {
                                        Log.i("Dialogos", "Opción elegida: " + names[item]);
                                    }
                                });*/

                disabilityTypesAdapter=new DisabilityTypesAdapter(disabilityTypes,CompleteRegsisterActivity.this,regsisterViewModel);
                //disabilityTypesAdapter.setList(disabilityTypes);
                binding.disabilityrecycler.setAdapter(disabilityTypesAdapter);
                binding.disabilityrecycler.setLayoutManager(new GridLayoutManager(CompleteRegsisterActivity.this,3));

                //LocalBroadcastManager.getInstance(CompleteRegsisterActivity.this).registerReceiver(mMessageReceiver, new IntentFilter("message_subject_intent"));



            }
        });




    }


    private void updatedate() {

        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        /*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm aa");
        currentDateandTime = sdf.format(new Date());*/

        binding.birthdate.setText(sdf.format(myCalendar.getTime()));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId() ){
            case R.id.birthdate:
                getbirthdate();
                break;
            case R.id.disabilitytypes:
                //builder.create().show();
                if(binding.disabilityrecycler.getVisibility() == View.GONE){
                    binding.disabilityrecycler.setVisibility(View.VISIBLE);
                }else{
                    binding.disabilityrecycler.setVisibility(View.GONE);
                }
                break;
            case R.id.disabled:
                /*String dis=binding.disabled.getText().toString();
                if(dis.equals("I am the disabled")){
                    binding.disabled.setText("I am not the disabled");
                    disabled=0;
                }else{
                    binding.disabled.setText("I am the disabled");
                    disabled=1;
                }
                break;*/
            case R.id.regsister:
                clientRegsister();
                break;
            case R.id.seenimg:
                if(binding.password.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                    binding.seenimg.setImageResource(R.drawable.ic_unseen);
                    binding.password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    binding.seenimg.setImageResource(R.drawable.ic_seen);
                    binding.password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);}
                break;
            case R.id.seenimgagain:
                if(binding.passwordagain.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD){
                    binding.seenimgagain.setImageResource(R.drawable.ic_unseen);
                    binding.passwordagain.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }else {
                    binding.seenimgagain.setImageResource(R.drawable.ic_seen);
                    binding.passwordagain.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);}
                break;
            case R.id.disimg:
                disabled=1;
                binding.disimg.setImageResource(R.drawable.ic_iam_dis);
                binding.heaimg.setImageResource(R.drawable.ic_notdis_off);
                break;
            case R.id.heaimg:
                disabled=0;
                binding.disimg.setImageResource(R.drawable.ic_iam_not_dis);
                binding.heaimg.setImageResource(R.drawable.ic_notdis_on);
                break;

            case R.id.distxt:
                disabled=1;
                binding.disimg.setImageResource(R.drawable.ic_iam_dis);
                binding.heaimg.setImageResource(R.drawable.ic_notdis_off);
                break;
            case R.id.heatxt:
                disabled=0;
                binding.disimg.setImageResource(R.drawable.ic_iam_not_dis);
                binding.heaimg.setImageResource(R.drawable.ic_notdis_on);
                break;
            case R.id.terms:
                Intent i8=new Intent(CompleteRegsisterActivity.this, TermssActivity.class);
                startActivity(i8);
                break;
            case R.id.signin:
                Intent i9=new Intent(CompleteRegsisterActivity.this, LoginActivity.class);
                startActivity(i9);
                break;
        }
    }

    private void clientRegsister() {
        if(!Network.isNetworkAvailable(CompleteRegsisterActivity.this)){
            androidx.appcompat.app.AlertDialog.Builder builder=new androidx.appcompat.app.AlertDialog.Builder(CompleteRegsisterActivity.this);
            builder.setIcon(R.drawable.warning);
            builder.setTitle("Warnning!");
            builder.setMessage("Network not exists,pleae connect to internet");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    /*Intent i=new Intent(ClientRegsisterActivity.this, IntroOneActivity.class);
                    startActivity(i);*/
                    dialog.dismiss();
                }
            });
            builder.create().show();
        }else {
            String name = binding.name.getText().toString();
            String mob = binding.mobile.getText().toString();
            String password = binding.password.getText().toString();
            String newpass = binding.passwordagain.getText().toString();
            String email = binding.email.getText().toString();
            String birthday = binding.birthdate.getText().toString();
            String emergencymobile = binding.emergencymobile.getText().toString();

            if (name.isEmpty()) {
                Toast.makeText(CompleteRegsisterActivity.this, "Must enter your name", Toast.LENGTH_SHORT).show();
                binding.name.setError("Must enter your name");
            } else if (mob.isEmpty()) {
                Toast.makeText(CompleteRegsisterActivity.this, "Must enter your mobile", Toast.LENGTH_SHORT).show();
                binding.mobile.setError("Must enter your mobile");
            } else if (password.isEmpty()) {
                Toast.makeText(CompleteRegsisterActivity.this, "Must enter your password", Toast.LENGTH_SHORT).show();
                binding.password.setError("Must enter your password");
            } else if (newpass.isEmpty()) {
                Toast.makeText(CompleteRegsisterActivity.this, "Must enter your password again", Toast.LENGTH_SHORT).show();
                binding.passwordagain.setError("Must enter your password again");
            } else if (!password.equals(newpass)) {
                Toast.makeText(CompleteRegsisterActivity.this, "Your password not match", Toast.LENGTH_SHORT).show();
                binding.passwordagain.setError("Your password not match");
            } else if (birthday.isEmpty()) {
                Toast.makeText(CompleteRegsisterActivity.this, "Must enter your birth day", Toast.LENGTH_SHORT).show();
                binding.birthdate.setError("Must enter your birth day");
            } else if (email.isEmpty()) {
                Toast.makeText(CompleteRegsisterActivity.this, "Must enter your email", Toast.LENGTH_SHORT).show();
                binding.email.setError("Must enter your email");
            } else if (emergencymobile.isEmpty()) {
                Toast.makeText(CompleteRegsisterActivity.this, "Must enter your emergency mobile", Toast.LENGTH_SHORT).show();
                binding.emergencymobile.setError("Must enter your emergency mobile");
            } else {

                Client client = new Client(email, name, mob, cityid, password, birthday, emergencymobile, areaid, genderid, disabled);
                doFullClientregsister(client);
                //Toast.makeText(ClientRegsisterActivity.this, "Your Secssfully Regsistered", Toast.LENGTH_SHORT).show();

            }
        }
    }

    private void doFullClientregsister(Client client) {
        regsisterViewModel.fullRegsisterClientOnline(client);
        regsisterViewModel.fullladdmsgMutableLiveData.observe(CompleteRegsisterActivity.this, new Observer<Client>() {
            @Override
            public void onChanged(final Client clientdata) {
                if(clientdata != null) {
                    Constants.saveClientData(clientdata.getClientId(), clientdata.getClientName(), clientdata.getClientPassword(), clientdata.getClientMobile() ,clientdata.getClientEmail() , CompleteRegsisterActivity.this);


                    LocalBroadcastManager.getInstance(CompleteRegsisterActivity.this).registerReceiver(mMessageReceiver, new IntentFilter("message_subject_intent"));

                    //List<DisabilitiesTypesidData> distypesidd= (List<DisabilitiesTypesidData>)getIntent().getSerializableExtra("distypesid");

                    /*for(int i = 0; i< distypesid.size(); i++) {

                        int distyid= distypesid.get(i).getDisabilitytypeid();

                        int clientid = clientdata.getClientId();
                        ClientDisability clientDisability = new ClientDisability(distyid, clientid);
                        regsisterViewModel.addDisabilityToClient(clientDisability);

                    }*/

                    regsisterViewModel.getAlldata().observe(CompleteRegsisterActivity.this, new Observer<List<DisTypesIds>>() {
                        @Override
                        public void onChanged(List<DisTypesIds> disTypesIds) {
                            for(int i=0;i<disTypesIds.size();i++){
                                int distyid= disTypesIds.get(i).getDistypeid();

                                int clientid = clientdata.getClientId();
                                ClientDisability clientDisability = new ClientDisability(distyid, clientid);
                                regsisterViewModel.addDisabilityToClient(clientDisability);
                            }
                        }
                    });



                    Toast.makeText(CompleteRegsisterActivity.this, clientdata.getClientName() + " secessfully Regsister", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(CompleteRegsisterActivity.this,LoginActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(CompleteRegsisterActivity.this, "Regsister failed!,try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void getbirthdate() {

        myCalendar = Calendar.getInstance();

        //EditText edittext= (EditText) findViewById(R.id.Birthday);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updatedate();
            }

        };

        new DatePickerDialog(CompleteRegsisterActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            //distypesid = (List<DisabilitiesTypesidData>) bundle.getSerializable("distypesid");
            distypesid = (List<DisabilitiesTypesidData>) intent.getSerializableExtra("distypesid");
        }
    };


}
