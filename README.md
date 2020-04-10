# Communal
communal  
# 引用方式  
项目build.gradle中 
```
buildscript {
	dependencies {
		//项目原有配置
		...
		//添加communal中的butterknife引入文件
		classpath 'com.jakewharton:butterknife-gradle-plugin:10.2.1'
	}
}
```
app下的build.gradle中<br>
```
dependencies {
	annotationProcessor 'com.jakewharton:butterknife-compiler:10.2.1'
	implementation 'com.egee.Communal:communallib:1.0.0'
}
```

# 引入库<br>
gson,com.google.code.gson:gson:2.8.6，json快速解析<br>
[Banner](https://github.com/youth5201314/banner),图片轮播控件1.4.10版本<br>
bugly,方便查找bug,需要自己申请接入APPkey后可直接使用<br>
RecyclerView，[RecyclerView适配器](https://github.com/CymChad/BaseRecyclerViewAdapterHelper)，Adapter继承BaseQuickAdapter可快速实现适配器<br>
[SmartRefreshLayout](https://github.com/scwang90/SmartRefreshLayout),非常强大的下拉刷新框架，它不只是支持所有的View，还支持多层嵌套的视图结构，吸取了各大优秀刷新框架优点，集成了各种炫酷的 Header 和 Footer<br>
glide,图片加载<br>
android-gif-drawable，可加载gif图片<br>
EventBus，事件发布-订阅总线<br>
autosize，导入包在清单文件添加<br>
```
<meta-data   android:name="design_width_in_dp"  android:value="360"/>
<meta-data   android:name="design_height_in_dp"  android:value="640"/> 
```
可实现屏幕适配  <br>
logger日志<br>
运行时权限rxPermission<br>
retrofit+rxJava<br>
butterknife<br>
[UpdatePlugin](https://github.com/easyandroidgroup/UpdatePlugin),可任意定制的app更新组件。<br>
