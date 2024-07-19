package com.beyond.board.author.service;

import com.beyond.board.author.domain.Author;
import com.beyond.board.author.dto.AuthorCreateReqDto;
import com.beyond.board.author.dto.AuthorDetResDto;
import com.beyond.board.author.dto.AuthorListResDto;
import com.beyond.board.author.dto.AuthorUpdateReqDto;
import com.beyond.board.author.repository.AuthorRepository;
import com.beyond.board.post.domain.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void authorCreate(AuthorCreateReqDto dto){
        if(authorRepository.findByEmail(dto.getEmail()).isPresent()){
            throw new IllegalArgumentException("이미 존재하는 email입니다.");
        }
        Author author = dto.toEntity();
//        cascade persist 테스트. remove 테스트는 회원삭제로 대체.
        author.getPosts().add(Post.builder()
                .title("가입인사")
                .contents("안녕하세요 " + dto.getName() + "입니다.")
                .author(author)
                .build());
        authorRepository.save(author);
    }

    public List<AuthorListResDto> authorList(){
        List<Author> authorList = authorRepository.findAll();
        List<AuthorListResDto> authorListResDtoList = new ArrayList<>();

        for(Author author : authorList){
            authorListResDtoList.add(author.listFromEntity());
        }
        return authorListResDtoList;
    }

    public AuthorDetResDto authorDetail(Long id){
        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없는 ID 입니다."));

        return author.detFromEntity();
    }
    public Author authorFindByEmail(String email){
        Author author = authorRepository.findByEmail(email).orElseThrow(()-> new EntityNotFoundException("해당 email 업음"));
        return author;
    }

    @Transactional
    public void authorDelete(Long id){
        authorRepository.deleteById(id);
    }

    @Transactional
    public void authorUpdate(Long id, AuthorUpdateReqDto dto){
        Author author = authorRepository.findById(id).orElseThrow(()->new EntityNotFoundException("없는 id"));
        author.updateAuthor(dto);
//        jpa가 특정 엔티티의 변경을 자동으로 인지하고 변경사항을 DB에 반영하는 것이 dirtychecking(변경감지)
//        더티채킹시 transactional(트랜잭셔널) 어노테이션이 필수적으로 필요.
//        authorRepository.save(author);
    }
}
