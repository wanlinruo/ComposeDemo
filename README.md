<img src="https://github.com/wanlinruo/ComposeDemo/raw/master/app/src/main/assets/jetpack-compose-hero.svg" alt="img" width="100%" />

### 一、简述

​		[Jetpack Compose](https://developer.android.google.cn/jetpack/compose)是Google I/O 2019 发布的Andorid UI框架，它不同于Andorid常见的Xml+命令式Coding的UI开发范式，而是基于Kotlin的DSL实现了一套类似React的声明式UI框架。[Jetpack Compose目前仍然处于Alpha版本](https://developer.android.google.cn/jetpack/androidx/versions)，目标是2020年能够发布稳定的Beta版本。伴随React Native、Flutter等大前端框架的兴起以及Jetpack Compose、SwiftUI等native框架的出现，声明式UI正逐渐成为客户端UI开发的新趋势。

特点：

1. 声明式编程模型，界面随应用状态自动更新
2. 组合 vs 继承
3. 关注点分离（SOC），减少耦合，增加内聚
4. 更少的代码，Kotlin简洁且易维护
5. 快速的开发，支持实时预览界面，并支持互动式预览
6. 向后兼容，与现有视图共同使用，无缝链接，并支持Material Design和动画

### 二、环境配置

由于Jetpack Compose还未正式发布，需要下载[最新Canary版的Android Studio 预览版](https://developer.android.google.cn/studio/preview)

以下三种方式可初步体验：

1. 尝试使用[Jetpack Compose 示例应用](https://github.com/android/compose-samples)
2. 创建支持[Jetpack Compose 的新应用](https://developer.android.google.cn/jetpack/compose/setup)
3. 现有项目中支持Jetpack Compose



基于现状，我主要介绍第三种方式：

- 配置Kotlin

  ```groovy
  plugins {  
    id 'org.jetbrains.kotlin.android' 
    version '1.4.0' 
  }
  ```

  

- 配置Gradle

  ```groovy
  android {    
    defaultConfig {        
      ...        
        minSdkVersion 21    
    }     
    buildFeatures {        
      // Enables Jetpack Compose for this module        
      compose true    
    }    ...     
      // Set both the Java and Kotlin compilers to target Java 8.     
      compileOptions {        
        sourceCompatibility 
        JavaVersion.VERSION_1_8        
        targetCompatibility JavaVersion.VERSION_1_8    
      }     
    kotlinOptions {        
      jvmTarget = "1.8"        
      useIR = true    
    }     
    composeOptions {        
      kotlinCompilerVersion '1.4.0'        
      kotlinCompilerExtensionVersion '1.0.0-alpha05'    
    } 
  }
  ```

  

- 添加工具包依赖项

  ```groovy
  dependencies {    
    implementation 'androidx.compose.ui:ui:1.0.0-alpha05'    
    // Tooling support (Previews, etc.)    
    implementation 'androidx.ui:ui-tooling:1.0.0-alpha05'    
    // Foundation (Border, Background, Box, Image, Scroll, shapes, animations, etc.)    
    implementation 'androidx.compose.foundation:foundation:1.0.0-alpha05'    
    // Material Design    
    implementation 'androidx.compose.material:material:1.0.0-alpha05'    
    // Material design icons    
    implementation 'androidx.compose.material:material-icons-core:1.0.0-alpha05'    
    implementation 'androidx.compose.material:material-icons-extended:1.0.0-alpha05'    
    // Integration with observables    
    implementation 'androidx.compose.runtime:runtime-livedata:1.0.0-alpha05'    
    implementation 'androidx.compose.runtime:runtime-rxjava2:1.0.0-alpha05'     
    // UI Tests    androidTestImplementation 'androidx.ui:ui-test:1.0.0-alpha05' }
  ```

### 三、基础使用

​    Jetpack Compose包含了基本组件（[compose.ui](https://developer.android.google.cn/jetpack/androidx/releases/compose-ui)）、Material Design 组件（[compose.material](https://developer.android.google.cn/jetpack/androidx/releases/compose-material)）、动画组件（[compose.animation](https://developer.android.google.cn/jetpack/androidx/releases/compose-animation)）等众多UI组件，在此我就不赘述了，在对应的文档中大家都可以参阅，此处我重点讲解一下关于Compose的关键点

#### @Compose

所有关于构建View的方法都必须添加@Compose的注解才可以。并且@Compose跟协程的Suspend的使用方法比较类似,被@Compose的注解的方法只能在同样被@Comopse注解的方法中才能被调用。

```kotlin
@Composable 
fun Greeting(name: String) {    
  Text(text = "Hello $name!") 
}
```

#### @Preview

常用用参数如下：
   1. `name: String`: 为该Preview命名，该名字会在布局预览中显示。
   2. `showBackground: Boolean`: 是否显示背景，true为显示。
   3. `backgroundColor: Long`: 设置背景的颜色。
   4. `showDecoration: Boolean`: 是否显示Statusbar和Toolbar，true为显示。
   5. `group: String`: 为该Preview设置group名字，可以在UI中以group为单位显示。
   6. `fontScale: Float`: 可以在预览中对字体放大，范围是从0.01。
   7. `widthDp: Int`: 在Compose中渲染的最大宽度，单位为dp。
   8. `heightDp: Int`: 在Compose中渲染的最大高度，单位为dp。

上面的参数都是可选参数，还有像背景设置等的参数并不是对实际的App进行设置，只是对Preview中的背景进行设置，为了更容易看清布局。

   ```kotlin
   @Preview(showBackground = true) 
   @Composable fun DefaultPreview() {    
     ComposeDemoTheme {        
       Greeting("Android")    
     } 
   }
   ```

   

当更改跟UI相关的代码时，会显示如下图的一个横条通知，点击Build&Refresh即可更新显示所更改代码的UI。

![img](https://github.com/wanlinruo/ComposeDemo/raw/master/app/src/main/assets/image2020-12-14_16-39-53.png)

#### setContent

该方法作用是和zaiLayout/View中的setContentView是一样的。setContent的方法也是有@Compose注解的方法。所以，在setContent中写入关于UI的@Compopse方法，即可在Activity中显示。

```kotlin
class MainActivity : AppCompatActivity() {    
  override fun onCreate(savedInstanceState: Bundle?) {        
  super.onCreate(savedInstanceState)        
    setContent {            
      Text("Hello world!")        
    } 
}
```



#### Modifier

`该类`是各个`Compose`的UI组件一定会用到的一个类。它是被用于设置UI的摆放位置，padding等修饰信息的类。关于`Modifier`相关的设置较多，在这里只介绍会经常用到的。

```kotlin
Modifier.padding(10.dp) // 给上下左右设置成同一个值 
Modifier.padding(10.dp, 11.dp, 12.dp, 13.dp) // 分别为上下左右设值 
Modifier.padding(10.dp, 11.dp) // 分别为上下和左右设值 
Modifier.padding(InnerPadding(10.dp, 11.dp, 12.dp, 13.dp))// 分别为上下左右设值 
Modifier.fillMaxHeight() // 填充整个宽度 
Modifier.fillMaxHeight() // 填充整个高度 
Modifier.fillMaxSize() // 填充整个宽度和高度 
Modifier.width(2.dp) // 设置宽度 
Modifier.height(3.dp)  // 设置高度 
Modifier.size(4.dp, 5.dp) // 设置高度和宽度 
Modifier.widthIn(2.dp) // 设置最大宽度 
Modifier.heightIn(3.dp) // 设置最大高度 
Modifier.sizeIn(4.dp, 5.dp, 6.dp, 7.dp) // 设置最大最小的宽度和高度
Modifier.gravity(Alignment.CenterHorizontally) // 横向居中 
Modifier.gravity(Alignment.Start) // 横向居左 
Modifier.gravity(Alignment.End) // 横向居右 
Modifier.rtl  // 从右到左 Modifier.ltr  // 从左到右 
Modifier.plus(otherModifier) // 把otherModifier的信息加入到现有的modifier中 
// Modifier的方法都返回Modifier的实例的链式调用，所以只要连续调用想要使用的方法即可。 
@Composable fun Greeting(name: String) {    
  Text(text = "Hello $name!", modifier = Modifier.padding(20.dp).fillMaxWidth()) 
}
```



#### Column，Row

Column和Row可以理解为在View/Layout体系中的纵向和横向的ViewGroup

```kotlin
Column( 
  verticalArrangement:Arrangement // 控制纵向布局关系 
  horizontalAlignment:Alignment // 控制横向对齐关系 
) 

Row( 
  horizontalArrangement:Alignment // 控制横向布局关系  
  verticalAlignment:Arrangement // 控制纵向对齐关系
)  

@Composable 
fun TestColumnRow() {    
  Column(        
    modifier = Modifier.fillMaxHeight().background(Color.Yellow),              
    verticalArrangement = Arrangement.SpaceBetween,        
    horizontalAlignment = Alignment.Start    
  ) {        
    Text(text = "java")        
    Text(text = "android")        
    Text(text = "python")    
  }     
  Row(        
    modifier = Modifier.fillMaxWidth().background(Color.LightGray),        
    verticalAlignment = Alignment.Top,        
    horizontalArrangement = Arrangement.SpaceBetween    
  ) {        
    Text(text = "java")        
    Text(text = "android")        
    Text(text = "python")    
  } 
} 
```



### 四、进阶使用

#### 状态管理

所有 Android 应用都有核心界面更新循环，如下所示：

![img](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1ebf80c62a32470db58fbddc25588f2d~tplv-k3u1fbpfcp-zoom-1.image)

Compose 专为单向数据流而打造。这是一种状态向下流动而事件向上流动的设计。

![img](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/efc46376a6c14887a025c470800e5f38~tplv-k3u1fbpfcp-zoom-1.image)

使用单向数据流的应用的界面更新循环如下所示：

事件：事件由界面的一部分生成并且向上传递。

更新状态：事件处理脚本可以更改状态。

显示状态：状态会向下传递，界面会观察新状态并显示该状态。



举两个例子展示：

```kotlin
//内部状态管理 
@Composable 
fun CounterInner() {    
  val count = remember { mutableStateOf(0) }    
  Button(onClick = { count.value += 1 }) 
  {        
    Text(text = "Count: ${count.value}")    
  } 
}
```

解释一下上图的数据流情况

事件：当点击发生时候，会触发count.value

更新状态：mutableStateOf会进行处理，然后设置count的状态

显示状态：系统会调用count的观察器，并且界面会显示新状态

```kotlin
//支持其他可观察类型的状态管理 
class CountViewModel : ViewModel() {    
  // LiveData holds state which is observed by the UI    
  // (state flows down from ViewModel)    
  private val _count = MutableLiveData(0)   
  val count: LiveData<Int> = _count     
  // onNameChanged is an event we're defining that the UI can invoke    
  // (events flow up from UI)    
  fun onCountChanged(newCount: Int) {        
    _count.value = newCount    
  } 
} 

@Composable 
fun Counter(countViewModel: CountViewModel = viewModel()) {     
  val observeAsState = countViewModel.count.observeAsState(0)    
  val count = observeAsState.value     
  Button(        
    colors = ButtonConstants.defaultButtonColors(backgroundColor = if (count > 5) Color.Green else Color.White),        
    onClick = { countViewModel.onCountChanged(count + 1) },    
  ) {        
    Text(text = "I've been clicked $count times")    
  } 
}
```

解释一下上图的数据流情况

事件：当点击发生时候，会触发onCountChanged

更新状态：onCountChanged会进行处理，然后设置_count的状态

显示状态：系统会调用count的观察器，并且界面会显示新状态

#### 状态提升

- 无状态可组合项是指本身无法改变任何状态的可组合项。无状态组件更容易测试、发生的错误往往更少，并且更有可能重复使用。
- 如果您的可组合项有状态，您可以通过使用状态提升使其变为无状态。
- 状态提升是一种编程模式，在这种模式下，通过将可组合项中的内部状态替换为参数和事件，将状态移至可组合项的调用方。
- 状态提升的过程可让您将单向数据流扩展到无状态可组合项。在这些可组合项的单向数据流示意图中，随着更多可组合项与状态交互，状态仍向下流动，而事件向上流动。

```kotlin
@Composable 
fun Counter(countViewModel: CountViewModel = viewModel()) {     
  val observeAsState = countViewModel.count.observeAsState(0)    
  val count = observeAsState.value        
  ButtonCount(count = count, onCountChanged = { countViewModel.onCountChanged(it) }) 
} 

@Composable 
fun ButtonCount(    
  /* state */ count: Int,    
  /* event */ onCountChanged: (Int) -> Unit ) {    
  Button(        
    colors = ButtonConstants.defaultButtonColors(backgroundColor = if (count > 5) Color.Green else Color.White),        
    onClick = { onCountChanged(count + 1) },    
  ) {        
    Text(text = "I've been clicked $count times")    
  } 
}
```



#### 互操作

##### Android View中的Compose

如果想使用Compose的情况下，又不想迁移整个应用，可以在xml里面增加ComposeView，类似于占位符，然后在Actviity/fragment中寻找该控件并调用setContent方法即可，在该方法中即可使用compose相关属性

```xml
<androidx.constraintlayout.widget.ConstraintLayout 
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"    
    tools:context=".AndroidViewComposeActivity">     
  <TextView        
      android:id="@+id/hello_world"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      android:text="Hello Android!"        
      app:layout_constraintTop_toTopOf="parent" />     
  <androidx.compose.ui.platform.ComposeView        
      android:id="@+id/compose_view_text"        
      android:layout_width="match_parent"        
      android:layout_height="wrap_content"        
      app:layout_constraintTop_toBottomOf="@id/hello_world" />     
  <androidx.compose.ui.platform.ComposeView        
      android:id="@+id/compose_view_img"        
      android:layout_width="match_parent"        
      android:layout_height="wrap_content"        
      app:layout_constraintTop_toBottomOf="@id/compose_view_text" />
</androidx.constraintlayout.widget.ConstraintLayout>
```



```kotlin
class AndroidViewComposeActivity : AppCompatActivity() {    
  override fun onCreate(savedInstanceState: Bundle?) {        
    super.onCreate(savedInstanceState)        
    setContentView(R.layout.activity_android_view_compose)         
    findViewById<ComposeView>(R.id.compose_view_text).setContent {            
      MaterialTheme {                
        Text("Hello Compose!")            
      }        
    }         
    findViewById<ComposeView>(R.id.compose_view_img).setContent {            
      val imageResource = imageResource(id = R.drawable.header)            
      val imageModifier = Modifier.preferredHeight(180.dp)
      .fillMaxWidth()
      .padding(16.dp)
      .clip(RoundedCornerShape(4.dp))
      
      MaterialTheme {
        Image(                    
          bitmap = imageResource,                    
          modifier = imageModifier,                    
          contentScale = ContentScale.Crop                
        )            
      }        
    }    
  } 
}
```

##### Compose中的Android View

如果碰到在Compose环境中，想要使用Android的View视图的情况，只需要使用AndroidView函数即可

```kotlin
@Composable 
fun CustomView() {    
  val selectedItem = remember { mutableStateOf(0) }     
  val context = AmbientContext.current     
  val customView = remember {        
    // Creates custom view        
    Button(context).apply {            
      // Sets up listeners for View -> Compose communication            
      setOnClickListener {                
        selectedItem.value += 1            
      }        
    }   
  }     
  // Adds view to Compose    
  AndroidView({ customView }) { 
    view ->
    // View's been inflated - add logic here if necessary 
    // As selectedItem is read here, AndroidView will recompose        
    // whenever the state changes        
    // Example of Compose -> View communication        
    view.text = selectedItem.value.toString()    
  } 
}
```

如果是需要使用xml的配置情况，也使用AndroidView函数即可

```kotlin
@Composable 
fun CustomView2() {    
  val context = AmbientContext.current     
  val customView = remember {        
    // Creates custom view        
    View.inflate(context, R.layout.layout_custom_view, null)    
  }     
  AndroidView({ customView }) 
}
```

#### 与通用库集成

##### ViewModel

从源码可看出，viewmodel函数底层也是通过ViewModelProvider进行获取的

```kotlin
@Composable 
fun <VM : ViewModel> viewModel(    
  modelClass: Class<VM>,    
  key: String? = null,    
  factory: ViewModelProvider.Factory? = null )
: VM = AmbientViewModelStoreOwner.current.get(modelClass, key, factory)
```

##### 数据流

Compose也是适配Android主流的基于流的方案，如

- LiveData.observeAsState()
- Flow.collectAsState()
- Observable.subscribeAsState()

在Compose中，LiveData.observeAsState()获取的State对象赋值给Text

```kotlin
@Composable 
fun HelloScreen(helloViewModel: HelloViewModel = viewModel()) {    
  // by default, viewModel() follows the Lifecycle as the Activity or Fragment    
  // that calls HelloScreen(). This lifecycle can be modified by callers of HelloScreen.     
  // name is the _current_ value of [helloViewModel.name]    
  // with an initial value of ""    
  val observeAsState = helloViewModel.name.observeAsState("")     
  Column {        
    Text(text = observeAsState.value)        
    TextField(            
      value = observeAsState.value,            
      onValueChange = { helloViewModel.onNameChanged(it) },            
      label = { Text("Name") }        
    )    
  } 
}
```

##### 异步操作

此处需要补充说明的是Compose的生命周期

Compose通过一系列Effect方法，实现生命周期函数

| Compose生命周期 | 说明                              | 对应React                            |
| --------------- | --------------------------------- | ------------------------------------ |
| onActive        | compose函数第一次被渲染到画面     | componentWillMount componentDidMount |
| onDispose       | compose函数从画面上移除           | componentWillUnmount                 |
| onCommit        | compose函数每次执行，画面重新渲染 | componentDidUpdate                   |

所以onCommit函数的使用类似于React的useEffect，支持可观察函数

```kotlin
@Suppress("ComposableNaming") 
@Composable 
/*inline*/ 
fun </*reified*/ V1> onCommit(    
  v1: V1,    
  /*noinline*/    
  callback: CommitScope.() -> Unit ) {    
  remember(v1) { PreCommitScopeImpl(callback) } 
}
```

仅当v1发生变化时onCommit才会执行

举个例子使用异步操作

```kotlin
@Composable fun fetchImage(url: String): ImageAsset? {    
  // Holds our current image, and will be updated by the onCommit lambda below    
  var image by remember(url) { mutableStateOf<ImageAsset?>(null) }     
  onCommit(url) {        
    // This onCommit lambda will be invoked every time url changes         
    val listener = object : ExampleImageLoader.Listener() {            
      override fun onSuccess(bitmap: Bitmap) {                
        // When the image successfully loads, update our image state                
        image = bitmap.asImageAsset()            
      }        
    }         
    // Now execute the image loader        
    val imageLoader = ExampleImageLoader.get()        
    imageLoader.load(url).into(listener)         
    onDispose {            
      // If we leave composition, cancel any pending requests            
      imageLoader.cancel(listener)        
    }    
  }     
  // Return the state-backed image property. Any callers of this function    
  // will be recomposed once the image finishes loading    
  return image 
}
```



### 五、原理解析

因为代码是基于Kotlin注解动态生成的，查看方法可以先build一个apk，然后查看其中的classess.dex文件，使用dex2jar转为jar包，然后使用jd-gui进行查看，下图是反编译得到的源码

```java
//CountActivityKt.class->CountActivity->CounterInner(Composer,int):void 
public static final void CounterInner(Composer<?> paramComposer, int paramInt) {
 paramComposer.startRestartGroup(-908461591,"C(CounterInner)47@2322L30,48@2374L20,48@2357L91:CountActivity.kt#ffoge4");    
  if (paramInt != 0 || !paramComposer.getSkipping()) {      
    paramComposer.startReplaceableGroup(-3687207, "C(remember):Remember.kt#9igjgp");      
    Object object = paramComposer.nextSlot();      
    if (object == SlotTableKt.getEMPTY()) {        
      object = MutableStateKt.mutableStateOf$default(Integer.valueOf(LiveLiterals$CountActivityKt.INSTANCE.Int$arg-0$call-mutableStateOf$fun-$anonymous$$arg-0$call-remember$val-count$fun-CounterInner()), null, 2, null);        paramComposer.updateValue(object);      
    }       
    paramComposer.endReplaceableGroup();      
    MutableState<Integer> mutableState = (MutableState)object;   paramComposer.startReplaceableGroup(-3686846, "C(remember)P(1):Remember.kt#9igjgp");      
    boolean bool = paramComposer.changed(mutableState);      
    object = paramComposer.nextSlot();      
    if (object == SlotTableKt.getEMPTY() || bool) {        
      object = new CountActivityKt$CounterInner$1$1(mutableState);        
      paramComposer.updateValue(object);      
    }       
    paramComposer.endReplaceableGroup();      
    ButtonKt
      .Button((Function0)object, null, false, null, null, null, null, null, null,(Function3)ComposableLambdaKt.composableLambda(paramComposer, -819892270, true, "C49@2406L36:CountActivity.kt#ffoge4",                                                                                                                         new CountActivityKt$CounterInner$2(mutableState)), paramComposer, 805306368, 510);    
  	} else {      
    paramComposer.skipToGroupEnd();    
  	}     
  	ScopeUpdateScope scopeUpdateScope = paramComposer.endRestartGroup();    
  if (scopeUpdateScope == null)      
    return;     
  scopeUpdateScope.updateScope(new CountActivityKt$CounterInner$3(paramInt));  
}
```

仔细查看源码可知

1. Composeable Annotation：
   1. 当编译器看到Composeable注解时，会插入额外的参数和函数调用等模板代码，
   2. 其中头部会加入startRestartGroup，尾部会加入endRestartGroup，中部函数部分会加入分组信息（startReplaceableGroup，endReplaceableGroup）
   3. 底层是通过Gap Buffer的方式进行Layoutnode的复用和管理
2. 位置记忆化：
   1. 执行时候会记忆代码执行顺序及缓存每个节点
   2. 打下分组信息（startReplaceableGroup，endReplaceableGroup），以组别进行更新
3. 重组：
   1. 获取到可组合函数（State<T>）,并与当前方法的Composer对象进行绑定
   2. 将状态保管到Composer内部的槽表中进行管理
   3. 内部的layoutnode复用和管理通过Gap Buffer方式进行

### 六、附录材料

- [代码示范Demo工程](https://github.com/wanlinruo/ComposeDemo)
- [Jetpack Compose 官网](https://developer.android.google.cn/jetpack/compose)
- [JetPack Compose Samples](https://github.com/android/compose-samples)
- [深入详解 Jetpack Compose | 优化 UI 构建](https://zhuanlan.zhihu.com/p/267250784?utm_source=wechat_session)
- [深入详解 Jetpack Compose | 实现原理](https://zhuanlan.zhihu.com/p/270682182)
- [Jetpack撰写第一原理（背景信息）](http://intelligiblebabble.com/compose-from-first-principles/)
- [Android 全新 UI 编程](https://juejin.cn/post/6850037262743240717#heading-4)
- [dex2jar-2.0.zip](https://github.com/wanlinruo/ComposeDemo/raw/master/app/src/main/assets/dex2jar-2.0.zip)
- [jd-gui-osx-1.6.6.tar](https://github.com/wanlinruo/ComposeDemo/raw/master/app/src/main/assets/jd-gui-osx-1.6.6.tar)
