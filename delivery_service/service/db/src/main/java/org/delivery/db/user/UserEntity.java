package org.delivery.db.user;


import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.delivery.db.BaseEntity;
import org.delivery.db.user.enums.UserStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity  // 옵션으로 여기에 (name = "user") 라고하면 UserEntity -> user 라고 이름을 바꿔서 user 테이블을 찾는다  @Table(name="user")과 똑같이 작동
@Table(name = "user")  // 이거는 이름을 바꾸는게 아니라 테이블 user를 찾는 것이다.
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class UserEntity extends BaseEntity {
    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 100, nullable = false)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(length = 150, nullable = false)
    private String address;

    private LocalDateTime registeredAt;

    private LocalDateTime unregisteredAt;

    private LocalDateTime lastLoginAt;
}
