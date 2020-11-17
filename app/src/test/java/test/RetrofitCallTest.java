package test;


import com.projeto.api.retrofit.RetrofitConfig;
import com.projeto.models.Usuario;
import org.junit.Test;
import java.io.IOException;
import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RetrofitCallTest {

    Usuario usuario = new Usuario("pedroh.mix@gmail.com","123456");

    @Test
    public void login_Success() {

        Call<Usuario> call = new RetrofitConfig().setUserService().logar(usuario);

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<Usuario> response = call.execute();
            Usuario authResponse = response.body();

            assertTrue(response.isSuccessful() && !authResponse.getKey().isEmpty());
            assertFalse(response.isSuccessful() || usuario.getKey().isEmpty());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testeRegistro() {

        Call<Usuario> call = new RetrofitConfig().setUserService().registrar(usuario);

        try {
            //Magic is here at .execute() instead of .enqueue()
            Response<Usuario> response = call.execute();
            Usuario authResponse = response.body();

            assertTrue(response.isSuccessful() && !authResponse.getKey().isEmpty());
            assertFalse(response.isSuccessful() || usuario.getKey().isEmpty());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}