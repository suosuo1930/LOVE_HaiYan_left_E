package com.example.activitylaunchmode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


public class ActivityTwo extends Activity {
	/*
	 * ��ʾ Activity ����������
	 */
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		super.onCreate(savedInstanceState);
		Log.v("shiwei","222222222222222222");
		setContentView(R.layout.two);
	}
	
	
	public void click(View view) {
		Intent intent=null;
		switch (view.getId()) {
		case R.id.one2://��������
			intent=new Intent(ActivityTwo.this,ActivityTwo.class);
			
			break;
		case R.id.two2://����Two
			intent=new Intent(ActivityTwo.this,MainActivity.class);
			break;
		
		}
		startActivity(intent);

	}

}