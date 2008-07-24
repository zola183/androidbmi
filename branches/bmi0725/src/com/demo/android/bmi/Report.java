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
import android.widget.TextView;

public class Report extends Activity {

	private TextView result;
	private TextView fieldsuggest;
	private Button backbutton;

    private void findViews()
    {
    	result = (TextView)this.findViewById(R.id.report_content);
    	fieldsuggest = (TextView)this.findViewById(R.id.report_suggest);
    	backbutton = (Button)this.findViewById(R.id.report_back);
    }

    //Listen for button clicks
    private void setListensers() {
    	backbutton.setOnClickListener(backBMI);
    }

    private OnClickListener backBMI = new OnClickListener()
    {
    	public void onClick(View v)
    	{
           // Close this Activity
           Report.this.finish();  		
    	}
    };
    	
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		
		setContentView(R.layout.report);
		findViews();
		setListensers();
		DecimalFormat nf = new DecimalFormat("0.0");
		
		Bundle b = this.getIntent().getExtras();
		double height = Double.parseDouble(b.getString("KEY_HEIGHT"))/100;
		double weight = Double.parseDouble(b.getString("KEY_WEIGHT"));
		double BMI = weight / (height * height);
		
		result.setText(getString(R.string.bmi_result) +nf.format(BMI));
		
		//Give health advice
		if(BMI>25){
			fieldsuggest.setText(R.string.advice_heavy_tw);
		}else if(BMI<20){
			fieldsuggest.setText(R.string.advice_light_tw);
		}else{
			fieldsuggest.setText(R.string.advice_average_tw);
		}
	}

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