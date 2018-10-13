package com.futsalyuk.runup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.futsalyuk.runup.futsalyuk.R;

public class regActivity extends AppCompatActivity {

    private Button mNext_reg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg1);

        mNext_reg = (Button)findViewById(R.id.next_reg);

        mNext_reg.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent iReg = new Intent(getApplicationContext(), reg2Activity.class);
                startActivity(iReg);
            }
        });
    }
}
