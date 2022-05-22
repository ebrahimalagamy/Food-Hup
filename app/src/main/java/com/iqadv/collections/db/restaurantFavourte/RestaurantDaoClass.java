package com.iqadv.collections.db.restaurantFavourte;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iqadv.collections.model.restaurantDetails.RestaurantModel;

import java.util.List;

@Dao
public interface RestaurantDaoClass {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRestaurantModel(RestaurantModel restaurantModel);

    @Query("delete from restaurant")
    void deleteRestaurantFav();

    @Query("select * from restaurant")
    List<RestaurantModel> getAllFavouriteRestaurant();

}
