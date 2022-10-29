package top.xherror.homework.Lab2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import top.xherror.homework.R
import top.xherror.homework.databinding.ActivityLab2Binding

class Lab2Activity : AppCompatActivity() {
    inner class Item(val name:String, val num :Int)
    private val items: MutableList<Item> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLab2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        for (i in 0..99) {
            items.add(Item("这是item $i",i))
        }
        val lab1Adapter=Lab2Adapter(items)
        val mRecyclerView = binding.recyclerView

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = lab1Adapter

        val dividerItemDecoration = DividerItemDecoration(
            mRecyclerView.context,
            (mRecyclerView.layoutManager as LinearLayoutManager).orientation
        )
        mRecyclerView.addItemDecoration(dividerItemDecoration)
    }


    inner class Lab2Adapter(private val mItems: MutableList<Item>): RecyclerView.Adapter<Lab2Adapter.TextViewHolder>() {

        inner class TextViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
            private val mTextView: TextView
            init {
                mTextView = itemView.findViewById(R.id.text)
            }
            fun bind(text: String?) {
                mTextView.text = text
            }
        }

        private val toContentActivity =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { it ->
                when (it.resultCode) {
                    RESULT_OK -> {
                        it.data?.let {it0 ->
                            val text=it0.getStringExtra("param1")
                            val num=it0.getIntExtra("param2",-1)
                            Log.d("Lab1Adapter","${text.toString()}+${num.toString()}")
                            if (num!=-1) {
                                mItems[num]=Item(text!!,num)
                                notifyItemChanged(num)
                            }
                        }
                    }
                    else -> {
                    }
                }
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
            val textViewHolder= TextViewHolder(view)
            textViewHolder.itemView.setOnClickListener {
                val intent= Intent(this@Lab2Activity,ContentActivity::class.java)
                val position = textViewHolder.adapterPosition
                val item = mItems[position]
                intent.putExtra("param1",item.name)
                intent.putExtra("param2",item.num)
                toContentActivity.launch(intent)
            }
            return textViewHolder
        }

        override fun onBindViewHolder(holder: TextViewHolder, position: Int) {
            holder.bind(mItems[position].name)
        }

        override fun getItemCount(): Int {
            return mItems.size
        }

    }


}