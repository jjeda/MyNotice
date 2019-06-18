package me.jjeda.mynotice.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jjeda.mynotice.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@Entity
@Table
public class Board implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본키 자동 할당 어노테이션
    private Long idx;

    @Column
    private String title;

    @Column
    private String subTitle;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING) // Enum 타입 매핑용 어노테이션
    private BoardType boardType; // Enum -> 데이터베이스 (String)

    @Column
    private LocalDateTime createdDate;

    @Column
    private LocalDateTime updatedDate;

    @OneToOne(fetch = FetchType.LAZY)  //Board와 Userㄹ를 1:1 관계로 설정 -> User 객체가 저장되는것이 아니라 PK가 저장
    private User user; //fetch 1. eager : 처음 도메인 조회할때 2.lazy : 사용될 때

    @Builder
    public Board(String title, String subTitle, String content, BoardType boardType, LocalDateTime createdDate, LocalDateTime updatedDate, User user) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.user = user;
    }
}
