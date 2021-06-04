package sg.edu.np.mad.practical3;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper
{
    public static String DATABASE_NAME = "users.db";
    public static String USER = "user";
    public static String COLUMN_NAME = "name";
    public static String COLUMN_DESC = "description";
    public static String COLUMN_ID = "id";
    public static String COLUMN_FOLLOWED = "followed";
    public static int DATABASE_VERSION = 1;

    public DBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        String CREATE_USER_TABLE = "CREATE TABLE " + USER +
                "(" + COLUMN_NAME + " TEXT," + COLUMN_DESC + " TEXT," + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_FOLLOWED + " INTEGER DEFAULT 0" + ")";

        db.execSQL(CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + USER);
        onCreate(db);
    }

    public void addUser(User user){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESC, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.isFollowed());

        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(USER, null, values);
        db.close();

    }

    public ArrayList<User> findUser(){
        String query = "SELECT * FROM " + USER;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<User> userList = new ArrayList<>();

        if(cursor.moveToFirst()){
            for(int i = 0; i < cursor.getCount(); i++){
                User queryData = new User();

                queryData.setName(cursor.getString(0));
                queryData.setDescription(cursor.getString(1));
                queryData.setId(cursor.getInt(2));
                if(cursor.getInt(3) == 0){
                    queryData.setFollowed(false);
                }
                else {
                    queryData.setFollowed(true);
                }
                userList.add(queryData);
                cursor.moveToNext();


            }







            cursor.close();


        }
        else {
            userList = null;
        }
        db.close();
        return userList;
    }

    public void updateUser(User user){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, user.getName());
        values.put(COLUMN_DESC, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.isFollowed());
        db.update(USER, values, COLUMN_ID + "= " + user.getId(), null);
        db.close();

    }



}
