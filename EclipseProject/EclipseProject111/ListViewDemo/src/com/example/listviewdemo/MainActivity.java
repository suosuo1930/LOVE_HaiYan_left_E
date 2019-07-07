package com.example.listviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
     /*
	 *演示 ListView 的基本使用。 
	 */
public class MainActivity extends Activity {
	
	private  ListView view;
	private ArrayAdapter<String> adapter;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view=(ListView) findViewById(R.id.list); 
		//1.准备数据源,且还要是 最终类型的、
		 final String [] city=getResources().getStringArray(R.array.city);
		//2.将数据源的数据加载到适配器中
		adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,city);
		
		//3.将适配器中的数据加载到 控件中。
		view.setAdapter(adapter);
		
		
		//表示当 listview 控件中 每项 item ***被点击****的监听事件
		view.setOnItemClickListener(new OnItemClickListener() {
			//表示当 Item 被点击时的回调方法。
			/*
			 * 第一个参数表示这个 适配器控件，就是这个 listview
			 * 第二个参数表示每一项的 View 的对象。
			 * 第三个参数表示当前项的 下标
			 * 第四个参数表示当前项的 ID 
			 */
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//1.从数据源中获取
				String  s1=city[arg2];
				//2.从适配器中获取
				String s2=adapter.getItem(arg2);
				//3.在 parent 中获取
				String s3=arg0.getItemAtPosition(arg2).toString();
				//4.在listview 控件中获取，
				String s4=view.getItemAtPosition(arg2).toString();
				Toast.makeText(MainActivity.this,"s1="+s1+"s2="+s2+"s3="+s3+"s4="+s4,Toast.LENGTH_SHORT).show();
			}
			
		});
		
		//表示 当 列表 中的某一项被 长时间（2秒左右）的按着的时被触发的监听事件
		view.setOnItemLongClickListener(new OnItemLongClickListener() {
             /*
              * 表示 当列表项被长按时要触发的方法(non-Javadoc)
              * Listview 即绑定了单击事件又绑定了 长按事件
              * 返回值表示 ：若返回false 表示对事件不处理(长按时对长按事件和单击事件都会触发)
              *             若返回true 表示对事件处理  (长按时只对长按事件进行触发)
              */
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Toast.makeText(MainActivity.this,"长按事件",Toast.LENGTH_SHORT).show();
				
				return true;
			}
		});
		
	}


}
