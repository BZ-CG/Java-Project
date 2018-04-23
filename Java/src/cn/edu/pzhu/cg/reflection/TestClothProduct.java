package cn.edu.pzhu.cg.reflection;
//静态代理模式

//接口
interface ClothFactory{
	void productCloth();
}
//被代理类
class NikeClothProduct implements ClothFactory{
	@Override
	public void productCloth() {
		System.out.println("Nike 工厂生产一批衣服。");
	}
	
}
//代理类
class ProxyFactory implements ClothFactory{
	ClothFactory cf;
	//创建代理类对象时，实际传入的是被代理类的对象
	public ProxyFactory(ClothFactory cf) {
		this.cf = cf;
	}
	@Override
	public void productCloth() {
		System.out.println("Nike 工厂开始收取代理费.");
		cf.productCloth();
	}
	
}
public class TestClothProduct {
	public static void main(String[] args) {
		NikeClothProduct nike = new NikeClothProduct();
		ProxyFactory pf = new ProxyFactory(nike);
		
		pf.productCloth();
	}
}
