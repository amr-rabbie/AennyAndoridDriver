package design.swira.aennyapp.ui.aenny.adapters;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.clientfavourites.ClientFavouriteResponse;
import design.swira.aennyapp.ui.aenny.clientfavourites.UpdateClientFavouriteActivity;
import design.swira.aennyapp.ui.aenny.customdialog.MyCustomDialogActivity;
import design.swira.aennyapp.ui.aenny.ongonigtrip.RouteActivity;
import design.swira.aennyapp.ui.aenny.testmap.TestMapsActivity;
import design.swira.aennyapp.utils.MyCustomDialog;

public class ClientFavouriteListAdapter extends RecyclerView.Adapter<ClientFavouriteListAdapter.ClientFavouriteListViewHolder> {

    List<ClientFavouriteResponse> list;
    Context context;

    public void setList(List<ClientFavouriteResponse> list){
        this.list=list;
    }

    public ClientFavouriteListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ClientFavouriteListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_list_item,parent,false);





        return new ClientFavouriteListViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientFavouriteListViewHolder holder, int position) {

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

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int id = list.get(position).getClient_Favourite_Id();
                //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                Intent i=new Intent(context, UpdateClientFavouriteActivity.class);
                i.putExtra("id",id);
                context.startActivity(i);

            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = list.get(position).getClient_Favourite_Id();
                //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                Intent i=new Intent(context, MyCustomDialogActivity.class);
                i.putExtra("id",id);
                i.putExtra("title","Confirm delete");
                i.putExtra("msg","Are you sure you want to delete this client favourite ?");
                i.putExtra("flag","fav");
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClientFavouriteListViewHolder extends RecyclerView.ViewHolder {
        CardView cview;
        ImageView img;
        TextView name,desc;
        TextView edit,delete;
        public ClientFavouriteListViewHolder(@NonNull View v) {
            super(v);
            cview=v.findViewById(R.id.cview);
            img=v.findViewById(R.id.img);
            name=v.findViewById(R.id.locname);
            desc=v.findViewById(R.id.locaddress);
            edit=v.findViewById(R.id.edit);
            delete=v.findViewById(R.id.delete);
        }
    }

    public  void showDialog(int id ,String title, String msg , Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //dialog.setCancelable(false);
        dialog.setContentView(R.layout.my_custom_dialog);

        TextView dtitle =  dialog.findViewById(R.id.dtitle);
        dtitle.setText(title);

        TextView dmsg =  dialog.findViewById(R.id.dmsg);
        dmsg.setText(msg);

        Button ok =  dialog.findViewById(R.id.ok);

        Button cancel =  dialog.findViewById(R.id.cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

        dialog.show();
    }





}
