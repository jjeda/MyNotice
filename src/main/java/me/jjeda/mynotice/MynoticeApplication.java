package me.jjeda.mynotice;

import me.jjeda.mynotice.board.Board;
import me.jjeda.mynotice.board.BoardRepository;
import me.jjeda.mynotice.board.BoardType;
import me.jjeda.mynotice.user.User;
import me.jjeda.mynotice.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@SpringBootApplication
public class MynoticeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MynoticeApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BoardRepository boardRepository) throws Exception{
		return(args -> {
			User user = userRepository.save(User.builder()
					.name("jjeda")
					.password("test")
					.email("jjeda@naver.com")
					.createdDate(LocalDateTime.now())
					.build());

			IntStream.rangeClosed(1,200).forEach(index ->boardRepository.save(Board.builder()
					.title("게시글"+index)
					.subTitle("순서"+index)
					.content("콘텐츠")
					.boardType(BoardType.free)
					.createdDate(LocalDateTime.now())
					.updatedDate(LocalDateTime.now())
					.user(user).build())
			);
		});
	}
}
