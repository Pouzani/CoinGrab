package test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import test.model.Article;

public class ArticleDAOImpl implements ArticleDAO {
	
	private DAOFactory daoFactory;
	
	public ArticleDAOImpl (DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}
	
	@Override
	public List<Article> getAllArticles() {
		List<Article> allArticles = new ArrayList<Article>();
		try {
			Connection conn = daoFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM articles");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Article article = new Article();
				article.setIdArticle(rs.getInt("article_id"));
				article.setTitle(rs.getString("title"));
				article.setContent(rs.getString("content"));
				article.setUser(rs.getString("user"));
				allArticles.add(article);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allArticles;
	}

	@Override
	public List<Article> getArticlesByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addArticle(Article article) {
		// TODO Auto-generated method stub
		try {
			Connection conn = daoFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("INSERT INTO articles VALUES (?,?,?,?)");
			ps.setString(1, null);
			ps.setString(2, article.getTitle());
			ps.setString(3, article.getContent());
			ps.setString(4, article.getUser());
			ps.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
