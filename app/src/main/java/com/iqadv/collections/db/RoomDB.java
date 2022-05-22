package com.iqadv.collections.db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.iqadv.collections.db.cart.CartDaoClass;
import com.iqadv.collections.db.foodFavourite.FoodDaoClass;
import com.iqadv.collections.db.restaurantFavourte.RestaurantDaoClass;
import com.iqadv.collections.db.userInfo.UserDaoClass;
import com.iqadv.collections.model.LoginModel;
import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;

@Database(entities = {FoodDetailsModel.class, RestaurantModel.class, LoginModel.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB instance;

    public abstract FoodDaoClass getDao();
    public abstract RestaurantDaoClass getRestaurantDao();
    public abstract UserDaoClass getUserDao();
    public abstract CartDaoClass getCartFoodDao();


    public static synchronized RoomDB getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    RoomDB.class, "DATABASE")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(rCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback rCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class FoodDataAsyncTask extends AsyncTask<Void, Void, Void> {
            private FoodDaoClass foodDaoClass;
            FoodDataAsyncTask(RoomDB roomDB){
                foodDaoClass = roomDB.getDao();
            }

        @Override
        protected Void doInBackground(Void... voids) {
                foodDaoClass.insertFoodModel(new FoodDetailsModel("10","20","25",
                        "4","pizza","2.5","hema","1","uploads/foods/f3.jpeg"));
            return null;
        }
    }
}
