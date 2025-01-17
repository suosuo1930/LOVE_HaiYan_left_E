package com.example.threadblock;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	TextView text;
	private int count=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		text=(TextView) findViewById(R.id.text1);
		//违反了UI 线程的第一条的规定， 主线程阻塞,故要开启工作线程
//		while (true) {
//			count++;
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			text.setText(count+"");
//			
//		}
		
		//非 UI 线程不能操作  UI 工具包。
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				count++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				text.setText(count+"");
				
			}
		}).start();
	}


}
