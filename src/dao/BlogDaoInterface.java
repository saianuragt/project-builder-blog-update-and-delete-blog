package dao;

import java.sql.SQLException;
import java.util.List;

import model.Blog;

public interface BlogDaoInterface {
	
		void insertBlog(Blog blog) throws SQLException, Exception;
		Blog selectBlog(int blogid);
		List<Blog> selectAllBlogs() throws SQLException, Exception;
		boolean deleteBlog(int id) throws SQLException;
		boolean updateBlog(Blog blog) throws SQLException, Exception;
	
}
