package main.activity;



import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.Tirax.RF.*;
import com.Tirax.RF.Compiler;
import com.Tirax.RF.Enums.SecurityType;
import com.Tirax.RF.SerialPortsHardware.DataProvider;
import com.Tirax.RF.SharedPrefrences;
import com.Tirax.RF.Storage.Values;
import com.example.cryo.*;
import com.friendlyarm.AndroidSDK.HardwareControler;

public class StopActivity extends MyActivity implements OnClickListener {


	private Handler timerHandler = new Handler();
	private Handler UIHandler = new Handler();
	public boolean firstUI=false;

	public  int time=10;
	public static int firstTime;

	private TextView power;
	private TextView timetext;
	private TextView vacuum;

	private int backpressed;
	public boolean finished=true;
	private boolean mute = false;
	private boolean pause = false;
	public int PedalWasActive=0;
	private ImageButton stopButton;

	private Handler repeatUpdateHandler = new Handler();
	public int powerValue;
	public int seekBarProgress = 0;

	private Mode op;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stop);
		initialTextViews();
		finished=false;
		firstTime=time;
		backpressed=0;
		//declaring main menu buttons
		setButtons();

		initialSeekBar();

		timerHandler.postDelayed(TimerRunnable, 0);
		UIHandler.postDelayed(UIreportsRunnable, 0);


	}

	private void initialSeekBar() {
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBar.setProgress(powerValue);
		seekBarProgress = powerValue;

		seekBar.setOnTouchListener(new View.OnTouchListener() {
			@Override
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (PedalWasActive > 0)
					return true;
				else
					return false;
			}
		});
		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {


			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (progress < 5) {
					seekBar.setProgress(5);
					seekBarProgress = 5;
				} else
					seekBarProgress = progress;
				power.setText(seekBarProgress + "%");


			}


			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				power.setText(seekBarProgress + "%");
				DataProvider.setRegister(DataProvider.RPWR, (char) (seekBarProgress * op.powerMultiplyer));

			}

		});
	}

	private void setButtons() {
		stopButton =(ImageButton) findViewById(R.id.btn_stop);
		stopButton.setOnClickListener(this);




		ImageButton settingsBtn =(ImageButton) findViewById(R.id.btn_stop_settings);
		settingsBtn.setOnClickListener(this);
	}


	private void initialTextViews() {
		//read temp datas and show
		power = (TextView) findViewById(R.id.txt_stop_power);
		timetext = (TextView) findViewById(R.id.txt_time_stop);


		op = Manager.getType();

		power.setText(op.power + "%");

		powerValue=op.power;



		time = 0;


	}


	@Override
	public void onClick(View arg0) {
		//starting corresponding intents
		if(PedalWasActive<=0) {
			if (arg0.getId() == R.id.btn_stop) {
				finishFunction();
			}

			if (arg0.getId() == R.id.btn_stop_settings) {
				Intent int_settings = new Intent(StopActivity.this, EnterPassActivity.class);
				startActivity(int_settings);
			}
			overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		}

	}

	private static Integer tryParse(String text) {
		try {
			return Integer.parseInt(text);
		} catch (NumberFormatException e) {
			return 0;
		}
	}


	private void finishFunction() {

		if(LogCatEnabler.startStop)
			Log.e("TIRAX3",">>>>>>>>>>>>>>> STOP <<<<<<<<<<<<<<<<<");

		finished=true;
		DataProvider.setRegister(DataProvider.RPWR, (char) 0);

		try {

			SecurityFile.save(SecurityType.TIME, ( tryParse(SecurityFile.load(SecurityType.TIME))+ time)+"");
		} catch (Exception e) {

			//TODO get a big error
		}

		this.finish();
	}

	 @Override
	 public void onBackPressed() {
		 backpressed++;
		 if(backpressed>3)
		 {
			 Intent int_temps=new Intent(StopActivity.this,ShowTimes.class);
			 startActivity(int_temps);
			 backpressed=0;
			 finished=true;
			 this.finish();
		 }
	 }

	//Stop When Timer Finished
	Runnable TimerRunnable = new Runnable() {

        @Override
        public void run() {

//			SharedPrefrences.setTime(time);
			timetext = (TextView) findViewById(R.id.txt_time_stop);
        	timetext.setText(time+"'");
			if(!pause)
        		time++;
        	if(!finished)
        		timerHandler.postDelayed(TimerRunnable, 60000);
        }
    };

	private void playEndMusic() {
		if(!mute && !finished) {
			for (int i = 0; i < 2; i++) {
				HardwareControler.PWMPlay(2000);
				android.os.SystemClock.sleep(1000);
				HardwareControler.PWMStop();

			}
		}

	}



	private int inrunnable=0;
	Runnable UIreportsRunnable = new Runnable() {

		@Override
		public void run() {


/*
			HardwareControler.PWMStop();
			inrunnable++;


			if(inrunnable>60 && (DataProvider.getRegister(DataProvider.RCRN)>135 ||
					DataProvider.getRegister((char)(DataProvider.RCRN+1))>128))
			{
				if(!mute) {
					HardwareControler.PWMPlay(2000);
					inrunnable = 0;
				}

			}*/

			//Log.e("TIRAX","pause activated Values value: "+(int)DataProvider.getRegister(DataProvider.RMKY_PAUSE));
			if(DataProvider.getBit(DataProvider.RMKY, DataProvider.RMKY_PAUSE)) {

				if (Values.PAUSE == 0) {
					if (pause == false) {

						stopButton.setBackgroundResource(R.drawable.pause);
						pause = true;
						if(LogCatEnabler.startStop)
							Log.e("TIRAX3",">>>>>>>>>>>>>>> PAUSE <<<<<<<<<<<<<<<<<");
						DataProvider.setRegister(DataProvider.RTYP0, (char) 0);


					}else {

							stopButton.setBackgroundResource(R.drawable.stopbut);
							pause = false;
							Compiler.setRTYPRegister(op);

					}
				}
				Values.PAUSE=1;

			}else{
				Values.PAUSE=0;
			}
			ImageButton powerRange = (ImageButton)findViewById(R.id.btn_stop_power_range);
			if(DataProvider.getPedalisActive()) {
				powerRange.getLayoutParams().height = (10 - (int)Math.ceil((double)(seekBarProgress * op.powerMultiplyer) / 10.0)) * 20;
			}
			else
				powerRange.getLayoutParams().height = (10)*20;

			if(DataProvider.getPedalisActive()){
				PedalWasActive=5;

			}
			else{
				PedalWasActive--;
			}
			timetext.setText((time-1)+"'");
			if(!finished)
				UIHandler.postDelayed(UIreportsRunnable, 100);
		}

	};




}
