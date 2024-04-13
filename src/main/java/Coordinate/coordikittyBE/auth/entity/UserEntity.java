package Coordinate.coordikittyBE.auth.entity;

import Coordinate.coordikittyBE.closet.entity.ClothEntity;
import Coordinate.coordikittyBE.page.alarm.entity.AlarmEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity(name="user")
public class UserEntity {
    @Id
    @Email
    @Column(name="id", nullable = false)
    private String email;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="age", nullable = false)
    private int age;
    @Column(name="nickname", nullable = false, length = 10)
    private String nickname;

    @Column(name="birth", nullable = false)
    private LocalDate birth;
    @Column(name="phone_number", nullable = false)
    private String phoneNumber;
    @Column(name="tall", nullable = false)
    private int tall;
    @Column(name="shoesize", nullable = false)
    private int shoeSize;
    @Column(name="weight", nullable = false)
    private int weight;

    @OneToMany(mappedBy = "userEntity")
    private List<ClothEntity> clothEntities;

    @OneToMany(mappedBy = "userEntity")
    private List<AlarmEntity> alarmEntities;
}


