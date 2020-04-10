# Communal
communal  
引用方式  
项目build.gradle中  
buildscript {  
	dependencies {  
		//项目原有配置  
		...  
		//添加communal中的butterknife引入文件  
		classpath 'com.jakewharton:butterknife-gradle-plugin:10.2.1'  
	}  
}  
app下的build.gradle中  
dependencies {  
	implementation 'com.egee.Communal:communallib:1.0.0'  
}
