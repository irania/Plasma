package main.activity;



import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.Tirax.RF.*;
import com.Tirax.RF.Compiler;
import com.Tirax.RF.Storage.Pages;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.*;


	public class StartActivity extends MyActivity implements OnClickListener {

	public int seekBarProgress;
	public static int time=0;
	public static int frequency;
		private int powerValue;
		TextView power;

		@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.start);



		declareButtons();


		Mode op = Manager.getType();
		TextView mode_text = (TextView) findViewById(R.id.txt_start_autoMode);
		mode_text.setText(op.autoMode );

		setPowerValue();

		power = (TextView) findViewById(R.id.txt_power_start);
		power.setText(powerValue + "%");


		initialSeekBar();

			
		
	}

		private void setPowerValue() {
			if(Pages.step == Pages.LOW)
				powerValue=25;
			else if(Pages.step == Pages.MEDIUM)
				powerValue =50;
			else
				powerValue=75;
			seekBarProgress=powerValue;
		}


		private void initialSeekBar() {
			SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
			seekBar.setProgress(powerValue);

			seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					if(progress<5) {
						seekBar.setProgress(5);

						seekBarProgress = 5;
					}
					else
						seekBarProgress = progress;

				}


				public void onStartTrackingTouch(SeekBar seekBar) {

				}

				public void onStopTrackingTouch(SeekBar seekBar) {
					power.setText(seekBarProgress + "%");


				}

			});
		}

		private void declareButtons() {
		//declaring main menu buttons
		Button auto=(Button) findViewById(R.id.btn_start);
		Button back=(Button) findViewById(R.id.btn_back_start);
		ImageButton settingsBtn =(ImageButton) findViewById(R.id.btn_start_settings);

		//declaring onclicklistener functions
		settingsBtn.setOnClickListener(this);


		auto.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {

		

				//starting corresponding intents
				if (arg0.getId()==R.id.btn_start){
					Values.power = seekBarProgress;
					sendStartCommunicationData();
					//declaring intents

					Intent int_stop=new Intent(StartActivity.this,StopActivity.class);
					startActivity(int_stop);
					this.finish();
				}
				
				if (arg0.getId()==R.id.btn_back_start){
					this.finish();
				}

		if (arg0.getId() == R.id.btn_start_settings) {
			Intent int_settings = new Intent(StartActivity.this,EnterPassActivity.class);
			startActivity(int_settings);
		}
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
				
	}
	
	public void sendStartCommunicationData(){
		try{

			if(LogCatEnabler.startStop)
				Log.e("TIRAX3",">>>>>>>>>>>>>>> START <<<<<<<<<<<<<<<<<");
			Mode op = Manager.getType();

			Compiler.setRegisters(op);

		}
		catch(Exception ex){
			Log.e("EXCEPTION TIRAX",ex+"");
		}
	}

	

}
