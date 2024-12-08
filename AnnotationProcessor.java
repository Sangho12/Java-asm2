import java.io.File;
import java.lang.reflect.Field;
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

    public static String CustomFileName(String methodName, Class<?> clazz){
        try{
            Method method = clazz.getMethod(methodName, ArrayList.class);
            String Filename = "DefaultName";
            if (method.isAnnotationPresent(CustomFileName.class)){
                CustomFileName customFileName = method.getAnnotation(CustomFileName.class);
                Filename = customFileName.Name();
                return Filename;
            }
            return Filename;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Invalid method name: " + methodName);
        }
    }

    public static boolean FailCustomValidate(student_admission_record stu, Class<?> clazz) {
        if (clazz.isAnnotationPresent(CustomValidate.class)) {
            CustomValidate customValidate = clazz.getAnnotation(CustomValidate.class);
            return (stu.getAge() < customValidate.minAge() || stu.getScore() < customValidate.minScore() || stu.getPercentage() < customValidate.minPercentage());
        }
        return false;
    }

}
