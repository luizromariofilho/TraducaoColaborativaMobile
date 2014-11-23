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
    private static final int REQUEST_CODE = 1;

    static {
        list.add(new Texto(1,"Text Example akd jaskdljasjdlask jdlkasj dklasjdklsjld jaskldjsk jdklasj dkls jdklajs dlkajs dajkl", true, "Texto Exemplo asjhdka kashdjkash jdask adjahs kadkshasdh ada d s dasdh askhd jkashdjkash dksj jas       ", "luizromariofilho@gmail.com", "leonan.teixeira@gmail.com"));
        list.add(new Texto(2,"Text Example", true, "Texto Exemplo", "luizromariofilho@gmail.com", "leonan.teixeira@gmail.com"));
        list.add(new Texto(3,"Text Example", true, "Texto Exemplo", "luizromariofilho@gmail.com", "leonan.teixeira@gmail.com"));
        list.add(new Texto(4,"Text Example", true, "Texto Exemplo", "luizromariofilho@gmail.com", "leonan.teixeira@gmail.com"));
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listagem_layout);
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new TextoListAdapter(list, this));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Texto texto = (Texto) parent.getItemAtPosition(position);
                Intent intent = new Intent(ListagemActivity.this, DetalhesActivity.class);
                Bundle parametro = new Bundle();
                parametro.putSerializable("texto",texto);
                intent.putExtras(parametro);
                startActivityForResult(intent,REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            // TODO implementar ainda
        }
    }

    public void btnAdicionarOnClick(View view){
        Intent intent = new Intent(ListagemActivity.this, FormActivity.class);
        startActivity(intent);
        this.finish();
    }
}