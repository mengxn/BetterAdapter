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
import me.codego.adapter.*
import me.codego.example.bean.Animal
import me.codego.example.bean.Cat
import me.codego.example.bean.Dog
import me.codego.example.bean.Pig
import me.codego.example.databinding.ItemMain3Binding

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
        // recyclerView.adapter = initAdapter()
        // recyclerView.adapter = initDataBindingAdapter()
        // recyclerView.adapter = initMultiAdapter()
        recyclerView.adapter = initMultiAdapter2()
    }

    /**
     * 基础单类型适配器
     */
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

    /**
     * 基础单类型适配器，使用 ViewDataBinding 绑定 View
     */
    private fun initDataBindingAdapter(): SingleAdapter2<ItemMain3Binding, String> {
        val adapter = SingleAdapter2<ItemMain3Binding, String>(R.layout.item_main_3) { holder ->
            holder.getBinding().contentTv.text = holder.data
        }
        val dataList = (0..20).map { "%d >> %s".format(it, TEXT_KOTLIN) }
        adapter.append(dataList)
        return adapter
    }

    /**
     * 继承 BetterAdapter 处理多类型布局
     */
    private fun initMultiAdapter(): BetterAdapter<Animal> {
        val dataList = (0..30).map { if (it % 3 == 0) Dog(it) else Cat(it) }.toMutableList()
        val animalAdapter = AnimalAdapter()
        animalAdapter.setData(dataList)
        return animalAdapter
    }

    /**
     * 使用 MultiAdapter 处理多类型布局
     */
    private fun initMultiAdapter2(): BetterAdapter<Animal> {
        val dataList = (0..99).map {
            when (it % 3) {
                0 -> Dog(it)
                1 -> Cat(it)
                else -> Pig(it)
            }
        }
        return MultiAdapter<Animal> {

            makeViewType { data ->
                when (data) {
                    is Dog -> 1
                    is Cat -> 2
                    is Pig -> 3
                }
            }

            addViewHolder(1, R.layout.item_main_3) { holder ->
                val dog = holder.data as? Dog
                (holder.getBinding() as ItemMain3Binding).contentTv.text = dog?.getName()
            }
            addViewHolder(2, R.layout.item_main_3) { holder ->
                val cat = holder.data as? Cat
                (holder.getBinding() as ItemMain3Binding).contentTv.text = cat?.getName()
            }
            addViewHolder(3, R.layout.item_main_3) { holder ->
                val pig = holder.data as? Pig
                (holder.getBinding() as ItemMain3Binding).contentTv.text = pig?.getName()
            }
        }.apply {
            setData(dataList)
        }
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
