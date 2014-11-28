package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;

/**
 * Created by Luiz Romario Filho on 11/23/2014.
 */
public class ConfiguracoesActivity extends Activity {
    Sincronizador sincronizador ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracoes_layout);
        sincronizador = new Sincronizador();
    }

    public void btnSincronizarOnClick(View view){
        sincronizador.executar();

    }
}