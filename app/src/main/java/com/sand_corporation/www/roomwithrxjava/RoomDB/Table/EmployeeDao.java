package com.sand_corporation.www.roomwithrxjava.RoomDB.Table;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Flowable;

@Dao()
public interface EmployeeDao {

    @Query("select * from Employee_Table")
    Flowable<List<Employee>> getEmployeeList();

    @Insert()
    void insertEmployee(Employee employee);
}
