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
import me.codego.example.bean.Animal
import me.codego.example.bean.Cat
import me.codego.example.bean.Dog

class MainActivity : AppCompatActivity() {

    companion object {

        private val TEXT_KOTLIN = "Kotlin 是一个基于 JVM 的新的编程语言，由 JetBrains 开发。\n" +
                "Kotlin可以编译成Java字节码，也可以编译成JavaScript，方便在没有JVM的设备上运行。\n" +
                "JetBrains，作为目前广受欢迎的Java IDE IntelliJ 的提供商，在 Apache 许可下已经开源其Kotlin 编程语言。\n" +
                "Kotlin已正式成为Android官方支持开发语言。"
        private val TEXT_AD = "Hi，这里是广告～"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
//        recyclerView.adapter = initAdapter()
        recyclerView.adapter = initMultiAdapter()
    }

    private fun initMultiAdapter(): MultiAdapter<Animal> {
        val dataList = (0..30).map { if (it % 2 == 0) Dog(it) else Cat(it) }.toMutableList()
        val typeFactory = object : ITypeFactory<Animal> {

            override fun type(data: Animal): ITypeFactory.TypeData<Animal> {
                // 简单进行区分类型
                if (data is Dog) {
                    return ITypeFactory.TypeData(R.layout.item_main_1) { view, item, position ->
                        view.contentTv.text = "$position >> ${item.getName()}"
                    }
                }
                return ITypeFactory.TypeData(R.layout.item_main_2) { view, item, position ->
                    view.adTv.text = "$position >> ${item.getName()}"
                }
            }
        }
        return MultiAdapter(typeFactory, dataList)
    }

    private fun initAdapter(): SingleAdapter<String> {
        val adapter: SingleAdapter<String> = SingleAdapter(R.layout.item_main_1) { view, item, position ->
            view.contentTv.text = item
        }
        val dataList = (0..20).map { "%d >> %s".format(it, TEXT_KOTLIN) }
        adapter.append(dataList)
        return adapter
    }
}
