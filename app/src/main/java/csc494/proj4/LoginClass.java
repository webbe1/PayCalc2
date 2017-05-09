package csc494.proj4;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.util.*;


public class LoginClass extends Activity{
	Button btnLogin;
    EditText etUserName, etPassWord;
    Map<String, String> mp;
    
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.main2);
        //button to login and change activities if successful
        btnLogin = (Button)findViewById(R.id.button4);
        //text fields
        etUserName = (EditText)findViewById(R.id.user);
        etPassWord = (EditText)findViewById(R.id.pass);
        //arrays to hold passwords and usernames
        String[] usernames = getResources().getStringArray(R.array.usernames);
        String[] passwords = getResources().getStringArray(R.array.passwords);
        
        mp=new HashMap<String, String>();//map to match usernames to passwords
        

        // adding or set elements in Map by put method key and value pair
        for(int x = 0; x < usernames.length; x++){
        	mp.put(usernames[x], passwords[x]);
        }
}
    
    public void login(View view){
    	String username = etUserName.getText().toString();
    	String password = etPassWord.getText().toString();
    	if(mp.get(username) == null){
    		new AlertDialog.Builder(this)
  		  .setTitle("Wrong username")
  		  .setMessage("The username you have entered is invalid.")
  		  .setNeutralButton("OK", null)
  		  .show();
    	}else if(password.equals(mp.get(username))){
    		new AlertDialog.Builder(this)
  		  .setTitle("Success")
  		  .setMessage("Login was successful.")
  		  .setNeutralButton("OK", null)
  		  .show();
    		finish();
    	}else{
    		new AlertDialog.Builder(this)
    		  .setTitle("Wrong username or password")
    		  .setMessage("The username or password you have entered is invalid.")
    		  .setNeutralButton("OK", null)
    		  .show();
    	}
    	
    	}
}
