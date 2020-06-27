package example.bmi.com.intent_lifecycle;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

        Log.i("lifecycle", "MainActivity : call onCreate()");

        bmi_height = (EditText) findViewById(R.id.editText);
        bmi_weight = (EditText)findViewById(R.id.editText2);
        calculate = (Button)findViewById(R.id.button);
        bmi_record = (TextView)findViewById(R.id.textView);

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double height = Double.parseDouble(bmi_height.getText().toString());
                    double weight = Double.parseDouble(bmi_weight.getText().toString());

                    Bundle bundle = new Bundle();
                    bundle.putDouble("height",height);
                    bundle.putDouble("weight",weight);

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
    protected void onStart() {
        super.onStart();
        Log.i("lifecycle", "MainActivity : call onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("lifecycle", "MainActivity : call onCreate()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("lifecycle", "MainActivity : call onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("lifecycle", "MainActivity : call onPause()");
    }

    @Override
    protected void onActivityResult(int requestCode ,int resultCode, Intent data){
        if (requestCode == resultCode){
            String record ="BMI數值記錄:"+data.getStringExtra("record");
            bmi_record.setText(record);
        }

        super.onActivityResult(requestCode,resultCode,data);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("lifecycle", "MainActivity : call onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("lifecycle", "MainActivity : call onDestroy()");

    }
}
