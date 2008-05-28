package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
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
		result = (TextView)this.findViewById(R.id.result);
		fieldsuggest = (TextView)this.findViewById(R.id.suggest);
    }
    
    //Listen for button clicks
    private void setListensers() {
    	calcbutton.setOnClickListener(calcBMI);
    }
    
    private OnClickListener calcBMI = new OnClickListener()
    {
    	public void onClick(View v)
    	{
    		/*
    		 * Main calc operations
    		 */
    		DecimalFormat nf = new DecimalFormat("0.0");
    		
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
    		//Intent i = new Intent(bmi.this, Report.class);
    		//startActivity(i);
    	}
    };

	protected static final int MENU_OPTIONS_ID = Menu.FIRST;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_OPTIONS_ID, R.string.options_label,
				new Runnable() {
					public void run() {
						openOptionsDialog();
					}
		});
		return true;
	}
	
	private void openOptionsDialog() {
		View view = getViewInflate().inflate(R.layout.about, null, null);
		new AlertDialog.Builder(this)
		.setTitle(R.string.about_title)
		.setView(view)
		.setPositiveButton(R.string.ok_label,
				new DialogInterface.OnClickListener(){
					public void onClick(
							DialogInterface dialoginterface, int i){
					}
				})
		.show();
	}
}