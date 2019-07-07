package com.example.listviewheaderandfooter;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
	/*
	 * 演示Listview 的常用方法
	 */
public class MainActivity extends Activity {
	private ListView view;
	private List<String> list;
  private ArrayAdapter<String> adapter;
  private TextView text;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view=(ListView) findViewById(R.id.view);
		text=(TextView) findViewById(R.id.text);
		list=new ArrayList<String>();
		for(int i=0;i<20;i++){
			list.add("item"+i);
		}
		
		adapter=new  ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list);
		ImageView iv=new ImageView(MainActivity.this);
		iv.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT));
		//iv.setImageResource(R.drawable.shi);..0000000000
		//注意事项： addHeaderView() 方法必须在 setAdapterS() 方法执行之前设置
		//view.addHeaderView(iv);//将参数中指定的控件VIEW添加到listview 的头部
		Button button=new Button(MainActivity.this);
		button.setText("加载更多！");
		button.setTextSize(20);
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int index=list.size();
				for(int i=index;i<index+20;i++){
					list.add("item"+i);
				}
				adapter.notifyDataSetChanged();
			}
		});
		view.addFooterView(button);//在 listview 的尾部添加指定的 view 视图之 Button 按钮。
		
		view.setAdapter(adapter);
		view.setEmptyView(text);//表示如果listview 中没有 显示的数据时 显示参数指定的view 视图
	}

	
	


}
