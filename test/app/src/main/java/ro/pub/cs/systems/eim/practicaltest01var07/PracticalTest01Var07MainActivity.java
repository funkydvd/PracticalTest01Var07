package ro.pub.cs.systems.eim.practicaltest01var07;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class PracticalTest01Var07MainActivity extends ActionBarActivity {


    private EditText et1 = null;

    private EditText et2 = null;

    private CheckBox cb1 = null;

    private CheckBox cb2 = null;

    private Button btn = null;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_main);
        et1 = (EditText) findViewById(R.id.editText);
        et2 = (EditText) findViewById(R.id.editText2);
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
}
