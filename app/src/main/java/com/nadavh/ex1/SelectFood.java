package com.nadavh.ex1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class SelectFood extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_food);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ListView listSheepFood = (ListView) findViewById(R.id.listView_select_food);
        String[] arraySheepFood = this.getResources().getStringArray(R.array.food_array);
        int[] arraySheepFoodImages = {
            R.drawable.sheep_food_option_1,
            R.drawable.sheep_food_option_2,
            R.drawable.sheep_food_option_3,
            R.drawable.sheep_food_option_4,
            R.drawable.sheep_food_option_5,
        };

        if (listSheepFood != null ){
            ImgTextArrayAdapter adapterSheepFood = new ImgTextArrayAdapter(this, arraySheepFood, arraySheepFoodImages);
            listSheepFood.setAdapter(adapterSheepFood);

            listSheepFood.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent output = new Intent();
                    output.putExtra("selectedSheepFood", position);
                    setResult(RESULT_OK, output);
                    finish();
                }
            });
        }
    }

}
