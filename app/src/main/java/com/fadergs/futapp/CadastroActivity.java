package com.fadergs.futapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {
    private EditText etNome, etQuantidade;
    private Button btnSalvar, btnJogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        etNome = (EditText) findViewById(R.id.nome);

        btnSalvar = (Button) findViewById(R.id.salvar);
        btnJogadores = (Button) findViewById(R.id.jogadores);
        btnJogadores.setVisibility(View.GONE);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });
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
//            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
//            alerta.setIcon( android.R.drawable.ic_dialog_info);
//            alerta.setTitle("Sucesso");
//            alerta.setMessage("Cadastre os jogadores clicando no botão!");
//            alerta.setPositiveButton("OK", null);
//            alerta.show();
//            etNome.setEnabled(false);
//            btnSalvar.setVisibility(View.GONE);
//            btnJogadores.setVisibility(View.VISIBLE);
        }
    }


}
