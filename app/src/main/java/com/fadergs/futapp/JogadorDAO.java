package com.fadergs.futapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class JogadorDAO {

    public static void inserir(Context contexto, Jogador jogador){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", jogador.getNome() );
        valores.put( "num_camisa", jogador.getNum_camisa());
        valores.put( "id_time", jogador.getId_time());

        db.insert("jogadores" , null , valores );

    }

    public static void excluir(Context contexto, int idJogador){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("jogadores", " id = " + idJogador,
                null);
    }


    public static List<Jogador> getJogadores(Context contexto, int idTime){
        List<Jogador> lista = new ArrayList<>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM jogadores WHERE id_time = "+ idTime +" ORDER BY nome ",
                null);
        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Jogador p = new Jogador();
                p.setId(  cursor.getInt( 0 ) );
                p.setNome( cursor.getString( 1 ) );
                p.setNum_camisa( cursor.getInt( 2 ) );
                lista.add( p );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }
}


