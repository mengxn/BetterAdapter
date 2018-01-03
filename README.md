## MultiTypeBaseAdapter
> 简化RecyclerView接入流程
1. 单类型
```kotlin
recyclerView.adapter = SingleAdapter<String>(R.layout.item_main_1).forEach { view, s ->
    // do something
}
```
2. 多类型
```kotlin
val typeFactory = object : ITypeFactory<String> {

            override fun type(data: String): ITypeFactory.TypeData {
                // 这里自定义 ViewType 逻辑
            }
        }
recyclerView.adapter = MultiAdapter(typeFactory).forEach { view, s ->
                                   when (typeFactory.type(s).type) {
                                       1 -> {
                                           // do something
                                       }
                                       2 -> {
                                           // do something
                                       }
                                       else -> {
                                       }
                                   }
                               }
```