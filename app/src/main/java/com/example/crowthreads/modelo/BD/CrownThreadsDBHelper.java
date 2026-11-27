package com.example.crowthreads.modelo.BD;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.crowthreads.modelo.entity.Atuendo;

import java.util.ArrayList;
import java.util.List;

public class CrownThreadsDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "CrownThreads.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_OUTFITS = "atuendos_guardados";
    public static final String COL_ID = "id";
    public static final String COL_NOMBRE_DISENO = "nombre_diseno";
    public static final String COL_PERSONAJE = "personaje";
    public static final String COL_VESTIDO = "vestido_id";
    public static final String COL_ZAPATILLAS = "zapatillas_id";
    public static final String COL_SOMBRERO = "sombrero_id";
    public static final String COL_ARETES = "aretes_id";
    public static final String COL_PELO = "pelo_id";

    public CrownThreadsDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createOutfitsTable = "CREATE TABLE " + TABLE_OUTFITS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOMBRE_DISENO + " TEXT NOT NULL, " +
                COL_PERSONAJE + " TEXT NOT NULL, " +
                COL_VESTIDO + " INTEGER DEFAULT 0, " +
                COL_ZAPATILLAS + " INTEGER DEFAULT 0, " +
                COL_SOMBRERO + " INTEGER DEFAULT 0, " +
                COL_ARETES + " INTEGER DEFAULT 0, " +
                COL_PELO + " INTEGER DEFAULT 0)";
        db.execSQL(createOutfitsTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTFITS);
        onCreate(db);
    }

    public long guardarAtuendo(Atuendo atuendo) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NOMBRE_DISENO, atuendo.getNombreDiseno());
        values.put(COL_PERSONAJE, atuendo.getPersonaje());
        values.put(COL_VESTIDO, atuendo.getVestidoId());
        values.put(COL_ZAPATILLAS, atuendo.getZapatillasId());
        values.put(COL_SOMBRERO, atuendo.getSombreroId());
        values.put(COL_ARETES, atuendo.getAretesId());
        values.put(COL_PELO, atuendo.getPeloId());

        long resultado = db.insert(TABLE_OUTFITS, null, values);
        db.close();
        return resultado;
    }

    public List<Atuendo> obtenerTodosLosAtuendos() {
        List<Atuendo> lista = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_OUTFITS + " ORDER BY " + COL_ID + " DESC", null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Atuendo a = new Atuendo(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_NOMBRE_DISENO)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_PERSONAJE)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_VESTIDO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_ZAPATILLAS)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_SOMBRERO)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_ARETES)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_PELO))
                );
                lista.add(a);
            }
            cursor.close();
        }
        db.close();
        return lista;
    }

    public int eliminarAtuendo(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int filas = db.delete(TABLE_OUTFITS, COL_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
        return filas;
    }
}