package design.swira.aennyappdriver.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import design.swira.aennyappdriver.R;

public class MyCustomDialog {






    public static void showDialog(int id ,String title, String msg , Context context,String flag) {
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
                if(flag.equals("fav")){
                    //ClientFavouriteViewModel clientFavouriteViewModel= ViewModelProviders.of(context).get(ClientFavouriteViewModel.class);
                }

            }
        });

        dialog.show();
    }


}
