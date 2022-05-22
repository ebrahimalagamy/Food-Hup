package com.iqadv.collections.db.cart;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;

import java.util.List;

@Dao
public interface CartDaoClass {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCartFoodModel(FoodDetailsModel foodDetailsModel);

    @Query("delete from food")
    void deleteFormCart();

    @Query("select * from food")
    List<FoodDetailsModel> getAllCatFood();
}
