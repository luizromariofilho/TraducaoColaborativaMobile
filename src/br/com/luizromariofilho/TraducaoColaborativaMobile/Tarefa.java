package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.os.AsyncTask;

public class Tarefa {

    private String endereco;
    private String params;

    public TarefaEvents tarefaEvents;

   public void setEventListen(TarefaEvents tarefaEvents){
        this.tarefaEvents  = tarefaEvents;
    }

    public Tarefa(String endereco){
        this.endereco = endereco;
        this.params = null;
    }
    public Tarefa(String endereco, String params){
        this.endereco = endereco;
        this.params = params;
    }

    private String executarPost() throws Exception{
        return JsonClienteService.Post(endereco,params);
    }
    private String executarGet() throws Exception{
        return JsonClienteService.Get(endereco);
    }

    public String executar() throws Exception{
        tarefaEvents.onIniciada();
        if(params!=null){
            return executarPost();
        }
        else{
            return executarGet();
        }
    }

    public String executar(boolean background){
        if(background){
            new HttpRequestTask(tarefaEvents).execute(this);
        }
        else{
            try{
                String retorno = executar();
                tarefaEvents.onCompleta(retorno);
                return retorno;
            }catch (Exception ex){
                ex.printStackTrace();
                tarefaEvents.onFalha("erro");
            }

        } return null;
    }

    private class HttpRequestTask extends AsyncTask<Tarefa,Void,String> {

        private TarefaEvents tarefaEvents;
        private boolean falha=false;

        public HttpRequestTask(TarefaEvents tarefaEvents){
            super();
            this.tarefaEvents = tarefaEvents;
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            try{
                return tarefas[0].executar();
            }catch (Exception ex){
                falha=true;
                return null;
            }
        }

        protected void onPostExecute(String retorno){
            if(falha){
                tarefaEvents.onFalha("falha");
            }else{
                tarefaEvents.onCompleta(retorno);
            }
        }
    }

}
