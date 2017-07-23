package com.dryseed.dryseedapp.framework.ormLite.test;

import java.sql.SQLException;
import java.util.List;

import android.test.AndroidTestCase;

import com.dryseed.dryseedapp.framework.ormLite.bean.Article;
import com.dryseed.dryseedapp.framework.ormLite.bean.Student;
import com.dryseed.dryseedapp.framework.ormLite.bean.User;
import com.dryseed.dryseedapp.framework.ormLite.db.ArticleDao;
import com.dryseed.dryseedapp.framework.ormLite.db.DatabaseHelper;
import com.dryseed.dryseedapp.framework.ormLite.db.UserDao;
import com.j256.ormlite.dao.Dao;
import com.orhanobut.logger.Logger;

public class OrmLiteDbTest extends AndroidTestCase
{
	public void testAddArticle()
	{
		User u = new User();
		u.setName("张鸿洋");
		new UserDao(getContext()).add(u);
		Article article = new Article();
		article.setTitle("ORMLite的使用");
		article.setUser(u);
		new ArticleDao(getContext()).add(article);

	}

	public void testGetArticleById()
	{
		Article article = new ArticleDao(getContext()).get(1);
		Logger.e(article.getUser() + " , " + article.getTitle());
	}

	public void testGetArticleWithUser()
	{

		Article article = new ArticleDao(getContext()).getArticleWithUser(1);
		Logger.e(article.getUser() + " , " + article.getTitle());
	}

	public void testListArticlesByUserId()
	{

		List<Article> articles = new ArticleDao(getContext()).listByUserId(1);
		Logger.e(articles.toString());
	}

	public void testGetUserById()
	{
		User user = new UserDao(getContext()).get(1);
		Logger.e(user.getName());
		if (user.getArticles() != null)
			for (Article article : user.getArticles())
			{
				Logger.e(article.toString());
			}
	}

	public void testAddStudent() throws SQLException
	{
		Dao dao = DatabaseHelper.getHelper(getContext()).getDao(Student.class);
		Student student = new Student();
		student.setDao(dao);
		student.setName("张鸿洋");
		student.create();
	}



}
