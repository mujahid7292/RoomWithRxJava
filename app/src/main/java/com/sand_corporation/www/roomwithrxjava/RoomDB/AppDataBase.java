package com.sand_corporation.www.roomwithrxjava.RoomDB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.sand_corporation.www.roomwithrxjava.RoomDB.Table.Employee;
import com.sand_corporation.www.roomwithrxjava.RoomDB.Table.EmployeeDao;

@Database(entities = Employee.class, version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public abstract EmployeeDao getEmployeeDao();
}
