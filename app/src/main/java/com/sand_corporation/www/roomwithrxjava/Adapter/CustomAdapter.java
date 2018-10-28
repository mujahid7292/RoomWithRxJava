package com.sand_corporation.www.roomwithrxjava.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.sand_corporation.www.roomwithrxjava.R;
import com.sand_corporation.www.roomwithrxjava.RoomDB.Table.Employee;

import java.util.List;

public class CustomAdapter  extends BaseAdapter {

    private Context context;
    private List<Employee> employeeList;
    private LayoutInflater layoutInflater;

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
        if (convertView == null){
            convertView = layoutInflater.inflate(R.layout.single_employee,null);
            holder = new ViewHolder();
            holder.txtFirstName = convertView.findViewById(R.id.txtFirstName);
            holder.txtLastName = convertView.findViewById(R.id.txtLastName);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        Employee employee = (Employee) getItem(position);
        holder.txtFirstName.setText(employee.getFirstName());
        holder.txtLastName.setText(employee.getLastName());
        return convertView;
    }

    public class ViewHolder{
        TextView txtFirstName, txtLastName;
    }
}
