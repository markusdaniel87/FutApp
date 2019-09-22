package com.fadergs.futapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class CadastroJogadoresActivity extends AppCompatActivity {
    int time_id;
    private EditText etNome, etCamisa;
    private Button btnSalvar;
    private ListView lvJogadores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_jogadores);

        lvJogadores = findViewById(R.id.lista_jogadores);

        time_id = getIntent().getIntExtra("time_id", 0);

        etNome = (EditText) findViewById(R.id.nome);
        etCamisa = (EditText) findViewById(R.id.quantidade);

        btnSalvar = (Button) findViewById(R.id.button2);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        lvJogadores.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir( (Jogador) adapterView.getItemAtPosition(i)  );
                return true;
            }
        });
    }

    private void salvar(){
        String nome   = etNome.getText().toString();
        String camisa = etCamisa.getText().toString();

        if( nome.isEmpty() ){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon( android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve informar o nome do time.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        } else if( camisa.isEmpty() ) {
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setIcon( android.R.drawable.ic_dialog_alert);
            alerta.setTitle("Atenção!");
            alerta.setMessage("Você deve informar o número da camisa.");
            alerta.setPositiveButton("OK", null);
            alerta.show();
        } else {
            Jogador p = new Jogador();
            p.setNome( nome );
            p.setNum_camisa( Integer.parseInt(camisa) );
            p.setId_time(time_id);
            JogadorDAO.inserir( this, p );
            etNome.setText("");
            etCamisa.setText("");
            carregarLista();
        }
    }


    private void excluir(final Jogador jogador){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Produto");
        alerta.setMessage("Confirma a exclusão do jogador "
                + jogador.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                JogadorDAO.excluir( CadastroJogadoresActivity.this, jogador.getId());
                carregarLista();
            }
        });
        alerta.show();

    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarLista();
    }

    private void carregarLista(){
        List<Jogador> lista = JogadorDAO.getJogadores(this, time_id);

        if ( lista.size() == 0 ){
            lvJogadores.setEnabled( false );
            Jogador fake = new Jogador();
            fake.setNome("Lista Vazia!");
            fake.setNum_camisa(0);
            lista.add( fake );
        }else {
            lvJogadores.setEnabled( true );
        }

        AdapterJogadores adapter = new AdapterJogadores( this, lista);

        lvJogadores.setAdapter( adapter );

    }
}
