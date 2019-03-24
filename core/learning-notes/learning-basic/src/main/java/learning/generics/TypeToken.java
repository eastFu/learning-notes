package pers.east.learning.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public abstract class TypeToken<T> {
	
	private Type type;

	public TypeToken() {
		Type superClass = getClass().getGenericSuperclass();

		this.setType(((ParameterizedType) superClass).getActualTypeArguments()[0]);
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
	
	public static void main(String[] args) {
		//匿名类，获取 泛型的Type， 可以 用作  对象转换， 比如 fastjson  gson 都有实现
		TypeToken typeToken = new TypeToken<List<String>>() {};
		System.out.println(typeToken.getType());
	}
}
