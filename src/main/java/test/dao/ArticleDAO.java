package test.dao;

import java.util.List;

import test.model.Article;

public interface ArticleDAO {
	public List<Article> getAllArticles();
	public List<Article> getArticlesByName(String name);
	public void addArticle(Article article);
}
