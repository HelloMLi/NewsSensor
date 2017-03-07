package lj.com.test;

import java.util.List;

import lj.com.model.Focus;
import lj.com.service.FocusService;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestFocusService extends AndroidTestCase {

	private static final String tag = "FocusServiceTest";
	public void testsave() throws Exception
	{
		FocusService p=new FocusService(this.getContext());
		//Focus f=new Focus("������+",new Date().toString());
		p.save("������+");
		p.save("һ��һ·");
	}
	public void testdelete() throws Exception
	{
		FocusService p=new FocusService(this.getContext());
		p.delete("������+");
		//p.delete(1);
	}
	public void testgetfocus() throws Exception
	{
		FocusService p=new FocusService(this.getContext());
		List<Focus> list=p.getfocus(0, p.getfocuscount());
		for(Focus l:list)
		{
			Log.i("lll", l.toString());
		}
		
	}
	
	public void testgetqxfocus() throws Exception
	{
		FocusService p=new FocusService(this.getContext());
		List<Focus> list=p.getqxfocus(0, p.getqxfocuscount());
		for(Focus l:list)
		{
			Log.i("qx", l.toString());
		}
		
	}
	
	public void testfind() throws Exception
	{
		FocusService p=new FocusService(this.getContext());
//		Person person=new Person("z","123");
//		Person per=p.find(1);
//		System.out.println(1);
		Log.i("find", p.findfocus("һ·")+"");
		//p.findfocus("һ��һ·");
	}
	public void testgetfocuscount() throws Exception
	{
		FocusService p=new FocusService(this.getContext());
		Log.i("focusco",""+p.getfocuscount());
	}
	public void testgetqxfocuscount() throws Exception
	{
		FocusService p=new FocusService(this.getContext());
		Log.i(tag,""+p.getqxfocuscount());
	}

}
