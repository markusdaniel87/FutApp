package com.fadergs.futapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class TimeDAO {

    public static void inserir(Context contexto, Time time){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", time.getNome() );

        db.insert("times" , null , valores );

    }
    public static void editar(Context contexto, Time time){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put( "nome", time.getNome() );

        db.update("times", valores,
                " id = " + time.getId(), null);

    }

    public static void excluir(Context contexto, int idTime){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getWritableDatabase();
        db.delete("times", " id = " + idTime,
                null);
        db.delete("jogadores", " id_time = "+ idTime, null);
    }


    public static List<Time> getProdutos(Context contexto){
        List<Time> lista = new ArrayList<>();
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM times ORDER BY nome",
                null);
        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();
            do{
                Time p = new Time();
                p.setId(  cursor.getInt( 0 ) );
                p.setNome( cursor.getString( 1 ) );
                lista.add( p );
            }while ( cursor.moveToNext() );
        }
        return lista;
    }

    public static Time getTimeById(Context contexto, int idTime){
        Banco banco = new Banco(contexto);
        SQLiteDatabase db = banco.getReadableDatabase();

        String sql = "SELECT * FROM times WHERE id = " + idTime;
        Cursor cursor = db.rawQuery( sql ,null);

        if ( cursor.getCount() > 0 ){
            cursor.moveToFirst();

            Time p = new Time();
            p.setId(  cursor.getInt( 0 ) );
            p.setNome( cursor.getString( 1 ) );

            return p;
        }else {
            return null;
        }
    }

}


