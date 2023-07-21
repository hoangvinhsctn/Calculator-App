package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.color.MaterialColorUtilitiesHelper;

public class MainActivity2 extends AppCompatActivity {
    private TextView tv;
    private String input;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = (TextView) findViewById(R.id.textView1);
    }

    public void newClick(View view){
        Button button = (Button) view;
        String data = button.getText().toString();

        switch (data)
        {
            case "Ac":
                input = "";
                break;
            case "Del":
                if(input.length() > 0)
                {
                    String newText = input.substring(0,input.length() - 1);
                    input = newText;
                }else
                    input = "";
                break;
            case "=":
                if(input != null)
                    Solve();
                break;

            //Xử lý x mũ n
            case "xⁿ":
                input += "^";
                break;

            default:
                if(input == null)
                {
                    input = "";
                }
                if(data.equals("sin") || data.equals("cos") || data.equals("tan") || data.equals("log") || data.equals("√") || data.equals("xⁿ"))
                {
                    Solve();
                }
                input += data;
        }
        tv.setText(input);
    }

    public void Solve()
    {
        if(input.split("\\√").length == 2)
        {
            String number[] = input.split("\\√");
            try {
                double sqrt2 = Math.sqrt(Double.parseDouble(number[1]));
                input = sqrt2 + "";
            } catch (Exception e)
            {

            }
        }

        if(input.split("sin").length == 2)
        {
            String number[] = input.split("sin");
            try {
                double kqsin = Math.sin(Double.parseDouble(number[1]));
                input = kqsin + "";
            } catch (Exception e)
            {

            }
        }

        if(input.split("cos").length == 2)
        {
            String number[] = input.split("cos");
            try {
                double kqcos = Math.cos(Double.parseDouble(number[1]));
                input = kqcos + "";
            } catch (Exception e) {

            }
        }

        if(input.split("tan").length == 2)
        {
            String number[] = input.split("tan");
            try {
                double kqtan = Math.tan(Double.parseDouble(number[1]));
                input = kqtan + "";
            } catch (Exception e) {

            }
        }

        if(input.split("log").length == 2)
        {
            String number[] = input.split("log");
            try {
                double kqlog = Math.log10(Double.parseDouble(number[1]));
                input = kqlog + "";
            } catch (Exception e) {

            }
        }

        if(input.split("\\^").length == 2) {
            String number[] = input.split("\\^");
            try {
                double so = Double.parseDouble(number[0]);
                double somu = Double.parseDouble(number[1]);
                double kq = Math.pow(so,somu);
                input = kq + "";
            } catch (Exception e)
            {

            }
        }
        delPoint(input);
    }
    public void delPoint(String dulieu)
    {
        String n[] = input.split("\\.");
        if(n.length > 1)
        {
            if(n[1].equals("0"))
            {
                input = n[0];
            }
        }
    }
}
