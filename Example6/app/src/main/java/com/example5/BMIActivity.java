package com.example5;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    private TextView bmi_resule;
    private Button http,phone_call,back;
    private String record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        bmi_resule = (TextView)findViewById(R.id.textView2);
        http = (Button)findViewById(R.id.button3);
        phone_call = (Button)findViewById(R.id.button2);
        back = (Button)findViewById(R.id.button4);

        http.setOnClickListener(mylistener);
        phone_call.setOnClickListener(mylistener);
        back.setOnClickListener(mylistener);


        Bundle bundle = getIntent().getExtras();
        double h = bundle.getInt("height")/100.0;
        double w = bundle.getInt("weight");
        double bmiValue = w/Math.pow(h,2);

        record=String.format("%.2f",bmiValue);

        if (bmiValue >= 0 && bmiValue < 10) {

            bmi_resule.setText("BMI:" + String.format("%.2f", bmiValue) + "極低");

        } else if (bmiValue >= 10 && bmiValue < 18) {

            bmi_resule.setText("BMI:" + String.format("%.2f", bmiValue) + "偏低");

        } else if (bmiValue >= 18 && bmiValue < 26) {

            bmi_resule.setText("BMI:" + String.format("%.2f", bmiValue) + "正常");

        } else if (bmiValue >= 26 && bmiValue < 30) {

            bmi_resule.setText("BMI:" + String.format("%.2f", bmiValue) + "偏高");

        } else if (bmiValue > 30 ) {

            bmi_resule.setText("BMI:" + String.format("%.2f", bmiValue) + "極高");
        }
    }

    private Button.OnClickListener mylistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button3:
                    Uri uri = Uri.parse("https://zh.wikipedia.org/wiki/%E8%BA%AB%E9%AB%98%E9%AB%94%E9%87%8D%E6%8C%87%E6%95%B8");
                    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                    startActivity(intent);
                    break;
                case R.id.button2:
                    Uri uri1 = Uri.parse("tel:0933456789");
                    Intent intent1 = new Intent(Intent.ACTION_DIAL,uri1);
                    startActivity(intent1);
                    break;
                case R.id.button4:
                    Intent intent2 = new Intent();
                    intent2.putExtra("record",record);
                    setResult(1,intent2);
                    finish();
                    break;
            }

        }
    };
}
