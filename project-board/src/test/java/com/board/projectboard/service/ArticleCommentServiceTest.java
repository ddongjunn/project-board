package com.board.projectboard.service;

import com.board.projectboard.domain.Article;
import com.board.projectboard.dto.ArticleCommentDto;
import com.board.projectboard.repository.ArticleCommentRepository;
import com.board.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks
    private ArticleCommentService sut;

    @Mock
    private ArticleCommentRepository articleCommentRepository;

    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleComments() {
        // Given
        Long articleId = 1L;

        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title", "content", "hashtag")));

        // When
        List<ArticleCommentDto> articleComment = sut.searchArticleComment(1L);

        // Then
        assertThat(articleComment).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComments_thenSavingArticleComments() {
        // Given
        Long articleId = 1L;

        given(articleRepository.findById(articleId)).willReturn(Optional.of(
                Article.of("title", "content", "hashtag")));

        // When
        List<ArticleCommentDto> articleComment = sut.searchArticleComment(1L);

        // Then
        assertThat(articleComment).isNotNull();
        then(articleRepository).should().findById(articleId);
    }


}