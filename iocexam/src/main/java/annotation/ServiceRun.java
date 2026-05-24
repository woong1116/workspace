package annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ServiceRun {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Service service = new Service();

        Method[] declaredMethods = service.getClass().getDeclaredMethods();

        for (Method m : declaredMethods) {
            if(m.isAnnotationPresent(PrintAnnotation.class)){
                System.out.println("{{{"+m.getName()+"}}}");

                PrintAnnotation annotation = m.getAnnotation(PrintAnnotation.class);

                for(int i=0;i<annotation.number();i++){
                    System.out.print(annotation.value());
                }
                System.out.println();

            }
//            사용자가 구현한 메서드 내부를 실행함!!
            m.invoke(service);
        }
    }
}
