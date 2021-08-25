package com.example.stat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Repositorio r = new Repositorio();
    EditText a;
    EditText m;
    EditText i;
    EditText n;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        a = (EditText) findViewById(R.id.a);
        m = (EditText) findViewById(R.id.m);
        i = (EditText) findViewById(R.id.i);
        n = (EditText) findViewById(R.id.n);
    }

    public void cadastra(View view){
        String nome = a.getText().toString();
        int matricula = Integer.parseInt(m.getText().toString());
        int idade = Integer.parseInt(i.getText().toString());
        float nota = Float.parseFloat(n.getText().toString());

        r.alunos.add(new Aluno(nome, matricula, idade, nota));
    }

    public void calculaMedia(){
        int qi = 0;
        int qv = 2;
        for(Aluno a:r.alunos){
            qi++;
        }
        for(int i = 0; i<qv; i++){
            float media = 0;
            for(int j = 0; j<qi; j++){
                media = media+r[j][i];
            }
            media = media/qi;
        }
    }
    public void calculaMediana(){
        int qi = 0;
        int qv = 2;
        for(Aluno a:r.alunos){
            qi++;
        }
        for(int i = 0; i<qv; i++){
            float mediana = 0;
            float []vs = new float[qi];
            for(int j = 0; j<qi; j++){
                vs [j]= r[j][i];
            }
            Arrays.sort(vs);
            int meio = qi/2;
            if(qi%2 == 1 ){
                mediana = vs[meio];
            }
            else{
                mediana = (vs[meio-1]+vs[meio])/2;
            }
            
        }

    }

    public void calculaModa(){
        int qi = 0;
        int qv = 2;
        for(Aluno a:r.alunos){
            qi++;
        }
        for(int i = 0; i<qv; i++){
            int maximoOcorrencia = 0;
            ArrayList<Float> modas = new ArrayList<Float>();
            for(int j = 0; j<qi; j++){
                int contOcorrencia = 0;
                for(int k = 0; k<qi; k++){
                    if(r[k][i] == r[j][i] && k!=j){
                        contOcorrencia++;
                    }
                }
                if(contOcorrencia > maximoOcorrencia){
                    maximoOcorrencia = contOcorrencia;
                    modas.clear();
                    modas.add(r[j][i]);
                }
                else if(contOcorrencia == maximoOcorrencia && !modas.contains(r[j][i])){
                    modas.add(r[j][i]);
                }
            }
            String s = "Moda(s) da variável "+nv[i]+":\n";
            for(float f:modas){
                s = s+f+"\n";
            }
            
        }
    }

}
