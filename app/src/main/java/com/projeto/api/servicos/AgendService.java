package com.projeto.api.servicos;

import com.projeto.models.Agendamento;
import com.projeto.models.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;

import static com.projeto.statics.ConstantesGlobais.AGENDAMENTOS;
import static com.projeto.statics.ConstantesGlobais.MEUS_AGENDAMENTOS;

public interface AgendService {


    @PUT(AGENDAMENTOS +"{id}/")
    Call<Agendamento> editarAgend(@Header("Authorization") String key, @Path("id") Long id, @Body Agendamento agendamento);
    @DELETE(AGENDAMENTOS +"{id}/")
    Call<Agendamento> deletarAgend(@Header("Authorization") String key, @Path("id") Long id);
    @GET(AGENDAMENTOS)
    Call<List<Agendamento>> listarAgendAdmin(@Header("Authorization") String s);

    @GET(MEUS_AGENDAMENTOS)
    Call<List<Agendamento>>listarAgendporUser(@Header("Authorization")String u);

    @PUT(AGENDAMENTOS + "{id}/")
    Call<Usuario> adicionarUsuario(@Header("Authorization") String key, @Path("id") Long id, @Body Usuario usuario);
}
