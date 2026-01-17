
package lib_domain.Core;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
        
public class JsonHelper
{
    private static JSONParser parser = new JSONParser();
    
    public static String ConvertToString(HashMap data) 
    {
        JSONObject JSONObject = new JSONObject(data);
        return JSONObject.toString().replace('"', '\"');
    }
    public static <T extends Object> String ConvertToString(List<T> data) throws Exception
    {
        JSONArray jsonArrayObj = new JSONArray();
        for (T obj : data) 
        {
            JSONObject jsonObject = new JSONObject();
            
            Class<?> c = obj.getClass();
            Field[] properties = c.getDeclaredFields();
            for (Field property : properties)
            {
                if (!property.getType().getName().contains("lib_domain"))
                    jsonObject.put(property.getName(), c.getMethod("get" + property.getName()).invoke(obj));
                else
                    jsonObject.put(property.getName(), ClassToString(c.getMethod("get" + property.getName()).invoke(obj)));
            }
            
            jsonArrayObj.add(jsonObject);
        }
        return jsonArrayObj.toJSONString();
    }
    
    public static <T extends Object> String ClassToString(T data) throws Exception
    {
        JSONArray jsonArrayObj = new JSONArray();
        JSONObject jsonObject = new JSONObject();
            
        Class<?> c = data.getClass();
        Field[] properties = c.getDeclaredFields();
        for (Field property : properties)
            jsonObject.put(property.getName(), c.getMethod("get" + property.getName()).invoke(data));
           
        jsonArrayObj.add(jsonObject);
        return jsonArrayObj.toJSONString();
    }
    
    public static <T extends Object> T ConvertToObject(String data) throws Exception
    {
        return (T)parser.parse(data);
    }   
    
    public static <T extends Object> T ConvertToClass(String data, Class<T> type) throws Exception
    {
        JSONObject jsonObject = (JSONObject) parser.parse(data);
        T entity = type.newInstance();

        Field[] fields = type.getDeclaredFields();
        for (Field field : fields) 
        {
            field.setAccessible(true); 
            String fieldName = field.getName();
            Object value = jsonObject.get(fieldName);
            if (value != null) 
            {
                if (field.getType() == String.class) 
                    field.set(entity, (String)value);
                else if (field.getType() == int.class || field.getType() == Integer.class) 
                    field.set(entity, ((Long)value).intValue());
                else if (field.getType() == long.class || field.getType() == Long.class) 
                    field.set(entity, (Long) value);
                else if (field.getType() == double.class || field.getType() == Double.class) 
                    field.set(entity, (double) value);
                else if (field.getType() == boolean.class || field.getType() == Boolean.class) 
                    field.set(entity, (boolean) value);
                else if (field.getType() == Date.class) 
                    field.set(entity, (Date) value);
            }
        }
        return entity;
    }   
}