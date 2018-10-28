package com.sand_corporation.www.roomwithrxjava.ViewModel;

import android.content.Context;
import android.databinding.ObservableField;

import com.sand_corporation.www.roomwithrxjava.RoomDB.Table.Employee;

import java.util.Observable;

public class ViewModel extends Observable {

    private Context context;
    public ObservableField<Employee> employeeObservableField = new ObservableField<>();

    public ViewModel(Context context) {
        this.context = context;
    }
}
