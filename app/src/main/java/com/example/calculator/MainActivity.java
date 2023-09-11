package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    CardView one ,two, three,four,five ,six,seven,eight,nine,zero,decimal,mul,div,add,sub,clear,back,equalTo;
    TextView operation,result;
    String input="",output;
    double finalResult=0.0;

    ArrayList<String> operands=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineHooks();
        // set Listener
        setListener();
        getResult();

    }

    private void setListener() {
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        decimal.setOnClickListener(this);
        mul.setOnClickListener(this);
        div.setOnClickListener(this);
        add.setOnClickListener(this);
        sub.setOnClickListener(this);
        clear.setOnClickListener(this);
        back.setOnClickListener(this);
        equalTo.setOnClickListener(this);

    }

    private void defineHooks() {
        one=findViewById(R.id.one);
        two=findViewById(R.id.two);
        three=findViewById(R.id.three);
        four=findViewById(R.id.four);
        five=findViewById(R.id.five);
        six=findViewById(R.id.six);
        seven=findViewById(R.id.seven);
        eight=findViewById(R.id.eight);
        nine=findViewById(R.id.nine);
        zero=findViewById(R.id.zero);
        decimal=findViewById(R.id.decimal);
        mul=findViewById(R.id.multiply);
        div=findViewById(R.id.divide);
        add=findViewById(R.id.add);
        sub=findViewById(R.id.subtraction);
        clear=findViewById(R.id.AC);
        back=findViewById(R.id.backspace);
        equalTo=findViewById(R.id.equalTo);
        operation=findViewById(R.id.operation);
        result=findViewById(R.id.result);
    }

    @Override
    public void onClick(View v) {
        if (v==zero){
            operation("0");
        } else if (v==one) {
            operation("1");
        } else if (v==two) {
            operation("2");
        } else if (v==three) {
            operation("3");
        } else if (v==four) {
            operation("4");
        }else if (v==five) {
            operation("5");
        } else if (v==six) {
            operation("6");
        }else if (v==seven) {
            operation("7");
        }else if (v==eight) {
            operation("8");
        }else if (v==nine) {
            operation("9");
        }else if (v==clear) {
            operation("AC");
        }else if (v==decimal) {
            operation(".");
        }else if (v==mul) {
            operation(" X ");
        }else if (v==div) {
            operation(" % ");
        }else if (v==back) {
            operation("<-");
        }else if (v==equalTo) {
            operation("result");
        }else if (v==add) {
            operation(" + ");
        }else if (v==sub) {
            operation(" - ");
        }

    }

    private void operation(String no) {
        if (no.equals("AC")){
            input="";
        } else if (no.equals("<-")) {
            if (!input.equals("")) {
                Character last = input.charAt(input.length()-1);
                if(last.equals(' ')){
                    input=input.substring(0,input.length()-2);
                }
                input = input.substring(0, input.length() - 1);
            }
        }
        else if(no.equals("result")){
            getResult();
        }
        else {
            boolean isTwiceOperator=false;
            String perv="";
            if(input.length()>1){
                perv=input.substring(input.length()-2,input.length()-1);
            }
            if((perv.equals("+")||perv.equals("X")||perv.equals("-")||perv.equals("%"))&&(no.equals(" + ")||no.equals(" - ")||no.equals(" X ")||no.equals(" % "))){
                isTwiceOperator=true;
            }
            if(input.equals("")&&(no.equals(" + ")||no.equals(" - ")||no.equals(" X ")||no.equals(" % "))){
                isTwiceOperator=true;
            }
            if(!isTwiceOperator){
                input=input+no;
            }

        }
        operation.setText(input);
    }

    private void getResult() {
        operands.clear();
        String dup=input;
        dup=dup+" ";
        int len=dup.length();
        String value="";
        Character ch;
        for(int i=0;i<len;i++){
            ch=dup.charAt(i);
            if(ch.equals(' ')){
                value=value.trim();
                operands.add(value);
                value="";
            }
            value=value+ch;

        }
        getAns("%");
        getAns("X");
        getAns("+");
        getAns("-");

        
        result.setText(String.valueOf(finalResult));


    }

    private void getAns(String operator) {
        int len=operands.size();
        for(int j=0;j<len*len;j++){
        for(int i=0;i<len;i++) {
            if (operands.get(i).equals(operator)) {
                if (operands.get(i).equals("/")) {

                } else if (operands.get(i).equals("%")) {
                    finalResult = Double.parseDouble(operands.get(i - 1)) / Double.parseDouble(operands.get(i + 1));

                } else if (operands.get(i).equals("X")) {
                    finalResult = Double.parseDouble(operands.get(i - 1)) * Double.parseDouble(operands.get(i + 1));
                } else if (operands.get(i).equals("-")) {
                    finalResult = Double.parseDouble(operands.get(i - 1)) - Double.parseDouble(operands.get(i + 1));

                } else if (operands.get(i).equals("+")) {
                    finalResult = Double.parseDouble(operands.get(i - 1)) + Double.parseDouble(operands.get(i + 1));

                }
                operands.remove(i - 1);
                operands.add(i-1,String.valueOf(finalResult));
                operands.remove(i + 1);
                operands.remove(i);
                len = len - 2;
            }
        }

        }

    }
}