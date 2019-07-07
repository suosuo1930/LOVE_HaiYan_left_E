package com.example.downloadimageprogress;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ImageView view;
	private String imagePath="";
	
	/*
	 * 显示下载进度
	 * 
	 */

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		view=(ImageView) findViewById(R.id.view);
	}
	//点击下载图片并且显示
public  void click(View view) {
	new MyAsyncTask().execute(imagePath);

}

class MyAsyncTask  extends AsyncTask<String ,Integer,byte[]>{
	private ProgressDialog pd;
	
	
	@Override
	protected void onPreExecute() {
		pd=new ProgressDialog(MainActivity.this);// 构建Dialog
		pd.setTitle("进度提示");//设置标题
		pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置样式
		pd.show();
	}
	
@Override
protected byte[] doInBackground(String... params) {
	ByteArrayOutputStream outputStream=new ByteArrayOutputStream();//先准备好一个字节数组输出流
	try {
		URL url=new URL(params[0]);//把路径传递过来
		HttpURLConnection conn=(HttpURLConnection) url.openConnection();//获得网络连接
		conn.setDoInput(true);//允许从从网络上读取
		conn.setRequestMethod("GET");  //设置请求当时为 get
		conn.connect();//设置打开连接
		
		int responseCode=conn.getResponseCode();//获得响应码
		if(responseCode==200){//如果响应码为200，则说明现在响应成功！
			InputStream in=conn.getInputStream();//成功之后，则开始获得输入流。
			//获取文件的下载图片的总长度
			long totallLength=conn.getContentLength();//专门的方法获取长度
			int currentLength=0;//表示当前下载的图片的文件长度
		
			byte [] date=new byte[1024];//声明一个字节数组，用于接收数据。
			int temp=0;
			while ((temp=in.read(date))!=-1) {//一个 While 循环开始相应的读取数据
				currentLength+=temp;//将每次循环读取的内容的长度添加到进度长度变量中，用于表示更新
				//开始计算，获得进度，更具文件的长度，与下载进度
				int progress=(int)((currentLength/(float)totallLength)*100);
				
				publishProgress(progress);//将进度发布到主线程中
				
				outputStream.write(date, 0,temp);//向字节数组输出流中写入字节数据
				outputStream.flush();//清空缓冲区
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
	
	return outputStream.toByteArray();
	
	
}
/*
 * 表示运行在住线程中，用来更行进度的回调方法。
 */

@Override
protected void onProgressUpdate(Integer... values) {
	Log.v("shiwei",Thread.currentThread().getName()+"---------onProgressUpdate------->");
	pd.setProgress(values[0]);//设置更新进度
	
	
}


@Override
protected void onPostExecute(byte[] result) {
	if(result!=null&&result.length!=0){//对结果进行判断，
		//将字节数组数据转化为   Bitmap  对象 
		Bitmap bm=BitmapFactory.decodeByteArray(result, 0, result.length);
		view.setImageBitmap(bm);
		
	}else{
		Toast.makeText(MainActivity.this,"图片下载失败！",Toast.LENGTH_SHORT).show();
	}
	
	pd.dismiss();//图片下载完之后，要把 Dialog 关闭， 不然会一直出现。
}



}
}






