package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private EditText txtEmail;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        this.txtEmail = (EditText) findViewById(R.id.txtEmail);
    }

    public void btnEntrarOnClick(View view){
        Sessao.email = txtEmail.getText().toString();
        Intent intent = new Intent(LoginActivity.this, ListagemActivity.class);
        startActivity(intent);
        this.finish();
    }
}