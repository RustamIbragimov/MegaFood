package rustam.megafood3;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import rustam.megafood3.model.Request;

public class FinishActivity extends AppCompatActivity {

    String orId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        orId = getIntent().getStringExtra("order_id");
        ((TextView) findViewById(R.id.textView4)).setText(orId);
        String status = getIntent().getStringExtra("status");
        ((TextView) findViewById(R.id.textView6)).setText(status);
    }


    public void onRefresh(View view) {
        AsyncTask<Void, Void, String> task = new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... params) {
                return Request.checkStatus(orId);
            }

            @Override
            protected void onPostExecute(String aVoid) {
                super.onPostExecute(aVoid);
                ((TextView) findViewById(R.id.textView6)).setText(aVoid);
            }
        };
        task.execute((Void)null);
    }
}
