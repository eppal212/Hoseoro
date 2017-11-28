package oberis.hoseoro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VacAcamToCcamDB extends SQLiteOpenHelper {
    final static private String DB_NAME = "VacAcamToCcam.db";
    final static private int DB_VERSION = 1;

    public VacAcamToCcamDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE VacAcamToCcam (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "aCam TEXT, " +
                "ktx TEXT, " +
                "road TEXT, " +
                "hospital TEXT, " +
                "station TEXT," +
                "terminal TEXT, " +
                "cCam TEXT);");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '7:40', '7:55', '8:00', '8:05', '8:15', '8:25', '8:35');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '8:20', '8:35', '8:40', '8:45', '8:55', '9:05', '9:15');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '9:00', '9:15', '9:20', '9:25', '9:35', '9:45', '9:55');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '9:30', '9:45', '9:50', '9:55', '10:05', '10:15', '10:25');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '10:30', '10:45', '10:50', '10:55', '11:05', '11:15', '11:25');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '11:30', '11:45', '11:50', '11:55', '12:05', '12:15', '12:25');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '12:30', '12:45', '12:50', '12:55', '13:05', '13:15', '13:25');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '14:00', '14:15', '14:20', '14:25', '14:35', '14:45', '14:55');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '15:00', '15:15', '15:20', '15:25', '15:35', '15:45', '15:55');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '16:00', '16:15', '16:20', '16:25', '16:35', '16:45', '16:55');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '17:00', '17:15', '17:20', '17:25', '17:35', '17:45', '17:55');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '17:20', '17:35', '17:40', '17:45', '17:55', '18:05', '18:15');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '17:50', '18:05', '18:10', '18:15', '18:25', '18:35', '18:45');");
        db.execSQL("INSERT INTO VacAcamToCcam VALUES (null, '18:20', '18:35', '18:40', '18:45', '18:55', '19:05', '19:15');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS VacAcamToCcam");
        onCreate(db);
    }
}
