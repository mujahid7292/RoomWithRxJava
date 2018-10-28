package com.sand_corporation.www.roomwithrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.sand_corporation.www.roomwithrxjava.Adapter.CustomAdapter;
import com.sand_corporation.www.roomwithrxjava.Remote.DataCallBack;
import com.sand_corporation.www.roomwithrxjava.Remote.DataManager;
import com.sand_corporation.www.roomwithrxjava.RoomDB.Table.Employee;

import java.util.List;

public class MainActivity extends AppCompatActivity implements DataCallBack {

    private static final String TAG = "MainActivity";
    private DataManager dataManager;
    private Button btnGetData, btnSendData;
    private EditText edtFirstName, edtLastName;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //WithoutMVVMDataBinding
        dataManager = new DataManager(this);

        btnGetData = findViewById(R.id.btnGetData);
        btnSendData = findViewById(R.id.btnSendData);
        listView = findViewById(R.id.listView);
        edtFirstName = findViewById(R.id.edtFirstName);
        edtLastName = findViewById(R.id.edtLastName);

        btnSendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFirstName = edtFirstName.getText().toString();
                String strLastName = edtLastName.getText().toString();
                dataManager.addData(MainActivity.this,strFirstName, strLastName);
            }
        });

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataManager.loadData(MainActivity.this);
            }
        });
    }

    @Override
    public void dataAdded() {
        Toast.makeText(this, "Data Added", Toast.LENGTH_SHORT).show();
        edtFirstName.setText("");
        edtLastName.setText("");
    }

    @Override
    public void errorAdded() {
        Toast.makeText(this, "Room DB Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadEmployeeList(List<Employee> employeeList) {
        Log.i(TAG,"Number Of Employee: " + employeeList.size());
        CustomAdapter mAdapter =new CustomAdapter(MainActivity.this,employeeList);
        listView.setAdapter(mAdapter);
    }
}
