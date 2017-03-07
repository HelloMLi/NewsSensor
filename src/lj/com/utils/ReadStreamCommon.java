package lj.com.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ReadStreamCommon {

	public static String readStream(InputStream is)
   	{
   		
   		InputStreamReader isr;
   		String result ="";
   		try {
   			String line="";
   			isr=new InputStreamReader(is,"utf-8");
   		    BufferedReader br=new  BufferedReader(isr);
   		    while((line=br.readLine())!=null)
   		    {
   		    	result+=line;
   		    }
   		} catch (UnsupportedEncodingException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		return result;
   	}
}
