package com.example.modeldigtub;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	private EditText ip_et;
	private EditText port_et;

	private TextView info_tv;
	private ToggleButton connect_tb;
	private Context context;
	private Button one;
	private Button two;
	private Button three;
	private Button four;

	private connectTask connect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bindView();
		initData();
		initEvent();
	}

	public Socket getSocket(String ip, int port) {
		final Socket socket = new Socket();
		final InetSocketAddress address = new InetSocketAddress(ip, port);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					socket.connect(address, 3000);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();

		if (!socket.isConnected()) {
			info_tv.setText("����ʧ��");
			return null;
		} else {
			return socket;
		}
	}

	private void initEvent() {
		connect_tb.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					String ip = ip_et.getText().toString().trim();
					String port = port_et.getText().toString().trim();
					if (checkIpPort(ip, port)) {
						Constant.IP = ip;
						Constant.port = Integer.parseInt(port);
					} else {
						info_tv.setText("ip�� port ���벻��ȷ������������......");
					}
					Constant.tubSocket = getSocket(Constant.IP, Constant.port);
					if (Constant.tubSocket != null) {
						info_tv.setText("���ӳɹ�......");
					}

				} else {
					if (Constant.tubSocket != null) {
						try {
							Constant.tubSocket.close();
							Thread.sleep(200);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					info_tv.setText("��������......");

					// if(connect!=null&&connect.getStatus()==AsyncTask.Status.RUNNING){
					// Constant.one=true;
					// try {
					// Thread.sleep(200);
					// } catch (InterruptedException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					//
					// connect.cancel(true);
					// connect.closeSocket();
					//
					// info_tv.setText("��������.......");
					// }
					// }
				}

			}
		});

		one.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Constant.tubSocket != null) {
					connect = new connectTask(context, info_tv);
					connect.execute(Constant.ONE_CMD);

				} else {
					info_tv.setText("���Ƚ������ӣ��ٲ���......");
				}

			}
		});

		two.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Constant.tubSocket != null) {
					connect = new connectTask(context, info_tv);
					connect.execute(Constant.TWO_CMD);

				} else {
					info_tv.setText("���Ƚ������ӣ��ٲ���......");
				}

			}
		});

		three.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Constant.tubSocket != null) {
					connect = new connectTask(context, info_tv);
					connect.execute(Constant.THREE_CMD);

				} else {
					info_tv.setText("���Ƚ������ӣ��ٲ���......");
				}

			}
		});

		four.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (Constant.tubSocket != null) {
					connect = new connectTask(context, info_tv);
					connect.execute(Constant.FOUR_CMD);

				} else {
					info_tv.setText("���Ƚ������ӣ��ٲ���......");
				}

			}
		});

	}

	private void initData() {
		info_tv.setText("��������......");
		ip_et.setText(Constant.IP);
		port_et.setText(String.valueOf(Constant.port));
		context = this;

	}

	private void bindView() {
		ip_et = (EditText) findViewById(R.id.ip_et);
		port_et = (EditText) findViewById(R.id.port_et);
		info_tv = (TextView) findViewById(R.id.info_et);

		connect_tb = (ToggleButton) findViewById(R.id.connect_tb);
		one = (Button) findViewById(R.id.one);
		two = (Button) findViewById(R.id.two);
		three = (Button) findViewById(R.id.three);
		four = (Button) findViewById(R.id.four);

	}

	/**
	 * IP��ַ���ö˿ں���֤�����ö˿ںţ�1024-65536��
	 * 
	 * @param IP
	 * @param port
	 * @return
	 */
	private boolean checkIpPort(String IP, String port) {
		boolean isIpAddress = false;
		boolean isPort = false;

		if (IP == null || IP.length() < 7 || IP.length() > 15 || "".equals(IP) || port == null || port.length() < 4
				|| port.length() > 5) {
			return false;
		}
		// �ж�IP��ʽ�ͷ�Χ
		String rexp = "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";

		Pattern pat = Pattern.compile(rexp);

		Matcher mat = pat.matcher(IP);

		isIpAddress = mat.find();

		// �ж϶˿�
		int portInt = Integer.parseInt(port);
		if (portInt > 1024 && portInt < 65536) {
			isPort = true;
		}

		return (isIpAddress && isPort);
	}

}