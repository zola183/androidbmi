package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bmi extends Activity {
	private static final String TAG = "Bmi";
	public static final String PREF_HEIGHT = "BMI_Height";
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        findViews();
        restorePrefs();
        setListensers();
    }

    private Button button_calc;
    private EditText field_height;
    private EditText field_weight;
    private TextView view_result;
    private TextView view_suggest;

    private void findViews()
    {
    	Log.d(TAG, "find Views");
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
    		//field_height.focusSearch(field_height.FOCUS_DOWN);
    	}
    }
    
    //Listen for button clicks
    private void setListensers() {
    	Log.d(TAG, "set Listensers");
    	button_calc.setOnClickListener(calcBMI);
    }

    private OnClickListener calcBMI = new OnClickListener()
    {
        public void onClick(View v)
        {
        	Log.d(TAG, "start to calc");
            DecimalFormat nf = new DecimalFormat("0.00");
            double height = Double.parseDouble(field_height.getText().toString())/100;
            double weight = Double.parseDouble(field_weight.getText().toString());
            double BMI = weight / (height * height);
            
            //Present result 
            view_result.setText(getText(R.string.bmi_result) + nf.format(BMI));
 
            //Give health advice 
            if(BMI>25){
                view_suggest.setText(R.string.advice_heavy);
            }else if(BMI<20){
                view_suggest.setText(R.string.advice_light);
            }else{
                view_suggest.setText(R.string.advice_average);
            }
            /*openOptionsDialog();
        	//Switch to report page
        	//Intent intent = new Intent(bmi.this, Report.class);
            Intent intent = new Intent();
            intent.setClass(Bmi.this, Report.class);
            Bundle bundle = new Bundle();
            bundle.putString("KEY_HEIGHT", field_height.getText().toString());
            bundle.putString("KEY_WEIGHT", field_weight.getText().toString());
            intent.putExtras(bundle);
            startActivity(intent);
            //finish();*/
        }
    };
	
    protected static final int MENU_ABOUT = Menu.FIRST;
    protected static final int MENU_Quit = Menu.FIRST+1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		Log.d(TAG, "open Menu");
		menu.add(0, MENU_ABOUT, 0, R.string.about_label);
		menu.add(0, MENU_Quit, 0, R.string.finish_label);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		Log.d(TAG, "select Menu Item");
		switch(item.getItemId()){
			case MENU_ABOUT:
				openOptionsDialog();
				break;
			case MENU_Quit:
				finish();
				break;
		}
		return true;
	}

	private void openOptionsDialog() {
		Log.d(TAG, "open Dialog");
		//Toast.makeText(this, "BMI ­pºâ¾¹", Toast.LENGTH_SHORT).show();
		//View view = getLayoutInflater().inflate(R.layout.about, null, true);
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
		Log.d(TAG, "save Pref");
		// Save user preferences. We need an Editor object to
		// make changes. All objects are from android.context.Context
		SharedPreferences settings = getSharedPreferences(PREF_HEIGHT, 0);
		//SharedPreferences.Editor editor = settings.edit();
		//editor.putString(PREF_HEIGHT, field_height.getText().toString());
		//commit edits
		//editor.commit();
		settings.edit()
			.putString(PREF_HEIGHT, field_height.getText().toString())
			.commit();
	}
}