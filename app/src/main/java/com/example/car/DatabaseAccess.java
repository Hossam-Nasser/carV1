package com.example.car;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;

public class DatabaseAccess {

    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;
    private static  DatabaseAccess instance;



    private DatabaseAccess(Context context){
        this.openHelper=new MyDatabase(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance != null){
            instance=new DatabaseAccess(context);
        }
        return instance;
    }

    public void open (){
          this.database= this.openHelper.getWritableDatabase();
         // this.database= this.openHelper.getReadableDatabase();
    }
    public void close (){
        if(this.database!=null) {
            this.database.close();
        }
    }

    //insert
    public boolean insertCar(CarModel car) {


        ContentValues values = new ContentValues();
        values.put(MyDatabase.CAR_CLN_MODEL, car.getModel());
        values.put(MyDatabase.CAR_CLN_COLOR, car.getColor());
        values.put(MyDatabase.CAR_CLN_Dpl, car.getDpl());
        values.put(MyDatabase.CAR_CLN_IMAGE, car.getImage());
        values.put(MyDatabase.CAR_CLN_Description, car.getDescription());

        long result = database.insert(MyDatabase.CAR_CLN_NAME, null, values);
        return result != -1;
    }

    //updateCar
    public boolean updateCar(CarModel car) {


        ContentValues values = new ContentValues();
        values.put(MyDatabase.CAR_CLN_MODEL, car.getModel());
        values.put(MyDatabase.CAR_CLN_COLOR, car.getColor());
        values.put(MyDatabase.CAR_CLN_Dpl, car.getDpl());
        values.put(MyDatabase.CAR_CLN_IMAGE, car.getImage());
        values.put(MyDatabase.CAR_CLN_Description, car.getDescription());

        String args [] ={car.getId()+""};
        long result = database.update(MyDatabase.CAR_CLN_NAME,values,"id=?",args);
        return result > -1;
    }


//    public long getcarsCout(){
//        //SQLiteDatabase db = getReadableDatabase();
//
//        return DatabaseUtils.queryNumEntries(database,MyDatabase.CAR_CLN_NAME);
//
//    }

    //delete
    public boolean deleteCar(CarModel car) {

        String args [] ={String.valueOf(car.getId())};
        long result = database.delete(MyDatabase.CAR_CLN_NAME,"id=?",args);
        return result > 0;
    }

    //retrieve
    public ArrayList<CarModel> getallcar(){
        ArrayList<CarModel> cars= new ArrayList<>();

   //     SQLiteDatabase db =openHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+MyDatabase.CAR_CLN_NAME,null);

        if(cursor!= null && cursor.moveToNext()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(MyDatabase.CAR_CLN_ID));
                String model = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_MODEL));
                String color = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_COLOR));
                double Dpl = cursor.getDouble(cursor.getColumnIndex(MyDatabase.CAR_CLN_Dpl));
                String image = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_IMAGE));
                String description = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_Description));



                CarModel car =new CarModel(id,model,color,Dpl,image,description);
                cars.add(car);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return cars;
   }

    //search
    public ArrayList<CarModel> getcar(String modelSearch){
        ArrayList<CarModel> cars= new ArrayList<>();

        //SQLiteDatabase db =getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+MyDatabase.CAR_CLN_NAME+ " WHERE "+
                MyDatabase.CAR_CLN_MODEL+ "=?",new String[]{modelSearch});

        if(cursor!= null && cursor.moveToNext()){
            do{
                int id = cursor.getInt(cursor.getColumnIndex(MyDatabase.CAR_CLN_ID));
                String model = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_MODEL));
                String color = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_COLOR));
                double Dpl = cursor.getDouble(cursor.getColumnIndex(MyDatabase.CAR_CLN_Dpl));
                String image = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_IMAGE));
                String description = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_Description));


                CarModel car =new CarModel(id,model,color,Dpl,image,description);
                cars.add(car);
            } while (cursor.moveToNext());
            cursor.close();
        }

        return cars;
    }

    //retrieve
    public CarModel getcar(int carId){

        //     SQLiteDatabase db =openHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM "+MyDatabase.CAR_CLN_NAME+ " WHERE "+
                MyDatabase.CAR_CLN_MODEL+ "=?",new String[]{String.valueOf(carId)});

        if(cursor!= null && cursor.moveToNext()){

                int id = cursor.getInt(cursor.getColumnIndex(MyDatabase.CAR_CLN_ID));
                String model = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_MODEL));
                String color = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_COLOR));
                double Dpl = cursor.getDouble(cursor.getColumnIndex(MyDatabase.CAR_CLN_Dpl));
                String image = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_IMAGE));
                String description = cursor.getString(cursor.getColumnIndex(MyDatabase.CAR_CLN_Description));

                CarModel c =new CarModel(id,model,color,Dpl,image,description);

            cursor.close();
            return c;
        }

        return null;
    }
}
