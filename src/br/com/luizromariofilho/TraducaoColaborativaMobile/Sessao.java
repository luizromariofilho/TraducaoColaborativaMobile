package br.com.luizromariofilho.TraducaoColaborativaMobile;

import java.util.Date;

/**
 * Created by Luiz Romario Filho on 11/22/2014.
 */
public class Sessao {
    public static String email;
    public static Date sincronizacao;


    public static void bancoSincronizado(){
        sincronizacao = new Date();
    }
}
