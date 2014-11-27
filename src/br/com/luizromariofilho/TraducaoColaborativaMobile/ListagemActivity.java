package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import br.com.luizromariofilho.TraducaoColaborativaMobile.entities.Texto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luiz Romario Filho on 11/22/2014.
 */
public class ListagemActivity extends Activity {
    private ListView listView;
    private static List<Texto> list = new ArrayList<Texto>();
    private static final int REQUEST_DETALHES_CODE = 1;
    private static final int REQUEST_ADICIONAR_CODE = 2;
    private static final int REQUEST_CONFIGURACOES_CODE = 3;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_layout);
        listView = (ListView) findViewById(R.id.listView);
        FachadaBD.getInstancia(this);

        list = FachadaBD.getInstancia().getAll();
        listView.setAdapter(new TextoListAdapter(list, this));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Texto texto = FachadaBD.getInstancia().get(parent.getItemIdAtPosition(position));
                Intent intent = new Intent(ListagemActivity.this, DetalhesActivity.class);
                Bundle parametro = new Bundle();
                parametro.putSerializable("texto",texto);
                intent.putExtras(parametro);
                startActivityForResult(intent,REQUEST_DETALHES_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_DETALHES_CODE){
            list = FachadaBD.getInstancia().getAll();
            listView.setAdapter(new TextoListAdapter(list, this));
        }else if(requestCode == REQUEST_ADICIONAR_CODE){
            list = FachadaBD.getInstancia().getAll();
            listView.setAdapter(new TextoListAdapter(list, this));
        }
    }

    public void btnAdicionarOnClick(View view){
        Intent intent = new Intent(ListagemActivity.this, FormActivity.class);
        startActivityForResult(intent,REQUEST_ADICIONAR_CODE);
    }

    public void btnConfiguracoesOnClick(View view){
        Intent intent = new Intent(ListagemActivity.this, ConfiguracoesActivity.class);
        startActivityForResult(intent,REQUEST_CONFIGURACOES_CODE);
    }
}