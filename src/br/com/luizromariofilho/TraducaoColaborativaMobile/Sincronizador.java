package br.com.luizromariofilho.TraducaoColaborativaMobile;

import br.com.luizromariofilho.TraducaoColaborativaMobile.entities.Texto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luiz Romario Filho on 11/27/2014.
 */
public class Sincronizador {
    private final Gson gson;
    public Sincronizador() {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }


    public void executar(){
        pullServer();
    }

    private void pullServer() {
        Tarefa tarefa;
        String ultimaSincronizacao;
        if(Util.ultimaSincronizacao != null){
            ultimaSincronizacao = new SimpleDateFormat("dd-MM-yyyyHH:mm:ss").format(Util.ultimaSincronizacao);
            tarefa = new Tarefa(Util.URL_WEBSERVICE + "/texto?ultimaSincronizacao="+ ultimaSincronizacao);
        }else{
            tarefa = new Tarefa(Util.URL_WEBSERVICE + "/texto");
        }

        tarefa.setEventListen(new TarefaEvents() {
            @Override
            public void onCompleta(String retorno) {
                TypeToken<List<Texto>> token = new TypeToken<List<Texto>>() {
                };
                List<Texto> textos= gson.fromJson(retorno, token.getType());
                for (Texto texto : textos) {
                    Texto textoLocal = FachadaBD.getInstancia().getByIdRemoto(texto.getId());
                    if(textoLocal!=null){
                        texto.setIdLocal(textoLocal.getIdLocal());
                    }
                    FachadaBD.getInstancia().salvar(texto);
                    Util.bancoSincronizado();
                }
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
        Tarefa tarefaPost;
        List<Texto> textos = FachadaBD.getInstancia().getAll();
        List<Texto> modificados = new ArrayList<Texto>();

        for (Texto texto : textos) {
            if(Util.ultimaSincronizacao==null || (texto.getUltimaAlteracao() != null && texto.getUltimaAlteracao().after(Util.ultimaSincronizacao))){
                modificados.add(texto);
            }
        }

        modificados = textos;

        for (final Texto texto : modificados) {
            String json = gson.toJson(texto);
            tarefaPost = new Tarefa(Util.URL_WEBSERVICE + "/texto",json);
            tarefaPost.setEventListen(new TarefaEvents() {
                @Override
                public void onCompleta(String retorno) {
                    texto.setId(Integer.parseInt(retorno));
                    FachadaBD.getInstancia().salvar(texto);
                }

                @Override
                public void onFalha(String retorno) {
                    System.out.println("Falha ao sincronizar.");
                }

                @Override
                public void onIniciada() {
                    System.out.println("Iniciando sincronização.");
                }
            });
            tarefaPost.executar(true);
        }

    }
}
