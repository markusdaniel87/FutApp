package com.fadergs.futapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class AdapterJogadores extends BaseAdapter {

    private List<Jogador> listaJogadores;
    private Context context;
    private LayoutInflater inflater;

    public AdapterJogadores(Context context, List<Jogador> listaJogadores){
        this.context = context;
        this.listaJogadores = listaJogadores;
        this.inflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return listaJogadores.size();
    }

    @Override
    public Object getItem(int i) {
        return listaJogadores.get( i );
    }

    @Override
    public long getItemId(int i) {
        return listaJogadores.get( i ).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ItemSuporte item;

        if ( view == null){
            view = inflater.inflate(R.layout.layout_lista_jogadores, null);
            item = new ItemSuporte();
            item.tvId = (TextView) view.findViewById(R.id.tvId);
            item.tvNome = (TextView) view.findViewById(R.id.tvNome);
            item.tvCamisa = (TextView) view.findViewById(R.id.tvCamisa);
            item.layout = (LinearLayout) view.findViewById(R.id.layout);
            view.setTag( item );
        }else {
            item = (ItemSuporte) view.getTag();
        }

        Jogador jogador = listaJogadores.get( i );
        item.tvId.setText( String.valueOf( jogador.getId() ) );
        item.tvNome.setText( jogador.getNome()  );
        item.tvCamisa.setText( String.valueOf(jogador.getNum_camisa())  );

        if ( jogador.getNome().equals( "Lista Vazia!" )){
            item.tvId.setText( " " );
        }

        if ( i % 2 == 0 ){
            item.layout.setBackgroundColor(Color.WHITE);
        }else {
            item.layout.setBackgroundColor( Color.rgb(230, 230, 230) );
        }


        return view;
    }


    private class ItemSuporte{
        TextView tvId, tvNome, tvCamisa;
        LinearLayout layout;
    }

}
