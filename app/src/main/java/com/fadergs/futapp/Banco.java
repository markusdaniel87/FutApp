package com.fadergs.futapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Banco extends SQLiteOpenHelper {

    private static final int VERSAO = 1;
    private static final String NOME = "FutApp";

    public Banco(Context contexto){
        super(contexto, NOME, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS times ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                " nome TEXT );" );
        sqLiteDatabase.execSQL( "CREATE TABLE IF NOT EXISTS jogadores ( " +
                " id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ," +
                " nome TEXT, num_camisa INTEGER, id_time INTEGER );" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}