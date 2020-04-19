package design.swira.aennyapp.ui.test3.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.test3.ResultsItem;
import design.swira.aennyapp.pojo.test3.Thumbnail;

public class MDataAdapter extends RecyclerView.Adapter<MDataAdapter.MDataViewHolder> {

    List<ResultsItem> mdatalist;

    public MDataAdapter(List<ResultsItem> mdatalist) {
        this.mdatalist=mdatalist;
    }

    public void setList(List<ResultsItem> mdatalist){
        this.mdatalist=mdatalist;
    }
    @NonNull
    @Override
    public MDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.marvel_item,parent,false);
        return new MDataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MDataViewHolder holder, int position) {

        ResultsItem data = mdatalist.get(position);

        Thumbnail thumbnail = data.getThumbnail();

        holder.name.setText(data.getName());


        String imgurl=thumbnail.getPath() + "/" + "portrait_uncanny" + "." + thumbnail.getExtension();

        Picasso.get()
                .load(imgurl)
                .resize(50, 50)
                .centerCrop()
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return mdatalist.size();
    }

    public class MDataViewHolder extends RecyclerView.ViewHolder {
        CardView cview;
        TextView name;
        ImageView img;
        public MDataViewHolder(@NonNull View v) {
            super(v);
            cview=v.findViewById(R.id.cview);
            name=v.findViewById(R.id.name);
            img=v.findViewById(R.id.img);
        }
    }
}
