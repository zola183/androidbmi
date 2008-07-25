package com.demo.android.com;

import java.text.DecimalFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bmi extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        findViews();
        setListensers();
    }

    private Button calcbutton;
    private EditText fieldheight;
    private EditText fieldweight;
    private TextView result;
    private TextView fieldsuggest;

    private void findViews()
    {
    	calcbutton = (Button)this.findViewById(R.id.submit);
    	fieldheight = (EditText)this.findViewById(R.id.height);
    	fieldweight = (EditText)this.findViewById(R.id.weight);
    	result = (TextView)findViewById(R.id.result);
    	fieldsuggest = (TextView)findViewById(R.id.suggest);
    }

    //Listen for button clicks
    private void setListensers() {
    	calcbutton.setOnClickListener(calcBMI);
    }

    private OnClickListener calcBMI = new OnClickListener()
    {
        public void onClick(View v)
        {
            DecimalFormat nf = new DecimalFormat("0.0");
            double height = Double.parseDouble(fieldheight.getText().toString())/100;
            double weight = Double.parseDouble(fieldweight.getText().toString());
            double BMI = weight / (height * height);
            
            //Present result 
            result.setText(getText(R.string.bmi_result) + nf.format(BMI));
 
            //Give health advice 
            if(BMI>25){
                fieldsuggest.setText(R.string.advice_heavy);
            }else if(BMI<20){
                fieldsuggest.setText(R.string.advice_light);
            }else{
                fieldsuggest.setText(R.string.advice_average);
            }
        }
    };
}