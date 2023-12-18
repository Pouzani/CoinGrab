package test;

import java.util.List;

import test.dao.ArticleImpl;
import test.model.Article;

public class ArticleTest {
	public static void main(String[] args) {
		List<Article> article;
		ArticleImpl articleImpl = new ArticleImpl();
		article = articleImpl.getAllArticles();
		System.out.println(article.get(0).getContent());
		System.out.println(article.get(0).getTitle());
		System.out.println(article.get(0).getUser());
		System.out.println(article.get(0).getIdArticle());
	}
}
