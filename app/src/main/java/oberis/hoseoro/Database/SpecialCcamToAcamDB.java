package oberis.hoseoro.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SpecialCcamToAcamDB  extends SQLiteOpenHelper {
    final static private String DB_NAME = "SpecialCcamToAcam.db";
    final static private int DB_VERSION = 2;

    public SpecialCcamToAcamDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SpecialCcamToAcam (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cCam TEXT, " +
                "terminal TEXT, " +
                "station TEXT, " +
                "hospital TEXT, " +
                "road TEXT," +
                "ktx TEXT, " +
                "aCam TEXT);");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '7:40', '7:50', '8:00', '8:05', '8:15', '8:25', '8:40');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '8:00', '8:10', '8:20', '8:25', '8:35', '8:45', '9:00');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '8:20', '8:30', '8:40', '8:45', '8:55', '9:05', '9:20');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '8:45', '8:55', '9:05', '9:10', '9:20', '9:30', '9:45');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '9:10', '9:20', '9:30', '9:35', '9:45', '9:55', '10:10');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '9:30', '9:40', '9:50', '9:55', '10:05', '10:15', '10:30');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '9:50', '10:00', '10:10', '10:15', '10:25', '10:35', '10:50');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '10:10', '10:20', '10:30', '10:35', '10:45', '10:55', '11:10');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '10:30', '10:40', '10:50', '10:55', '11:05', '11:15', '11:30');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '10:55', '11:05', '11:15', '11:20', '11:30', '11:40', '11:55');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '11:20', '11:30', '11:40', '11:45', '11:55', '12:05', '12:20');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '11:35', '11:45', '11:55', '12:00', '12:10', '12:20', '12:35');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '11:50', '12:00', '12:10', '12:15', '12:25', '12:35', '12:50');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '12:15', '12:25', '12:35', '12:40', '12:50', '13:00', '13:15');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '12:40', '12:50', '13:00', '13:05', '13:15', '13:25', '13:40');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '13:05', '13:15', '13:25', '13:30', '13:40', '13:50', '14:05');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '13:30', '13:40', '13:50', '13:55', '14:05', '14:15', '14:30');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '13:55', '14:05', '14:15', '14:20', '14:30', '14:40', '14:55');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '14:20', '14:30', '14:40', '14:45', '14:55', '15:05', '15:20');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '14:30', '14:40', '14:50', '14:55', '15:05', '15:15', '15:30');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '14:40', '14:50', '15:00', '15:05', '15:15', '15:25', '15:40');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '15:05', '15:15', '15:25', '15:30', '15:40', '15:50', '16:05');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '15:30', '15:40', '15:50', '15:55', '16:05', '16:15', '16:30');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '15:45', '15:55', '16:05', '16:10', '16:20', '16:30', '16:45');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '16:00', '16:10', '16:20', '16:25', '16:35', '16:45', '17:00');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '16:25', '16:35', '16:45', '16:50', '17:00', '17:10', '17:25');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '16:50', '17:00', '17:10', '17:15', '17:25', '17:35', '17:50');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '17:00', '17:10', '17:20', '17:25', '17:35', '17:45', '18:00');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '17:10', '17:20', '17:30', '17:35', '17:45', '17:55', '18:10');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '17:35', '17:45', '17:55', '18:00', '18:10', '18:20', '18:35');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '18:00', '18:10', '18:20', '18:25', '18:35', '18:45', '19:00');");
        db.execSQL("INSERT INTO SpecialCcamToAcam VALUES (null, '18:10', '18:20', '18:30', '18:35', '18:45', '18:55', '19:10');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SpecialCcamToAcam");
        onCreate(db);
    }
}
