package com.demo.android.bmi;

import java.text.DecimalFormat;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
        findViews();
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
    	button_calc = (Button)this.findViewById(R.id.submit);
    	field_height = (EditText)this.findViewById(R.id.height);
    	field_weight = (EditText)this.findViewById(R.id.weight);
    	view_result = (TextView)findViewById(R.id.result);
    	view_suggest = (TextView)findViewById(R.id.suggest);
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
            DecimalFormat nf = new DecimalFormat("0.0");
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
            //openOptionsDialog();
        }
    };
	
    protected static final int MENU_OPTIONS_ID = Menu.FIRST;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		Log.d(TAG, "open Menu");
		menu.add(0, MENU_OPTIONS_ID, R.string.options_label, R.drawable.icon);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item)
	{
		super.onOptionsItemSelected(item);
		int id = item.getItemId();
		Log.d(TAG, "select Menu Item");
		switch(id){
			case MENU_OPTIONS_ID:
				openOptionsDialog();
				break;
		}
		return true;
	}

	private void openOptionsDialog() {
		Log.d(TAG, "open Dialog");
		View view = getLayoutInflater().inflate(R.layout.about, null);
		new AlertDialog.Builder(this)
		.setTitle(R.string.about_title)
		.setView(view)
		.setPositiveButton(R.string.ok_label,
				new DialogInterface.OnClickListener(){
					public void onClick(
							DialogInterface dialoginterface, int i){
					}
				})
		.setNegativeButton(R.string.homepage_label,
				new DialogInterface.OnClickListener(){
					public void onClick(
							DialogInterface dialoginterface, int i){
						//go to url
						Uri uri = Uri.parse("http://androidbmi.googlecode.com/");
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
					}
				})
		.show();
	}
}