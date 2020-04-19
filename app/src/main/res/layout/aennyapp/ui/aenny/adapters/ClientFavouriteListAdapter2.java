package design.swira.aennyapp.ui.aenny.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.ui.aenny.clientfavourites.OnLocationFavouriteClick;

public class ClientFavouriteListAdapter2 extends RecyclerView.Adapter<ClientFavouriteListAdapter2.ClientFavouriteListViewHolder> {

    List<ClientFavouriteResponse> list;

    OnLocationFavouriteClick onLocationFavouriteClick;

    public ClientFavouriteListAdapter2(OnLocationFavouriteClick onLocationFavouriteClick) {
        this.onLocationFavouriteClick = onLocationFavouriteClick;
    }

    public void setList(List<ClientFavouriteResponse> list){
        this.list=list;
    }

    @NonNull
    @Override
    public ClientFavouriteListAdapter2.ClientFavouriteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_list_item2,parent,false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int)v.getTag();
                ClientFavouriteResponse clientFavouriteResponse = list.get(pos);
                onLocationFavouriteClick.onListClick(clientFavouriteResponse);
            }
        });

        return new ClientFavouriteListAdapter2.ClientFavouriteListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientFavouriteListAdapter2.ClientFavouriteListViewHolder holder, int position) {

        String icon=list.get(position).getClientFavouriteNotes();

        if(icon.equals("home")){
            holder.img.setImageResource(R.drawable.ic_home);
        }else if(icon.equals("hospital")){
            holder.img.setImageResource(R.drawable.ic_hospital);
        }else if(icon.equals("school")){
            holder.img.setImageResource(R.drawable.ic_school);
        }else if(icon.equals("location")){
            holder.img.setImageResource(R.drawable.ic_location);
        }

        holder.name.setText(list.get(position).getClientFavouriteName());
        holder.desc.setText(list.get(position).getClientFavouriteDesc());

        holder.cview.setTag(position);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClientFavouriteListViewHolder extends RecyclerView.ViewHolder {
        CardView cview;
        ImageView img;
        TextView name,desc;
        public ClientFavouriteListViewHolder(@NonNull View v) {
            super(v);
            cview=v.findViewById(R.id.cview);
            img=v.findViewById(R.id.img);
            name=v.findViewById(R.id.locname);
            desc=v.findViewById(R.id.locaddress);
        }
    }
}
