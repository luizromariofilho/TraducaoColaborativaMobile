package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import br.com.luizromariofilho.TraducaoColaborativaMobile.entities.Texto;

/**
 * Created by Luiz Romario Filho on 11/22/2014.
 */
public class DetalhesActivity extends Activity {
    private TextView txtTextoOriginal;
    private TextView txtEmailAutor;
    private EditText txtTraducao;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_layout);

        this.txtEmailAutor = (TextView) findViewById(R.id.txtEmailAutor);
        this.txtTextoOriginal = (TextView) findViewById(R.id.txtTextoOriginal);
        this.txtTraducao = (EditText) findViewById(R.id.txtTraducao);

        Texto texto = (Texto) getIntent().getExtras().getSerializable("texto");

        this.txtTextoOriginal.setText(texto.getTextoOriginal());
        this.txtEmailAutor.setText(texto.getEmailAutor());
        this.txtTraducao.setText(texto.getTextoTraduzido());
    }
}