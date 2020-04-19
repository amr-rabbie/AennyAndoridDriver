package design.swira.aennyapp.ui.test.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import design.swira.aennyapp.R;

public class StocksSpinnerAdapter extends ArrayAdapter<String> {

    private Context ctx;
    private String[] contentArray;
    private int[] imageArray;

    public StocksSpinnerAdapter(Context context, int resource, String[] objects,
                                int[] imageArray) {
        super(context,  R.layout.country_spinner_item, R.id.name, objects);
        this.ctx = context;
        this.contentArray = objects;
        this.imageArray = imageArray;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.country_spinner_item, null);

        }

        TextView name = (TextView) convertView.findViewById(R.id.name);
        name.setText(contentArray[position]);

        ImageView imageView = (ImageView)convertView.findViewById(R.id.img);
        imageView.setImageResource(imageArray[position]);

        return convertView;

    }

}