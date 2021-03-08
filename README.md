# dagger

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
            implementation 'com.github.lany192:dagger:Tag'
    }
