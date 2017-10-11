package mdluex.smartx;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import mdluex.smartx.R;

public class intro_main extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter=BluetoothAdapter.getDefaultAdapter();
    private int int_tost = 0;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int bluetoothState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR);
                switch (bluetoothState) {
                    case BluetoothAdapter.STATE_ON:
                        //Bluethooth is on, now you can perform your tasks
                        int_tost = 1;
                        final Handler handler = new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                bton();
                            }
                        }, 2000);
                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_main);

        //Set a filter to only receive bluetooth state changed events.
        IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mReceiver, filter);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                btck();
            }
        }, 1000);

        tost();

    }

    public void btck(){
        if (bluetoothAdapter == null) {
            Toast.makeText(getApplicationContext(),"Device doesnt Support Bluetooth",Toast.LENGTH_SHORT).show();
        }
        if(!bluetoothAdapter.isEnabled())
        {
            Intent enableAdapter = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableAdapter, 0);
        }
        if (bluetoothAdapter.isEnabled()){
            bton();
        }
    }

    public void bton(){
            Intent ss=new Intent(getApplicationContext(),SplashScreen.class);
            startActivity(ss);
    }

    public void tost() {
        final TextView tost_msg = (TextView) this.findViewById(R.id.tost_msg);
        if (int_tost == 0) {
            final Handler tost = new Handler();
            tost.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tost_msg.setVisibility(View.VISIBLE);
                }
            }, 20000);
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);
        System.exit(1);
    }
}
