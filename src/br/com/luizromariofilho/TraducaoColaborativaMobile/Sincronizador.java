package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.content.Intent;
import br.com.luizromariofilho.TraducaoColaborativaMobile.entities.Texto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Luiz Romario Filho on 11/27/2014.
 */
public class Sincronizador {

    public void executar(){
        pullServer();
        //if(Sessao.isEmptyBd){
        //    pullServer();
        //}else{
            //pushServer();
        //    pullServer();
        //}
    }

    private void pullServer() {
        Tarefa tarefa;
        String ultimaSincronizacao;
        if(Util.sincronizacao != null){
            ultimaSincronizacao = new SimpleDateFormat("dd-MM-yyyyHH:mm").format(Util.sincronizacao);
            tarefa = new Tarefa(Util.URL_WEBSERVICE + "/texto?ultimaSincronizacao="+ ultimaSincronizacao);
        }else{
            tarefa = new Tarefa(Util.URL_WEBSERVICE + "/texto");
        }

        tarefa.setEventListen(new TarefaEvents() {
            @Override
            public void onCompleta(String retorno) {
                Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                TypeToken<List<Texto>> token = new TypeToken<List<Texto>>() {
                };
                List<Texto> textos= gson.fromJson(retorno, token.getType());

                //...procurar pelo id do elemento de retorno no banco de dados local
                //se exisitir atualzar(tomar cuidado para manter o idlocal)
                //se não insere

                if(Util.sincronizacao == null){
                    for (Texto texto : textos) {
                        FachadaBD.getInstancia().salvar(texto);
                    }
                }
                Util.bancoSincronizado();
            }

            @Override
            public void onFalha(String retorno) {
                System.out.println("Erro ao sincronizar!");
            }

            @Override
            public void onIniciada() {
                System.out.println("Iniciando sincronização!");
            }
        });
        tarefa.executar(true);
    }

    private void pushServer() {
        //Localizar elementos novs (com id=null)
        //Elementos com ultma atualizacao depois da data definda na sessao

        Tarefa tarefaPost = new Tarefa(Util.URL_WEBSERVICE + "/texto","JSON OBJECT");
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
    }
}
