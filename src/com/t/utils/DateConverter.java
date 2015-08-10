package com.t.utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
  
public class DateConverter extends StrutsTypeConverter {  
	  
    private static String DATE_FOMART1 = "yyyy-MM-dd"; 
    private static String DATE_FOMART2 = "yyyy/MM/dd";
  
    @SuppressWarnings("rawtypes")
	@Override  
    public Object convertFromString(Map context, String[] values, Class toClass) {  
        Date date = null;  
        String dateString = null;  
        if (values != null && values.length > 0) {  
            dateString = values[0];  
            if (dateString != null) {  
                // 匹配IE浏览器  
                SimpleDateFormat format = new SimpleDateFormat(DATE_FOMART1);  
                try {  
                    date = format.parse(dateString);  
                } catch (ParseException e) {  
                    date = null;  
                }  
                // 匹配Firefox浏览器  
                if (date == null) {  
                    format = new SimpleDateFormat(DATE_FOMART2);  
                    try {  
                        date = format.parse(dateString);  
                    } catch (ParseException e) {  
                        date = null;  
                    }  
                }  
            }  
        }  
        return date;  
    }  
  
    @SuppressWarnings("rawtypes")
	@Override  
    public String convertToString(Map context, Object o) {  
    	 if (o instanceof Date) {  
             SimpleDateFormat sdf = new SimpleDateFormat(DATE_FOMART1);   
             return sdf.format((Date)o);  
         }   
         return "";  
    }
}  