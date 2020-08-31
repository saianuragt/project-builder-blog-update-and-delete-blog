package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Blog;
import utility.ConnectionManager;

public class BlogDaoImpl implements BlogDaoInterface{

	public void insertBlog(Blog blog) throws Exception {
		
		ConnectionManager cm = new ConnectionManager();
		String sql="insert into project_week5_2(b_id,b_title,b_description,b_date)values(?,?,?,?)";
		PreparedStatement ps = cm.getConnection().prepareStatement(sql);
		ps.setLong(1, blog.getBlogId());
		ps.setString(2, blog.getBlogTitle());
		ps.setString(3, blog.getBlogDescription());
		ps.setDate(4, java.sql.Date.valueOf(blog.getPostedOn()));
		ps.executeUpdate();
		cm.getConnection().close();
	}

	
	public Blog selectBlog(int blogid) {
		try {
			Blog blog = null;
		
	     	Connection con = ConnectionManager.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT b_id,b_title,b_description,b_date FROM project_week5_2 WHERE b_id =?");
			ps.setInt(1, blogid);
		
			ResultSet rs = ps.executeQuery();

			
			while (rs.next()) {
				long blogId = rs.getInt("blogId");
				String blogTitle = rs.getString("blogName");
				String blogDescription = rs.getString("blogDescription");
				LocalDate postedOn = rs.getDate("postedOn").toLocalDate();
				
				blog = new Blog();
				blog.setBlogId(blogId);
				blog.setBlogTitle(blogTitle);
				blog.setBlogDescription(blogDescription);
				blog.setPostedOn(postedOn);
			}
		
		return blog;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
		
	}

	
	public List<Blog> selectAllBlogs() throws SQLException, Exception {
		List<Blog> list=new ArrayList<Blog>(); 
		
		ConnectionManager cm = new ConnectionManager();
		String sql="select * from project_week5_2";
		PreparedStatement ps = cm.getConnection().prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		Blog blog = new Blog();
		while(rs.next()) {
			
			long blogId = rs.getLong(1);
			String blogTitle = rs.getString(2);
			String BlogDescription = rs.getString(3);
			LocalDate postedOn = rs.getDate(4).toLocalDate();
			
			blog.setBlogId(blogId);
			blog.setBlogTitle(blogTitle);
			blog.setBlogDescription(BlogDescription);
			blog.setPostedOn(postedOn);
			
			list.add(blog);
		}
		return list;
	
	}

	
	public boolean deleteBlog(int b_id) throws SQLException {
		
		
		try {
			boolean delete ;
			Connection con = ConnectionManager.getConnection();
			PreparedStatement ps= con.prepareStatement("DELETE FROM project_week5_2 WHERE b_id = ?");
			ps.setInt(1, b_id);
			delete = ps.execute();
			return delete;
			} catch (Exception e) {
				System.out.println(e);
			}
			
			return false;
	}

	
	public boolean updateBlog(Blog blog) throws SQLException, Exception {
		boolean update = false;

		try {

			Connection con = ConnectionManager.getConnection();
			
			PreparedStatement ps = con.prepareStatement("UPDATE project_week5_2 SET  b_title = ?, b_description = ?, b_date = ? WHERE b_id = ?"); 
			
			ps.setString(1, blog.getBlogTitle());
			ps.setString(2,blog.getBlogDescription());
			ps.setDate(3, java.sql.Date.valueOf(blog.getPostedOn()));
			ps.setInt(4, blog.getBlogId());
			update = ps.executeUpdate() > 0;
			
		}catch (Exception e) {
			System.out.println(e);
		}
		return update;
		
	}
	
}