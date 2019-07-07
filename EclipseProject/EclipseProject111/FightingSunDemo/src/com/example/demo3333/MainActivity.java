package com.example.demo3333;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.example.demo3333.BluetoothLeClass.OnDataAvailableListener;
import com.example.demo3333.BluetoothLeClass.OnServiceDiscoverListener;
import com.fro.util.FROSun;
import com.fro.util.HexStrConvertUtil;

@SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
public class MainActivity extends Activity {
	public static String TAG = "MainActivity";

	private final Timer timer = new Timer();
	private TimerTask task;
	private Handler handler;

	private static final String UUID_SERVICE = "0000fff0-0000-1000-8000-00805f9b34fb";
	private static final String UUID_CHAR6 = "0000fff6-0000-1000-8000-00805f9b34fb";
	private static final String UUID_DESC = "00002902-0000-1000-8000-00805f9b34fb";

	private ToggleButton button;
	private ListView listDevice;
	private TextView error_tv;
	private TextView sun_tv;

	// BLE �豸
	private BluetoothLeClass mBLE;
	// ���յ��ֶ�
	private StringBuffer str_sb;
	// �����б�������
	private mBleDevicelistAdapter mbledevicelistAdapter;
	// ���� Gatt ����
	private BluetoothGattService mService;
	// ���� GATT����������
	private BluetoothGattCharacteristic gattCharacteristic_char6;
	// �Ƿ�����д����
	private boolean isReadTaskRun;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bindView();
		initData();
		initEvent();

