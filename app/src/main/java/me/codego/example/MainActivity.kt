package me.codego.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main_1.view.*
import kotlinx.android.synthetic.main.item_main_2.view.*
import me.codego.adapter.ITypeFactory
import me.codego.adapter.MultiAdapter
import me.codego.adapter.SingleAdapter
import me.codego.adapter.ViewHolder

class MainActivity : AppCompatActivity() {

    private val TEXT_KOTLIN = "Kotlin 是一个基于 JVM 的新的编程语言，由 JetBrains 开发。\n" +
            "Kotlin可以编译成Java字节码，也可以编译成JavaScript，方便在没有JVM的设备上运行。\n" +
            "JetBrains，作为目前广受欢迎的Java IDE IntelliJ 的提供商，在 Apache 许可下已经开源其Kotlin 编程语言。\n" +
            "Kotlin已正式成为Android官方支持开发语言。"
    private val TEXT_AD = "Hi，这里是广告～"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.adapter = initAdapter()
//        recyclerView.adapter = initMultiAdapter()
    }

    private fun initMultiAdapter(): MultiAdapter<String> {
        val dataList = (0..30).map { if (it % 2 == 0) TEXT_KOTLIN else TEXT_AD }.toMutableList()
        val typeFactory = object : ITypeFactory<String> {

            override fun type(data: String): ITypeFactory.TypeData {
                // 简单进行区分类型
                if (data.length > 10) {
                    return ITypeFactory.TypeData(1, R.layout.item_main_1)
                }
                return ITypeFactory.TypeData(2, R.layout.item_main_2)
            }

            override fun createViewHolder(view: View, type: Int): ViewHolder<String> {
                return when (type) {
                    1 -> ViewHolder<String>(view) { view1, s -> view1.contentTv.text = s }
                    2 -> ViewHolder<String>(view) { view2, s -> view2.adTv.text = s }
                    else -> throw Exception("type is not define")
                }
            }

        }
        return MultiAdapter(dataList, typeFactory)
    }

    private fun initAdapter(): SingleAdapter<String> {
        val adapter: SingleAdapter<String> = SingleAdapter(R.layout.item_main_1) { view, s ->
            view.contentTv.text = s
        }
        val dataList = (0..20).map { "%d >> %s".format(it, TEXT_KOTLIN) }
        adapter.append(dataList)
        return adapter
    }
}
