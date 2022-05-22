package com.iqadv.collections.db.foodFavourite;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iqadv.collections.model.restaurantDetails.FoodDetailsModel;

import java.util.List;

@Dao
public interface FoodDaoClass {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFoodModel(FoodDetailsModel foodDetailsModel);

    @Query("delete from food")
    void deleteFormFav();

    @Query("select * from food")
    List<FoodDetailsModel> getAllFavouriteFood();
}