		mBLE = new BluetoothLeClass(this);
		if (mBLE.initialize()) {
			error_tv.setText("��ʼ���ɹ�");
		} else {
			sun_tv.setText("��ʼ��ʧ��");
		}
		
		
		// ����BLE�ն˵�Serviceʱ�ص�
		mBLE.setOnServiceDiscoverListener(mOnServiceDiscover);
		// �յ�BLE�ն����ݽ������¼�
		mBLE.setOnDataAvailableListener(mOnDataAvailable);

	}

	private void bindView() {
		button = (ToggleButton) findViewById(R.id.button);
		listDevice = (ListView) findViewById(R.id.list);
		error_tv = (TextView) findViewById(R.id.error_tv);
		sun_tv = (TextView) findViewById(R.id.sun_tv);

	}

	private void initData() {
		str_sb = new StringBuffer("");
		mbledevicelistAdapter = new mBleDevicelistAdapter(this);
		listDevice.setAdapter(mbledevicelistAdapter);

	}

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	private void initEvent() {
		handler = new Handler() {

			@Override
			public void handleMessage(Message msg) {
				if (msg.what == 1) {
					if (isReadTaskRun) {
						ReadBle();
					}
				}

			}
		};

		task = new TimerTask() {

			@Override
			public void run() {
				Message message = new Message();
				message.what = 1;
				handler.sendMessage(message);

			}
		};
		// ��ʼ��ʱ��
		timer.schedule(task, 100, 1000);

		button.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@SuppressLint("NewApi")
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					mbledevicelistAdapter.clear();
					// ��ʼɨ��
					mBLE.getmBluetoothAdapter().startLeScan(mLeScanCallback);
					   error_tv.setText("����ɨ��BLE �ն�....");
				} else {
					mBLE.getmBluetoothAdapter().stopLeScan(mLeScanCallback);
					   error_tv.setText("��ֹͣɨ��BLE �ն�..");
				}

			}
		});

		// Item ���
		listDevice.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				button.setChecked(false);
				mBLE.getmBluetoothAdapter().stopLeScan(mLeScanCallback);
				final BluetoothDevice device = mbledevicelistAdapter.getDevice(position);
						
				if (device == null)
					return;

				isReadTaskRun = !isReadTaskRun;
				if (isReadTaskRun) {
					// ����Gatt ����
					error_tv.setText("�������� BEL�ն�...");
					if (!mBLE.connect(device.getAddress())) {
						error_tv.setText("����ʧ�ܣ�");
					} else {
						mBLE.disconnect();
						error_tv.setText("�Ͽ�����");
					}

				}

			}

		});

	}

	/*
	 * ɨ�赽�豸Deviceʱ�Ļص��¼�
	 */
	private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {

		@Override
		public void onLeScan(final BluetoothDevice device, int rssi,
				byte[] scanRecord) {
			runOnUiThread(new Runnable() {

				@Override
				public void run() {
					mbledevicelistAdapter.addDevice(device);
					mbledevicelistAdapter.notifyDataSetChanged();
				}
			});
		}
	};

	/*
	 * ������ BLE �ն˷���ļ����¼�
	 */
	private BluetoothLeClass.OnServiceDiscoverListener mOnServiceDiscover = new OnServiceDiscoverListener() {

		@Override
		public void onServiceDiscover(BluetoothGatt gatt) {
			mService = gatt.getService(UUID.fromString(UUID_SERVICE));
			if (mService != null) {
				// UUID_CHAR6�ǿ��Ը�����ģ�鴮��ͨ�ŵ�Characteristic
				gattCharacteristic_char6 = mService.getCharacteristic(UUID
						.fromString(UUID_CHAR6));
				if (gattCharacteristic_char6 != null) {
					// �����豸��֪ͨ
					mBLE.setCharacteristicNotification(
							gattCharacteristic_char6, true);
					// ���½�����������ʾ
					runOnUiThread(new Runnable() {
						public void run() {
							error_tv.setText("���ӳɹ���");
						}
					});
				} else {
					Log.i(TAG, "mCharacteristic=null");
				}
			} else {
				Log.i(TAG, "mService=null");
				runOnUiThread(new Runnable() {
					public void run() {
						error_tv.setText("�豸ʧ�ܣ�");
					}
				});
			}
		}

	};

	/*
	 * �յ�BLE�ն����ݽ����ļ����¼�
	 */
	private BluetoothLeClass.OnDataAvailableListener mOnDataAvailable = new OnDataAvailableListener() {

		/*
		 * �յ� BLE�ն����ݱ������¼�
		 */
		@Override
		public void onCharacteristicRead(BluetoothGatt gatt,
				BluetoothGattCharacteristic characteristic, int status) {
			Log.i(TAG,
					"onCharRead"
							+ gatt.getDevice().getName()
							+ "read"
							+ characteristic.getUuid().toString()
							+ "--->"
							+ HexStrConvertUtil.bytesToHexString(characteristic
									.getValue()));

		}

		/*
		 * �յ�BLE �ն� д�����ݻص�
		 */
		@Override
		public void onCharacteristicWrite(BluetoothGatt gatt,
				BluetoothGattCharacteristic characteristic) {
			Log.i(TAG, "characteristic.getUuid().toString()="
					+ characteristic.getUuid().toString());
			if (characteristic.getUuid().toString().equals(UUID_CHAR6)) {
				// ��������
				Float fData = FROSun.getData(Const.SUN_LEN, Const.NODE_NUM,
						characteristic.getValue());
				String value = String.valueOf((int) (float) fData + "Lux");

				Log.i(TAG, "onChatWrite" + gatt.getDevice().getName()
						+ "  value" + value);
				// ׷�ӵ������ֶ�
				str_sb.replace(0, str_sb.length(), value);
				// ���½�����������ʾ
				runOnUiThread(new Runnable() {

					@Override
					public void run() {
						updateDisplaySendInfo();

					}

				});

			}

		}
	};

	/*
	 * ���½�����ʾ��
	 */
	private void updateDisplaySendInfo() {
		sun_tv.setText(str_sb.toString());
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		timer.cancel();
		mBLE.getmBluetoothAdapter().stopLeScan(mLeScanCallback);
		mBLE.disconnect();
		mBLE.close();

	}

	/*
	 * ��ȡBLE ������
	 */

	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
	private void ReadBle() {
		if (mBLE.getmBluetoothGatt() != null
				&& gattCharacteristic_char6 != null) {
			// ��ȡ��������
			String writeStr = Const.SUN_CMD;
			// ������������
			gattCharacteristic_char6.setValue(HexStrConvertUtil
					.hexStringToByte(writeStr));
			// ������ģ��д������
			mBLE.getmBluetoothGatt().writeCharacteristic(
					gattCharacteristic_char6);
			Log.i(TAG, "��ȡ����....");
		} else {
			Log.i(TAG,
					"mBLE.getBluetoothGatt()=null  or  gattCharacteristic_char6=null");
		}

	}

}