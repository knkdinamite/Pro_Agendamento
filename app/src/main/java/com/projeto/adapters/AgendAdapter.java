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
import com.projeto.models.Aplicacao;
import com.projeto.models.Usuario;

import java.util.List;

public class AgendAdapter extends BaseAdapter {

    Context context;
    List<Agendamento> agendamentos;
    private LayoutInflater mInflater;

    public AgendAdapter(Context context, List<Agendamento> agendamentos) {
        this.agendamentos = agendamentos;
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
        final Agendamento agendamento = agendamentos.get(position);
        agendamento.setContext(context);
        Usuario usuarioLogado = Usuario.verificaUsuarioLogado();


        final AgendAdapter.ListContent holder;
        View v = convertView;
        if (v == null) {
            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.agend_item_lista, null);
            holder = new AgendAdapter.ListContent();
            holder.agend_lista_textview_nome = (TextView) v.findViewById(R.id.agend_lista_textview_nome);
            holder.agend_item_delete = (ImageView) v.findViewById(R.id.agend_item_delete);
            holder.agend_item_lista_progressBar = (ProgressBar) v.findViewById(R.id.agend_item_lista_progressBar);
            holder.agend_item_lista_progressBar.setVisibility(View.GONE);

            holder.agend_item_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    holder.agend_item_lista_progressBar.setVisibility(View.VISIBLE);
                    holder.agend_item_delete.setVisibility(View.GONE);

                    //agendamento.deletarUsuario();
                }
            });


         ///   holder.agend_item_view.setOnClickListener(new View.OnClickListener() {
        //        @Override
         //       public void onClick(View v) {

           //         Aplicacao.irParaUsuarioDetalheActivity(usuario.getContext(),usuario.getId());
          //      }
          //  });


            v.setTag(holder);
        } else {
            holder = (AgendAdapter.ListContent) v.getTag();
        }


        holder.agend_lista_textview_nome.setText(agendamento.getNome_agendamento());

        return v;
    }

    public static class ListContent {
        TextView agend_lista_textview_nome;
        View agend_item_view;
        ImageView agend_item_delete;
        ProgressBar agend_item_lista_progressBar;
    }
}

