package com.example.practicelayout;

import com.example.practicelayout.util.SystemUiHider;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 * 
 * @see SystemUiHider
 */
public class MainActivity extends Activity implements OnClickListener{
	
	private static final boolean AUTO_HIDE = true;
	private static final int AUTO_HIDE_DELAY_MILLIS = 0;
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
	private SystemUiHider mSystemUiHider;

	Button levelSelect, settings, exitGame, achievements;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		initialize();
		final View controlsView = findViewById(R.id.fullscreen_content_controls);
		final View contentView = findViewById(R.id.fullscreen_content);

		mSystemUiHider = SystemUiHider.getInstance(this, contentView,
				HIDER_FLAGS);
		mSystemUiHider.setup();
		mSystemUiHider
				.setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
					int mControlsHeight;
					int mShortAnimTime;

					@Override
					@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
					public void onVisibilityChange(boolean visible) {
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
							if (mControlsHeight == 0) {
								mControlsHeight = controlsView.getHeight();
							}
							if (mShortAnimTime == 0) {
								mShortAnimTime = getResources().getInteger(
										android.R.integer.config_shortAnimTime);
							}
							controlsView
									.animate()
									.translationY(visible ? 0 : mControlsHeight)
									.setDuration(mShortAnimTime);
						} else {
							controlsView.setVisibility(visible ? View.VISIBLE
									: View.GONE);
						}

						if (visible && AUTO_HIDE) {
							delayedHide(AUTO_HIDE_DELAY_MILLIS);
						}
					}
				});
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		delayedHide(0);
	}

	View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
		@Override
		public boolean onTouch(View view, MotionEvent motionEvent) {
			if (AUTO_HIDE) {
				delayedHide(AUTO_HIDE_DELAY_MILLIS);
			}
			return false;
		}
	};

	Handler mHideHandler = new Handler();
	Runnable mHideRunnable = new Runnable() {
		@Override
		public void run() {
			mSystemUiHider.hide();
		}
	};


	private void delayedHide(int delayMillis) {
		mHideHandler.removeCallbacks(mHideRunnable);
		mHideHandler.postDelayed(mHideRunnable, delayMillis);
	}
	private void initialize(){
		levelSelect = (Button) findViewById(R.id.levelSelect);
		settings = (Button) findViewById(R.id.settings);
		achievements = (Button) findViewById(R.id.achievements);
		exitGame = (Button) findViewById(R.id.exitGame);
		levelSelect.setOnClickListener(this);
		settings.setOnClickListener(this);
		exitGame.setOnClickListener(this);
		achievements.setOnClickListener(this); 
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.levelSelect:
			try {
				Class ourClass = Class.forName("com.example.practicelayout.levelSelect");
				Intent ourIntent = new Intent(MainActivity.this, ourClass);
				startActivity(ourIntent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			break;
		case R.id.settings:
			try {
				Class ourClass = Class.forName("com.example.practicelayout.settings");
				Intent ourIntent = new Intent(MainActivity.this, ourClass);
				startActivity(ourIntent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			break;
		case R.id.achievements:
			break;
		case R.id.exitGame:
			break;

		}
	}
}
