package com.example.gpt_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    Button button;
    EditText editText;
    TextView textView;

    private String str,receiveMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        button = findViewById(R.id.process);
        editText = findViewById(R.id.text);
        textView = findViewById(R.id.output);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String url = "http://192.168.43.95:5000/?a=";
                String name = editText.getText().toString();
                url = url+name;

                try {
                    URL targetURL = new URL(url);

                    HttpURLConnection connection = (HttpURLConnection) targetURL.openConnection();
                    if (connection.getResponseCode() == connection.HTTP_OK) {
                        InputStreamReader tmp = new InputStreamReader(connection.getInputStream(), "UTF-8");
                        BufferedReader reader = new BufferedReader(tmp);
                        StringBuffer buffer = new StringBuffer();
                        while ((str = reader.readLine()) != null) {
                            buffer.append(str);
                        }
                        receiveMsg = buffer.toString();

                        textView.setText(receiveMsg);
                        reader.close();
                }

            } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}