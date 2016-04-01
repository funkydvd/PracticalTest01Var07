package ro.pub.cs.systems.eim.practicaltest01var07;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PracticalTest01Var07SecondaryActivity extends ActionBarActivity {
    private Button okButton = null;
    private Button cancelButton = null;
    private EditText et1 = null;
    private EditText et2 = null;


    private ButtonClickListener buttonClickListener = new ButtonClickListener();
    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button2:
                    setResult(RESULT_OK, null);
                    break;
                case R.id.button3:
                    setResult(RESULT_CANCELED, null);
                    break;
            }
            finish();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_test01_var07_secondary);
        et1 = (EditText) findViewById(R.id.editText3);
        et2 = (EditText) findViewById(R.id.editText4);
        Intent intent = getIntent();
        if (intent != null && intent.getExtras().containsKey("et1"))
            et1.setText(intent.getStringExtra("et1"));
        else
            et1.setText("NU");
        if (intent != null && intent.getExtras().containsKey("et2"))
            et2.setText(intent.getStringExtra("et2"));
        okButton = (Button) findViewById(R.id.button2);
        cancelButton = (Button) findViewById(R.id.button3);

        okButton.setOnClickListener(buttonClickListener);
        cancelButton.setOnClickListener(buttonClickListener);

    }

}
