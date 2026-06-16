package builderexam.builderuser;

public class User {
    private String name;
    private int age;
    private String email;
    private String address;

    private User(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.email = builder.email;
        this.address = builder.address;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder{
        private String name;
        private int age;
        private String email;
        private String address;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder age(int age) {
            this.age = age;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public User build() {
            return new User(this);
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", email='" + email + '\'' +
                    ", address='" + address + '\'' +
                    '}';
        }
    }
}
