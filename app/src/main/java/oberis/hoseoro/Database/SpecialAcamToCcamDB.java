package oberis.hoseoro.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpecialAcamToCcamDB  extends SQLiteOpenHelper {
    final static private String DB_NAME = "SpecialAcamToCcam.db";
    final static private int DB_VERSION = 1;

    public SpecialAcamToCcamDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SpecialAcamToCcam (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "aCam TEXT, " +
                "ktx TEXT, " +
                "road TEXT, " +
                "hospital TEXT, " +
                "station TEXT," +
                "terminal TEXT, " +
                "cCam TEXT);");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '7:40', '7:55', '8:05', '8:10', '8:20', '8:30', '8:40');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '8:00', '8:15', '8:25', '8:30', '8:40', '8:50', '9:00');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '8:20', '8:35', '8:45', '8:50', '9:00', '9:10', '9:20');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '8:45', '9:00', '9:10', '9:15', '9:25', '9:35', '9:45');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '9:10', '9:25', '9:35', '9:40', '9:50', '10:00', '10:10');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '9:30', '9:45', '9:55', '10:00', '10:10', '10:20', '10:30');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '9:50', '10:05', '10:15', '10:20', '10:30', '10:40', '10:50');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '10:10', '10:25', '10:35', '10:40', '10:50', '11:00', '11:10');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '10:30', '10:45', '10:55', '11:00', '11:10', '11:20', '11:30');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '10:55', '11:10', '11:20', '11:25', '11:35', '11:45', '11:55');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '11:20', '11:35', '11:45', '11:50', '12:00', '12:10', '12:20');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '11:35', '11:50', '12:00', '12:05', '12:15', '12:25', '12:35');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '11:50', '12:05', '12:15', '12:20', '12:30', '12:40', '12:50');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '12:15', '12:30', '12:40', '12:45', '12:55', '13:05', '13:15');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '12:40', '12:55', '13:05', '13:10', '13:20', '13:30', '13:40');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '13:05', '13:20', '13:30', '13:35', '13:45', '13:55', '14:05');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '13:30', '13:45', '13:55', '14:00', '14:10', '14:20', '14:30');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '13:55', '14:10', '14:20', '14:25', '14:35', '14:45', '14:55');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '14:20', '14:35', '14:45', '14:50', '15:00', '15:10', '15:20');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '14:30', '14:45', '14:55', '15:00', '15:10', '15:20', '15:30');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '14:40', '14:55', '15:05', '15:10', '15:20', '15:30', '15:40');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '15:05', '15:20', '15:30', '15:35', '15:45', '15:55', '16:05');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '15:30', '15:45', '15:55', '16:00', '16:10', '16:20', '16:30');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '15:45', '16:00', '16:10', '16:15', '16:25', '16:35', '16:45');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '16:00', '16:15', '16:25', '16:30', '16:40', '16:50', '17:00');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '16:25', '16:40', '16:50', '16:55', '17:05', '17:15', '17:25');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '16:50', '17:05', '17:15', '17:20', '17:30', '17:40', '17:50');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '17:00', '17:15', '17:25', '17:30', '17:40', '17:50', '18:00');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '17:10', '17:25', '17:35', '17:40', '17:50', '18:00', '18:10');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '17:35', '17:50', '18:00', '18:05', '18:15', '18:25', '18:35');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '18:00', '18:15', '18:25', '18:30', '18:40', '18:50', '19:00');");
        db.execSQL("INSERT INTO SpecialAcamToCcam VALUES (null, '18:10', '18:25', '18:35', '18:40', '18:50', '19:00', '19:10');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SpecialAcamToCcam");
        onCreate(db);
    }
}
