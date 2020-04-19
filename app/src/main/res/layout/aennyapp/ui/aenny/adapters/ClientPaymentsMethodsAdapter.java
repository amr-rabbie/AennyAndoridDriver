package design.swira.aennyapp.ui.aenny.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import design.swira.aennyapp.R;
import design.swira.aennyapp.pojo.aenny.clientpaymentsmethods.ClientsPaymentsMethodsResponse;
import design.swira.aennyapp.pojo.aenny.paymentsmethods.PaymentsMethodsResponse;

public class ClientPaymentsMethodsAdapter  extends BaseAdapter {
    Context context; List<ClientsPaymentsMethodsResponse> cityList;
    public ClientPaymentsMethodsAdapter(Context context, List<ClientsPaymentsMethodsResponse> cityList) {
        this.context=context;
        this.cityList=cityList;
    }

    @Override
    public int getCount() {
        try{
            if(cityList.size() > 0) {
                return cityList.size();
            }else{
                return 0;
            }}
        catch (Exception e){
            Log.e("e",e.getMessage());
            return 0;
        }
    }

    @Override
    public ClientsPaymentsMethodsResponse getItem(int i) {
        return cityList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CountriesViewHolder holder=null;
        if(view == null){
            view= LayoutInflater.from(context).inflate(R.layout.spinner_item,viewGroup,false);
            holder=new CountriesViewHolder(view);
            view.setTag(holder);
        }else{
            holder= (CountriesViewHolder) view.getTag();
        }

        int paymentTypeId = cityList.get(i).getPaymentTypeId();

        String payname="Cash: ";
        if(paymentTypeId ==1){
            payname="Cash: ";
        }else  if(paymentTypeId ==2){
            payname="Visa: ";
        }else  if(paymentTypeId ==3){
            payname="MasterCard:  ";
        }

        String cardNumber = cityList.get(i).getCardNumber();

        String paycard=payname + cardNumber;

        holder.name.setText(paycard);


        return view;
    }



    public class CountriesViewHolder{

        TextView name;
        public CountriesViewHolder(View v) {
            name=v.findViewById(R.id.name);
        }
    }
}
