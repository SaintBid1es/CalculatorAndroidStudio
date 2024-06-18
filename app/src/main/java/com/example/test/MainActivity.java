package com.example.test;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.Normalizer;

public class MainActivity extends AppCompatActivity {

    private TextView Formula,EndResult;
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0;
    private Button Resultbtn,plusbtn,minusbtn,umnojitbtn,delitbtn,Exponentiationbtn,buttonKOREN,buttonPROCENT,tanBtn,ctgBtn;
    private char Action;
    private double  valueFirst;

    private double valueSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setupView();
        View.OnClickListener numberClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                Formula.setText(Formula.getText().toString() + button.getText().toString());
            }
        };
        button0.setOnClickListener(numberClickListener);
        button1.setOnClickListener(numberClickListener);
        button2.setOnClickListener(numberClickListener);
        button3.setOnClickListener(numberClickListener);
        button4.setOnClickListener(numberClickListener);
        button5.setOnClickListener(numberClickListener);
        button6.setOnClickListener(numberClickListener);
        button7.setOnClickListener(numberClickListener);
        button8.setOnClickListener(numberClickListener);
        button9.setOnClickListener(numberClickListener);

        View.OnClickListener actionClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                if(valueFirst == 0.0){
                    valueFirst = Double.parseDouble(Formula.getText().toString());
                    Action = button.getText().charAt(0);
                    Formula.setText(String.valueOf(valueFirst) + Action);
                    return;
                }
                if(valueSecond==0.0){
                   String formula = Formula.getText().toString();
                   int index = formula.indexOf(Action);
                   valueSecond = Double.parseDouble(formula.substring(index+1));
                   calculate();
                Action = button.getText().charAt(0);
                Formula.setText(String.valueOf(valueFirst) + Action);
                EndResult.setText(Double.toString(valueFirst));
                }

            }
        };
        plusbtn.setOnClickListener(actionClickListener);
        minusbtn.setOnClickListener(actionClickListener);
        umnojitbtn.setOnClickListener(actionClickListener);
        delitbtn.setOnClickListener(actionClickListener);
        Exponentiationbtn.setOnClickListener(actionClickListener);
        buttonKOREN.setOnClickListener(actionClickListener);
        buttonPROCENT.setOnClickListener(actionClickListener);
        //tanBtn.setOnClickListener(actionClickListener);
       // ctgBtn.setOnClickListener(actionClickListener);
        Resultbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
                Action = '=';
                EndResult.setText(String.valueOf(valueFirst));
                Formula.setText(null);
            }
        });


    }
    public void ClearIventClick(View view){

        valueFirst=0;
        valueSecond=0;
        Formula.setText("");
        EndResult.setText("");

    }


    private void setupView(){

        button0 = (Button) findViewById(R.id.button0);
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button6 = (Button) findViewById(R.id.button6);
        button7 = (Button) findViewById(R.id.button7);
        button8 = (Button) findViewById(R.id.button8);
        button9 = (Button) findViewById(R.id.button9);
        plusbtn = (Button) findViewById(R.id.plusbtn);
        minusbtn = (Button) findViewById(R.id.minusbtn);
        umnojitbtn = (Button) findViewById(R.id.umnojitbtn);
        delitbtn = (Button) findViewById(R.id.delitbtn);
        buttonKOREN = (Button) findViewById(R.id.buttonKOREN);
        buttonPROCENT = (Button) findViewById(R.id.buttonPROCENT);
        tanBtn = (Button) findViewById(R.id.tanBtn);
        ctgBtn = (Button) findViewById(R.id.ctgBtn);
        Exponentiationbtn = (Button) findViewById(R.id.Exponentiationbtn);
        Resultbtn = (Button) findViewById(R.id.Resultbtn);
        Formula = (TextView) findViewById(R.id.Formula);
        EndResult = (TextView) findViewById(R.id.EndResult);
    }
    private void calculate(){
        if(!Double.isNaN(valueFirst)){
            String textFormula = Formula.getText().toString();
            int indexAction = textFormula.indexOf(Action);
            if(indexAction != -1){
                String number = textFormula.substring(indexAction+1);
                valueSecond = Double.parseDouble(number);
                switch (Action){
                    case '+':
                        valueFirst +=valueSecond;
                        break;
                    case '-':
                        valueFirst -=valueSecond;
                        break;
                    case '*':
                        valueFirst *=valueSecond;
                        break;
                    case '/':
                        if(valueSecond ==0){
                            valueFirst = 0.0;
                            Formula.setText("Учи математику!");
                        }else {
                            valueFirst /= valueSecond;
                        }
                        break;
                    case '^':
                       valueFirst= Math.pow(valueFirst,valueSecond);
                        break;
                    case '%':
                        valueFirst=(valueFirst*valueSecond)/100;
                        break;
                    case '√':
                        valueFirst = Math.sqrt(valueFirst);
                        break;
                    case 't':
                        valueFirst = Math.tan(valueFirst);
                        break;
                    case 'c':
                        valueFirst = Math.tan(valueFirst)/1;
                        break;
                    case '=':
                        valueFirst = valueSecond;
                        break;
                }
            }

        }else{
            valueFirst = Double.parseDouble(Formula.getText().toString());
        }
        EndResult.setText(null);
        Formula.setText(null);
    }
}