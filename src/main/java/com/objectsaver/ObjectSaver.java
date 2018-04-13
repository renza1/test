/**
 * @author dyc
 * java
 * ObjectSaver.java
 * 
 * 2016年6月21日-下午2:38:15
 *  2016XX公司-版权所有
 * 
 */
package com.objectsaver;

/**
 * @author dyc
 * @ClassName ObjectSaver
 * @Description
 * @date 2016年6月21日
 * 
 * @version 1.0.0
 * 
 */
import java.io.*;
import java.util.Date;

public class ObjectSaver {
	public static void main(String[] args) throws Exception {
		/* 其中的 D:\\objectFile.obj 表示存放序列化对象的文件 */

		// 序列化对象
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("D:\\objectFile.obj"));
		Customer customer = new Customer("王麻子", 24);
		out.writeObject("你好!"); // 写入字面值常量
		out.writeObject("你好2!"); // 写入字面值常量
		out.writeObject(new Date()); // 写入匿名Date对象
		out.writeObject(customer); // 写入customer对象
		out.close();

		// 反序列化对象
		ObjectInputStream in = new ObjectInputStream(new FileInputStream("D:\\objectFile.obj"));
		System.out.println("obj1 " + (String) in.readObject()); // 读取字面值常量
		System.out.println("obj1 " + (String) in.readObject()); // 读取字面值常量
		System.out.println("obj2 " + (Date) in.readObject()); // 读取匿名Date对象
		Customer obj3 = (Customer) in.readObject(); // 读取customer对象
		System.out.println("obj3 " + obj3);
		in.close();
	}
}

class Customer implements Serializable {
	private String name;
	private int age = 5;

	public Customer(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String toString() {
		return "name=" + name + ", age=" + age;
	}
}
