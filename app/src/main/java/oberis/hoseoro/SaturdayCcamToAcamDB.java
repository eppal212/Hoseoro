package oberis.hoseoro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SaturdayCcamToAcamDB extends SQLiteOpenHelper{
    final static private String DB_NAME = "SaturdayCcamToAcam.db";
    final static private int DB_VERSION = 1;

    public SaturdayCcamToAcamDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE SaturdayCcamToAcam (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "cCam TEXT, " +
                "terminal TEXT, " +
                "station TEXT, " +
                "hospital TEXT, " +
                "road TEXT," +
                "ktx TEXT, " +
                "aCam TEXT);");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '8:30', '8:40', '8:50', '9:00', '9:05', '9:10', '9:25');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '9:30', '9:40', '9:50', '10:00', '10:05', '10:10', '10:25');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '10:30', '10:40', '10:50', '11:00', '11:05', '11:10', '11:25');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '11:30', '11:40', '11:50', '12:00', '12:05', '12:10', '12:25');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '12:30', '12:40', '12:50', '13:00', '13:05', '13:10', '13:25');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '13:30', '13:40', '13:50', '14:00', '14:05', '14:10', '14:25');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '14:30', '14:40', '14:50', '15:00', '15:05', '15:10', '15:25');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '15:30', '15:40', '15:50', '16:00', '16:05', '16:10', '16:25');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '16:00', '16:10', '16:20', '16:30', '16:35', '16:40', '16:55');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '16:30', '16:40', '16:50', '17:00', '17:05', '17:10', '17:25');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '17:30', '17:40', '17:50', '18:00', '18:05', '18:10', '18:25');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '18:00', '18:10', '18:20', '18:30', '18:35', '18:40', '18:55');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '18:40', '18:50', '19:00', '19:10', '19:15', '19:20', '19:35');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '19:10', '19:20', '19:30', '19:40', '19:45', '19:50', '20:05');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '20:00', '20:10', '20:20', '20:30', '20:35', '20:40', '20:55');");
        db.execSQL("INSERT INTO SaturdayCcamToAcam VALUES (null, '20:30', '20:40', '20:50', '21:00', '21:05', '21:10', '21:25');");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS SaturdayCcamToAcam");
        onCreate(db);
    }

}
