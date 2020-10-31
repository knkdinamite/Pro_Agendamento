package com.projeto.activities.Agendamento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.projeto.R;
import com.projeto.models.Agendamento;
import com.projeto.models.Usuario;
import com.projeto.adapters.AgendAdapter;

public class AgendamentoActivity extends AppCompatActivity {
    ListView agend_lista_listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agend_lista);

        Usuario usuario = Usuario.verificaUsuarioLogado();
        if (usuario != null) {
            usuario.setContext(AgendamentoActivity.this);
            agend_lista_listview.findViewById(R.id.agend_lista_listview);
            Agendamento.listarAgendRemoto(usuario,agend_lista_listview);
        }
        //Agendamento agendamento = new Agendamento(AgendamentoActivity.this,"tst","22/02/2022","10:00:00","13:00:00");
        //agendamento.setId(4L);
        //agendamento.editarAgendamento(usuario.getKey());
        //Agendamento agendamento = new Agendamento(AgendamentoActivity.this,"cha","21/08/2021","12:00:00","14:00:00");
        //agendamento.setId(3L);
        //agendamento.deletarAgendamento(usuario.getKey());
    }
}