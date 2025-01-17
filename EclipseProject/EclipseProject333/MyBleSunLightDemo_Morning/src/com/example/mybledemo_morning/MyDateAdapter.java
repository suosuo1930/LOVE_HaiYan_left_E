package com.example.mybledemo_morning;

import java.util.ArrayList;
import java.util.List;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyDateAdapter extends BaseAdapter {

	private List<BluetoothDevice> list = new ArrayList<BluetoothDevice>();
	private LayoutInflater inflater;
	private Context context2;

	public MyDateAdapter(Context context) {
		this.context2 = context;
		inflater = LayoutInflater.from(context2);
	}
	public BluetoothDevice getDevice(int position){
			return list.get(position);
	}
	

	@Override
	public int getCount() {
		return list.size();
	}

	public void addDevice(BluetoothDevice device) {
		if (!list.contains(device)) {
			list.add(device);
		}
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View View, ViewGroup parent) {
		ViewHolder holder;
		if(View==null){
			holder=new ViewHolder();
			View=inflater.inflate(R.layout.item,null);
		    holder.devideName=(TextView) View.findViewById(R.id.device_name);
		    holder.devideAddress=(TextView) View.findViewById(R.id.device_address);
		    View.setTag(holder);
		}else{
			holder=(ViewHolder) View.getTag();
		}
		BluetoothDevice device=list.get(position);
		String name=device.getName();
		if(name==null){
			holder.devideName.setText("δ֪�豸");
		}else{
			holder.devideName.setText(name);
		}
		holder.devideAddress.setText(device.getAddress());
		return View;
	}

	final class ViewHolder{
		TextView  devideName;
		TextView devideAddress;
	}

	public void clear() {
		list.clear();
		
	}

}
