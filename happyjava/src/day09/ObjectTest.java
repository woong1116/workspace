package day09;

class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // toString() 오버라이딩
    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + "}";
    }

    // equals() 오버라이딩
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Person person = (Person) obj;
        return age == person.age && name.equals(person.name);
    }

    // hashCode() 오버라이딩
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + age;
        return result;
    }
}

public class ObjectTest {
    public static void main(String[] args) {
        Person p1 = new Person("김철수", 25);
        Person p2 = new Person("김철수", 25);
        Person p3 = new Person("이영희", 23);

        // toString() 테스트
        System.out.println(p1.toString());  // Person{name='김철수', age=25}
        System.out.println(p1);  // toString() 자동 호출

        // equals() 테스트
        System.out.println(p1.equals(p2));  // true
        System.out.println(p1.equals(p3));  // false
        System.out.println(p1 == p2);       // false (참조 비교)

        // hashCode() 테스트
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());  // p1과 같음 (equals true면 hashCode도 같아야 함)

        // getClass() 테스트
        System.out.println(p1.getClass().getName());  // Person

        // clone() 사용 (위해서는 Cloneable 인터페이스 구현 필요)
    }
}