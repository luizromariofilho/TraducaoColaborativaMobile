package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
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
    private Texto texto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detalhes_layout);

        texto = (Texto) getIntent().getExtras().getSerializable("texto");

        this.txtEmailAutor = (TextView) findViewById(R.id.txtEmailAutor);
        this.txtTextoOriginal = (TextView) findViewById(R.id.txtTextoOriginal);
        this.txtTraducao = (EditText) findViewById(R.id.txtTraducao);


        this.txtTextoOriginal.setText(texto.getTextoOriginal());
        this.txtEmailAutor.setText(texto.getEmailAutor());
        this.txtTraducao.setText(texto.getTextoTraduzido());
    }

    public void btnTraduzirOnClick(View view){
        texto.setTextoTraduzido(this.txtTraducao.getText().toString());
        texto.setEmailTradutor(Sessao.email);
        texto.setTraduzido(true);
        FachadaBD.getInstancia().salvar(texto);
        this.finish();
    }
}