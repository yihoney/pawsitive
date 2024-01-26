package com.pawsitive.usergroup.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    private int memberNo;

    @MapsId("memberNo")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "birth")
    private LocalDateTime birth;

    @Column(name = "gender")
    private char gender;

    @Column(name = "type")
    private int type;

    @Column(name = "stage")
    private int stage;

    @Column(name = "mbti")
    private String mbti;


}
