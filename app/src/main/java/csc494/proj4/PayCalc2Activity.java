package csc494.proj4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import java.text.DecimalFormat;
import java.util.*;
import java.io.*;

public class PayCalc2Activity extends Activity {
	Button btnCalc, btnClear, btnLogin;
    EditText etHours, etRate, etTotal, etFedtax, etStatetax, etNetPay;
    Map<String, String> mp;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main);
   
        btnCalc = (Button)findViewById(R.id.button1);
        btnClear = (Button)findViewById(R.id.button2);
        btnLogin = (Button)findViewById(R.id.button3);
       
        etHours = (EditText)findViewById(R.id.hrs);
        etRate = (EditText)findViewById(R.id.rate);
        etTotal = (EditText)findViewById(R.id.tot);
        etFedtax = (EditText)findViewById(R.id.fed);
        etStatetax = (EditText)findViewById(R.id.state);
        etNetPay = (EditText)findViewById(R.id.net);
       
        
    }
    
    public void doLogin(View view){
    	Intent i = new Intent(this, LoginClass.class);
		//passing information to launched activity
    	startActivityForResult(i, 0);
    	etHours.setFocusableInTouchMode(true);
		etRate.setFocusableInTouchMode(true);

    }
    
    public void calculate(View view)
    {
    	DecimalFormat df = new DecimalFormat("0.00");
    	double hrs = Double.parseDouble(etHours.getText().toString());
    	double rate = Double.parseDouble(etRate.getText().toString());
    	double totalpay = 0;
    	if(hrs > 40){
    		totalpay = (40 * rate) + ((hrs - 40)* (rate * 1.5));
    	}else{
    		totalpay = hrs * rate;
    	}
    	double fed = totalpay * .22;
    	double state = totalpay * .022;
    	double netpay = totalpay - (fed + state);
    	etTotal.setText(String.valueOf(df.format(totalpay)));
    	etFedtax.setText(String.valueOf(df.format(fed)));
    	etStatetax.setText(String.valueOf(df.format(state)));
    	etNetPay.setText(String.valueOf(df.format(netpay)));
    }
    
    public void clear(View view)
    {
    	etHours.setText("");
    	etRate.setText("");
    	etTotal.setText("");
    	etFedtax.setText("");
    	etStatetax.setText("");
    	etNetPay.setText("");
    }
    
    @Override
    public void onDestroy(){
    	try {
			PrintWriter out = new PrintWriter(openFileOutput("pay.txt", MODE_PRIVATE));
			out.println(etHours.getText());
			out.println(etRate.getText());
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	super.onDestroy();
    	
    }
    
   
    }