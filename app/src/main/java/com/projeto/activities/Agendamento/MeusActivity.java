package com.projeto.activities.Agendamento;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.projeto.R;
import com.projeto.adapters.AgendAdapter;
import com.projeto.models.Aplicacao;
import com.projeto.models.MeusAgendamentos;
import com.projeto.models.Usuario;

public class MeusActivity extends AppCompatActivity {
    ListView agend_lista_user;
    private AgendAdapter adaptador = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meus_agendamentos);

        agend_lista_user = (ListView) findViewById(R.id.agend_lista_user);

        Usuario usuario = Usuario.verificaUsuarioLogado();
        // buscar lista de agendamentos no banco interno
        
        //e necessario filtrar os agendamentos que nao possuem user
        if (usuario != null) {
            usuario.setContext(MeusActivity.this);
            MeusAgendamentos meusAgendamentos = new MeusAgendamentos(MeusActivity.this);
            meusAgendamentos.listarAgenduser(usuario, agend_lista_user);



        }
        
    }


    @Override
    public void onBackPressed() {
        Aplicacao.irParaAgendamentoActivity(MeusActivity.this);
        finish();
        super.onBackPressed();
    }

}