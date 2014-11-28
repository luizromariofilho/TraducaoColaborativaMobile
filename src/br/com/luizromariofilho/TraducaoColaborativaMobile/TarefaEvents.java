package br.com.luizromariofilho.TraducaoColaborativaMobile;

public interface TarefaEvents {
    public void onCompleta(String retorno);
    public void onFalha(String retorno);
    public void onIniciada();
}
