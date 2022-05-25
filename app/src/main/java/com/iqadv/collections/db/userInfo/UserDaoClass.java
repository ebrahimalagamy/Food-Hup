package com.iqadv.collections.db.userInfo;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.iqadv.collections.model.LoginModel;
import com.iqadv.collections.model.restaurantDetails.RestaurantModel;

import java.util.List;

@Dao
public interface UserDaoClass {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserModel(LoginModel loginModel);

    @Query("delete from user")
    void deleteUser();

    @Query("select * from user")
    LoginModel getUserInfo();

}
