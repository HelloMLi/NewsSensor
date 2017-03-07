package lj.com.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lj.com.model.Topic;

public class DateUtil {

	 public static String getStatetime(int beforedays) throws ParseException{
   	  
   	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
   	       
   	        Calendar c = Calendar.getInstance();  
   	        c.add(Calendar.DATE,  beforedays);  
   	        Date monday = c.getTime();
   	        String preMonday = sdf.format(monday);
   	        return preMonday;
   	   } 
	 
	 public static String getRetrieveHotTopicDataUrl(int beforedays) throws ParseException
	 {
		String now=getStatetime(0);
		String before=getStatetime(beforedays);
		int days=-beforedays;
	    String url="http://websensor.playbigdata.com/fss3/service.svc/RetrieveHotTopicData?url=search2.aspx?q=*&icount=10&mindate="+before+"&maxdate="+now+"&days="+days+"&getText=false&relative=true&facet=true";
	    return url;
	 }
	 
	 public static List<Topic> RemoveSame(List<Topic> list)
	    {
	        //上面写的那句是多余的，这个是最终的  
	        for (int i = 0; i < list.size() - 1; i++)
	        {
	            for (int j = i + 1; j < list.size(); j++)
	            {
	                if (list.get(i).getName().equals(list.get(j).getName()))
	                {
	                    list.remove(j);
	                    j--;
	                }
	            }
	        }
	        return list;
	    }
	 public static void add( LinkedHashMap<String, List<Integer>> map,String key,List<Integer> list){
	    	if(map.get(key)==null)
	    	{
	    		map.put(key,list);
	    	}
	    	else
	    	{
	    		List<Integer> keylist=map.get(key);
	    		keylist.addAll(list);
	    	    map.put(key,keylist);
	    	}
	   
	    }
}
