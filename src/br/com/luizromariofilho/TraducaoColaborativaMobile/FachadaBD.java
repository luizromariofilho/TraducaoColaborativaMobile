package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.luizromariofilho.TraducaoColaborativaMobile.entities.Texto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Luiz Romario Filho on 11/25/2014.
 */
public class FachadaBD extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "traducao_colaborativa";
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_TABELA = "texto";

    private static final String KEY_ID = "id";
    private static final String KEY_ID_LOCAL = "idLocal";
    private static final String KEY_TEXTO_ORIGINAL = "textoOriginal";
    private static final String KEY_TRADUZIDO = "traduzido";
    private static final String KEY_TEXTO_TRADUZIDO = "textoTraduzido";
    private static final String KEY_EMAIL_AUTOR = "emailAutor";
    private static final String KEY_EMAIL_TRADUTOR = "emailTradutor";
    private static final String KEY_DATA_SINCRONIZACAO = "ultimaSincronizacao";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    // comandos para o banco de dados
    private static final String CRIAR_TABELA_TEXTO = "CREATE TABLE " + NOME_TABELA + " (" + KEY_ID_LOCAL + " INTEGER PRIMARY KEY, " + KEY_ID + " INTEGER,"+ KEY_TEXTO_ORIGINAL + " TEXT, " + KEY_TRADUZIDO + " INT, "
                                                     + KEY_TEXTO_TRADUZIDO + " TEXT, " + KEY_EMAIL_AUTOR + " TEXT, " + KEY_EMAIL_TRADUTOR + " TEXT, " + KEY_DATA_SINCRONIZACAO +" DATETIME)";

    // criar instancia unica
    private static FachadaBD instancia = null;

    public static FachadaBD getInstancia(Context context) {
        if (instancia == null) {
            instancia = new FachadaBD(context);
        }

        return instancia;
    }

    public static FachadaBD getInstancia() {
        return instancia;
    }

    private FachadaBD(Context context) {
        super(context, NOME_BANCO, null, VERSAO_BANCO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CRIAR_TABELA_TEXTO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }


    public long salvar(Texto texto) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues campos = new ContentValues();
        if(texto.getIdLocal() != null){
            campos.put(KEY_EMAIL_AUTOR, texto.getEmailAutor());
            campos.put(KEY_EMAIL_TRADUTOR, texto.getEmailTradutor());
            campos.put(KEY_ID, texto.getId());
            campos.put(KEY_TEXTO_ORIGINAL, texto.getTextoOriginal());
            campos.put(KEY_TEXTO_TRADUZIDO, texto.getTextoTraduzido());
            campos.put(KEY_TRADUZIDO, texto.getTraduzido() ? 1 : 0);
            Date ultimaSincronizacao = texto.getUltimaSincronizacao();
            if(ultimaSincronizacao != null) {
                campos.put(KEY_DATA_SINCRONIZACAO, simpleDateFormat.format(ultimaSincronizacao));
            }
            return db.update(NOME_TABELA,campos, KEY_ID_LOCAL + " = " + texto.getIdLocal(),null);
        } else {
            campos.put(KEY_EMAIL_AUTOR, texto.getEmailAutor());
            campos.put(KEY_EMAIL_TRADUTOR, texto.getEmailTradutor());
            campos.put(KEY_ID, texto.getId());
            Integer ultimoId = getMaxId(db);
            campos.put(KEY_ID_LOCAL, ultimoId + 1);
            campos.put(KEY_TEXTO_ORIGINAL, texto.getTextoOriginal());
            campos.put(KEY_TEXTO_TRADUZIDO, texto.getTextoTraduzido());
            campos.put(KEY_TRADUZIDO, texto.getTraduzido() ? 1 : 0);
            Date ultimaSincronizacao = texto.getUltimaSincronizacao();
            if(ultimaSincronizacao != null) {
                campos.put(KEY_DATA_SINCRONIZACAO, simpleDateFormat.format(ultimaSincronizacao));
            }
            return db.insert(NOME_TABELA, null, campos);
        }
    }

    private Integer getMaxId(SQLiteDatabase db) {
        Cursor c = db.rawQuery("SELECT MAX("+ KEY_ID_LOCAL+") AS ID FROM " + NOME_TABELA, null);
        Integer id = 0;
        if (c != null) {
            boolean continuar = c.moveToFirst();
            while (continuar) {
                id = c.getInt(c.getColumnIndex("ID"));
                continuar = c.moveToNext();
            }
        }
        return id;
    }

    public Texto get(long id){
        Texto texto = null;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + NOME_TABELA + " WHERE " + KEY_ID_LOCAL + " = " + id, null);
        if (c != null) {
            boolean continuar = c.moveToFirst();
            while (continuar) {
                texto = populateTexto(c);
                continuar = c.moveToNext();
            }
        }
        return texto;
    }

    public List<Texto> getAll() {
        List<Texto> textos = new ArrayList<Texto>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + NOME_TABELA, null);
        if (c != null) {
            boolean continuar = c.moveToFirst();
            while (continuar) {
                textos.add(populateTexto(c));
                continuar = c.moveToNext();
            }
        }
        return textos;
    }

    private Texto populateTexto(Cursor cursor) {
        Texto texto = new Texto();

        texto.setId(cursor.getInt(cursor.getColumnIndex(KEY_ID)));
        texto.setIdLocal(cursor.getInt(cursor.getColumnIndex(KEY_ID_LOCAL)));
        texto.setTextoTraduzido(cursor.getString(cursor.getColumnIndex(KEY_TEXTO_TRADUZIDO)));
        texto.setTextoOriginal(cursor.getString(cursor.getColumnIndex(KEY_TEXTO_ORIGINAL)));
        texto.setEmailAutor(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_AUTOR)));
        texto.setEmailTradutor(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_TRADUTOR)));
        texto.setTraduzido(cursor.getInt(cursor.getColumnIndex(KEY_TRADUZIDO)) == 1);
        String ultimaSincronizacao = null;
        ultimaSincronizacao = cursor.getString(cursor.getColumnIndex(KEY_DATA_SINCRONIZACAO));
        if(ultimaSincronizacao != null){
            try {
                texto.setUltimaSincronizacao(simpleDateFormat.parse(ultimaSincronizacao));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return texto;
    }
}
