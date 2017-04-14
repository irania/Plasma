package main.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.Tirax.RF.Enums.TestType;
import com.Tirax.RF.MyActivity;
import com.Tirax.RF.Test.TestSerialBrief;
import com.example.cryo.R;

public class TestResult extends MyActivity implements View.OnClickListener {

    public static TestType test= TestType.SERIAL_BRIEF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);

        Button back  = (Button) findViewById(R.id.btn_testres_back);
        back.setOnClickListener(this);

        TextView log = (TextView) findViewById(R.id.txt_testres_log);
        switch (test){
            case SERIAL_BRIEF:{
                TestSerialBrief tsb = new TestSerialBrief();
                log.setText("Brief Serial Communication Test Result:\n");
                log.append(tsb.Run());
            }

        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_testres_back){
            this.finish();
        }
    }
}
