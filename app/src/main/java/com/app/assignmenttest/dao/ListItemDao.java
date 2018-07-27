package com.app.assignmenttest.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.app.assignmenttest.entity.ListItem;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Devanshu 27 july 2018
 */

@Dao
public interface ListItemDao {

    @Query("SELECT * FROM Facts")
    List<ListItem> getAll();



    @Query("SELECT COUNT(*) from Facts")
    int countUsers();

    @Insert
    void insertAll(ListItem... listItems);

    @Delete
    void delete(ListItem listItem);

    @Query("DELETE FROM Facts")
    void delete();
}
