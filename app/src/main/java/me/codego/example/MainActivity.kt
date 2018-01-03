package me.codego.example

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_main_1.view.*
import kotlinx.android.synthetic.main.item_main_2.view.*
import me.codego.adapter.ITypeFactory
import me.codego.adapter.MultiAdapter
import me.codego.adapter.SingleAdapter

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
//        recyclerView.adapter = initAdapter()
        recyclerView.adapter = initMultiAdapter()
    }

    private fun initMultiAdapter(): MultiAdapter<String> {
        val typeFactory = object : ITypeFactory<String> {

            override fun type(data: String): ITypeFactory.TypeData {
                // 这里自定义多 ViewType 逻辑，此处简单进行区分
                if (data.length > 10) {
                    return ITypeFactory.TypeData(1, R.layout.item_main_1)
                }
                return ITypeFactory.TypeData(2, R.layout.item_main_2)
            }
        }
        return MultiAdapter(typeFactory).forEach { view, s ->
            when (typeFactory.type(s).type) {
                1 -> {
                    view.contentTv.text = s
                }
                2 -> {
                    view.adTv.text = s
                }
                else -> {
                }
            }
        }.also { it.setData((0..100).map { if (it % 2 == 0) TEXT_KOTLIN else TEXT_AD }) }
    }

    private fun initAdapter(): MultiAdapter<String> {
        return SingleAdapter<String>(R.layout.item_main_1).forEach { view, s ->
            view.contentTv.text = s
        }.also { it.setData((0..20).map { "%d >> %s".format(it, TEXT_KOTLIN) }) }
    }
}
