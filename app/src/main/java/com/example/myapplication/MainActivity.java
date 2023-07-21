package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView tv;
    private  Button btn;
    private String input,output; //Khai báo chuỗi kết quả
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.button1);
        tv = (TextView) findViewById(R.id.textView);

        //Chuyển sang Activity 2 - Intent
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(i1);
            }
        });
    }

    //Xử lý sự kiện nhấn nút button
    public void ButtonClick(View view){
        Button button = (Button) view;
        String data = button.getText().toString(); //Lấy chữ từ button

        switch (data)
        {
            //Xử lý xóa màn hình
            case "Ac":
                input = "";
                break;
            //Xử lý xóa backspace
            case "Del":
                if(input.length() > 0)
                {
                    String newText = input.substring(0,input.length() - 1);
                    input = newText;
                }else
                    input = "";
                break;
            //Xử lý nhấn "="
            case "=":
                if(input != null)
                    Solve();
                break;
            //Xử lý khi nhấn tăng 1 đơn vị
            case "↑":
                if(input != null)
                {
                    if(input.isEmpty() || TextUtils.isDigitsOnly(input) != true)
                    {
                        Toast.makeText(this, "Bạn phải nhập đúng giá trị số", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        String number[] = input.split("\\↑"); //Xử dụng \\↑ để tách ↑ ra khỏi biểu thức chính quy
                        Double newUp = Double.parseDouble(number[0]) + 1;
                        input = newUp.toString();
                        delPoint(input);
                    }
                }else
                {
                    Toast.makeText(this, "Bạn phải nhập giá trị", Toast.LENGTH_SHORT).show();
                }
                break;
            //Xử lý khi nhấn giảm 1 đơn vị
            case "↓":
                if(input != null)
                {
                    if(input.isEmpty() || TextUtils.isDigitsOnly(input) != true)
                    {
                        Toast.makeText(this, "Bạn phải nhập đúng giá trị số", Toast.LENGTH_SHORT).show();
                    }else {
                        String number1[] = input.split("\\↓");
                        Double newDown = Double.parseDouble(number1[0]) - 1;
                        input = newDown.toString();
                        delPoint(input);
                    }
                }else
                {
                    Toast.makeText(this, "Bạn phải nhập giá trị", Toast.LENGTH_SHORT).show();
                }
                break;
            //Mặc định
            default:
                if(input == null)
                {
                    input = "";
                }
                if(data.equals("+") || data.equals("-") || data.equals("×") || data.equals("÷"))
                {
                    Solve();
                }
                input += data;
        }
        tv.setText(input);
    }

    //Hàm xử lý các thuật toán liên quan đến tính toán
    public void Solve()
    {
        //Xử lý phép nhân
        if(input.split("\\×").length == 2) {
            String number[] = input.split("\\×");
            try {
                double mul = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                input = mul + "";
            } catch (Exception e)
            {

            }
        }
        //Xử lý phép chia
        if(input.split("\\÷").length == 2) {
            String number[] = input.split("\\÷");
            try {
                if(Double.parseDouble(number[1]) == 0)
                    Toast.makeText(this, "Lỗi phép tính", Toast.LENGTH_SHORT).show();
                else
                {
                    double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                    input = div + "";
                }
            } catch (Exception e)
            {

            }
        }
        //Xử lý phép cộng
        if(input.split("\\+").length == 2) {
            String number[] = input.split("\\+");
            try {
                double add = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                input = add + "";
            } catch (Exception e)
            {

            }
        }
        //Xử lý phép trừ
        if(input.split("\\-").length == 2) {
            String number[] = input.split("\\-");
            try {
                double sub = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                input = sub + "";
            } catch (Exception e)
            {

            }
        }
        delPoint(input);
    }

    //Hàm xóa số 0 sau dấu .
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