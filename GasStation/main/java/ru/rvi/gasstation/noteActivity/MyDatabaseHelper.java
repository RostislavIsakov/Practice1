package ru.rvi.gasstation.noteActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * Класс для взаимодействия с базой данных Note.db
 *
 * @author Исаков.Р, 17ит17
 */

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Note.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_DRIVE = "note_drive";
    private static final String COLUMN_FUELD = "note_fueld";
    private static final String COLUMN_PAID = "note_paid";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_DRIVE + " TEXT, " +
                COLUMN_FUELD  + " TEXT, " +
                COLUMN_PAID + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addBook(String drive, String fueld, String paid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_DRIVE, drive);
        cv.put(COLUMN_FUELD, fueld);
        cv.put(COLUMN_PAID, paid);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Успешно добавлена!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id, String drive, String fueld, String paid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_DRIVE, drive);
        cv.put(COLUMN_FUELD, fueld);
        cv.put(COLUMN_PAID, paid);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Ошибка", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Успешное обновление!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Ошибка при удалении.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Успешное удаление.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
