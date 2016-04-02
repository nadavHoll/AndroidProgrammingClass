package com.nadavh.ex1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

public class Ex1 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText numOfSheepEditText = (EditText) findViewById(R.id.num_of_sheep_editText);
        final SeekBar sheepSeekBar = (SeekBar) findViewById(R.id.num_of_sheep_seekBar);
        final CheckBox withFoodCheckBox = (CheckBox) findViewById(R.id.with_food_checkBox);
        final Button makeOrderButton = (Button) findViewById(R.id.make_order_button);

        if (numOfSheepEditText != null && sheepSeekBar != null && withFoodCheckBox != null && makeOrderButton != null) {

            numOfSheepEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    String etVal = s.toString();
                    try {
                        sheepSeekBar.setProgress(Integer.parseInt(etVal));
                        int position = numOfSheepEditText.getText().length();
                        numOfSheepEditText.setSelection(position);
                    } catch (NumberFormatException e) {
                        sheepSeekBar.setProgress(0);
                    }
                }
            });

            sheepSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    numOfSheepEditText.setText(String.valueOf(progress));
                    if (withFoodCheckBox.isChecked() && (progress > 0)) {
                        if (!makeOrderButton.isEnabled()) {
                            makeOrderButton.setEnabled(true);
                            invalidateOptionsMenu();
                        }
                    } else {
                        if (makeOrderButton.isEnabled()) {
                            makeOrderButton.setEnabled(false);
                            invalidateOptionsMenu();
                        }
                    }
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            withFoodCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked && (sheepSeekBar.getProgress() > 0)) {
                        if (!makeOrderButton.isEnabled()) {
                            makeOrderButton.setEnabled(true);
                            invalidateOptionsMenu();
                        }
                    } else {
                        if (makeOrderButton.isEnabled()) {
                            makeOrderButton.setEnabled(false);
                            invalidateOptionsMenu();
                        }
                    }
                }
            });

            if (withFoodCheckBox.isChecked() && sheepSeekBar.getProgress() > 0 ) {
                makeOrderButton.setEnabled(true);
            } else {
                makeOrderButton.setEnabled(false);
            }
        }

    }

    public void makeOrderClick(View view){
        Intent intent = new Intent(this, orderSent.class);
        startActivity(intent);
    }

    public void selectClick(View view){
        Intent intentSelectFood = new Intent(this, SelectFood.class);
        startActivityForResult(intentSelectFood, Integer.parseInt(this.getString(R.string.SELECT_FOOD_REQUEST)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ex1, menu);
        try {
            final SeekBar sheepSeekBar = (SeekBar) findViewById(R.id.num_of_sheep_seekBar);
            final CheckBox withFoodCheckBox = (CheckBox) findViewById(R.id.with_food_checkBox);
            menu.findItem(R.id.appbar_item_send_order).setVisible(sheepSeekBar.getProgress()>0 && withFoodCheckBox.isChecked());
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),"Failed to create appbar button",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch(item.getItemId()){
            case R.id.appbar_item_send_order:
                try {
                    final SeekBar sheepSeekBar = (SeekBar) findViewById(R.id.num_of_sheep_seekBar);
                    final CheckBox withFoodCheckBox = (CheckBox) findViewById(R.id.with_food_checkBox);
                    if (sheepSeekBar.getProgress()>0 && withFoodCheckBox.isChecked()){
                        Intent intent = new Intent(this,orderSent.class);
                        startActivity(intent);
                    }
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Failed to initiate intent",Toast.LENGTH_SHORT).show();
                }
                return true;
            case R.id.action_settings:
                return true;
            default:
                // if we got here, the user's action was not recognized
                // invoke the superclass to handle it
                return super.onOptionsItemSelected(item);
        }


        /**
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
        **/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == Integer.parseInt(this.getString(R.string.SELECT_FOOD_REQUEST))) {
            if (resultCode == RESULT_OK){
                String[] foodArray = this.getResources().getStringArray(R.array.food_array);
                switch(data.getIntExtra("selectedSheepFood",5)){
                    case 0:
                        Toast.makeText(getApplicationContext(),foodArray[0],Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(getApplicationContext(),foodArray[1],Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(getApplicationContext(),foodArray[2],Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        Toast.makeText(getApplicationContext(),foodArray[3],Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        Toast.makeText(getApplicationContext(),foodArray[4],Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),"intent data does not contain 'selectedSheepFood'",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
