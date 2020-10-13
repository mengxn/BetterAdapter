package me.codego.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main_1.view.*
import me.codego.adapter.BetterAdapter
import me.codego.adapter.SingleAdapter
import me.codego.adapter.ViewHolder
import me.codego.example.bean.Animal
import me.codego.example.bean.Cat
import me.codego.example.bean.Dog

class MainActivity : AppCompatActivity() {

    companion object {

        private val TEXT_KOTLIN = "Kotlin 是一个基于 JVM 的新的编程语言，由 JetBrains 开发。\n" +
                "Kotlin可以编译成Java字节码，也可以编译成JavaScript，方便在没有JVM的设备上运行。\n" +
                "JetBrains，作为目前广受欢迎的Java IDE IntelliJ 的提供商，在 Apache 许可下已经开源其Kotlin 编程语言。\n" +
                "Kotlin已正式成为Android官方支持开发语言。"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = initAdapter()
        // recyclerView.adapter = initMultiAdapter()
    }

    private fun initAdapter(): SingleAdapter<String> {
        val adapter: SingleAdapter<String> = SingleAdapter(R.layout.item_main_1) { holder ->
            // way 1
            holder.itemView.contentTv.text = holder.data
            // way 2
            holder.setText(R.id.contentTv, holder.data)
            // way 3
            holder.getView<TextView>(R.id.contentTv)?.text = holder.data
        }
        val dataList = (0..20).map { "%d >> %s".format(it, TEXT_KOTLIN) }
        adapter.append(dataList)
        return adapter
    }

    private fun initMultiAdapter(): BetterAdapter<Animal> {
        val dataList = (0..30).map { if (it % 3 == 0) Dog(it) else Cat(it) }.toMutableList()
        val animalAdapter = AnimalAdapter()
        animalAdapter.setData(dataList)
        return animalAdapter
    }

    class AnimalAdapter : BetterAdapter<Animal>() {

        companion object {
            private const val TYPE_DOG = 1
            private const val TYPE_CAT = 2
        }

        override fun getItemViewType(position: Int): Int {
            return when (getItem(position)) {
                is Dog -> TYPE_DOG
                else -> TYPE_CAT
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<Animal> {
            return when (viewType) {
                TYPE_DOG -> DotViewHolder(inflateChildView(parent, R.layout.item_main_1))
                else -> CatViewHolder(inflateChildView(parent, R.layout.item_main_2))
            }
        }

        class DotViewHolder(view: View) : ViewHolder<Animal>(view) {

            override fun bind(data: Animal) {
                super.bind(data)
                setText(R.id.contentTv, data.getName())
            }
        }

        class CatViewHolder(view: View) : ViewHolder<Animal>(view) {
            override fun bind(data: Animal) {
                super.bind(data)
                setText(R.id.adTv, data.getName())
            }
        }

    }

}
