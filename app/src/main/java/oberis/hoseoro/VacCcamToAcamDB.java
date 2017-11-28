package oberis.hoseoro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class VacCcamToAcamDB extends SQLiteOpenHelper{
    final static private String DB_NAME = "VacCcamToAcam.db";
    final static private int DB_VERSION = 1;

    public VacCcamToAcamDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE VacCcamToAcam (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cCam TEXT, " +
                "terminal TEXT, " +
                "station TEXT, " +
                "hospital TEXT, " +
                "road TEXT," +
                "ktx TEXT, " +
                "aCam TEXT);");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '7:40', '7:50', '8:00', '8:10', '8:15', '8:20', '8:35');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '8:20', '8:30', '8:40', '8:50', '8:55', '9:00', '9:15');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '9:00', '9:10', '9:20', '9:30', '9:35', '9:40', '9:55');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '9:30', '9:40', '9:50', '10:00', '10:05', '10:10', '10:25');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '10:30', '10:40', '10:50', '11:00', '11:05', '11:10', '11:25');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '11:30', '11:40', '11:50', '12:00', '12:05', '12:10', '12:25');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '12:30', '12:40', '12:50', '13:00', '13:05', '13:10', '13:25');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '14:00', '14:10', '14:20', '14:30', '14:35', '14:40', '14:55');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '15:00', '15:10', '15:20', '15:30', '15:35', '15:40', '15:55');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '16:00', '16:10', '16:20', '16:30', '16:35', '16:40', '16:55');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '17:00', '17:10', '17:20', '17:30', '17:35', '17:40', '17:55');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '17:20', '17:30', '17:40', '17:50', '17:55', '18:00', '18:15');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '17:50', '18:00', '18:10', '18:20', '18:25', '18:30', '18:45');");
        db.execSQL("INSERT INTO VacCcamToAcam VALUES (null, '18:20', '18:30', '18:40', '18:50', '18:55', '19:00', '19:15');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS VacCcamToAcam");
        onCreate(db);
    }

}