package test;


import com.projeto.models.Agendamento;
import com.projeto.models.Usuario;
import com.projeto.api.retrofit.RetrofitConfig;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RetrofitCallTest {

   //private static Usuario usuarioTeste = new Usuario("Yan","YSM@gmail.com","123456");
    private static Usuario usuarioTeste = new Usuario("pedroh.mix@gmail.com","123456");
   //private static Usuario usuarioTeste = new Usuario("Y1","yteste@gmail.com","123456");
   // private static Usuario usuarioTeste = new Usuario("Y2","yteste@gmail.com","123456");
   // private static Usuario usuarioTeste = new Usuario("Y3","yteste@gmail.com","123456");
    // private static Usuario usuarioTeste = new Usuario("Y4","yteste@gmail.com","123456");
    private static Agendamento agendamento = new Agendamento();

    @Test
    public void A_testeRegistro() {

        Call<Usuario> call = new RetrofitConfig().setUserService().registrar(usuarioTeste);

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<Usuario> response = call.execute();
            Usuario usuario = response.body();

            if (response.isSuccessful()){
                assertNotNull(usuario);
                usuarioTeste.setId(usuario.getId());
            }else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }
    }

    @Test
    public Usuario B_login_Success() {

        Call<Usuario> call = new RetrofitConfig().setUserService().logar(usuarioTeste);

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<Usuario> response = call.execute();
            Usuario usuario = response.body();

            if (response.isSuccessful()){
                assertNotNull(usuario);
                usuarioTeste.setKey(usuario.getKey());

                return usuarioTeste;
            }else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }
        return null;
    }
    @Test
    public void C_verificarUsuarioLogadoTest() {

        Call<Usuario> call = new RetrofitConfig().setUserService().requisitarObjetoUsuario("Token "+usuarioTeste.getKey());

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<Usuario> response = call.execute();
            Usuario usuario = response.body();

            if (response.isSuccessful()){
                assertNotNull(usuario);
            }else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }

    @Test
    public void D_listarUsuarioTest() {

        Call<List<Usuario>> call = new RetrofitConfig().setUserService().listarUsuariosAdmin("Token "+usuarioTeste.getKey());

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<List<Usuario>> response = call.execute();
            List<Usuario> usuarios = response.body();

            if (response.isSuccessful()){
                assertNotNull(usuarios);
            }else {
                fail();
            }


        } catch (IOException e) {
            fail();
            e.printStackTrace();
        }

    }


}