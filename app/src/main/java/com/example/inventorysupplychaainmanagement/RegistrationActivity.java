package com.example.inventorysupplychaainmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class RegistrationActivity extends AppCompatActivity {

    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        edUsername = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
        edEmail = findViewById(R.id.editTextRegEmail);
        edConfirm = findViewById(R.id.editTextRegConfirmPassword);
        btn = findViewById(R.id.buttonRegister);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override



            public void onClick(View view) {
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                String email = edEmail.getText().toString();
                String confirm = edConfirm.getText().toString();
                String baseUrl = "http://13.233.26.112/";
                Database db = new Database(getApplicationContext(), "inventory", null, 1);

                if(username.length()==0 || password.length()==0 || email.length()==0 || confirm.length()==0){
                    Toast.makeText(getApplicationContext(),"Please fill all details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(confirm)==0){
                        if(isValid(password)){
                            URL url = null;
                            try {
                                url = new URL(baseUrl + "/register");
                                HttpURLConnection client = null;
                                try {
                                    client = (HttpURLConnection) url.openConnection();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                // on below line setting method as post.
                                try {
                                    client.setDoOutput(true);
                                    client.setDoInput(true);
                                    client.setRequestMethod("POST");
                                } catch (ProtocolException e) {
                                    e.printStackTrace();
                                }
                                client.setRequestProperty("Content-Type", "application/json");
                                client.setRequestProperty("Accept", "application/json");
                                client.setRequestProperty("source-name", "streamlining-inventory-management");
                                // on below line setting client.
                                String jsonInputString = new String("{ username:" + username + "," + "email:" + email + "password:" + password + "}");
                                OutputStream os = client.getOutputStream();
                                os.write(jsonInputString.getBytes("UTF-8"));
                                os.close();

                                InputStream in = new BufferedInputStream(client.getInputStream());
                                String result = org.apache.commons.io.IOUtils.toString(in, "UTF-8");
                                JSONObject jsonObject = new JSONObject(result);

                                Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                            } catch (MalformedURLException e) {
                                e.printStackTrace();
                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter, digit amd special symbol", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password and Confirm Password didn't match", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() < 8) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1 = 1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++) {
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }

            for (int s = 0; s < passwordhere.length(); s++) {
                char c = passwordhere.charAt(s);
                if (c >= 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }

            if (f1 == 1 && f2 == 1 && f3 == 1) {
                return true;
            }
            return false;
        }
    }
}