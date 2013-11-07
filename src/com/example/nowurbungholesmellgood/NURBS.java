package com.example.nowurbungholesmellgood;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;




public class NURBS extends Activity {
	private static final int REQUEST_ENABLE_BT=3;
	
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
BluetoothAdapter mBluetoothAdapter;

	public void initializeBluetooth(){
		// Initializes Bluetooth adapter.
		final BluetoothManager bluetoothManager =
		        (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
		mBluetoothAdapter = bluetoothManager.getAdapter();
		// displays a dialog requesting user permission to enable Bluetooth.
		if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
		    Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		    startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
		}
		if (mBluetoothAdapter.isEnabled()){
			DeviceScanActivity scanner = new DeviceScanActivity();
			scanner.scanLeDevice(true);
		}
	}
}
