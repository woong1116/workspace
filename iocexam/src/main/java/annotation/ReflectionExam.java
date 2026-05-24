package annotation;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionExam {
    public static void main(String[] args) throws Exception {
        // Person 클래스의  Class객체
        Class<?> personClass = Class.forName("annotation.Person");
        //Class 객체로 부터 Person 의 인스턴스 생성.
        Object personInstance = personClass.getDeclaredConstructor().newInstance();

        System.out.println(personInstance);

//        private 한 필드 name에 접근
        Field nameFiled = personClass.getDeclaredField("name");
        nameFiled.setAccessible(true); //private 필드 접근 가능!!
        nameFiled.set(personInstance,"carami");

        System.out.println(personInstance);

//        private 한 메서드 sayHello 에 접근
        Method method = personClass.getDeclaredMethod("sayHello");
        method.setAccessible(true);
        method.invoke(personInstance);
    }
}
