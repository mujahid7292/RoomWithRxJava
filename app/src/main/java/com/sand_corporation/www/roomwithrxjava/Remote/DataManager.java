package com.sand_corporation.www.roomwithrxjava.Remote;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.sand_corporation.www.roomwithrxjava.RoomDB.AppDataBase;
import com.sand_corporation.www.roomwithrxjava.RoomDB.Table.Employee;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class DataManager {
    private Context mContext;
    private AppDataBase mDB;

    public DataManager(Context mContext) {
        this.mContext = mContext;
        mDB = Room.databaseBuilder(mContext,AppDataBase.class,"employee.db").build();
    }

    public void addData(final DataCallBack dataCallBack,
                        final String firstName,
                        final String lastName){

        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                //We can not access Room DB in our main thread. For this reason we will
                //use rxJava's Completable thread to access Room DB. It is like async task.
                Employee employee1 = new Employee("Saifullah Al'"," Mujahid" );
                mDB.getEmployeeDao().insertEmployee(employee1);
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        dataCallBack.dataAdded();
                    }

                    @Override
                    public void onError(Throwable e) {
                        dataCallBack.errorAdded();
                    }
                });


    }

    public void loadData(final DataCallBack dataCallBack){
        mDB.getEmployeeDao().getEmployeeList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Employee>>() {
                    @Override
                    public void accept(List<Employee> employeeList) throws Exception {
                        dataCallBack.loadEmployeeList(employeeList);
                    }
                });
    }
}
