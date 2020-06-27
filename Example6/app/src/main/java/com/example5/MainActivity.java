package com.example5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView bmi_record;
    private EditText bmi_height,bmi_weight;
    private Button calculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bmi_height = (EditText) findViewById(R.id.editText);
        bmi_weight = (EditText)findViewById(R.id.editText2);
        calculate = (Button)findViewById(R.id.button);
        bmi_record = (TextView)findViewById(R.id.textView);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int height = Integer.parseInt(bmi_height.getText().toString());
                    int weight = Integer.parseInt(bmi_weight.getText().toString());

                    Bundle bundle = new Bundle();
                    bundle.putInt("height",height);
                    bundle.putInt("weight",weight);

                    Intent intent = new Intent(MainActivity.this,BMIActivity.class);
                    intent.putExtras(bundle);
                    startActivityForResult(intent,1);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this,"數值錯誤",Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode ,int resultCode, Intent data){
        if (requestCode == resultCode){
            String record ="BMI數值記錄:"+data.getStringExtra("record");
            bmi_record.setText(record);
        }

        super.onActivityResult(requestCode,resultCode,data);
    }
}
