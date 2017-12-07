package oberis.hoseoro.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HolidayCcamToAcamDB extends SQLiteOpenHelper{
    final static private String DB_NAME = "HolidayCcamToAcam.db";
    final static private int DB_VERSION = 10;

    public HolidayCcamToAcamDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE HolidayCcamToAcam (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cCam TEXT, " +
                "terminal TEXT, " +
                "station TEXT, " +
                "hospital TEXT, " +
                "road TEXT," +
                "ktx TEXT, " +
                "aCam TEXT);");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '8:30', '8:40', '8:50', '8:55', '9:00', '9:10', '9:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '9:30', '9:40', '9:50', '9:55', '10:00', '10:10', '10:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '10:00', '10:10', '10:20', '10:25', '10:30', '10:40', '10:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '10:30', '10:40', '10:50', '10:55', '11:00', '11:10', '11:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '11:00', '11:10', '11:20', '11:25', '11:30', '11:40', '11:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '11:30', '11:40', '11:50', '11:55', '12:00', '12:10', '12:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '12:00', '12:10', '12:20', '12:25', '12:30', '12:40', '12:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '13:00', '13:10', '13:20', '13:25', '13:30', '13:40', '13:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '13:30', '13:40', '13:50', '13:55', '14:00', '14:10', '14:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '14:00', '14:10', '14:20', '14:25', '14:30', '14:40', '14:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '14:30', '14:40', '14:50', '14:55', '15:00', '15:10', '15:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '15:00', '15:10', '15:20', '15:25', '15:30', '15:40', '15:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '15:30', '15:40', '15:50', '15:55', '16:00', '16:10', '16:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '16:00', '16:10', '16:20', '16:25', '16:30', '16:40', '16:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '16:30', '16:40', '16:50', '16:55', '17:00', '17:10', '17:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '17:00', '17:10', '17:20', '17:25', '17:30', '17:40', '17:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '17:30', '17:40', '17:50', '17:55', '18:00', '18:10', '18:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '18:00', '18:10', '18:20', '18:25', '18:30', '18:40', '18:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '18:30', '18:40', '18:50', '18:55', '19:00', '19:10', '19:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '19:00', '19:10', '19:20', '19:25', '19:30', '19:40', '19:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '19:30', '19:40', '19:50', '19:55', '20:00', '20:10', '20:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '20:00', '20:10', '20:20', '20:25', '20:30', '20:40', '20:55');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '20:30', '20:40', '20:50', '20:55', '21:00', '21:10', '21:25');");
        db.execSQL("INSERT INTO HolidayCcamToAcam VALUES (null, '21:00', '21:10', '21:20', '21:25', '21:30', '21:40', '21:55');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS HolidayCcamToAcam");
        onCreate(db);
    }

}
