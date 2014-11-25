package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

/**
 * Created by Luiz Romario Filho on 11/23/2014.
 */
public class ConfiguracoesActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.configuracoes_layout);
    }

    public void btnSincronizarOnClick(View view){
        this.finish();
    }
}