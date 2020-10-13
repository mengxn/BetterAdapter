## BetterAdapter
> 简化RecyclerView接入流程

1. 在build.gradle文件中添加引用
```gradle
implementation 'me.codego:adapter:1.0.3'
```

2. 代码中创建Adapter

    - 单类型

    ```kotlin
    recyclerView.adapter = SingleAdapter<String>(R.layout.item_main_1) { holder ->
        // do something
        // holder.data 为数据源，holder.setXXX 为快捷方式 holder.getView(id) 获取指定ID的View
        
        // way 1
        holder.itemView.contentTv.text = holder.data
        // way 2
        holder.setText(R.id.contentTv, holder.data)
        // way 3
        holder.getView<TextView>(R.id.contentTv).text = holder.data
    }
    ```

    - 自定义Adapter（多类型）

    ```kotlin
    class AnimalAdapter : BetterAdapter<Animal>() {
    
            companion object {
                private const val TYPE_DOG = 1
                private const val TYPE_CAT = 2
            }
    
            // 自定义规则进行区分类型
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
    
                // 绑定数据
                override fun bind(data: Animal) {
                    super.bind(data)
                    setText(R.id.contentTv, data.getName())
                }
            }
    
            class CatViewHolder(view: View) : ViewHolder<Animal>(view) {
                
                // 绑定数据
                override fun bind(data: Animal) {
                    super.bind(data)
                    setText(R.id.adTv, data.getName())
                }
            }
    
        }
    ```
