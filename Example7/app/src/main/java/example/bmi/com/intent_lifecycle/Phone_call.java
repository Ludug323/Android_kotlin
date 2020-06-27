package example.bmi.com.intent_lifecycle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by User on 2017/11/11.
 */

public class Phone_call extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri1 = Uri.parse("tel:0933456789");
        Intent intent1 = new Intent(Intent.ACTION_DIAL,uri1);
        startActivity(intent1);
    }
}
