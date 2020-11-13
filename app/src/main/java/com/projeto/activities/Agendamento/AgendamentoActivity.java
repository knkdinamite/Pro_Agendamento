package com.projeto.activities.Agendamento;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.R;
import com.projeto.adapters.AgendAdapter;
import com.projeto.models.Agendamento;
import com.projeto.models.Aplicacao;
import com.projeto.models.Usuario;
import com.projeto.activities.Agendamento.MeusActivity;

public class AgendamentoActivity extends AppCompatActivity {
    ListView agend_lista_listview;
    private AgendAdapter adaptador = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agend_lista);
        agend_lista_listview = (ListView) findViewById(R.id.agend_lista_listview);

        Usuario usuario = Usuario.verificaUsuarioLogado();
            if (usuario != null) {
                usuario.setContext(AgendamentoActivity.this);


            Agendamento.listarAgendRemoto(usuario,agend_lista_listview);



        }
        // EDITAR
        //Agendamento agendamento = new Agendamento(AgendamentoActivity.this,"tst","22/02/2022","10:00:00","13:00:00");
        //agendamento.setId(4L);
        //agendamento.editarAgendamento(usuario.getKey());

        // DELETAR
        //Agendamento agendamento = new Agendamento(AgendamentoActivity.this,"cha","21/08/2021","12:00:00","14:00:00");
        //agendamento.setId(3L);
        //agendamento.deletarAgendamento(usuario.getKey());
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_agend, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_item_meus:
                meus_agend();
                return true;
            case R.id.menu_item_voltar:
                voltar();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void meus_agend(){
        Aplicacao.irParaMeusAgends(AgendamentoActivity.this);
    }
    private void voltar() {

        Aplicacao.irParaAppActivity(AgendamentoActivity.this);
    }

}