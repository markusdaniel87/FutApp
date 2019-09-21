package com.fadergs.futapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {
    private EditText etNome;
    private Button btnSalvar, btnJogadores;
    int time_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        time_id = getIntent().getIntExtra("time_id", 0);

        etNome = (EditText) findViewById(R.id.nome);

        btnSalvar = (Button) findViewById(R.id.salvar);
        btnJogadores = (Button) findViewById(R.id.jogadores);

        if(time_id > 0){
            btnJogadores.setVisibility(View.VISIBLE);
            String nome_time = getIntent().getExtras().getString("nome_time");
            etNome.setText(nome_time);
        } else {
            btnJogadores.setVisibility(View.GONE);
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (time_id > 0){
                    editar();
                } else {
                    salvar();
                }
            }
        });

        btnJogadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                jogadores();
            }
        });
    }

    private void jogadores(){
        Intent intent =  new Intent(this, CadastroJogadoresActivity.class);
        intent.putExtra("time_id", time_id);
        startActivity(intent);
    }

    private void editar(){
        String nome = etNome.getText().toString();

        if( nome.isEmpty() ){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon( android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve informar o nome do time.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        }else {
            Time p = new Time();
            p.setId(time_id);
            p.setNome( nome );
            TimeDAO.editar( this, p );
            this.finish();
        }
    }

    private void salvar(){
        String nome = etNome.getText().toString();

        if( nome.isEmpty() ){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon( android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve informar o nome do time.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        }else {
            Time p = new Time();
            p.setNome( nome );
            TimeDAO.inserir( this, p );
            this.finish();
        }
    }


}
