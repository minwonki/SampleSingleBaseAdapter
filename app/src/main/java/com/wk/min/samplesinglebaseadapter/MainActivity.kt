package com.wk.min.samplesinglebaseadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.util.Consumer
import androidx.databinding.DataBindingUtil
import com.wk.min.samplesinglebaseadapter.databinding.ActivityMainBinding
import com.wk.min.samplesinglebaseadapter.databinding.LayoutSingleRowBinding
import com.wk.min.samplesinglebaseadapter.model.SingleRowModel
import com.wk.min.samplesinglebaseadapter.ui.BaseRecyclerView

class MainActivity : AppCompatActivity() {

    private val singleAdapter =
        BaseRecyclerView.Adapter<SingleRowModel, LayoutSingleRowBinding>(
            layoutResId = R.layout.layout_single_row,
            variableId = BR.singleItem,
            callback = Consumer {
                Toast.makeText(this@MainActivity, it.title, Toast.LENGTH_SHORT).show()
            })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main).apply {
            textView.text = getString(R.string.header)
            listView.adapter = singleAdapter
        }
    }

    override fun onStart() {
        super.onStart()

        val list = ArrayList<SingleRowModel>()
        list.add(SingleRowModel("1st title", "1st content"))
        list.add(SingleRowModel("2st title", "2st content"))
        list.add(SingleRowModel("3st title", "3st content"))

        singleAdapter.bindList(list)
        singleAdapter.notifyDataSetChanged()
    }
}
