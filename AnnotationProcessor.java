import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class AnnotationProcessor {

    public static void OutputProcessor(String methodName, Class<?> clazz, ArrayList<student_admission_record> returnvalue) {
        try {
            Method method = clazz.getMethod(methodName, File.class);
            if (method.isAnnotationPresent(Output.class)) {
                for (student_admission_record a : returnvalue) {
                    System.out.println(a);
                }
            }
        } catch (NoSuchMethodException e){
            throw new RuntimeException("Invalid method name: " + methodName);
        }
    }



}
