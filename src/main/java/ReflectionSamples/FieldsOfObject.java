package ReflectionSamples;

import java.lang.reflect.Field;
import java.util.HashMap;
public class FieldsOfObject {
}



class scratch_27 extends scratch_26 {
    public scratch_27(String field1, int a, String field2) {
        super(field1, a);
        this.field2 = field2;
    }

    String field2;

    public static void main(String[] args) throws Exception{
        scratch_27 jj=new scratch_27("Hello1",4,"hello2");
        HashMap<String,Object> fields=getMemberFields(jj);
        System.out.println(fields);

    }

    private static HashMap<String, Object> getMemberFields(Object obj) throws IllegalAccessException,
            NoSuchFieldException
    {
        HashMap<String, Object> fieldValues = new HashMap<String, Object>();

        Class<?> objClass = obj.getClass();
        while(objClass.getSuperclass()!=null){ // we don't want to process Object.class
            Field[] fields = objClass.getDeclaredFields();
            for(Field field : fields)
            {
                field.setAccessible(true);
                fieldValues.put(field.getName(), field.get(obj));
                if (!field.getType().isPrimitive() && !field.getType().getName().contains("java.lang"))
                {
                    getMemberFields(field.get(obj));
                }
            }
            objClass = objClass.getSuperclass();
        }
        return fieldValues;
    }
}

class scratch_26 {
    String field1;
    int a;

    public scratch_26(String field1, int a) {
        this.field1 = field1;
        this.a = a;
    }
}