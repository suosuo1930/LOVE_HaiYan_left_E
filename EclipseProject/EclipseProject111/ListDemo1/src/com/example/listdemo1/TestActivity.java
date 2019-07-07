package com.example.listdemo1;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class TestActivity extends ListActivity {
	
	 String[] str={"上海","北京","广州"};
	 TextView text;
	 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test);
		text=(TextView) findViewById(R.id.textView1);
		
		
	
	 ArrayAdapter<String> adapter=new ArrayAdapter<String>
	 (this,R.layout.demo2, R.id.textView1,str);
		setListAdapter(adapter);		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String s=str[position];
		Log.v("shiwei","-------------->"+s);
		text.setText(s);
		super.onListItemClick(l, v, position, id);
	}
}
