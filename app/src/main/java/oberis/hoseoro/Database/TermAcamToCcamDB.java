package oberis.hoseoro.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TermAcamToCcamDB extends SQLiteOpenHelper {
    final static private String DB_NAME = "TermAcamToCcam.db";
    final static private int DB_VERSION = 5;

    public TermAcamToCcamDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE TermAcamToCcam (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "aCam TEXT, " +
                "ktx TEXT, " +
                "road TEXT, " +
                "hospital TEXT, " +
                "station TEXT," +
                "terminal TEXT, " +
                "cCam TEXT);");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '7:45', '8:00', '8:05', '8:10', '8:20', '8:30', '8:40');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '8:00', '8:15', '8:20', '8:25', '8:35', '8:45', '8:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '8:10', '8:25', '8:30', '8:35', '8:45', '8:55', '9:05');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '8:20', '8:35', '8:40', '8:45', '8:55', '9:05', '9:15');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '8:25', '8:40', '8:45', '8:50', '9:00', '9:10', '9:20');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '8:30', '8:45', '8:50', '8:55', '9:05', '9:15', '9:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '8:40', '8:55', '9:00', '9:05', '9:15', '9:25', '9:35');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '8:50', '9:05', '9:10', '9:15', '9:25', '9:35', '9:45');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '9:00', '9:15', '9:20', '9:25', '9:35', '9:45', '9:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '9:10', '9:25', '9:30', '9:35', '9:45', '9:55', '10:05');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '9:20', '9:35', '9:40', '9:45', '9:55', '10:05', '10:15');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '9:30', '9:45', '9:50', '9:55', '10:05', '10:15', '10:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '9:40', '9:55', '10:00', '10:05', '10:15', '10:25', '10:35');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '9:50', '10:05', '10:10', '10:15', '10:25', '10:35', '10:45');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '10:00', '10:15', '10:20', '10:25', '10:35', '10:45', '10:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '10:15', '10:30', '10:35', '10:40', '10:50', '11:00', '11:10');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '10:30', '10:45', '10:50', '10:55', '11:05', '11:15', '11:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '10:45', '11:00', '11:05', '11:10', '11:20', '11:30', '11:40');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '11:00', '11:15', '11:20', '11:25', '11:35', '11:45', '11:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '11:10', '11:25', '11:30', '11:35', '11:45', '11:55', '12:05');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '11:20', '11:35', '11:40', '11:45', '11:55', '12:05', '12:15');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '11:30', '11:45', '11:50', '11:55', '12:05', '12:15', '12:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '11:45', '12:00', '12:05', '12:10', '12:20', '12:30', '12:40');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '12:00', '12:15', '12:20', '12:25', '12:35', '12:45', '12:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '12:15', '12:30', '12:35', '12:40', '12:50', '13:00', '13:10');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '12:30', '12:45', '12:50', '12:55', '13:05', '13:15', '13:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '12:45', '13:00', '13:05', '13:10', '13:20', '13:30', '13:40');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '13:00', '13:15', '13:20', '13:25', '13:35', '13:45', '13:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '13:15', '13:30', '13:35', '13:40', '13:50', '14:00', '14:10');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '13:30', '13:45', '13:50', '13:55', '14:05', '14:15', '14:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '13:45', '14:00', '14:05', '14:10', '14:20', '14:30', '14:40');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '14:00', '14:15', '14:20', '14:25', '14:35', '14:45', '14:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '14:15', '14:30', '14:35', '14:40', '14:50', '15:00', '15:10');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '14:30', '14:45', '14:50', '14:55', '15:05', '15:15', '15:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '14:45', '14:50', '15:05', '15:10', '15:20', '15:30', '15:40');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '15:00', '15:15', '15:20', '15:25', '15:35', '15:45', '15:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '15:10', '15:25', '15:30', '15:35', '15:45', '15:55', '16:05');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '15:20', '15:35', '15:40', '15:45', '15:55', '16:05', '16:15');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '15:30', '15:45', '15:50', '15:55', '16:05', '16:15', '16:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '15:40', '15:55', '16:00', '16:05', '16:15', '16:25', '16:35');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '15:50', '16:05', '16:10', '16:15', '16:25', '16:35', '16:45');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '16:00', '16:15', '16:20', '16:25', '16:35', '16:45', '16:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '16:15', '16:30', '16:35', '16:40', '16:50', '17:00', '17:10');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '16:30', '16:45', '16:50', '16:55', '17:05', '17:15', '17:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '16:35', '16:50', '16:55', '17:00', '17:10', '17:20', '17:30');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '16:40', '16:55', '17:00', '17:05', '17:15', '17:25', '17:35');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '16:50', '17:05', '17:10', '17:15', '17:25', '17:35', '17:45');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '17:00', '17:15', '17:20', '17:25', '17:35', '17:45', '17:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '17:10', '17:25', '17:30', '17:35', '17:45', '17:55', '18:05');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '17:20', '17:35', '17:40', '17:45', '17:55', '18:05', '18:15');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '17:25', '17:40', '17:45', '17:50', '18:00', '18:10', '18:20');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '17:30', '17:45', '17:50', '17:55', '18:05', '18:15', '18:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '17:35', '17:50', '17:55', '18:00', '18:10', '18:20', '18:30');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '17:40', '17:55', '18:00', '18:05', '18:15', '18:25', '18:35');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '17:45', '18:00', '18:05', '18:10', '18:20', '-', '-');"); // 특이사항
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '17:50', '18:05', '18:10', '18:15', '18:25', '18:35', '18:45');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '18:00', '18:15', '18:20', '18:25', '18:35', '18:45', '18:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '18:05', '18:20', '18:25', '18:30', '18:40', '-', '-');"); // 특이사항
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '18:10', '18:25', '18:30', '18:35', '18:45', '18:55', '19:05');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '18:20', '18:35', '18:40', '18:45', '18:55', '19:05', '19:15');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '18:30', '18:45', '18:50', '18:55', '19:05', '19:15', '19:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '18:40', '18:55', '19:00', '19:05', '19:15', '19:25', '19:35');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '18:50', '19:05', '19:10', '19:15', '19:25', '19:35', '19:45');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '19:00', '19:15', '19:20', '19:25', '19:35', '19:45', '19:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '19:15', '19:30', '19:35', '19:40', '19:50', '20:00', '20:10');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '19:30', '19:45', '19:50', '19:55', '20:05', '20:15', '20:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '19:45', '20:00', '20:05', '20:10', '20:20', '20:30', '20:40');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '20:00', '20:15', '20:20', '20:25', '20:35', '20:45', '20:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '20:20', '20:35', '20:40', '20:45', '20:55', '21:05', '21:15');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '20:30', '20:45', '20:50', '20:55', '21:05', '21:15', '21:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '20:40', '20:55', '21:00', '21:05', '21:15', '21:25', '21:35');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '21:00', '21:15', '21:20', '21:25', '21:35', '21:45', '21:55');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '21:20', '21:35', '21:40', '21:45', '21:55', '22:05', '22:15');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '21:30', '21:45', '21:50', '21:55', '22:05', '22:15', '22:25');");
        db.execSQL("INSERT INTO TermAcamToCcam VALUES (null, '21:40', '21:55', '22:00', '22:05', '22:15', '22:25', '22:35');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS TermAcamToCcam");
        onCreate(db);
    }
}
