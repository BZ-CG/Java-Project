package cn.edu.pzhu.cg.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//动态代理,反射是动态语言的关键
interface Subject{
	public void action();
}
//被代理类
class RealSubject implements Subject{

	@Override
	public void action() {
		System.out.println("我是被代理类，RealSubject.");
	}
	
}
class MyInvocationHandler implements InvocationHandler{
	Object object;//实现了接口的被代理类对象的声明
	
	//①给被代理类对象实例化②返回一个代理类对象
	public Object blind(Object object){
		this.object = object;
		return Proxy.newProxyInstance(object.getClass().getClassLoader(), object.getClass().getInterfaces(),this);
	}
	
	//当通过对代理类发起对重写函数的调用时，都会转化为如下的invoke方法调用。
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		//returnVal是method的返回值(重写方法的返回值)
		Object returnVal = method.invoke(object, args);
		return returnVal;
	}
	
}

public class TestProxy {
	public static void main(String[] args) {
		//1.创建被代理类的对象
		RealSubject real = new RealSubject();
		//2.创建一个实现了InvocationHandler接口的对象
		MyInvocationHandler handler = new MyInvocationHandler();
		//3.调用blind方法，动态的返回一个同样实现了real被代理所在类的接口的代理类对象
		Object object = handler.blind(real);
		Subject subject = (Subject)object;//这里的subject就是代理类的对象
		subject.action();//一执行就转到InvocationHandler接口的实现类的invoke方法
		
		NikeClothProduct nike = new NikeClothProduct();
		ClothFactory proxyCloth = (ClothFactory)handler.blind(nike);
		proxyCloth.productCloth();
	}
	
}
