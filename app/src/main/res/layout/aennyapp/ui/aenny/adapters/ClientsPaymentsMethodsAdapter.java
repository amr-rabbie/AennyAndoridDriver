package design.swira.aennyapp.ui.aenny.adapters;

import android.content.Context;
import android.content.Intent;
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
import design.swira.aennyapp.pojo.aenny.clientpaymentsmethods.ClientsPaymentsMethodsResponse;
import design.swira.aennyapp.ui.aenny.customdialog.MyCustomDialogActivity;
import design.swira.aennyapp.ui.aenny.paymentsmethods.UpdatePaymentMethodActivity;

public class ClientsPaymentsMethodsAdapter extends RecyclerView.Adapter<ClientsPaymentsMethodsAdapter.ClientsPaymentsMethodsViewHolder> {
    List<ClientsPaymentsMethodsResponse> list;

    Context context;

    public ClientsPaymentsMethodsAdapter(Context context) {
        this.context=context;
    }

    public void SetList(List<ClientsPaymentsMethodsResponse> list){
        this.list=list;
    }

    @NonNull
    @Override
    public ClientsPaymentsMethodsAdapter.ClientsPaymentsMethodsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_list_item,parent,false);
        return new ClientsPaymentsMethodsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientsPaymentsMethodsAdapter.ClientsPaymentsMethodsViewHolder holder, int position) {

        int methodd=list.get(position).getPaymentTypeId();
        if(methodd == 1){
            holder.method.setText("Cash");
            holder.img.setImageResource(R.drawable.ic_walet);
        }else if(methodd == 2){
            holder.method.setText("Visa");
            holder.img.setImageResource(R.drawable.ic_visa);
        }else if(methodd == 3){
            holder.method.setText("Master Card");
            holder.img.setImageResource(R.drawable.ic_mastercard);
        }

        holder.holder.setText(list.get(position).getCardHolderName());
        holder.number.setText(list.get(position).getCardNumber());
        holder.expiere.setText(list.get(position).getExpirationDate());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = list.get(position).getClient_Payments_Methods_id();
                //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                Intent i=new Intent(context, UpdatePaymentMethodActivity.class);
                i.putExtra("id",id);
                context.startActivity(i);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = list.get(position).getClient_Payments_Methods_id();
                //showDialog(id,"Confirm Delete","Are you sure you want to delete this client favourite ?",context);

                Intent i=new Intent(context, MyCustomDialogActivity.class);
                i.putExtra("id",id);
                i.putExtra("title","Confirm delete");
                i.putExtra("msg","Are you sure you want to delete this client payment ?");
                i.putExtra("flag","pay");
                context.startActivity(i);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ClientsPaymentsMethodsViewHolder extends RecyclerView.ViewHolder {
        CardView cview;
        ImageView img;
        TextView method,holder,number,expiere;
        TextView edit,delete;
        public ClientsPaymentsMethodsViewHolder(@NonNull View v) {
            super(v);
            cview=v.findViewById(R.id.cview);
            img=v.findViewById(R.id.img);
            method=v.findViewById(R.id.paymentmethod);
            holder=v.findViewById(R.id.placeholder);
            number=v.findViewById(R.id.cardnumber);
            expiere=v.findViewById(R.id.expiredate);
            edit=v.findViewById(R.id.edit);
            delete=v.findViewById(R.id.delete);

        }
    }
}
