package com.sand_corporation.www.roomwithrxjava;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.sand_corporation.www.roomwithrxjava.Adapter.CustomAdapter;
import com.sand_corporation.www.roomwithrxjava.Presenter.Presenter;
import com.sand_corporation.www.roomwithrxjava.Remote.DataCallBack;
import com.sand_corporation.www.roomwithrxjava.Remote.DataManager;
import com.sand_corporation.www.roomwithrxjava.RoomDB.Table.Employee;
import com.sand_corporation.www.roomwithrxjava.ViewModel.ViewModel;
import com.sand_corporation.www.roomwithrxjava.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataCallBack {

    private static final String TAG = "MainActivity";
    private ActivityMainBinding mainBinding;
    private DataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //WithMVVMDataBinding
        mainBinding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        dataManager = new DataManager(this);
        Employee employee = new Employee("","");
        ViewModel viewModel = new ViewModel(this,employee);
        mainBinding.setViewModel(viewModel);


        mainBinding.setPresenter(new Presenter() {
            @Override
            public void sendData() {
                if (mainBinding.getViewModel().employeeObservableField.get() != null){
                    Employee employee = mainBinding.getViewModel().employeeObservableField.get() ;
                    String strFirstName =employee.getFirstName();
                    String strLastName = employee.getLastName();
                    dataManager.addData(MainActivity.this,strFirstName, strLastName);
                } else {
                    Toast.makeText(MainActivity.this, "Employee == Null",
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void getData() {
                dataManager.loadData(MainActivity.this);
            }
        });


    }

    @Override
    public void dataAdded() {
        Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
        Employee employee = new Employee("","");
        ViewModel viewModel = new ViewModel(this,employee);
        mainBinding.setViewModel(viewModel);
    }

    @Override
    public void errorAdded() {
        Toast.makeText(this, "Room DB Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadEmployeeList(List<Employee> employeeList) {
        Log.i(TAG,"Number Of Employee: " + employeeList.size());
        CustomAdapter mAdapter =new CustomAdapter(MainActivity.this,employeeList);
        mainBinding.listView.setAdapter(mAdapter);
    }
}
