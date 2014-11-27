package br.com.luizromariofilho.TraducaoColaborativaMobile;

/**
 * Created with IntelliJ IDEA.
 * User: Leo
 * Date: 30/01/14
 * Time: 11:26
 * To change this template use File | Settings | File Templates.
 */
public interface TarefaEvents {
    public void onCompleta(String retorno);
    public void onFalha(String retorno);
    public void onIniciada();
}
