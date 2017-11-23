package oberis.hoseoro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SundayAcamToCcamDB extends SQLiteOpenHelper {
    final static private String DB_NAME = "SundayAcamToCcam.db";
    final static private int DB_VERSION = 3;

    public SundayAcamToCcamDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SundayAcamToCcam (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "aCam TEXT, " +
                "ktx TEXT, " +
                "road TEXT, " +
                "hospital TEXT, " +
                "station TEXT," +
                "terminal TEXT, " +
                "cCam TEXT);");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '8:30', '8:45', '8:50', '8:55', '9:05', '9:15', '9:25');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '9:30', '9:45', '9:50', '9:55', '10:05', '10:15', '10:25');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '10:30', '10:45', '10:50', '10:55', '11:05', '11:15', '11:25');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '11:30', '11:45', '11:50', '11:55', '12:05', '12:15', '12:25');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '12:30', '12:45', '12:50', '12:55', '13:05', '13:15', '13:25');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '13:30', '13:45', '13:50', '13:55', '14:05', '14:15', '14:25');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '14:30', '14:45', '14:50', '14:55', '15:05', '15:15', '15:25');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '15:30', '15:45', '15:50', '15:55', '16:05', '16:15', '16:25');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '16:30', '16:45', '16:50', '16:55', '17:05', '17:15', '17:25');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '17:00', '17:15', '17:20', '17:25', '17:35', '17:45', '17:55');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '17:50', '18:05', '18:10', '18:15', '18:25', '18:35', '18:45');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '18:20', '18:35', '18:40', '18:45', '18:55', '19:05', '19:15');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '19:10', '19:25', '19:30', '19:35', '19:45', '19:55', '20:05');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '19:30', '19:45', '19:50', '19:55', '20:05', '20:15', '20:25');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '20:20', '20:35', '20:40', '20:45', '20:55', '21:05', '21:15');");
        db.execSQL("INSERT INTO SundayAcamToCcam VALUES (null, '20:40', '20:55', '21:00', '21:05', '21:15', '21:25', '21:35');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SundayAcamToCcam");
        onCreate(db);
    }
}
