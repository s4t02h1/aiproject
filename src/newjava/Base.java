package newjava;
class Base	//�R���p�C���G���[
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
		//Base.foo()���I�[�o�[���C�h
	}
	public void bar()
	{
		//Base.bar()�̃I�[�o�[���[�h�����݂�
	}
}