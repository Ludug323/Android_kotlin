package example.bmi.com.intent_lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    private TextView bmi_resule;
    private Button choose,back;
    private String record;

    double h,w,bmiValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);

        Log.i("lifecycle", "BMIActivity : call onCreate()");

        bmi_resule = (TextView)findViewById(R.id.textView2);
        choose = (Button)findViewById(R.id.button3);
        back = (Button)findViewById(R.id.button4);


        choose.setOnClickListener(mylistener);
        back.setOnClickListener(mylistener);

        Bundle bundle = getIntent().getExtras();
        h = bundle.getDouble("height")/100.0;
        w = bundle.getDouble("weight");


        bmiValue = w/Math.pow(h,2);

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
                    Intent intent = new Intent();
                    intent.setAction("good.car");
                    intent = Intent.createChooser(intent, "請選擇一個建議動作!");
                    startActivity(intent);
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

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("lifecycle", "BMIActivity : call onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecycle", "BMIActivity : call onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("lifecycle", "BMIActivity : call onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifecycle", "BMIActivity : call onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecycle", "BMIActivity : call onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle", "BMIActivity : call onDestroy()");

    }
}
