package com.kfit.spring_boot_mybatis.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import com.alibaba.fastjson.JSONArray;

import net.minidev.json.JSONObject;
import net.sf.jsqlparser.parser.ParseException;

public class JsonXML {
	
	public static  String JsonToXml(Object json)     
    {    
        if(json==null){    
            return null;    
        }else{    
         Element elements=new Element("xml");    
         getXMLFromObject(json,"xml",elements);    
         XMLOutputter xmlOut = new XMLOutputter();      
         String res=xmlOut.outputString(elements);    
         return res;    
       }    
    }    
    
    
private static void getXMLFromObject(Object obj,String tag,Element parent)    
    {    
        if(obj==null)    
            return;    
        Element child;    
        String eleStr;    
        Object childValue;    
        if(obj instanceof JSONObject)    
        {    
            JSONObject jsonObject=(JSONObject)obj;    
            for(Object temp:jsonObject.keySet())    
            {    
                eleStr=temp.toString();    
                childValue=jsonObject.get(temp);    
                child=new Element(eleStr);    
                if(childValue instanceof JSONArray)    
                    getXMLFromObject(childValue,eleStr,parent);    
                else{    
                    parent.addContent(child);    
                    getXMLFromObject(childValue,eleStr,child);    
                }    
            }    
        }else if(obj instanceof JSONArray){    
            JSONArray jsonArray=(JSONArray)obj;    
            for(int i=0;i<jsonArray.size();i++)    
            {    
                childValue=jsonArray.get(i);    
                child=new Element(tag);    
                parent.addContent(child);    
                getXMLFromObject(childValue,tag,child);    
            }    
        }else if(obj instanceof Date){    
            SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
            parent.setText(sf.format((Date)obj));    
        }else{    
            parent.setText(obj.toString());    
        }    
    }    
            
    
public static void main(String[] args) throws ParseException    
    {    
  JSONObject rootObject=new JSONObject();    
         
      JSONArray dataArray=new JSONArray();    
      JSONObject dataObject1=new JSONObject();    
      dataObject1.put("PERSONID", "35020500200610000000000701355117");    
      dataObject1.put("XM", "吴聪楠");    
      dataObject1.put("SFZH","350624198908052530");    
      dataArray.add(dataObject1);    
      JSONObject dataObject2=new JSONObject();    
      dataObject2.put("PERSONID", "35020500200610000000000701355116");    
      dataObject2.put("XM", "吴聪楠2");    
      dataObject2.put("SFZH","350624198908052531");    
      dataArray.add(dataObject2);    
        
      JSONObject dataRootObject=new JSONObject();    
      dataRootObject.put("Row", dataArray);    
      JSONObject dataObject=new JSONObject();    
      dataObject.put("Status", "00");    
      dataObject.put("ErrorMsg", "");    
      dataObject.put("Data",dataRootObject);    
      rootObject.put("ROOT", dataObject);    
          
      System.out.println(rootObject.toString());    
      System.out.println(JsonToXml(rootObject));            
            
    }  

}
