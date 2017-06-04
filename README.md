# Reflect
The tool for java reflection.

## 简介
一个 Java 反射小工具，主要是提高反射的易用性;

## 修改点
* 去除 Java 反射的受检异常; 
* 采用流式接口调用;

## 库引入
```
compile 'com.mingyuans.reflect:reflect:1.0.0'
```

## 使用示例
```java
 // 获取字段
 String tag = Reflect.onClass(Person.class).field("TAG").getValue();
 
 // 修改值
 Reflect.onClass(Person.class).field("TAG").setValue("Animal");
 
 //方法调用
 Person person = (Person) Reflect.onClass(Person.class)
                .method("create")
                .find(new Class[]{String.class, String.class})
                .call("Hello", "world");
```

## License
```
Copyright (c) 2017 mingyuan

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```
