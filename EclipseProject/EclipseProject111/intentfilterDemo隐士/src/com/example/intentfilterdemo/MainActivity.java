package com.example.intentfilterdemo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
	}
	public  void click(View view ) {
	switch (view.getId()) {
	case R.id.one://点击按钮以隐藏启动的方式启动当前应用中的activity
		Intent intent=new Intent();
		intent.setAction("shiwei");
		//intent.addCategory("android.intent.category.DEFAULT");  默认的分类
		intent.setData(Uri.parse("shi://wei:8080/res"));
		intent.setDataAndType(Uri.parse("shi://wei:8080/res"), "text/*");
		
		startActivity(intent);
		break;

	case R.id.two:
		Intent intent2=new Intent();
		intent2.setAction("wei");
		startActivity(intent2);
		break;

	}

	}


}
