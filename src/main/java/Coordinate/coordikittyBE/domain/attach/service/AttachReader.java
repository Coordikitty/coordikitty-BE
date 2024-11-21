package Coordinate.coordikittyBE.domain.attach.service;

import Coordinate.coordikittyBE.domain.attach.entity.Attach;
import Coordinate.coordikittyBE.domain.attach.repository.AttachRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttachReader {
    private final AttachRepository attachRepository;

    public List<Attach> readAllByPostId(UUID postId) {
        return attachRepository.findAllByPostId(postId);
    }
}
