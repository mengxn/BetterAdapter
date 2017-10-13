## MultiTypeBaseAdapter
> 简化RecyclerView接入流程
1. 单类型
```kotlin
recyclerView.adapter = BaseAdapter<String>(R.layout.item_main_1){ view, t -> 
    // do something
}
```
2. 多类型
```kotlin
recyclerView.adapter = MultiTypeBaseAdapter<String> {
            object : ITypeFactory<String> {
                override fun type(data: String): ITypeFactory.TypeData {
                    // 根据需求进行类型区分
                    if (data.length > 10) {
                        return ITypeFactory.TypeData(1, R.layout.item_main_1)
                    }
                    return ITypeFactory.TypeData(2, R.layout.item_main_2)
                }

                override fun createViewHolder(view: View, type: Int): BaseViewHolder<String> {
                    // 根据类型实现ViewHolder逻辑
                    return when (type) {
                        1 -> BaseViewHolder<String>(view) { view1, s ->
                            view1.contentTv.text = s
                        }
                        2 -> BaseViewHolder<String>(view) { view2, s ->
                            view2.adTv.text = s
                        }
                        else -> throw Exception("type is not define")
                    }
                }
            }
        }
```