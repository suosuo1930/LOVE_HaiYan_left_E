package com.example.activitylaunchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends Activity {
	/*
	 * 演示 Activity 的启动过程
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("shiwei","111111111111111111111");
		setContentView(R.layout.activity_main);
	}
	
	public void click(View view) {
		Intent intent=null;
		switch (view.getId()) {
		case R.id.one://启动自身
			intent=new Intent(MainActivity.this,MainActivity.class);
			break;
		case R.id.two://启动Two
			intent=new Intent(MainActivity.this,ActivityTwo.class);
			break;
		
		}
		startActivity(intent);

	}

}
