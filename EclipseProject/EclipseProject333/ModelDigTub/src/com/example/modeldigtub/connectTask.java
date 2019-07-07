package com.example.modeldigtub;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

import com.fro.util.FROIOControl;
import com.fro.util.StreamUtil;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class connectTask extends AsyncTask<String, Void, Void> {
		
	private Context context;
	private TextView info_tv;
	
	private boolean isok;

	byte[] date;
	public connectTask(Context context, TextView info_tv) {
		this.context=context;
		this.info_tv=info_tv;
		
	}
	
	
	@Override
	protected void onPreExecute() {
		info_tv.setText("正在发送命令......");
		
	}
	
	@Override
	protected Void doInBackground(String... params) {
	
		if(Constant.tubSocket!=null){
			try {
					StreamUtil.writeCommand(Constant.tubSocket.getOutputStream(),params[0]);
					date=StreamUtil.readData(Constant.tubSocket.getInputStream());
					isok=FROIOControl.isSuccess(Constant.NODE_LEN,Constant.NODE_NUM,date);
					publishProgress();
					Thread.sleep(200);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	
	@Override
	protected void onProgressUpdate(Void... values) {
		if(isok){
			info_tv.setText("操作成功");
		}else{
			info_tv.setText("操作失败");
		}
	}
	
	
	
	public void closeSocket(){
		if(Constant.tubSocket!=null){
			try {
				Constant.tubSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}



}
