package org.example.oauthexam.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true, length = 50)
    private String username;  //carami
    @Column(nullable = false, length = 100)
    private String password;  //1234
    @Column(nullable = false, length = 50)
    private String name;  //강경미
    @Column(nullable = false,length = 100)
    private String email; //carami@exam.com
    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registrationDate = LocalDateTime.now();



    // 소셜 로그인을 위해서 컬럼 2개를 추가함.  어떤 provider를 이용해서 로그인했고,  아이디는 무엇인지 저장.
    @Column(name="social_id")
    private String socialId;
    private String provider; //github, google,




    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;


//    //샘플!!!  알맞게 수정해서 사용하기.
//    public static User from(UserRegiserDTO registerDTO, String encodedPassword, Set<Role> roles) {
//        User user = new User();
//        user.setUsername(registerDTO.getUsername());
//        user.setPassword(encodedPassword);
//        user.setName(registerDTO.getName());
//        user.setEmail(registerDTO.getEmail());
//        user.setRoles(roles);
//        return user;
//    }

    @PrePersist
    public void prePersist(){
        if(this.registrationDate == null){
            this.registrationDate = LocalDateTime.now();
        }
    }
}