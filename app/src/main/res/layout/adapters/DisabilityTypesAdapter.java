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
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.data.room.aeeny.DisTypesIds;
import design.swira.aennyapp.pojo.aenny.DisabilitiesTypesidData;
import design.swira.aennyapp.pojo.aenny.disability_types.DisabilityTypes;
import design.swira.aennyapp.ui.aenny.regsister.RegsisterViewModel;

public class DisabilityTypesAdapter extends RecyclerView.Adapter<DisabilityTypesAdapter.DisabilityTypesViewHolder> {

    List<DisabilityTypes> disabilityTypesList;
    List<DisabilitiesTypesidData> distypesid=new ArrayList<>();
    Context context;
    RegsisterViewModel viewModel;

    public DisabilityTypesAdapter(List<DisabilityTypes> disabilityTypesList, Context context,RegsisterViewModel viewModel) {
        this.disabilityTypesList=disabilityTypesList;
        this.context=context;
        this.viewModel=viewModel;
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

        viewModel.deleteall();






        holder.name.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked ){

                    int disid=disabilityTypesList.get(position).getDisabilityTypeId();

                    DisTypesIds disTypesIds=new DisTypesIds(disid);

                    viewModel.insert(disTypesIds);


                }else{
                    int disid=disabilityTypesList.get(position).getDisabilityTypeId();

                    DisTypesIds disTypesIds=new DisTypesIds(disid);

                    viewModel.delete(disTypesIds);
                }

            }
        });


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
