package main.activity.Auto;




import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.Tirax.RF.MyActivity;
import com.Tirax.RF.Storage.Pages;
import com.example.cryo.*;

import main.activity.StartActivity;


public class AutoActivity extends MyActivity implements OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.plasma_auto);

        //declaring main menu buttons
        Button acne=(Button) findViewById(R.id.btn_acne);
        Button blepharo=(Button) findViewById(R.id.btn_blepharoplasty);
        Button scar=(Button) findViewById(R.id.btn_scar);
        Button mole = (Button) findViewById(R.id.btn_mole);


        //declaring onclicklistener functions
        acne.setOnClickListener(this);
        blepharo.setOnClickListener(this);
        scar.setOnClickListener(this);
        mole.setOnClickListener(this);



    }

    @Override
    public void onClick(View arg0) {

        //declaring intents
        Intent int_start=new Intent(AutoActivity.this,SelectStep.class);
        //Pages.step = Pages.LOW;

        //starting corresponding intents
        if (arg0.getId()==R.id.btn_acne){
            Pages.auto_type = Pages.ACNE;
            startActivity(int_start);
        }

        if (arg0.getId()==R.id.btn_blepharoplasty){
            Pages.auto_type = Pages.BLEPHARO;
            startActivity(int_start);
        }

        if (arg0.getId()==R.id.btn_scar){
            Pages.auto_type = Pages.SCAR;
            startActivity(int_start);
        }

        if(arg0.getId() == R.id.btn_mole){
            Pages.auto_type = Pages.MOLE;
            startActivity(int_start);
        }


        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


}
