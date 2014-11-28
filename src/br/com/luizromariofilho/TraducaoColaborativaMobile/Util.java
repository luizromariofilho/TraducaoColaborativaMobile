package br.com.luizromariofilho.TraducaoColaborativaMobile;

import java.util.Date;

/**
 * Created by Luiz Romario Filho on 11/22/2014.
 */
public class Util {
    public static String email;
    public static Date ultimaSincronizacao;
    public static String URL_WEBSERVICE = "http://10.0.3.2:8080/TraducaoColaborativa/rest";


    public static void bancoSincronizado(){
        ultimaSincronizacao = new Date();
    }
}
