package com.example.calculator.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.calculator.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Button button0, button1,button2, button3, button4,  button5, button6, button7, button8, button9;
    private Button buttonSuma, buttonResta, buttonMultiplicacion, buttonDivision, buttonPorcentaje,buttonBorrar, buttonIgual, buttonAC, buttonPunto;
    private TextView textView1, textView2;
    private int contador=1;
    private double resultado=0;
    private double numero=0;

    char identificardor;
    int longitud=0;
    int operador=0;
    boolean entrar=false;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        textView1=(TextView) root.findViewById(R.id.textView1);
        textView2=(TextView) root.findViewById(R.id.textView2);

        button0 = (Button) root.findViewById(R.id.button0);
        button0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (entrar==true){
                    textView2.setText("");
                    textView1.setText("");
                    entrar=false;
                }
                textView1.setText(textView1.getText() + "0");
                contador=0;
            }
        });

        button1 = (Button) root.findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (entrar==true){
                    textView2.setText("");
                    textView1.setText("");
                    entrar=false;
                }
                textView1.setText(textView1.getText() + "1");
                contador=0;
            }
        });

        button2 = (Button) root.findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (entrar==true){
                    textView2.setText("");
                    textView1.setText("");
                    entrar=false;
                }
                textView1.setText(textView1.getText() + "2");
                contador=0;
            }
        });

        button3 = (Button) root.findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (entrar==true){
                    textView2.setText("");
                    textView1.setText("");
                    entrar=false;
                }
                textView1.setText(textView1.getText() + "3");
                contador=0;
            }
        });

        button4 = (Button) root.findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (entrar==true){
                    textView2.setText("");
                    textView1.setText("");
                    entrar=false;
                }
                textView1.setText(textView1.getText() + "4");
                contador=0;
            }
        });

        button5 = (Button) root.findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (entrar==true){
                    textView2.setText("");
                    textView1.setText("");
                    entrar=false;
                }
                textView1.setText(textView1.getText() + "5");
                contador=0;
            }
        });

        button6 = (Button) root.findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (entrar==true){
                    textView2.setText("");
                    textView1.setText("");
                    entrar=false;
                }
                textView1.setText(textView1.getText() + "6");
                contador=0;
            }
        });
        button7 = (Button) root.findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (entrar==true){
                    textView2.setText("");
                    textView1.setText("");
                    entrar=false;
                }
                textView1.setText(textView1.getText() + "7");
                contador=0;
            }
        });
        button8 = (Button) root.findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (entrar==true){
                    textView2.setText("");
                    textView1.setText("");
                    entrar=false;
                }
                textView1.setText(textView1.getText() + "8");
                contador=0;
            }
        });
        button9 = (Button) root.findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (entrar==true){
                    textView2.setText("");
                    textView1.setText("");
                    entrar=false;
                }
                textView1.setText(textView1.getText() + "9");
                contador=0;
            }
        });

        buttonSuma = (Button) root.findViewById(R.id.buttonSuma);
        buttonSuma.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(contador==0 && operador==0){
                    numero= Double.parseDouble(textView1.getText().toString());
                    textView1.setText(textView1.getText() + "+");
                    contador=1;
                    identificardor='+';
                    longitud= textView1.getText().length();
                    operador=1;

                }

            }
        });

        buttonResta = (Button) root.findViewById(R.id.buttonResta);
        buttonResta.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(contador==0 && operador==0){
                    numero= Double.parseDouble(textView1.getText().toString());
                    textView1.setText(textView1.getText() + "-");
                    contador=1;
                    operador=1;
                    identificardor='-';
                    longitud= textView1.getText().length();
                }
            }
        });

        buttonMultiplicacion = (Button) root.findViewById(R.id.buttonMultiplicacion);
        buttonMultiplicacion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(contador==0 && operador==0){
                    numero= Double.parseDouble(textView1.getText().toString());
                    textView1.setText(textView1.getText() + "*");
                    contador=1;
                    operador=1;
                    identificardor='*';
                    longitud= textView1.getText().length();
                }
            }
        });

        buttonDivision = (Button) root.findViewById(R.id.buttonDivision);
        buttonDivision.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(contador==0 && operador==0){
                    numero= Double.parseDouble(textView1.getText().toString());
                    textView1.setText(textView1.getText() + "/");
                    contador=1;
                    operador=1;
                    identificardor='/';
                    longitud= textView1.getText().length();
                }
            }
        });

        buttonAC = (Button) root.findViewById(R.id.buttonAC);
        buttonAC.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                textView1.setText("");
                textView2.setText("");
                contador=0;
                longitud= 0;
                operador=0;
            }
        });

        buttonBorrar = (Button) root.findViewById(R.id.buttonBorrar);
        buttonBorrar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String cadena= (String) textView1.getText();
                if(cadena!=""){
                    String replaza = cadena.substring(cadena.length()-1);
                    //textView2.setText("Hola:"+replaza+"Adios");
                    char caracter=replaza.charAt(0);
                    if((caracter=='*')||(caracter=='+')||(caracter=='-')||(caracter=='/')||(caracter=='.')){
                        contador=0;
                        operador=0;
                    }
                    cadena =cadena.substring(0,cadena.length()-1);
                    textView1.setText(cadena);
                }

            }
        });

        buttonPunto = (Button) root.findViewById(R.id.buttonPunto);
        buttonPunto.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(contador==0){
                    textView1.setText(textView1.getText() + ".");
                    contador=1;
                }
            }
        });

        buttonPorcentaje=(Button) root.findViewById(R.id.buttonPorcentaje);
        buttonPorcentaje.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(contador==0 && operador==0){
                    numero= Double.parseDouble(textView1.getText().toString());
                    textView1.setText(textView1.getText() + "%");
                    contador=1;
                    operador=1;
                    identificardor='%';
                    longitud=textView1.getText().length();
                }
            }
        });

        buttonIgual=(Button) root.findViewById(R.id.buttonIgual);
        buttonIgual.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String cadena="";
                if (contador==0) {

                   if (identificardor == '+') {
                       double sumando = 0;
                       cadena = textView1.getText().toString();
                       cadena = cadena.substring(longitud, cadena.length());
                       sumando = Double.parseDouble(cadena.toString());
                       resultado = numero + sumando;
                       contador = 1;
                   } else if (identificardor == '-') {
                       double sumando = 0;
                       cadena = textView1.getText().toString();
                       cadena = cadena.substring(longitud, cadena.length());
                       sumando = Double.parseDouble(cadena.toString());
                       resultado = numero - sumando;
                       contador = 1;
                   } else if (identificardor == '*') {
                       double sumando = 0;
                       cadena = textView1.getText().toString();
                       cadena = cadena.substring(longitud, cadena.length());
                       sumando = Double.parseDouble(cadena.toString());
                       resultado = numero * sumando;
                       contador = 1;
                   } else if (identificardor == '/') {
                       double sumando = 0;
                       cadena = textView1.getText().toString();
                       cadena = cadena.substring(longitud, cadena.length());
                       sumando = Double.parseDouble(cadena.toString());
                       resultado = numero / sumando;
                       contador = 1;
                   } else if (identificardor=='%'){
                       double sumando = 0;
                       cadena = textView1.getText().toString();
                       cadena = cadena.substring(longitud, cadena.length());
                       sumando = Double.parseDouble(cadena.toString());
                       double porcentaje= numero*(sumando/100);
                       textView2.setText(String.valueOf(porcentaje));
                       resultado = numero-porcentaje;
                       textView2.setText(String.valueOf(resultado));
                       contador = 1;
                   }else {
                       numero= Double.parseDouble(textView1.getText().toString());
                       resultado=numero;
                   }
                    //textView2.setText(String.valueOf(resultado));
                   String comprobar = String.valueOf(resultado);
                   Boolean decimal = comprobar.endsWith(".0");
                   if (decimal==true) {
                       textView2.setText(String.valueOf(resultado).substring(0, comprobar.length() - 2));
                   }else {
                       textView2.setText(String.valueOf(resultado));
                   }
                   entrar=true;
               }
            }
        });

        return root;
    }
}