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

    private OnClickListener calcBMI = new OnClickListener()
    {
    	public void onClick(View v)
    	{
    		DecimalFormat nf = new DecimalFormat("0.0");
    		EditText fieldheight = (EditText)findViewById(R.id.height);
    		EditText fieldweight = (EditText)findViewById(R.id.weight);
    		double height = Double.parseDouble(fieldheight.getText().toString())/100;
    		double weight = Double.parseDouble(fieldweight.getText().toString());
    		double BMI = weight / (height * height);

    		TextView result = (TextView)findViewById(R.id.result);
    		result.setText("Your BMI is "+nf.format(BMI));
    		
    		//Give health advice
    		if(BMI>25){
    			showAlert("Warning", "You need go diet", "ok", false);
    		}else if(BMI<20){
    			showAlert("Warning", "You need eat more", "ok", false);
    		}else{
    			showAlert("Congratulation", "You are in good shape", "ok", false);
    		}
    		
    		//Add error checking
    	}
    };
}