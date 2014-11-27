package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class LoginActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private EditText txtEmail;
    private TextView txtError;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);


        this.txtEmail = (EditText) findViewById(R.id.txtEmail);
        this.txtError = (TextView) findViewById(R.id.txtError);
        txtError.setVisibility(View.GONE);
    }

    public void btnEntrarOnClick(View view){
        String email = txtEmail.getText().toString();
        Sessao.email = email;
        if(emailValido(email)){
            Intent intent = new Intent(LoginActivity.this, ListagemActivity.class);
            startActivity(intent);
            this.finish();
        } else{
            txtError.setVisibility(View.VISIBLE);
        }
    }

    private Boolean emailValido(String email) {
        return Pattern.matches("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})$", email);
    }
}