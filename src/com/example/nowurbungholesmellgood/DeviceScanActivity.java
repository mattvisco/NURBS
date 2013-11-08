package com.example.nowurbungholesmellgood;

import android.app.Activity;
import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.os.Handler;
import android.util.Log;

/**
 * Activity for scanning and displaying available BLE devices.
 */
public class DeviceScanActivity extends ListActivity {
	NURBS master;
    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;


    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;
    
    
    /**
     * If you want to scan for only specific types of peripherals, 
     * you can instead call startLeScan(UUID[], BluetoothAdapter.LeScanCallback), 
     * providing an array of UUID objects that specify the GATT services your app supports.
     * @param enable
     */
   public void scanLeDevice(final boolean enable, BluetoothManager bluetoothManager, NURBS master) {
	   this.master=master;
	   mHandler = new Handler();
	   mBluetoothAdapter =  bluetoothManager.getAdapter();
	   if (mBluetoothAdapter==null){
		   Log.i("debuggin","bluetooth adapter null");
	   }
	   Log.i("debugging", "in scanLeDevice");
	 
	   
        if (enable) {
            // Stops scanning after a pre-defined scan period.
        	
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    mBluetoothAdapter.startLeScan(mLeScanCallback);                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
	   }
    
    
  
    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(final BluetoothDevice device, int rssi,
                byte[] scanRecord) {
        	Log.i("debugging", "in onLeScan");
            runOnUiThread(new Runnable() {
               @Override
               public void run() {
            	   Log.i("debugging", "added device " + device.getName() + " with UUID " + device.getUuids());
            	   master.setBluetoothDevice(device);
               }
           });
       }
    };

}