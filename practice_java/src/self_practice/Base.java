class Base	//コンパイルエラー
{
	public void foo()
	{}
	public final void bar()
	{}
	
}

class Derived extends Base
{
	public void foo()
	{
		//Base.foo()をオーバーライド
	}
	public void bar()
	{
		//Base.bar()のオーバーラードを試みる
	}
}