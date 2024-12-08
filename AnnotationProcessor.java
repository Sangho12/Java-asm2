import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static String OutputFileName(String methodName, Class<?> clazz){
        try{
            Method method = clazz.getMethod(methodName, ArrayList.class);
            String name = "output.csv";
            if (method.isAnnotationPresent(CustomFileName.class)){
                Scanner in = new Scanner(System.in);
                System.out.println("Please input your output file name below: ");
                name = in.nextLine();
                while (name == ""){
                    System.out.println("Invalid name, file name can't be empty." + "\n" + "Please enter again:");
                    name = in.nextLine();
                }
            }
            return name;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Invalid method name: " + methodName);
        }
    }


}
