package builderexam.builderuser;

public class UserMain {
    public static void main(String[] args) {
        User woong = new User.Builder().name("woong")
                .age(20)
                .email("woong@woong.com")
                .address("주소")
                .build();

        System.out.println(woong);

        User kim = new User.Builder()
                .name("kim")
                .build();

        System.out.println(kim);

        User hong = new User.Builder()
                .address("서울")
                .age(10)
                .build();

        System.out.println(hong);

        User lee = User.builder()
                .name("lee")
                .build();

        System.out.println(lee);
    }
}
