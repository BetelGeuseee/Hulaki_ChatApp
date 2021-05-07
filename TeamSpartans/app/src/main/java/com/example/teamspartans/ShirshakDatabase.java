package com.example.teamspartans;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;
public class ShirshakDatabase {
    MyDatabaseHelper myDatabaseHelper;
    Context con;
    ShirshakDatabase(Context context){
        this.con=context;
        myDatabaseHelper=new MyDatabaseHelper(context);
    }
    public long insertAllData(String u_name, String password, String fname, String lname, String gen, String pos,int jersey) {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDatabaseHelper.USER_NAME, u_name);
        contentValues.put(MyDatabaseHelper.PASSWORD, password);
        contentValues.put(MyDatabaseHelper.FIRST_NAME, fname);
        contentValues.put(MyDatabaseHelper.LAST_NAME, lname);
        contentValues.put(MyDatabaseHelper.GENDER, gen);
        contentValues.put(MyDatabaseHelper.POSITION, pos);
        contentValues.put(MyDatabaseHelper.JERSEY_NUMBER,jersey);
        contentValues.put(MyDatabaseHelper.MATCH_PLAYED,0);
        contentValues.put(MyDatabaseHelper.RUNS,0);
        contentValues.put(MyDatabaseHelper.WICKETS,0);
       long id= db.insert(MyDatabaseHelper.TABLE_NAME, null, contentValues);
        db.close();
        return id;
    }
    public boolean updateRuns(String uname,int runs){
        int dummy=100;
        int checker=20; //20 is just for checking the value if it changes while updating;
        String temp=getAllUsername();
        String[] tempArray=temp.split(",");
        for(int i=0;i<tempArray.length;i++){
            if(uname.equals(tempArray[i])) {
                dummy = 99;
                break;
            }
        }
        if(dummy==99) {
            SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MyDatabaseHelper.RUNS, runs);
            String[] whereargs = {uname};
            //udpate row whose username is inside uname;
            checker = db.update(MyDatabaseHelper.TABLE_NAME, contentValues, MyDatabaseHelper.USER_NAME + "=?", whereargs);
        }
        else{
            Toast.makeText(con,"Username cannot be found",Toast.LENGTH_SHORT).show();
        }
     if(checker!=20)
         return true;
     else
         return false;
    }
    public boolean updateWickets(String uname,int wickets){
        int dummy=100;
        int checker=20; //20 is just for checking the value if it changes while updating;
        String temp=getAllUsername();
        String[] tempArray=temp.split(",");
        for(int i=0;i<tempArray.length;i++){
            if(uname.equals(tempArray[i])) {
                dummy = 99;
                break;
            }
        }
        if(dummy==99) {
            SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MyDatabaseHelper.WICKETS, wickets);
            String[] whereargs = {uname};
            //udpate row whose username is inside uname;
            checker = db.update(MyDatabaseHelper.TABLE_NAME, contentValues, MyDatabaseHelper.USER_NAME + "=?", whereargs);
        }
        else{
            Toast.makeText(con,"Username cannot be found",Toast.LENGTH_SHORT).show();
        }
        if(checker!=20)
            return true;
        else
            return false;
    }
    public boolean updateMatches(String uname,int matches){
        int dummy=100;
        int checker=20; //20 is just for checking the value if it changes while updating;
        String temp=getAllUsername();
        String[] tempArray=temp.split(",");
        for(int i=0;i<tempArray.length;i++){
            if(uname.equals(tempArray[i])) {
                dummy = 99;
                break;
            }
        }
        if(dummy==99) {
            SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MyDatabaseHelper.MATCH_PLAYED, matches);
            String[] whereargs = {uname};
            //udpate row whose username is inside uname;
            checker = db.update(MyDatabaseHelper.TABLE_NAME, contentValues, MyDatabaseHelper.USER_NAME + "=?", whereargs);
        }
        else{
            Toast.makeText(con,"Username cannot be found",Toast.LENGTH_SHORT).show();
        }
        if(checker!=20)
            return true;
        else
            return false;
    }
    public boolean deleteUsername(String uname){
        int checkerPoint=20;
        SQLiteDatabase db= myDatabaseHelper.getWritableDatabase();
        String[] whereargs={uname};
       checkerPoint= db.delete(MyDatabaseHelper.TABLE_NAME,MyDatabaseHelper.USER_NAME + "=?",whereargs);
       if(checkerPoint!=20)
           return true;
       else
           return false;
    }
    public String getAllUsername(){
        StringBuffer buffer = new StringBuffer();
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        String[] columns={MyDatabaseHelper.USER_NAME};
        Cursor cursor = db.query(MyDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        while(cursor.moveToNext()){
            int index=cursor.getColumnIndex(MyDatabaseHelper.USER_NAME);
            String $allUser=cursor.getString(index);
            buffer.append($allUser+","+"");
        }
        return buffer.toString();
    }
    public String getAllPosition(){
        StringBuffer buffer = new StringBuffer();
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        String[] columns={MyDatabaseHelper.POSITION};
        Cursor cursor = db.query(MyDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        while(cursor.moveToNext()){
            int index=cursor.getColumnIndex(MyDatabaseHelper.POSITION);
            String $allPos=cursor.getString(index);
            buffer.append($allPos+","+"");
        }
        return buffer.toString();
    }
    public String getFirstAndLast(String uname){
        StringBuffer buffer = new StringBuffer();
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        String[] columns={MyDatabaseHelper.USER_NAME,MyDatabaseHelper.FIRST_NAME,MyDatabaseHelper.LAST_NAME};
        Cursor cursor =db.query(MyDatabaseHelper.TABLE_NAME,columns,myDatabaseHelper.USER_NAME+" = '"+uname+"' ",null,null,null,null);
        while(cursor.moveToNext()){
            int index=cursor.getColumnIndex(MyDatabaseHelper.FIRST_NAME);
            String $first=cursor.getString(index);
            int index2=cursor.getColumnIndex(MyDatabaseHelper.LAST_NAME);
            String $second=cursor.getString(index2);
            buffer.append($first+" "+$second);
        }
        return buffer.toString();
    }
    public String getRunsMatchesWickets(String uname){
        StringBuffer buffer=new StringBuffer();
        SQLiteDatabase db =myDatabaseHelper.getWritableDatabase();
        String[] columns={MyDatabaseHelper.USER_NAME,MyDatabaseHelper.RUNS,MyDatabaseHelper.MATCH_PLAYED,MyDatabaseHelper.WICKETS};
        Cursor cursor =db.query(MyDatabaseHelper.TABLE_NAME,columns,myDatabaseHelper.USER_NAME+" = '"+uname+"' ",null,null,null,null);
        while(cursor.moveToNext()){
            int index=cursor.getColumnIndex(MyDatabaseHelper.RUNS);
            String $runs=cursor.getString(index);
            int index2=cursor.getColumnIndex(MyDatabaseHelper.WICKETS);
            String $wickets=cursor.getString(index2);
            int index3=cursor.getColumnIndex(MyDatabaseHelper.MATCH_PLAYED);
            String $matches=cursor.getString(index3);
            buffer.append($runs+","+$wickets+","+$matches);
        }
        return buffer.toString();
    }
    public String getGenPosJersey(String uname){
        StringBuffer buffer=new StringBuffer();
        SQLiteDatabase db =myDatabaseHelper.getWritableDatabase();
        String[] columns={MyDatabaseHelper.USER_NAME,MyDatabaseHelper.GENDER,MyDatabaseHelper.POSITION,MyDatabaseHelper.JERSEY_NUMBER};
        Cursor cursor =db.query(MyDatabaseHelper.TABLE_NAME,columns,myDatabaseHelper.USER_NAME+" = '"+uname+"' ",null,null,null,null);
        while(cursor.moveToNext()){
            int index=cursor.getColumnIndex(MyDatabaseHelper.GENDER);
            String $gen=cursor.getString(index);
            int index2=cursor.getColumnIndex(MyDatabaseHelper.POSITION);
            String $pos=cursor.getString(index2);
            int index3=cursor.getColumnIndex(MyDatabaseHelper.JERSEY_NUMBER);
            String $jersey=cursor.getString(index3);
            buffer.append($gen+","+$pos+","+$jersey);
        }
        return buffer.toString();
    }
    public String getUsernameAndPassword(String uname){
        StringBuffer buffer = new StringBuffer();
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        String[] columns={MyDatabaseHelper.USER_NAME,MyDatabaseHelper.PASSWORD};
        Cursor cursor =db.query(MyDatabaseHelper.TABLE_NAME,columns,myDatabaseHelper.USER_NAME+" = '"+uname+"' ",null,null,null,null);
        while(cursor.moveToNext()){
            int index = cursor.getColumnIndex(MyDatabaseHelper.PASSWORD);
           String $passWord = cursor.getString(index);
           buffer.append($passWord);
        }
        return buffer.toString();
    }
    public String getAllData() {
        StringBuffer buffer = new StringBuffer();
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        String[] columns = {MyDatabaseHelper.UID, MyDatabaseHelper.FIRST_NAME};
        Cursor cursor = db.query(MyDatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int index1 = cursor.getColumnIndex(MyDatabaseHelper.UID);
            int id = cursor.getInt(index1);
            int index2 = cursor.getColumnIndex(MyDatabaseHelper.FIRST_NAME);
            String name = cursor.getString(index2);
            buffer.append(id + " " + name +"\n");
        }
        return buffer.toString();
    }
    static class MyDatabaseHelper extends SQLiteOpenHelper {
        private Context context;
        private static final String DATABASE_NAME = "registerdatabase";
        private static final int DATABASE_VERSION = 1;
        private static final String TABLE_NAME = "playerinfo";
        private static final String UID = "_id";
        private static final String FIRST_NAME = "firstname";
        private static final String LAST_NAME = "lastname";
        private static final String USER_NAME = "username";
        private static final String PASSWORD = "password";
        private static final String GENDER = "gender";
        private static final String POSITION = "position";
        private static final String ProfilePic ="profilepic";
        private static final String RUNS="TotalRuns";
        private static final String MATCH_PLAYED="Matches";
        private static final String WICKETS="Wickets";
        private static final String JERSEY_NUMBER="JerseyNumber";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT," + FIRST_NAME + " VARCHAR(255) ," + PASSWORD + " VARCHAR(255)," + LAST_NAME + " VARCHAR(255)," + USER_NAME + " VARCHAR(255)," + GENDER + " VARCHAR(255)," + POSITION + " VARCHAR(255)," + RUNS + " INTEGER," + WICKETS + " INTEGER," + MATCH_PLAYED + " INTEGER," + JERSEY_NUMBER + " INTEGER);";
        private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        MyDatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try {

                db.execSQL(CREATE_TABLE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                db.execSQL(DROP_TABLE);
                onCreate(db);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
