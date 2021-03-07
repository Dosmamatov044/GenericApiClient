package com.example.genericapiclient.viewModel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.genericapiclient.api.GenericApi
import com.example.genericapiclient.model.GenericModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class GenericViewModel(private var context:Application):AndroidViewModel(context){
    private val compositeDisposable= CompositeDisposable()
    var mutableLiveDataList :MutableLiveData<MutableList<GenericModel>> =MutableLiveData(mutableListOf())
    private val list = mutableListOf<GenericModel>()

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun fetchQuestList(genericApi: GenericApi){
        compositeDisposable.add(genericApi.getGenericList().subscribeOn(Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(
                {
                    list.add(it)
                    mutableLiveDataList.value=list
                },{
        }))}
}
