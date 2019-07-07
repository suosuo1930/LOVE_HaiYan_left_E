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
	 * ��ʾListview �ĳ��÷���
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
		//ע����� addHeaderView() ���������� setAdapterS() ����ִ��֮ǰ����
		//view.addHeaderView(iv);//��������ָ���Ŀؼ�VIEW���ӵ�listview ��ͷ��
		Button button=new Button(MainActivity.this);
		button.setText("���ظ��࣡");
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
		view.addFooterView(button);//�� listview ��β������ָ���� view ��ͼ֮ Button ��ť��
		
		view.setAdapter(adapter);
		view.setEmptyView(text);//��ʾ���listview ��û�� ��ʾ������ʱ ��ʾ����ָ����view ��ͼ
	}

	
	


}