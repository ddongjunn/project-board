package com.board.projectboard.repository;

import com.board.projectboard.domain.Article;
import com.board.projectboard.domain.QArticle;
import com.jayway.jsonpath.JsonPath;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {

    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }

    Page<Article> findByTitleContaining(String searchKeyword, Pageable pageable);

    Page<Article> findByContentContaining(String searchKeyword, Pageable pageable);

    Page<Article> findBy_AndUserAccount_UserIdContaining(String searchKeyword, Pageable pageable);

    Page<Article> findBy_AndUserAccount_NickNameContaining(String searchKeyword, Pageable pageable);

    Page<Article> findByHashtag(String s, Pageable pageable);
}