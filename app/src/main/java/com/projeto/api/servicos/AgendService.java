package com.projeto.api.servicos;

import com.projeto.models.Agendamento;
import com.projeto.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface AgendService {



    @DELETE("agendamenros/{id}/")
    Call<Usuario> deletarAgend(@Header("Authorization") String key, @Path("id") Long id);
    @GET("agendamentos/")
    Call<List<Agendamento>> listarAgendAdmin(@Header("Authorization") String s);
}
