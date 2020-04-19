package design.swira.aennyapp.ui.aenny.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.data.room.aeeny.DisTypesIds;
import design.swira.aennyapp.pojo.aenny.DisabilitiesTypesidData;
import design.swira.aennyapp.pojo.aenny.client_disablity.ClientDisability;
import design.swira.aennyapp.pojo.aenny.clients.ClientDisabilitiesItem;
import design.swira.aennyapp.pojo.aenny.disability_types.DisabilityTypes;
import design.swira.aennyapp.ui.aenny.regsister.OnDisabilitesSelected;
import design.swira.aennyapp.ui.aenny.regsister.RegsisterViewModel;

public class UpdateDisabilityTypesAdapter  extends RecyclerView.Adapter<UpdateDisabilityTypesAdapter.DisabilityTypesViewHolder> {

    List<DisabilityTypes> disabilityTypesList;
    List<DisabilitiesTypesidData> distypesid=new ArrayList<>();
    Context context;
    RegsisterViewModel viewModel;
    List<DisTypesIds> disTypesIdss;
    int cid;
    List<ClientDisability> clientDisabilityList;

    List<ClientDisabilitiesItem> disabilitiesItemList;

    OnDisabilitesSelected onDisabilitesSelected;



    public UpdateDisabilityTypesAdapter(List<DisabilityTypes> disabilityTypesList, Context context, RegsisterViewModel viewModel, List<DisTypesIds> disTypesIdss, int cid, List<ClientDisability> clientDisabilityList) {
        this.disabilityTypesList=disabilityTypesList;
        this.context=context;
        this.viewModel=viewModel;
        this.disTypesIdss=disTypesIdss;
        this.cid=cid;
        this.clientDisabilityList=clientDisabilityList;
        disabilitiesItemList=new ArrayList<>();
    }

    public void setList(List<DisabilityTypes> disabilityTypesList){
        this.disabilityTypesList=disabilityTypesList;
    }

    @NonNull
    @Override
    public DisabilityTypesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.disability_types_item,parent,false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int)v.getTag();
                disabilityTypesList.get(pos);
            }
        });

        return new DisabilityTypesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DisabilityTypesViewHolder holder, final int position) {



        holder.name.setText(disabilityTypesList.get(position).getDisabilityTypeName());

       /* Picasso.get()
                .load(disabilityTypesList.get(position).getDisabilityTypeIcon())
                .resize(50, 50)
                .centerCrop()
                .into(holder.img);*/

        holder.cview.setTag(position);

        for(int i=0;i<disTypesIdss.size();i++){
            if(disabilityTypesList.get(position).getDisabilityTypeId() == disTypesIdss.get(i).getDistypeid()){
                holder.name.setChecked(true);
            }
        }



        //viewModel.deleteall();


        //holder.name.setEnabled(false);


        holder.name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                int disid=disabilityTypesList.get(position).getDisabilityTypeId();

                /*if(isChecked == true ){

                    int disid=disabilityTypesList.get(position).getDisabilityTypeId();

                    DisTypesIds disTypesIds=new DisTypesIds(disid);

                    viewModel.insert(disTypesIds);

                    ClientDisability clientDisability = new ClientDisability(disid, cid);
                    viewModel.addDisabilityToClient(clientDisability);


                }else if(isChecked == false){
                    int disid=disabilityTypesList.get(position).getDisabilityTypeId();

                    DisTypesIds disTypesIds=new DisTypesIds(disid);

                    viewModel.delete(disTypesIds);

                    //ClientDisability clientDisability = new ClientDisability(disid, cid);
                    viewModel.deleteDisabilityTypesByClientId(cid);
                }*/

                if(isChecked){
                    disabilitiesItemList.add(new ClientDisabilitiesItem(disid));
                }else{
                    disabilitiesItemList.remove(new ClientDisabilitiesItem(disid));
                }

            }
        });

        //onDisabilitesSelected.OnDisSelected(disabilitiesItemList);


        //Constants.saveClientsDisabilities(distypesid.get(0).getDisabilitytypeid(),distypesid.get(1).getDisabilitytypeid(),distypesid.get(2).getDisabilitytypeid(),distypesid.get(3).getDisabilitytypeid(),distypesid.get(4).getDisabilitytypeid(),context);





    }

    @Override
    public int getItemCount() {
        return disabilityTypesList.size();
    }

    public class DisabilityTypesViewHolder extends RecyclerView.ViewHolder {
        CardView cview;
        ImageView img;
        CheckBox name;
        public DisabilityTypesViewHolder(@NonNull View v) {
            super(v);
            cview=v.findViewById(R.id.cview);
            img=v.findViewById(R.id.img);
            name=v.findViewById(R.id.name);
        }
    }
}
