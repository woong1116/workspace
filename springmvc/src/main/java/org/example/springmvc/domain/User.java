package org.example.springmvc.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotEmpty(message = "이름을 입력하세요.")
    private String name;
    @NotEmpty(message = "비밀번호를 입력하세요.")
    @Size(min = 4, max = 6, message = "비밀번호는 4-6자 사이만 입력됩니다.")
//    @Pattern(regexp = "",message = "")
    private String password;
    @NotEmpty(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식에 맞게 작성하세요.")
    private String email;
//
//    public User(String name, String password, String email) {
//        this.name = name;
//        this.password = password;
//        this.email = email;
//    }
//
//    public User(){}
//
//    @Override
//    public String toString() {
//        return "User{" +
//                "name='" + name + '\'' +
//                ", password='" + password + '\'' +
//                ", email='" + email + '\'' +
//                '}';
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
}
