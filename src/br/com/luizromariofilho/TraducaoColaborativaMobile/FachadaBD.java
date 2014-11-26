package br.com.luizromariofilho.TraducaoColaborativaMobile;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import br.com.luizromariofilho.TraducaoColaborativaMobile.entities.Texto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Luiz Romario Filho on 11/25/2014.
 */
public class FachadaBD extends SQLiteOpenHelper {
    private static final String NOME_BANCO = "traducao_colaborativa";
    private static final int VERSAO_BANCO = 1;
    private static final String NOME_TABELA = "texto";

    private static final String KEY_ID = "ID";
    private static final String KEY_TEXTO_ORIGINAL = "textoOriginal";
    private static final String KEY_TRADUZIDO = "traduzido";
    private static final String KEY_TEXTO_TRADUZIDO = "textoTraduzido";
    private static final String KEY_EMAIL_AUTOR = "emailAutor";
    private static final String KEY_EMAIL_TRADUTOR = "emailTradutor";

    // comandos para o banco de dados
    private static final String CRIAR_TABELA_TEXTO = "CREATE TABLE " + NOME_TABELA + " (" + KEY_ID + " INTEGER PRIMARY KEY, " + KEY_TEXTO_ORIGINAL + " TEXT, " + KEY_TRADUZIDO + " INT, "
                                                     + KEY_TEXTO_TRADUZIDO + " TEXT, " + KEY_EMAIL_AUTOR + " TEXT, " + KEY_EMAIL_TRADUTOR + " TEXT)";

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
        if(texto.getId() != null){
            campos.put(KEY_EMAIL_AUTOR, texto.getEmailAutor());
            campos.put(KEY_EMAIL_TRADUTOR, texto.getEmailTradutor());
            campos.put(KEY_ID, texto.getId());
            campos.put(KEY_TEXTO_ORIGINAL, texto.getTextoOriginal());
            campos.put(KEY_TEXTO_TRADUZIDO, texto.getTextoTraduzido());
            campos.put(KEY_TRADUZIDO, texto.getTraduzido() ? 1 : 0);

            return db.update(NOME_TABELA,campos, KEY_ID + " = " + texto.getId(),null);
        } else {
            campos.put(KEY_EMAIL_AUTOR, texto.getEmailAutor());
            campos.put(KEY_EMAIL_TRADUTOR, texto.getEmailTradutor());
            campos.put(KEY_ID, texto.getId());
            campos.put(KEY_TEXTO_ORIGINAL, texto.getTextoOriginal());
            campos.put(KEY_TEXTO_TRADUZIDO, texto.getTextoTraduzido());
            campos.put(KEY_TRADUZIDO, texto.getTraduzido() ? 1 : 0);

            return db.insert(NOME_TABELA, null, campos);
        }
    }

    public Texto get(long id){
        Texto texto = null;
        SQLiteDatabase db = this.getReadableDatabase();

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
        texto.setTextoTraduzido(cursor.getString(cursor.getColumnIndex(KEY_TEXTO_TRADUZIDO)));
        texto.setTextoOriginal(cursor.getString(cursor.getColumnIndex(KEY_TEXTO_ORIGINAL)));
        texto.setEmailAutor(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_AUTOR)));
        texto.setEmailTradutor(cursor.getString(cursor.getColumnIndex(KEY_EMAIL_TRADUTOR)));
        texto.setTraduzido(cursor.getInt(cursor.getColumnIndex(KEY_TRADUZIDO)) == 1);
        return texto;
    }
}
