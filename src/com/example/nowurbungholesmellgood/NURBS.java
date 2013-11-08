package com.example.nowurbungholesmellgood;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.nurbs.R;




public class NURBS extends Activity {
	private static final int REQUEST_ENABLE_BT=3;
	BluetoothDevice device;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nurbs);
		initializeBluetooth();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nurb, menu);
		return true;
	}
	
	public void setBluetoothDevice(BluetoothDevice dev){
		this.device = dev;
	}
BluetoothAdapter mBluetoothAdapter;

	public void initializeBluetooth(){
		// Initializes Bluetooth adapter.
		final BluetoothManager bluetoothManager =
		        (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();
		
		
		// displays a dialog requesting user permission to enable Bluetooth.
		if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
			Log.i("debugging", "something is not right");
		    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
		if (mBluetoothAdapter == null){
			Log.i("debugging", "bluetooth adapter is null");
		}
		else if (mBluetoothAdapter.isEnabled()){
			Log.i("debugging", "calling scanner");
			DeviceScanActivity scanner = new DeviceScanActivity();
		
			scanner.scanLeDevice(true, bluetoothManager, this);
		}
	}
}
