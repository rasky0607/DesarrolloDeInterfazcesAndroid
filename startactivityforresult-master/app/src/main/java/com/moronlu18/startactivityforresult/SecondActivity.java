package com.moronlu18.startactivityforresult;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView txvSum;
    private int sum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Bundle bundle = getIntent().getExtras();

        int x = bundle.getInt("x");
        int y = bundle.getInt("y");
        sum = x + y;

        txvSum = findViewById(R.id.txvSum);
        txvSum.setText(x+" + "+y+" = "+sum);
    }

    public void sendResult(View view){
        Intent returnIntent = new Intent();
        returnIntent.putExtra("sum",sum);
        setResult(RESULT_OK,returnIntent);
        finish();


    }
}
