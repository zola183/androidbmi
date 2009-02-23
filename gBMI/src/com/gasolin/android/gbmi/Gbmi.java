package com.gasolin.android.gbmi;

import java.text.DecimalFormat;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Gbmi extends Activity {
	public static final String PREF_HEIGHT = "BMI_Height";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = Locale.TRADITIONAL_CHINESE;
        res.updateConfiguration(conf, dm);*/
        
        setContentView(R.layout.main);
        findViews();
        setListensers();
        restorePrefs();
    }
    
    private Button button_calc;
    private EditText field_height;
    private EditText field_weight;
    private TextView view_result;
    private TextView view_suggest;

    private void findViews()
    {
    	button_calc = (Button) findViewById(R.id.submit);
    	field_height = (EditText) findViewById(R.id.height);
    	field_weight = (EditText) findViewById(R.id.weight);
    	view_result = (TextView) findViewById(R.id.result);
    	view_suggest = (TextView) findViewById(R.id.suggest);
    }

    // Restore preferences
    private void restorePrefs()
    {
    	SharedPreferences settings = getSharedPreferences(PREF_HEIGHT, 0);
    	String pref_height = settings.getString(PREF_HEIGHT, "");
    	if(pref_height!="")
    	{
    		field_height.setText(pref_height);
    		field_weight.requestFocus();
    	}
    }
    
    //Listen for button clicks
    private void setListensers() {
    	button_calc.setOnClickListener(calcBMI);
    }
    
    private Button.OnClickListener calcBMI = new Button.OnClickListener()
    {
        public void onClick(View v)
        {
            DecimalFormat nf = new DecimalFormat("0.00");
            try{
	            double height = Double.parseDouble(field_height.getText().toString())/100;
	            double weight = Double.parseDouble(field_weight.getText().toString());
	            double BMI = weight / (height * height);
	            //Present result 
	            view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));
	 
	            //Give health advice 
	            if(BMI>27){
	            	view_suggest.setText(R.string.advice_fat);
	            }else if(BMI>25){
	                view_suggest.setText(R.string.advice_heavy);
	            }else if(BMI<20){
	                view_suggest.setText(R.string.advice_light);
	            }else{
	                view_suggest.setText(R.string.advice_average);
	            }
            }
            catch(Exception err)
            {
            	Toast.makeText(Gbmi.this, getString(R.string.input_error), Toast.LENGTH_SHORT).show();
            }
        }
    };
    
    protected static final int MENU_ABOUT = Menu.FIRST;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, MENU_ABOUT, 0, R.string.about_label);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		switch(item.getItemId()){
			case MENU_ABOUT:
				openOptionsDialog();
				break;
		}
		return true;
	}

	private void openOptionsDialog() {
		new AlertDialog.Builder(this)
		.setTitle(R.string.about_title)//.setView(view)
		.setMessage(R.string.about_msg)
		.setPositiveButton(R.string.ok_label,
				new DialogInterface.OnClickListener(){
					public void onClick(
							DialogInterface dialoginterface, int i){
					}
				})
		.show();
	}
	
	@Override
	protected void onStop(){
		super.onStop();
		// Save user preferences. We need an Editor object to
		// make changes. All objects are from android.context.Context
		SharedPreferences settings = getSharedPreferences(PREF_HEIGHT, 0);
		settings.edit()
			.putString(PREF_HEIGHT, field_height.getText().toString())
			.commit();
	}
}