package com.example.nowurbungholesmellgood;

import java.util.UUID;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

/**
 * Activity for scanning and displaying available BLE devices.
 */
public class DeviceScanActivity extends ListActivity {

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
   public void scanLeDevice(final boolean enable, BluetoothManager bluetoothManager) {
	   mHandler = new Handler();
	   mBluetoothAdapter =  bluetoothManager.getAdapter();
	   Log.i("debugging", "in scanLeDevice");
        if (enable) {
            // Stops scanning after a pre-defined scan period.
        	
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                	Log.i("debugging", "in run");
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    Log.i("debugging", "end of run");
                }
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
            runOnUiThread(new Runnable() {
               @Override
               public void run() {
            	   Log.i("debugging", "added device " + device.getName() + " with UUID " + device.getUuids());
               }
           });
       }
    };

}