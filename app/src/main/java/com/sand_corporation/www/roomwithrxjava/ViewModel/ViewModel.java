package com.sand_corporation.www.roomwithrxjava.ViewModel;

import android.content.Context;
import android.databinding.ObservableField;

import com.sand_corporation.www.roomwithrxjava.RoomDB.Table.Employee;

import java.util.Observable;

public class ViewModel extends Observable {

    private Context context;
    //This below Observable Employee Object is to get from EditText
    public ObservableField<Employee> employeeObservableField= new
            ObservableField<>();
    //This employee Object is to show in ListView
    public Employee employee;

    //This constructor for custom adapter
    public ViewModel(Employee employee) {
        this.employee = employee;
    }

    public ViewModel(Context context, Employee employee) {
        this.context = context;
        employeeObservableField.set(employee);
    }


}
