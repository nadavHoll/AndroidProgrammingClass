package com.nadavh.ex1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nadav H on 02/04/2016.
 */
public class ImgTextArrayAdapter extends ArrayAdapter<String> {
    private final Context myContext;
    private final String[] myStrings;
    private final int[] myImages;

    public ImgTextArrayAdapter(Context context, String[] values, int[] images){
        super(context, R.layout.img_text_list_item, values);
        myContext = context;
        myStrings = values;
        myImages = images;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.img_text_list_item, parent, false);
        TextView textPart = (TextView) rowView.findViewById(R.id.img_text_item_text_part);
        ImageView imagePart = (ImageView) rowView.findViewById(R.id.img_text_item_image_part);

        textPart.setText(myStrings[position]);
        imagePart.setImageResource(myImages[position]);

        return rowView;
    }
}
