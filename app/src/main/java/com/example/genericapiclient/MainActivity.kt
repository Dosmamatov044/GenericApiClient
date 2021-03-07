package com.example.genericapiclient

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.genericapiclient.app.App
import com.example.genericapiclient.model.GenericModel
import com.example.genericapiclient.rv.GenericAdapter
import com.example.genericapiclient.viewModel.GenericViewModel
import kotlinx.android.synthetic.main.activity_main.*
//id не показываю,думаю не логично их показывть.
class MainActivity : AppCompatActivity() {
    private var viewModel: GenericViewModel? = null
    private lateinit var adapter: GenericAdapter
    private var mutableList: MutableList<GenericModel> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[GenericViewModel::class.java]
        (this.application as? App)?.genericApi?.let { viewModel!!.fetchQuestList(it) }
        initRecyclerView()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.setHasFixedSize(true)
        adapter = GenericAdapter(mutableList, this)
        viewModel?.mutableLiveDataList?.observe(this, {

            if (mutableList.size > 0) {
                mutableList.removeLast()
            }
            mutableList = it
            adapter.update(mutableList)
        })
        recyclerView.adapter = adapter
    }
}

