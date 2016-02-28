package rustam.megafood3;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import rustam.megafood3.model.Request;

public class CheckOutActivity extends AppCompatActivity {
    String orderID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        orderID = getIntent().getStringExtra("ord_id");
        if(orderID==null){
            orderID="0";
        }
    }

    public void onPay(View v){
        AsyncTask<Void,Void,String> task = new AsyncTask<Void, Void, String>() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                CheckOutActivity.this.findViewById(R.id.progressBar).setVisibility(View.VISIBLE);
                CheckOutActivity.this.findViewById(R.id.content).setVisibility(View.GONE);
            }

            @Override
            protected String doInBackground(Void... params) {
                return Request.checkOut(orderID, "ничего", "никто", "никогда", "cnn");
            }

            @Override
            protected void onPostExecute(String aVoid) {
                super.onPostExecute(aVoid);
                Intent intent = new Intent(CheckOutActivity.this, FinishActivity.class);
                intent.putExtra("status", aVoid);
                intent.putExtra("order_id", orderID);
                startActivity(intent);
                CheckOutActivity.this.findViewById(R.id.progressBar).setVisibility(View.GONE);
                CheckOutActivity.this.findViewById(R.id.content).setVisibility(View.VISIBLE);
            }
        };
        task.execute((Void)null);
    }
}
