package com.demo.android.bmi;

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
        
        //Listen for button clicks
        Button button = (Button)findViewById(R.id.submit);
        button.setOnClickListener(calcBMI);
    }

    private EditText fieldheight;
    private EditText fieldweight;
    private TextView result;
    private TextView fieldsuggest;
    private void findViews()
    {
    	fieldheight = (EditText)this.findViewById(R.id.height);
		fieldweight = (EditText)this.findViewById(R.id.weight);
		result = (TextView)findViewById(R.id.result);
		fieldsuggest = (TextView)findViewById(R.id.suggest);
    }
    
    private OnClickListener calcBMI = new OnClickListener()
    {
    	public void onClick(View v)
    	{
    		/*
    		 * Main operations
    		 */
    		DecimalFormat nf = new DecimalFormat("0.0");
    		findViews();
    		double height = Double.parseDouble(fieldheight.getText().toString())/100;
    		double weight = Double.parseDouble(fieldweight.getText().toString());
    		double BMI = weight / (height * height);

    		result.setText(getString(R.string.bmi_result) +nf.format(BMI));
    		//Give health advice
    		if(BMI>25){
    			//showAlert("Warning", getString(R.string.advice_heavy), "ok", false);
    			fieldsuggest.setText(R.string.advice_heavy_tw);
    		}else if(BMI<20){
     			//showAlert("Warning", getString(R.string.advice_light), "ok", false);
    			fieldsuggest.setText(R.string.advice_light_tw);
    		}else{
    			//showAlert("Congratulation", getString(R.string.advice_average), "ok", false);
    			fieldsuggest.setText(R.string.advice_average_tw);
    		}
    		
    		//Add error checking
    	}
    };
    
}