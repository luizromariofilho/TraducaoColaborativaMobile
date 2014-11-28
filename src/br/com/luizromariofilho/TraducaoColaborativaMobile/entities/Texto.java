package br.com.luizromariofilho.TraducaoColaborativaMobile.entities;

import java.io.Serializable;
import java.util.Date;

public class Texto implements Serializable {
    private Integer id;
    private Integer idLocal;
    private String textoOriginal;
    private Boolean traduzido;
    private String textoTraduzido;
    private String emailAutor;
    private String emailTradutor;
    private Date ultimaSincronizacao;

    public Texto() {
    }

    public Texto(Integer id, String textoOriginal, Boolean traduzido, String textoTraduzido, String emailAutor, String emailTradutor) {
        this.id = id;
        this.textoOriginal = textoOriginal;
        this.traduzido = traduzido;
        this.textoTraduzido = textoTraduzido;
        this.emailAutor = emailAutor;
        this.emailTradutor = emailTradutor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTextoOriginal() {
        return textoOriginal;
    }

    public void setTextoOriginal(String textoOriginal) {
        this.textoOriginal = textoOriginal;
    }

    public Boolean getTraduzido() {
        return traduzido;
    }

    public void setTraduzido(Boolean traduzido) {
        this.traduzido = traduzido;
    }

    public String getTextoTraduzido() {
        return textoTraduzido;
    }

    public void setTextoTraduzido(String textoTraduzido) {
        this.textoTraduzido = textoTraduzido;
    }

    public String getEmailAutor() {
        return emailAutor;
    }

    public void setEmailAutor(String emailAutor) {
        this.emailAutor = emailAutor;
    }

    public String getEmailTradutor() {
        return emailTradutor;
    }

    public void setEmailTradutor(String emailTradutor) {
        this.emailTradutor = emailTradutor;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public Date getUltimaSincronizacao() {
        return ultimaSincronizacao;
    }

    public void setUltimaSincronizacao(Date ultimaSincronizacao) {
        this.ultimaSincronizacao = ultimaSincronizacao;
    }
}
