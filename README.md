## MultiTypeBaseAdapter
> 简化RecyclerView接入流程
1. 单类型
```kotlin
recyclerView.adapter = SingleAdapter<String>(R.layout.item_main_1) { view, s, position ->
    // do something
}
```
2. 多类型
```kotlin
val typeFactory = object : ITypeFactory<String> {

            override fun type(data: String): ITypeFactory.TypeData<String> {
                // 这里自定义 ViewType 逻辑
                if (data.length < 10) {
                    return ITypeFactory.TypeData(R.layout.item_main_1) { view, item, position ->
                            // 数据绑定
                    }
                }
                return ITypeFactory.TypeData(R.layout.item_main_2) { view, item, position ->
                        // 数据绑定
                }
            }
        }
recyclerView.adapter = MultiAdapter(typeFactory)
```
3. 自定义
```kotlin
recyclerView.adapter = object : BetterAdapter<String>() {
        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder<String> {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_main_1, parent, false)
            return ViewHolder(view){ view, item, position ->
                // 数据绑定
            }
        }

        override fun onBindViewHolder(holder: ViewHolder<String>?, position: Int) {
            super.onBindViewHolder(holder, position)
            // 自定义数据处理
        }
```