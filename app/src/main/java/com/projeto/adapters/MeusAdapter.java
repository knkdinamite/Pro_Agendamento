package com.projeto.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.projeto.R;
import com.projeto.models.Agendamento;
import com.projeto.models.Agendamento;
import com.projeto.models.Aplicacao;
import com.projeto.models.MeusAgendamentos;
import com.projeto.models.Usuario;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MeusAdapter extends BaseAdapter {

    Context context;
    List<Agendamento> agendamentos;
    private LayoutInflater mInflater;

    public MeusAdapter(Context context, List<Agendamento> agendamento) {
        Comparator<Agendamento> comparator = new Comparator<Agendamento>() {
            @Override
            public int compare(Agendamento left, Agendamento right) {
                return (int) (left.getId() - right.getId()); // use your logic
            }
        };

        Collections.sort(agendamentos, comparator); // use the comparator as much as u want
        this.agendamentos = agendamento;
        this.context = context;
    }

    @Override
    public int getCount() {
        return agendamentos.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Agendamento agendamento = agendamentos.get(position) ;
        agendamento.setContext(context);
        Usuario usuarioLogado = Usuario.verificaUsuarioLogado();


        final MeusAdapter.ListContent holder;
        View v = convertView;
        if (v == null) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.meus_item_lista, null);
            holder = new MeusAdapter.ListContent();
            holder.meus_item_view = (View) v.findViewById(R.id.meus_item_view);
            holder.meus_lista_textview_nome = (TextView) v.findViewById(R.id.meus_lista_textview_nome);
            holder.meus_lista_textview_data = (TextView) v.findViewById(R.id.meus_lista_textview_data);
            holder.meus_lista_textview_horainicio = (TextView) v.findViewById(R.id.meus_lista_textview_horainicio);
            holder.meus_lista_textview_horafinal = (TextView) v.findViewById(R.id.meus_lista_textview_horafinal);



            holder.meus_item_view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Aplicacao.AlertarpraRemover(context,agendamento);


                }
            });


            v.setTag(holder);
        } else {
            holder = (MeusAdapter.ListContent) v.getTag();
        }



        holder.meus_lista_textview_nome.setText(agendamento.getNome_agendamento());
        holder.meus_lista_textview_data.setText(agendamento.getData());
        holder.meus_lista_textview_horainicio.setText(agendamento.getHorainicio());
        holder.meus_lista_textview_horafinal.setText(agendamento.getHorafinal());

        return v;
    }

    public static class ListContent {
        TextView meus_lista_textview_nome, meus_lista_textview_horafinal, meus_lista_textview_data, meus_lista_textview_horainicio;
        View meus_item_view;
        ImageView meus_item_delete;
        ProgressBar meus_item_lista_progressBar;

    }
}
