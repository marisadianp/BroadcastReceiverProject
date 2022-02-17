package apps.jobcourse.broadcastreceiverproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static CustomReceiver mCustomReceiver = new CustomReceiver();
    private Button sendBtn;
    private String ACTION_CUSTOM_BROADCAST = "ACTION_CUSTOM_BROADCAST";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);

        registerReceiver(mCustomReceiver, intentFilter);

        sendBtn = findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent("ACTION_CUSTOM_BROADCAST");
                intent.putExtra("Data", "this is the data");
                LocalBroadcastManager.getInstance(view.getContext()).sendBroadcast(intent);
            }
        });

        IntentFilter intentFilter1 = new IntentFilter("ACTION_CUSTOM_BROADCAST");
        LocalBroadcastManager.getInstance(this).registerReceiver(mCustomReceiver,intentFilter1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mCustomReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mCustomReceiver);
    }
}