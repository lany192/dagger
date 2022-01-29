[![](https://jitpack.io/v/lany192/dagger.svg)](https://jitpack.io/#lany192/dagger)
# dagger
（已废弃，推荐使用hilt）
基于dagger2的android拓展库，通过注解自动生成ActivityModule模块，节省了注册Activity步骤

#使用

##前提
    依赖google/dagger依赖库

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	    //dagger库引入
        implementation 'com.google.dagger:dagger-android:+'
        implementation 'com.google.dagger:dagger-android-support:+'
        annotationProcessor 'com.google.dagger:dagger-compiler:+'
        annotationProcessor 'com.google.dagger:dagger-android-processor:+'
	    //拓展库引入
        implementation 'com.github.lany192.dagger:annotations:1.0.3'
        annotationProcessor 'com.github.lany192.dagger:compiler:1.0.3'
	}
	
	引入AndroidModule模块到AppComponent中
	
	在需要注册的类上加入@Dagger
