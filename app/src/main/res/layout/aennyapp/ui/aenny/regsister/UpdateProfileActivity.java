package design.swira.aennyapp.ui.aenny.regsister;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import design.swira.aennyapp.R;
import design.swira.aennyapp.data.room.aeeny.DisTypesIds;
import design.swira.aennyapp.databinding.ActivityUpdateProfileBinding;
import design.swira.aennyapp.pojo.aenny.areas.Areas;
import design.swira.aennyapp.pojo.aenny.cities.City;
import design.swira.aennyapp.pojo.aenny.client_disablity.ClientDisability;
import design.swira.aennyapp.pojo.aenny.clients.Client;
import design.swira.aennyapp.pojo.aenny.clients.ClientDisabilitiesItem;
import design.swira.aennyapp.pojo.aenny.clients.UpdateClientResponse;
import design.swira.aennyapp.pojo.aenny.disability_types.DisabilityTypes;
import design.swira.aennyapp.pojo.aenny.genders.Genders;
import design.swira.aennyapp.ui.aenny.AennyMainActivity;
import design.swira.aennyapp.ui.aenny.adapters.AreasAdapter;
import design.swira.aennyapp.ui.aenny.adapters.CitiesAdapter;
import design.swira.aennyapp.ui.aenny.adapters.DisabilityTypesAdapter;
import design.swira.aennyapp.ui.aenny.adapters.GendersAdapter;
import design.swira.aennyapp.ui.aenny.adapters.UpdateDisabilityTypesAdapter;
import design.swira.aennyapp.ui.aenny.changepasswordmobile.ChangeMobileActivity;
import design.swira.aennyapp.ui.aenny.changepasswordmobile.ChangePasswordActivity;
import design.swira.aennyapp.ui.aenny.mainpage.MainClientActivity;
import design.swira.aennyapp.utils.Constants;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener , OnDisabilitesSelected {

    ActivityUpdateProfileBinding binding;
    Calendar myCalendar ;
    boolean calanderDate = false;
    private String currentDateandTime;
    RegsisterViewModel regsisterViewModel;

    CitiesAdapter citiesAdapter;
    AreasAdapter areasAdapter;
    GendersAdapter gendersAdapter;
    UpdateDisabilityTypesAdapter disabilityTypesAdapter;
    AlertDialog.Builder builder;
    int disabled;
    int cityid;
    int areaid;
    int genderid;
    List<DisTypesIds> disTypesIdss=new ArrayList<>();
    List<ClientDisability> clientDisabilityList=new ArrayList<>();
    Client newclient;

    List<ClientDisabilitiesItem> disabilitiesItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_update_profile);

        binding= DataBindingUtil.setContentView(this,R.layout.activity_update_profile);

        regsisterViewModel= ViewModelProviders.of(this).get(RegsisterViewModel.class);



        bindData();

        binding.birthdate.setOnClickListener(this);
        binding.disabilitytypes.setOnClickListener(this);
        binding.disabled.setOnClickListener(this);
        binding.update.setOnClickListener(this);
        binding.changemob.setOnClickListener(this);
        binding.changepass.setOnClickListener(this);
    }

    private void bindData() {



        regsisterViewModel.getCities();
        //regsisterViewModel.getAreas();
        regsisterViewModel.getGenders();
        regsisterViewModel.getDisabilityTypes();

        int clientid=Constants.getClientId(UpdateProfileActivity.this);
        regsisterViewModel.getClientById(clientid);







        regsisterViewModel.citiesMutableLiveData.observe(UpdateProfileActivity.this, new Observer<List<City>>() {
            @Override
            public void onChanged(final List<City> cities) {
                citiesAdapter =new CitiesAdapter(UpdateProfileActivity.this,cities);
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

        regsisterViewModel.areasbycityMutableLiveData.observe(UpdateProfileActivity.this, new Observer<List<Areas>>() {
            @Override
            public void onChanged(final List<Areas> areas) {
                areasAdapter =new AreasAdapter(UpdateProfileActivity.this,areas);
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


        regsisterViewModel.gendersMutableLiveData.observe(UpdateProfileActivity.this, new Observer<List<Genders>>() {
            @Override
            public void onChanged(final List<Genders> genders) {
                gendersAdapter=new GendersAdapter(UpdateProfileActivity.this,genders);
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

        regsisterViewModel.getAlldata().observe(UpdateProfileActivity.this, new Observer<List<DisTypesIds>>() {
            @Override
            public void onChanged(List<DisTypesIds> disTypesIds) {
                for(int i=0;i<disTypesIds.size();i++){
                    disTypesIdss.add(new DisTypesIds(disTypesIds.get(i).getDistypeid()));
                }
            }
        });

        regsisterViewModel.getDisabilityTypesByClientId(Constants.getClientId(UpdateProfileActivity.this));

        regsisterViewModel.disbyclientidMutableLiveData.observe(UpdateProfileActivity.this, new Observer<List<ClientDisability>>() {
            @Override
            public void onChanged(List<ClientDisability> clientDisabilities) {
                if(clientDisabilities != null) {
                    for (int i = 0; i < clientDisabilities.size(); i++) {
                        clientDisabilityList.add(new ClientDisability(clientDisabilities.get(i).getDisabilityTypeId(), Constants.getClientId(UpdateProfileActivity.this)));
                    }
                }

            }
        });

        regsisterViewModel.disabilitytypesMutableLiveData.observe(UpdateProfileActivity.this, new Observer<List<DisabilityTypes>>() {
            @Override
            public void onChanged(final List<DisabilityTypes> disabilityTypes) {


                int cid=Constants.getClientId(UpdateProfileActivity.this);

                disabilityTypesAdapter=new UpdateDisabilityTypesAdapter(disabilityTypes,UpdateProfileActivity.this,regsisterViewModel,disTypesIdss,cid,clientDisabilityList);
                //disabilityTypesAdapter.setList(disabilityTypes);
                binding.disabilityrecycler.setAdapter(disabilityTypesAdapter);
                binding.disabilityrecycler.setLayoutManager(new GridLayoutManager(UpdateProfileActivity.this,3));



            }
        });


        regsisterViewModel.clientbyidMutableLiveData.observe(UpdateProfileActivity.this, new Observer<Client>() {
            @Override
            public void onChanged(Client client) {

                binding.mobile.setText(client.getClientMobile());
                binding.password.setText(client.getClientPassword());
                binding.passwordagain.setText(client.getClientPassword());
                binding.name.setText(client.getClientName());
                binding.email.setText(client.getClientEmail());
                binding.emergencymobile.setText(client.getClientRelativeMobile());

                String date=client.getClientBirthDate();



                SimpleDateFormat dateFormat = new SimpleDateFormat(
                        "yyyy-MM-dd");




                try {
                    Date d=dateFormat.parse(date);
                    String myFormat = "yyyy-MM-dd";
                    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                    String myday=sdf.format(d);
                    binding.birthdate.setText(myday);

                } catch (ParseException e) {
                    e.printStackTrace();
                }

                int d=client.getUseAppDisabled();
                if(d  == 0){
                    binding.disabled.setText("I am not the disabled");
                }else{
                    binding.disabled.setText("I am the disabled");
                }

                int c=client.getClientCity();
                c=c-1;
                binding.livingcity.setSelection(c);


                int a=client.getClientArea();
                a=a-1;
                binding.livingarea.setSelection(a);

                int g=client.getClientGender();
                g=g-1;
                binding.gender.setSelection(g);


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
                String dis=binding.disabled.getText().toString();
                if(dis.equals("I am the disabled")){
                    binding.disabled.setText("I am not the disabled");
                    disabled=0;
                }else{
                    binding.disabled.setText("I am the disabled");
                    disabled=1;
                }
                break;
            case R.id.update:
                clientUpdate();
                break;
            case R.id.changemob:
                Intent i=new Intent(UpdateProfileActivity.this, ChangeMobileActivity.class);
                startActivity(i);
                break;
            case R.id.changepass:
                Intent ii=new Intent(UpdateProfileActivity.this, ChangePasswordActivity.class);
                startActivity(ii);
                break;
        }
    }

    private void clientUpdate() {
        String name = binding.name.getText().toString();
        String mob = binding.mobile.getText().toString();
        String password = binding.password.getText().toString();
        String newpass = binding.passwordagain.getText().toString();
        String email=binding.email.getText().toString();
        String birthday=binding.birthdate.getText().toString();
        String emergencymobile=binding.emergencymobile.getText().toString();

        if (name.isEmpty()) {
            Toast.makeText(UpdateProfileActivity.this, "Must enter your name", Toast.LENGTH_SHORT).show();
            binding.name.setError("Must enter your name");
        } else if (mob.isEmpty()) {
            Toast.makeText(UpdateProfileActivity.this, "Must enter your mobile", Toast.LENGTH_SHORT).show();
            binding.mobile.setError("Must enter your mobile");
        }/* else if (password.isEmpty()) {
            Toast.makeText(UpdateProfileActivity.this, "Must enter your password", Toast.LENGTH_SHORT).show();
            binding.password.setError("Must enter your password");
        }else if (newpass.isEmpty()) {
            Toast.makeText(UpdateProfileActivity.this, "Must enter your password again", Toast.LENGTH_SHORT).show();
            binding.passwordagain.setError("Must enter your password again");
        } else if (!password.equals(newpass)) {
            Toast.makeText(UpdateProfileActivity.this, "Your password not match", Toast.LENGTH_SHORT).show();
            binding.passwordagain.setError("Your password not match");
        } */else {

            int clientid=Constants.getClientId(UpdateProfileActivity.this);
            /*Client client=new Client(clientid,email, name, mob, cityid, password, birthday, emergencymobile, areaid, genderid, disabled);
            doUpdateClientProfile(client);*/
            //Toast.makeText(ClientRegsisterActivity.this, "Your Secssfully Regsistered", Toast.LENGTH_SHORT).show();


            List<ClientDisabilitiesItem> disabilitiesItems=new ArrayList<>();
            disabilitiesItems.add(new ClientDisabilitiesItem(1));

            //UpdateClientResponse updateClientResponse=new UpdateClientResponse(name,mob,cityid,areaid,birthday,clientid,genderid,emergencymobile,disabilitiesItemList,disabled);
            UpdateClientResponse updateClientResponse=new UpdateClientResponse(name,mob,cityid,areaid,birthday,clientid,genderid,emergencymobile,disabilitiesItems,disabled);
            updateClientFullData(updateClientResponse);

        }
    }

    private void updateClientFullData(UpdateClientResponse updateClientResponse) {
        int clientid=Constants.getClientId(UpdateProfileActivity.this);
        regsisterViewModel.UpdateClientData(clientid,updateClientResponse);
        regsisterViewModel.updateClientResponseMutableLiveData.observe(UpdateProfileActivity.this, new Observer<UpdateClientResponse>() {
            @Override
            public void onChanged(UpdateClientResponse updateClientResponse) {
                if(updateClientResponse != null){
                    Toast.makeText(UpdateProfileActivity.this, "You secssfully updated your profile", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(UpdateProfileActivity.this, MainClientActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(UpdateProfileActivity.this, "Update Profile failed!,try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void doUpdateClientProfile(Client client) {
        int clientid=Constants.getClientId(UpdateProfileActivity.this);
        /*regsisterViewModel.deleteProfileClient(clientid);
        regsisterViewModel.fullRegsisterClientOnline(client);
        regsisterViewModel.fullladdmsgMutableLiveData.observe(UpdateProfileActivity.this, new Observer<Client>() {
            @Override
            public void onChanged(Client clientdata) {
                if(clientdata != null) {
                    Constants.saveClientData(clientdata.getClientId(), clientdata.getClientName(), clientdata.getClientPassword(), clientdata.getClientMobile(), UpdateProfileActivity.this);


                    Toast.makeText(UpdateProfileActivity.this, clientdata.getClientName() + " secessfully Updated Profile", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(UpdateProfileActivity.this, AennyMainActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(UpdateProfileActivity.this, "Update failed!,try again", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        regsisterViewModel.updateClientProfile(clientid,client);
        regsisterViewModel.updatemsgMutableLiveData.observe(UpdateProfileActivity.this, new Observer<Client>() {
            @Override
            public void onChanged(final Client clientdata) {
                if(clientdata != null) {
                    Constants.saveClientData(clientdata.getClientId(), clientdata.getClientName(), clientdata.getClientPassword(), clientdata.getClientMobile() , clientdata.getClientEmail(), UpdateProfileActivity.this);

                    /*regsisterViewModel.deleteDisabilityTypesByClientId(clientdata.getClientId());


                    regsisterViewModel.getAlldata().observe(UpdateProfileActivity.this, new Observer<List<DisTypesIds>>() {
                        @Override
                        public void onChanged(List<DisTypesIds> disTypesIds) {
                            for(int i=0;i<disTypesIds.size();i++){
                                int distyid= disTypesIds.get(i).getDistypeid();

                                int clientid = clientdata.getClientId();
                                ClientDisability clientDisability = new ClientDisability(distyid, clientid);
                                regsisterViewModel.addDisabilityToClient(clientDisability);
                            }
                        }
                    });*/

                    Toast.makeText(UpdateProfileActivity.this, "You secssfully updated your profile", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(LoginActivity.this, client.toString(), Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(UpdateProfileActivity.this, MainClientActivity.class);
                    startActivity(i);
                }else{
                    Toast.makeText(UpdateProfileActivity.this, "Update Profile failed!,try again", Toast.LENGTH_SHORT).show();
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

        new DatePickerDialog(UpdateProfileActivity.this, date, myCalendar
                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }


    @Override
    public void OnDisSelected(List<ClientDisabilitiesItem> disabilitiesItems) {
        disabilitiesItemList=new ArrayList<>();
        for(int i=0;i<disabilitiesItems.size();i++){
            ClientDisabilitiesItem item = disabilitiesItems.get(i);
            disabilitiesItemList.add(new ClientDisabilitiesItem(item.getId()));
        }
    }





}