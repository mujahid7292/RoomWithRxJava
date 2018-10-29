package com.sand_corporation.www.roomwithrxjava.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sand_corporation.www.roomwithrxjava.R;
import com.sand_corporation.www.roomwithrxjava.RoomDB.Table.Employee;
import com.sand_corporation.www.roomwithrxjava.ViewModel.ViewModel;
import com.sand_corporation.www.roomwithrxjava.databinding.SingleEmployeeBinding;

import java.util.List;

public class CustomAdapter  extends BaseAdapter {

    private Context context;
    private List<Employee> employeeList;
    private LayoutInflater layoutInflater;
    private static final String TAG = "CustomAdapter";

    public CustomAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return employeeList.size();
    }

    @Override
    public Object getItem(int position) {
        return employeeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        SingleEmployeeBinding singleEmployeeBinding = DataBindingUtil
                .inflate(layoutInflater,R.layout.single_employee,parent,false);
        Employee employee = employeeList.get(position);
        String strFirstName = employee.getFirstName();
        String strLastName = employee.getLastName();
        Log.i(TAG,"Name: " + strFirstName +" " + strLastName);
        ViewModel model = new ViewModel(employee);
        singleEmployeeBinding.setViewModel(model);
        View view = singleEmployeeBinding.getRoot();
        return view;
    }

    public class ViewHolder{
        TextView txtFirstName, txtLastName;
    }
}
