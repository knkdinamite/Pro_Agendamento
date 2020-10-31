package com.projeto.models;


import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import com.orm.SugarRecord;
import com.projeto.activities.usuario.UsuarioDetalheActivity;
import com.projeto.adapters.AgendAdapter;
import com.projeto.models.Agendamento;
import com.projeto.adapters.UsuariosAdapter;
import com.projeto.api.retrofit.RetrofitConfig;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Agendamento extends SugarRecord {

    private transient Context context;
    private String nome_agendamento;
    private String data;
    private String horainicio;
    private String horafinal;


    public String getNome_agendamento() {
        return nome_agendamento;
    }

    public void setNome_agendamento(String nome_agendamento) {
        this.nome_agendamento = nome_agendamento;
    }


    public Agendamento() {

    }

    public Agendamento(Context context, String nome_agendamento, String data, String horainicio, String horafinal) {
        this.context = context;
        this.nome_agendamento = nome_agendamento;
        this.data = data;
        this.horainicio = horainicio;
        this.horafinal = horafinal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafinal() {
        return horafinal;
    }

    public void setHorafinal(String horafinal) {
        this.horafinal = horafinal;
    }

    public void adicionar() {
        //Adicionar datas e horarios ao seu proprio painel de usuario.


    }

    public void deletarAgendamento(String key) {
        // deletar do seu painel os horarios marcados da su tela.
        Call<Agendamento> call = new RetrofitConfig(this.context).setAgendService().deletarAgend( "Token "+ key,this.getId());
        call.enqueue(new Callback<Agendamento>() {

            @Override
            public void onResponse(@NonNull Call<Agendamento> call, @NonNull Response<Agendamento> response) {
                if (response.isSuccessful()) {
                    confirmarAgendDeletado();

                } else {
                    deletaragendBanco();
                    Aplicacao.irParaAgendamentoActivity(context);

            }

            }

            @Override
            public void onFailure(Call<Agendamento> call, Throwable t) {
                Log.d("Excluiu","nao foi excluido");
            }
        });
    }


    public void editarAgendamento(String key)  {
        Call<Agendamento> call = new RetrofitConfig(context).setAgendService().editarAgend("Token "+ key ,this.getId(),this);
        call.enqueue(new Callback<Agendamento>() {

            @Override
            public void onResponse(@NonNull Call<Agendamento> call, @NonNull Response<Agendamento> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Aplicacao.irParaListarUsuariosActivity(context);
                    }
                }else {
                    confirmarAgendNaoEditado(context);
                }

            }

            @Override
            public void onFailure(@NonNull Call<Agendamento> call, @NonNull Throwable t) {
                Log.e("retrofit", "Erro ao enviar o usuario:" + t.getMessage());

            }
        });
    }


    public void adicionarAgendamento() {
        //Salvar em sua paginas seus horarios em tela.



    }



    public static void listarAgendRemoto(@NotNull Usuario usuario, ListView agend_lista_listview) {
        Call<List<Agendamento>> call = new RetrofitConfig(usuario.getContext()).setAgendService().listarAgendAdmin("Token " + usuario.getKey());
        call.enqueue(new Callback<List<Agendamento>>() {
            @Override
            public void onResponse(Call<List<Agendamento>> call, Response<List<Agendamento>> response) {
                if (response.isSuccessful()) {
                    List<Agendamento> agendamentos = response.body();
                    Log.d("listarAgend", "listar");

                    AgendAdapter adaptador = new AgendAdapter(usuario.getContext(), agendamentos);
                     agend_lista_listview.setAdapter(adaptador);
                }
            }

            @Override
            public void onFailure(Call<List<Agendamento>> call, Throwable t) {
                Log.d("listarAgend", "listar");

            }
        });

    }

//Caso venha a faltar algo, vc me diz que  eu arrumo a classe.


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
    private void confirmarAgendDeletado() {
        Toast.makeText(this.context, "Agendamento Deletado", Toast.LENGTH_SHORT).show();
    }
    public void deletaragendBanco(){
        this.delete();
    }
    private static void confirmarAgendNaoEditado(Context context) {
        ((UsuarioDetalheActivity)context).esconderProgressBar();

        Toast.makeText(context, "Erro ao editar usuário", Toast.LENGTH_SHORT).show();

    }

}