package com.example.demo3333;

import java.util.ArrayList;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class mBleDevicelistAdapter extends BaseAdapter {

	private ArrayList<BluetoothDevice> mLeDevices;
	private LayoutInflater mInflator;
	private Activity mContext;

	public mBleDevicelistAdapter(Activity c) {
		super();
		mContext = c;
		mLeDevices = new ArrayList<BluetoothDevice>();
		mInflator = mContext.getLayoutInflater();

	}

	@Override
	public int getCount() {
		return mLeDevices.size();
	}

	@Override
	public Object getItem(int position) {
		return mLeDevices.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void clear() {
		mLeDevices.clear();

	}

	public void addDevice(BluetoothDevice device) {
		if (!mLeDevices.contains(device)) {
			mLeDevices.add(device);
		}

	}

	public BluetoothDevice getDevice(int position) {
		return mLeDevices.get(position);
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder viewHolder;
		if (view == null) {
			view = mInflator.inflate(R.layout.itemdevice, null);
			viewHolder = new ViewHolder();
			viewHolder.deviceAddress = (TextView) view
					.findViewById(R.id.address);
			viewHolder.deviceName = (TextView) view.findViewById(R.id.name);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		// 对应的设备进行处理
		BluetoothDevice device = mLeDevices.get(position);
		final String deviceName = device.getName();
		if (deviceName != null && deviceName.length() > 0) {
			viewHolder.deviceName.setText(deviceName);
		} else {
			viewHolder.deviceAddress.setText("未知设备");
		}
		viewHolder.deviceAddress.setText(device.getAddress());

		return view;
	}

	public class ViewHolder {
		TextView deviceName;
		TextView deviceAddress;

	}

}
