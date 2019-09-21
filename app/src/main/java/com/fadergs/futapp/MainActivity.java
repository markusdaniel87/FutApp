package com.fadergs.futapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button button;
    private ListView lvTimes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvTimes = findViewById(R.id.lista_times);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTela();
            }
        });

        lvTimes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                openEdit( (Time) adapterView.getItemAtPosition(i) );
            }
        });

        lvTimes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                excluir( (Time) adapterView.getItemAtPosition(i)  );
                return true;
            }
        });
    }

    private void excluir(final Time time){
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Excluir Produto");
        alerta.setMessage("Confirma a exclusão do produto "
                + time.getNome() + "?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                TimeDAO.excluir(MainActivity.this, time.getId());
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
        List<Time> lista = TimeDAO.getProdutos(this);

        if ( lista.size() == 0 ){
            lvTimes.setEnabled( false );
            Time fake = new Time();
            fake.setNome("Lista Vazia!");
            lista.add( fake );
        }else {
            lvTimes.setEnabled( true );
        }

        AdapterTimes adapter = new AdapterTimes(this, lista);

        lvTimes.setAdapter( adapter );

    }

    public void openTela(){
        Intent intent =  new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void openEdit(final Time time){
        Intent intent =  new Intent(this, CadastroActivity.class);
        intent.putExtra("nome_time", time.getNome());
        intent.putExtra("time_id", time.getId());
        startActivity(intent);
    }
}
