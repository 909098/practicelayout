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
import android.widget.CheckBox;

public class levelSelect extends Activity implements OnClickListener{
	
	private static final boolean AUTO_HIDE = true;
	private static final int AUTO_HIDE_DELAY_MILLIS = 0;
	private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
	private SystemUiHider mSystemUiHider;
	public static int level;
	Button backToMenuLevelSelect, level1, level2, level3, level4, level5;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.levelselect);
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
		backToMenuLevelSelect = (Button) findViewById(R.id.backToMenuLevelSelect);
		backToMenuLevelSelect.setOnClickListener(this);
		level1 = (Button) findViewById(R.id.level1);
		level1.setOnClickListener(this);
		level2 = (Button) findViewById(R.id.level2);
		level2.setOnClickListener(this);
		level3 = (Button) findViewById(R.id.level3);
		level3.setOnClickListener(this);
		level4 = (Button) findViewById(R.id.level4);
		level4.setOnClickListener(this);
		level5 = (Button) findViewById(R.id.level5);
		level5.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.level1:
			try {
				level = 1;
				Class ourClass = Class.forName("com.example.practicelayout.Game");
				Intent ourIntent = new Intent(levelSelect.this, ourClass);
				startActivity(ourIntent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			break;
		case R.id.level2:
			try {
				level = 2;
				Class ourClass = Class.forName("com.example.practicelayout.Game");
				Intent ourIntent = new Intent(levelSelect.this, ourClass);
				startActivity(ourIntent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			break;
		case R.id.level3:
			try {
				level = 3;
				Class ourClass = Class.forName("com.example.practicelayout.Game");
				Intent ourIntent = new Intent(levelSelect.this, ourClass);
				startActivity(ourIntent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			break;
		case R.id.level4:
			try {
				level = 4;
				Class ourClass = Class.forName("com.example.practicelayout.Game");
				Intent ourIntent = new Intent(levelSelect.this, ourClass);
				startActivity(ourIntent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			break;
		case R.id.level5:
			try {
				level = 5;
				Class ourClass = Class.forName("com.example.practicelayout.Game");
				Intent ourIntent = new Intent(levelSelect.this, ourClass);
				startActivity(ourIntent);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			break;
		case R.id.backToMenuLevelSelect:
			try {
				Class ourClass = Class.forName("com.example.practicelayout.MainActivity");
				Intent ourIntent = new Intent(levelSelect.this, ourClass);
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
