package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import br.com.luizromariofilho.TraducaoColaborativaMobile.entities.Texto;

/**
 * Created by Luiz Romario Filho on 11/23/2014.
 */
public class FormActivity extends Activity {
    private Texto texto;
    private TextView txtTextoOriginal;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_layout);
        texto = new Texto();

        this.txtTextoOriginal = (TextView) findViewById(R.id.txtTextoOriginal);
    }

    public void btnSalvarOnClick(View view){
        texto.setTextoOriginal(txtTextoOriginal.getText().toString());
        texto.setEmailAutor(Sessao.email);
        texto.setTraduzido(false);

        FachadaBD.getInstancia().salvar(texto);
        this.finish();
    }
}