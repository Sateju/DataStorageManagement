package jjtelechea.netmind.com.datastoragemanagement;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "MiBaseDeDatos";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Mi_Tabla";
    private static final String KEY_WORD = "Nombre";
    private static final String KEY_DESCRIPTION = "Apellidos";
    private static final String DATABASE_CREATE_COMMAND = "CREATE TABLE "
            + MyDatabase.TABLE_NAME + " (" + MyDatabase.KEY_WORD + " TEXT, "
            + MyDatabase.KEY_DESCRIPTION + " TEXT);";


    public MyDatabase(Context context) {
        super(context, MyDatabase.DATABASE_NAME, null, MyDatabase.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MyDatabase.DATABASE_CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
