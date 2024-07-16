package Coordinate.coordikittyBE.backoffice.user.service;

import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothDao;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostDao;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final String lastStanding = "jmg@naver.com";

    private final UserRepository userRepository;
    private final ClothRepository clothRepository;
    private final ClothDao clothDao;
    private final PostRepository postRepository;
    private final PostDao postDao;

    /**
     * 이거 일단 jmg@naver.com 얘 뺴고 다 지우는데,
     * 현재 각 테이블에 CONSTRAINT 를 id 제외하고 외래키는 다 지워놔서
     * email 비교 후에 jmg 가 아니라면, user-post-cloth-history 에서 다 지우면 됨
     * 지울 때,
     *      cloth 는 url 미리 받아서 firebase 에서 삭제
     *      post 는 postImgs 미리 받아서 firebase 에서 삭제
     *      profile image 도 email 로 지우기 전에 firebase 에서 삭제
     * test 로 지워본 email = qqqq1111@naver.com
     *      현재 user 에서만 삭제
     *      post, cloth, history, firebase(cloth o, postImgs x, profileUrl x)에 잔재
     */
    // SCRUM-218 user records 삭제
    @Transactional
    public String allButOne() {
        userRepository.deleteByEmailNot(lastStanding);
        return "삭제 완료";
    }

    @Transactional
    public String deleteInFirebaseWithClothImageUrl() {
        userRepository.findAll()
                .forEach(user -> {
                    String email = user.getEmail();
                    if (!email.equals(lastStanding)) {
                        List<Cloth> cloth = clothRepository.findAllByUserEmail(email);
                        cloth.forEach(c -> clothDao.delete(c.getId()));
                    }
                });
        return "firebase에 저장된 cloth image 삭제 완료";
    }

    @Transactional
    public String deleteInFirebaseWithPostImageUrl() {
        userRepository.findAll()
                .forEach(user -> {
                    String email = user.getEmail();
                    if (!email.equals(lastStanding)) {
                        List<Post> posts = postRepository.findAllByUserEmail(email);
                        posts.forEach(post -> postDao.delete(post.getId()));
                    }
                });
        return "firebase에 저장된 post image 삭제 완료";
    }

    /**
     * user 테이블에 미리 uuid 필드 생성 후 값 채워 넣기 - 진행 중
     * user 테이블에 user_email 필드에 기존 email 값 복제
     * user 테이블에서 pk (email) 삭제
     * user 테이블에서 pk (uuid) 추가
     */
    // SCRUM-215 from email to uuid
    @Transactional
    public String createId() {
        System.out.println("SCRUM-215 from email to uuid");
        userRepository.findAll().forEach(User::createId);
        return "user id 생성 완료";
    }

    @Transactional
    public String copyEmail() {
        userRepository.findAll().forEach(User::copyEmail);
        return "user email 복제 완료";
    }

    // SCRUM-216 변경된 id 영향 반영
}
