package Coordinate.coordikittyBE.domain.auth.withdraw.service;

import Coordinate.coordikittyBE.domain.attach.repository.AttachRepository;
import Coordinate.coordikittyBE.domain.auth.entity.User;
import Coordinate.coordikittyBE.domain.auth.repository.RefreshTokenRepository;
import Coordinate.coordikittyBE.domain.auth.repository.UserRepository;
import Coordinate.coordikittyBE.domain.closet.entity.Cloth;
import Coordinate.coordikittyBE.domain.closet.repository.ClothRepository;
import Coordinate.coordikittyBE.domain.history.repository.HistoryRepository;
import Coordinate.coordikittyBE.domain.post.entity.Post;
import Coordinate.coordikittyBE.domain.post.repository.PostDao;
import Coordinate.coordikittyBE.domain.post.repository.PostImageRepository;
import Coordinate.coordikittyBE.domain.post.repository.PostRepository;
import Coordinate.coordikittyBE.exception.CoordikittyException;
import Coordinate.coordikittyBE.exception.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WithdrawService {
    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final HistoryRepository historyRepository;
    private final ClothRepository clothRepository;
    private final PostRepository postRepository;
    private final AttachRepository attachRepository;
    private final PostImageRepository postImageRepository;
    private final PostDao postDao;

    @Transactional
    public String withdraw(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CoordikittyException(ErrorType.MEMBER_NOT_FOUND));

        // 유저가 남긴 모든 history
        historyRepository.deleteAllByUserId(user.getId());

        List<Cloth> clothes = clothRepository.findAllByUserId(user.getId());
        clothes.forEach(cloth -> attachRepository.deleteAllByClothId(cloth.getId()));

        List<Post> posts = postRepository.findAllByUserId(user.getId());
        posts.forEach(post -> {
            // 유저의 게시글에 남은 모든 history
            historyRepository.deleteAllByPostId(post.getId());
            attachRepository.deleteAllByPostId(post.getId());

            postDao.delete(post.getId());
            postImageRepository.deleteAllByPostId(post.getId());
        });

        refreshTokenRepository.deleteByUserId(user.getId());
        userRepository.deleteById(user.getId());
        return "삭제 성공";
    }
}
