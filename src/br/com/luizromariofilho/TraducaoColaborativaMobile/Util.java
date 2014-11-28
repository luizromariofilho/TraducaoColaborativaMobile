package br.com.luizromariofilho.TraducaoColaborativaMobile;

import java.util.Date;

/**
 * Created by Luiz Romario Filho on 11/22/2014.
 */
public class Util {
    public static String email;
    public static Date sincronizacao;
    public static String URL_WEBSERVICE = "http://192.168.0.105:8080/TraducaoColaborativa/rest";
    //public static Boolean isEmptyBd = FachadaBD.getInstancia().getAll() == null || FachadaBD.getInstancia().getAll().isEmpty();


    public static void bancoSincronizado(){
        sincronizacao = new Date();
    }
}
