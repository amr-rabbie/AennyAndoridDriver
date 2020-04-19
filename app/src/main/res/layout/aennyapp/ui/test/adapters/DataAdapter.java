package design.swira.aennyapp.ui.test.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.test.RowsItem;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {
    List<RowsItem> datalist;

    public DataAdapter(List<RowsItem> rowsItems) {
        this.datalist=rowsItems;
    }

    public void setList(List<RowsItem> datalist){
        this.datalist=datalist;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_list_item,parent,false);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int)v.getTag();
                RowsItem data = datalist.get(pos);

                AlertDialog.Builder builder=new AlertDialog.Builder(parent.getContext());
                builder.setIcon(R.drawable.stock);
                builder.setTitle(data.getName());
                builder.setMessage("\n" + "Price: " +data.getPrice() + "\n\n" + " Precentage: " + data.getChangePercentage());
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
            }
        });

        return new DataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
        RowsItem stock = datalist.get(position);
        holder.name.setText(stock.getName());
        holder.sector.setText(stock.getSector());
        holder.price.setText(stock.getPrice()+"");
        holder.precntage.setText(stock.getChangePercentage()+"");

        holder.cview.setTag(position);

        if(stock.getChangePercentage() > 0){
            holder.price.setTextColor(Color.GREEN);
            holder.precntage.setTextColor(Color.GREEN);
        }else{
            holder.price.setTextColor(Color.RED);
            holder.precntage.setTextColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return datalist.size();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder {
        CardView cview;
        TextView name,sector,price,precntage;
        public DataViewHolder(@NonNull View v) {
            super(v);
            cview=v.findViewById(R.id.cview);
            name=v.findViewById(R.id.name);
            price=v.findViewById(R.id.price);
            sector=v.findViewById(R.id.sector);
            precntage=v.findViewById(R.id.prectange);
        }
    }
}
