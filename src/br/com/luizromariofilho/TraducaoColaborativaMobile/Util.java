package br.com.luizromariofilho.TraducaoColaborativaMobile;

import java.util.Date;

/**
 * Created by Luiz Romario Filho on 11/22/2014.
 */
public class Util {
    public static String email;
    public static Date ultimaSincronizacao;
    //public static String URL_WEBSERVICE = "http://192.168.0.105:8080/TraducaoColaborativa/rest";
    public static String URL_WEBSERVICE = "http://10.12.68.2:8080/TraducaoColaborativa/rest";
    //public static String URL_WEBSERVICE = "http://192.168.1.128:8080/TraducaoColaborativa/rest";


    public static void bancoSincronizado(){
        ultimaSincronizacao = new Date();
    }
}
