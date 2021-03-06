package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var07MainActivity extends ActionBarActivity {


    private EditText et1 = null;

    private EditText et2 = null;

    private CheckBox cb1 = null;

    private CheckBox cb2 = null;

    private Button btn = null;
    private IntentFilter intentFilter = new IntentFilter();
    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements Button.OnClickListener {

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button:
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07SecondaryActivity.class);

                    intent.putExtra("et1", et1.getText().toString());
                    intent.putExtra("et2", et2.getText().toString());

                    startActivityForResult(intent, 100);

            }
        }
    }
    private MessageBroadcastReceiver messageBroadcastReceiver = new MessageBroadcastReceiver();
    private class MessageBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d("[Message]", intent.getStringExtra("message"));
        }
    }
    private int serviceStatus;
    private int started = 0;
        @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);
            for (int index = 0; index < Constants.actionTypes.length; index++) {
                intentFilter.addAction(Constants.actionTypes[index]);
            }
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
        btn = (Button) findViewById(R.id.button);
            btn.setOnClickListener(buttonClickListener);
        cb1 = (CheckBox) findViewById(R.id.checkBox);

        cb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked())
                {
                    et1.setEnabled(true);
                }
                else
                {
                    et1.setEnabled(false);
                }
                if ((((CheckBox) view).isChecked()==false && cb2.isChecked()==false) && et1.getText().toString().length()>0 &&
                        et2.getText().toString().length()>0 && started == 0)
                {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
                    intent.putExtra("et1", et1.getText().toString());
                    intent.putExtra("et2", et2.getText().toString());
                    getApplicationContext().startService(intent);
                    serviceStatus = 1;
                    started = 1;
                }
            }
        });
        cb2 = (CheckBox) findViewById(R.id.checkBox2);

        cb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CheckBox) view).isChecked())
                {
                    et2.setEnabled(true);
                }
                else
                {
                    et2.setEnabled(false);
                }
                if ((((CheckBox) view).isChecked()==false && cb1.isChecked()==false) && et1.getText().toString().length()>0 &&
                        et2.getText().toString().length()>0 && started == 0)
                {
                    Intent intent = new Intent(getApplicationContext(), PracticalTest01Var07Service.class);
                    intent.putExtra("et1", et1.getText().toString());
                    intent.putExtra("et2", et2.getText().toString());
                    getApplicationContext().startService(intent);
                    started = 1;
                    serviceStatus = 1;
                }
            }
        });

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("cb1"))  {
                cb1.setChecked(true);
                et1.setEnabled(true);
            }
            else
                cb1.setChecked(false);
            if (savedInstanceState.containsKey("cb2"))  {
                cb2.setChecked(true);
                et2.setEnabled(true);
            }
            else
                cb2.setChecked(false);
            if (savedInstanceState.containsKey("et1")) {
                et1.setText(savedInstanceState.getString("et1"));
            } else {
                ;
            }
            if (savedInstanceState.containsKey("et2")) {
                et2.setText(savedInstanceState.getString("et2"));
            } else {
                ;
            }
        } else {
             cb2.setChecked(false);
            cb1.setChecked(false);
        }

    }



    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey("cb1"))  {
                cb1.setChecked(true);
                et1.setEnabled(true);
            }
            else
                cb1.setChecked(false);
            if (savedInstanceState.containsKey("cb2"))  {
                cb2.setChecked(true);
                et2.setEnabled(true);
            }
            else
                cb2.setChecked(false);
            if (savedInstanceState.containsKey("et1")) {
                et1.setText(savedInstanceState.getString("et1"));
            } else {
               ;
            }
            if (savedInstanceState.containsKey("et2")) {
                et2.setText(savedInstanceState.getString("et2"));
            } else {
                ;
            }
        }

    }
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
      if (cb1.isChecked())
          savedInstanceState.putString("cb1","1");
        if (cb2.isChecked())
            savedInstanceState.putString("cb2","1");

        savedInstanceState.putString("et1", et1.getText().toString());
        savedInstanceState.putString("et2", et2.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 100) {
            Toast.makeText(this, "The activity returned with result " + resultCode, Toast.LENGTH_LONG).show();
        }
    }
    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, PracticalTest01Var07Service.class);
        stopService(intent);
        super.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(messageBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        unregisterReceiver(messageBroadcastReceiver);
        super.onPause();
    }
}
