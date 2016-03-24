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
                    if (withFoodCheckBox.isChecked() && (progress > 0)){
                        makeOrderButton.setEnabled(true);
                    } else {
                        makeOrderButton.setEnabled(false);
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
                        makeOrderButton.setEnabled(true);
                    } else {
                        makeOrderButton.setEnabled(false);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ex1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
