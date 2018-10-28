package com.sand_corporation.www.roomwithrxjava.Remote;

import com.sand_corporation.www.roomwithrxjava.RoomDB.Table.Employee;

import java.util.List;

public interface DataCallBack {
    void dataAdded();
    void errorAdded();
    void loadEmployeeList(List<Employee> employeeList);
}
