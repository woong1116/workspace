package builderexam.lombokuser;

public class UserMain {
    public static void main(String[] args) {
        User kang = User.builder()
                .name("kang")
                .email("kang@gmail.com")
                .build();

        System.out.println(kang);
    }
}
