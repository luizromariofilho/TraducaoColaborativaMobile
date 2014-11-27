package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import java.text.SimpleDateFormat;

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

        //Localizar elementos novs (com id=null)
        //Elementos com ultma atualizacao depois da data definda na sessao

        Tarefa tarefaPost = new Tarefa("http://10.100.0.102:8080/TraducaoColaborativa/rest/texto","JSON OBJECT");
        tarefaPost.setEventListen(new TarefaEvents() {
            @Override
            public void onCompleta(String retorno) {
                //Ao terminar de enviar
                //definir id de retorno para o campo id do objeo texto
            }

            @Override
            public void onFalha(String retorno) {

            }

            @Override
            public void onIniciada() {

            }
        });

        tarefaPost.executar(true);


        Tarefa tarefa = new Tarefa("http://10.100.0.102:8080/TraducaoColaborativa/rest/texto?ultimaSincronizacao="+new SimpleDateFormat("dd-MM-yyyyH:mm").format(Sessao.sincronizacao));
        tarefa.setEventListen(new TarefaEvents() {
            @Override
            public void onCompleta(String retorno) {
                //...procurar pelo id do elemento de retorno no banco de dados local
                //se exisitir atualzar(tomar cuidado para manter o idlocal)
                //se não insere
                Sessao.bancoSincronizado();
            }

            @Override
            public void onFalha(String retorno) {

            }

            @Override
            public void onIniciada() {

            }
        });
        tarefa.executar(true);

        this.finish();
    }
}