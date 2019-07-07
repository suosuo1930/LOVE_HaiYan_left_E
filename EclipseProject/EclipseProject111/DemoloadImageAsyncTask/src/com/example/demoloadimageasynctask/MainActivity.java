package com.example.demoloadimageasynctask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	/*
	 * �������ͼƬ
	 * ʹ���첽����
	 */
	private ImageView show;
	private String imagePath="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		show=(ImageView) findViewById(R.id.image);
		
		
	}
	
	public  void click(View view ) {
		//�����첽����
		new MyAsyncTask().execute(imagePath);	
	
		
	}
	
	/*
	 * ��������
	 * Params: ��ʾ��ǰ���첽����
	 * Progress: ��ʾ��ǰ�첽�����ʱ����ʱ�Ľ�������
	 * Result: ��ʾ ��ǰAsyncTask ��ʱ�����������������
	 */
	class MyAsyncTask extends AsyncTask<String,Void,byte[]>{
		
		@Override
		protected void onPreExecute() {//��ʾ���첽����֮ǰ��׼���������ǻص�������
        Log.v("shi",Thread.currentThread().getName()+"  ---------onpreExecute--------");			
		}
		/*
		 * ��ʾ��onPreExecute() ����ִ�н���֮������ִ�С�
		 * �÷��������ڹ����߳��У���Ҫִ�к�ʱ������
		 * �÷����Ĳ��������з��� �ĵ�һ������һ��
		 * ����ֵһ������������ͬ��
		 */
		@Override
		protected byte[] doInBackground(String... params) {
			 Log.v("shi",Thread.currentThread().getName()+"  ---------doInBackground--------");	
			ByteArrayOutputStream  out=new ByteArrayOutputStream();
			byte[] images=null;
			try {
				URL url=new URL(params[0]);
				HttpsURLConnection conn= (HttpsURLConnection) url.openConnection();
				conn.setDoInput(true);
				conn.setRequestMethod("GET");
				conn.connect();
				int responseCode=conn.getResponseCode();
				if(responseCode==200){
					InputStream in=conn.getInputStream();
					byte[] date=new byte[1024];
					int temp=0;
					while ((temp=in.read(date))!=-1) {
					out.write(date,0,temp);
						
					}
				}
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			images=out.toByteArray();
			return images;
			
		}
		
		/*
		 * �� doinBackgroung() ����ִ�еĽ�����ظ��÷�����
		 */
		@Override
		protected void onPostExecute(byte[] result) {
	    Log.v("shi",Thread.currentThread().getName()+"  ---------onPostExecute--------");	
		if(result!=null&&result.length!=0){
			Bitmap bm=BitmapFactory.decodeByteArray(result,0,result.length);
			show.setImageBitmap(bm);
		}else{
			Toast.makeText(MainActivity.this,"ͼƬ����ʧ�� ��",Toast.LENGTH_SHORT).show();
		}
			super.onPostExecute(result);
			
		}
		
		
	}


}























