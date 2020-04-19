package design.swira.aennyapp.ui.aenny.cities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.cities.City;

public class CitiesListAdapter extends RecyclerView.Adapter<CitiesListAdapter.CitiesViewHolder> {

    List<City> cityList;

    public void setList(List<City> cityList){
        this.cityList=cityList;
    }

    @NonNull
    @Override
    public CitiesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.city_list_item,parent,false);
        return new CitiesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CitiesViewHolder holder, int position) {

        holder.city.setText(cityList.get(position).getCityName());

    }

    @Override
    public int getItemCount() {
        return cityList.size();
    }

    public class CitiesViewHolder extends RecyclerView.ViewHolder {
        CardView cview;
        TextView city;
        public CitiesViewHolder(@NonNull View v) {
            super(v);
            cview=v.findViewById(R.id.cview);
            city=v.findViewById(R.id.city);
        }
    }
}
