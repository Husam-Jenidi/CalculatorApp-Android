package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView ResultView;

    @SuppressLint("WrongViewCast")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ResultView = findViewById(R.id.textView);

        if (savedInstanceState != null) {
            String editTextValue = savedInstanceState.getString("edit_text_value");
            ResultView.setText(editTextValue);
        }

        Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnDot, btnplus, btnminus, btnmultiply, btndivide, btnresult, btnreset;

        btn1 = findViewById(R.id.No1);
        btn2 = findViewById(R.id.No2);
        btn3 = findViewById(R.id.No3);
        btn4 = findViewById(R.id.No4);
        btn5 = findViewById(R.id.No5);
        btn6 = findViewById(R.id.No6);
        btn7 = findViewById(R.id.No7);
        btn8 = findViewById(R.id.No8);
        btn9 = findViewById(R.id.No9);
        btn0 = findViewById(R.id.No0);
        btnDot = findViewById(R.id.theDot);
        btnplus = findViewById(R.id.plus);
        btnminus = findViewById(R.id.minus);
        btnmultiply = findViewById(R.id.multiply);
        btndivide = findViewById(R.id.divide);
        btnreset = findViewById(R.id.reset);
        btnresult = findViewById(R.id.btnresult);

        btn0.setOnClickListener(v -> ResultView.append("0"));
        btn1.setOnClickListener(v -> ResultView.append("1"));


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append("2");
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append("3");
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append("4");
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append("5");
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append("6");
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append("7");
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append("8");
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append("9");
            }
        });
        btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append(" + ");
            }
        });
        btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append(" - ");
            }
        });
        btnmultiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append(" X ");
            }
        });
        btndivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultView.append(" / ");
            }
        });


        btnDot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String resultText = ResultView.getText().toString();
                String[] numbers = resultText.split("[+]");
                String lastNumber = numbers[numbers.length - 1];
                if (lastNumber.contains(".")) {
                    // The last number already has a decimal point, do nothing
                } else {
                    // Append the decimal point to the last number
                    ResultView.append(".");
                } }});
//                if (ResultView.getText().toString().contains(".")) {}
//                else
//                    ResultView.append(".");
//            }

        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {ResultView.setText("");}
        });

        btnresult.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String resultText = ResultView.getText().toString();

                    String[] numbers = resultText.split("[+\\-*/X]");
                    // check if the last character in the input is an operator
                    String lastChar = resultText.substring(resultText.length() - 1);
                    boolean isOperatorLast = lastChar.equals("+") || lastChar.equals("-") || lastChar.equals("X") || lastChar.equals("/");
                    float result = 0;
                    boolean isFirstFactor = true;
                    String operator = "";

                    for (int i = 0; i < numbers.length; i++) {
                        float number = Float.parseFloat(numbers[i]);
                        if (isFirstFactor) {
                            result = number;
                            isFirstFactor = false;
                        } else {
                            if (!operator.isEmpty()) {
                                switch (operator) {
                                    case "+":
                                        result += number;
                                        break;
                                    case "-":
                                        result -= number;
                                        break;
                                    case "X":
                                        result *= number;
                                        break;
                                    case "/":
                                        result /= number;
                                        break;
                                }
                            }
                        }

                        // check if there is a following operator
                        if (i < resultText.length() - 1) {
                            String nextChar = resultText.substring(i + numbers[i].length(), i + numbers[i].length() + 1);
                            if (nextChar.equals("+") || nextChar.equals("-") || nextChar.equals("X") || nextChar.equals("/")) {
                                operator = nextChar.trim();
                            }
                        }
                    }

                    if (!isOperatorLast) {
                        ResultView.setText(String.valueOf(result));
                    } else {
                        ResultView.setText("");
                    }
                }


        });

    }



    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putString("edit_text_value", ResultView.getText().toString());
    }
}
