package test;

import test.TestDeamon.ThA;
import test2.TestInnerClass;
import test2.TestInnerClass.ThreadB;

// http://stackoverflow.com/questions/283816/how-to-access-java-classes-in-the-default-package
// That's no longer allowed. So to access a default package class from within a
// packaged class requires moving the default package class into a package of
// its own.
/** import Unfinished; */

// If you have access to the source generated by groovy, some post-processing is
// needed to move the file into a dedicated package and add this "package"
// directive at its beginning.

// http://zhaohe162.blog.163.com/blog/static/3821679720101120104444895/?suggestedreading&wumii
// 星号也是按需导入，但不鼓励
// java.lang会自动导入
// 只能导入public的类和接口,enum也可以引入,他算比较特殊的类,准确地说应该是可见的，即同一个包内可以不用声明public,内部类要视包含他的类是否为可见的
// import与import static 要与访问权限挂钩的 不然不能导入
//
// http://zhaohe162.blog.163.com/blog/static/3821679720118283333448/?suggestedreading&wumii
// 再说说Package权限。Java语言规定，在同一个包中的class，如果没有修饰符，默认为Package权限，
// 包内的class都可以访问。但是这还不够准确。确切的说，只有由同一个ClassLoader装载的class才具有以上的Package权限。
// 比如启动类装载器装载了java.lang.String，类路径装载器装载了我们自己写的java.lang.Test，
// 它们不能互相访问对方具有Package权限的方法。这样就阻止了恶意代码访问核心类的Package权限方法。
public class TestImport
{

	public static void staticMethod()
	{
		System.out.println("TestImport.staticMethod()");
		ThreadB b = new TestInnerClass().new ThreadB();
		// ThreadA a = new TestDeamon2().new ThreadA();
		// 同一个包内所以 ThA可见
		ThA a = new TestDeamon().new ThA();
		outThread c = new outThread();
		// outThread2 c = new outThread2();

	}

	public static void main(String[] args)
	{
		staticMethod();
	}

}