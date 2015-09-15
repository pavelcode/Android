package com.cblue.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCMySQLHelper {
	// 数据库url格式："jdbc:mysql://IP:端口/数据库的名字"
	private static String url = "jdbc:mysql://127.0.0.1:3306/android1310";
	private static String user = "root";// 访问数据库的用户名
	private static String password = "root";// 访问数据库的密码
	private static Connection conn = null;
	private static Statement statement = null;
	private static PreparedStatement pStatement = null;

	public JDBCMySQLHelper() {
		getConnection();
	}

	/**
	 * 作用：建立数据库连接
	 */
	private void getConnection() {
		// 1.加载数据库驱动；
		// 2.建立数据库连接。Connection对象。
		// 3.建立Statement对象。Statement对象的作用是传送sql语句到数据库服务器。
		// 4.执行sql语句。
		// 5.处理结果。
		try {
			// 利用反射原理加载数据库驱动程序
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("加载驱动失败！");
		}

		try {
			// 建立数据库连接
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("数据库连接失败！");
		}
	}

	/**
	 * 此方法是用Statement传递SQL语句 作用：执行sql select语句
	 * 
	 * @param sql
	 * @return ResultSet结果集
	 */
	/*
	 * public ResultSet selectResultSet(String sql) { try { //
	 * statement的作用是传递sql语句，让服务器对sql执行操作。 statement = conn.createStatement(); //
	 * 执行sql语句 ResultSet rs = statement.executeQuery(sql); return rs; } catch
	 * (SQLException e) { e.printStackTrace(); } return null; }
	 */
	// ------------------------------------------------------------------------------
	/**
	 * 使用PreparedStatement传递SQL语句 传入的SQL语句中带有占位符.
	 * 例:" Select * from 表名 where name=?" 传入的List用来存放替代sql语句中的占位符即例句中的?号
	 * 
	 * @return 返回的是查询结果，可以把表头当做key，表中各行的值当做value
	 */
	public static ResultSet selcResultSet(String sql, List<Object> values) {
		try {
			pStatement = conn.prepareStatement(sql);
			for (int i = 0; i < values.size(); i++) {
				pStatement.setObject(i + 1, values.get(i));
			}
			ResultSet rs = pStatement.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * 此方法是用来返回查询条件的总条数,例：查询表中有几个人 因为占位符是从1开始计算的，所有循环中第一个是i+1；
	 * 
	 * @return
	 */
	public static int selectResultSetCount(String sql, List<Object> params) {
		try {
			pStatement = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				pStatement.setObject(i + 1, params.get(i));
			}
			ResultSet rs = pStatement.executeQuery();
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 得到一个可以遍历的List
	 * 
	 * @param rs
	 * @return
	 */
	public static List<Map<String, Object>> resultSetToList(ResultSet rs,
			List<Object> keys) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if (rs != null) {
			try {
				while (rs.next()) {
					Map<String, Object> map = new HashMap<String, Object>();
					for (int i = 0; i < keys.size(); i++) {
						map.put(keys.get(i).toString(),
								rs.getObject(keys.get(i).toString()));
					}
					list.add(map);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	/**
	 * Statement传递sql语句， 作用：根据条件查找符合条件的条数
	 * 
	 * @param sql语句一定是
	 *            select count(*) from 表名 where 条件
	 * @return 查询到的条数
	 */
	public static int selectResultSetCount(String sql) {
		try {
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			// 默认游标在数据集的前方。只有next之后才能指到第一条数据
			rs.next();
			// columnIndex the first column is 1, the second is 2
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 此方法是用Statement修改表. 作用：对数据库进行update ,insert ,delete。CRUD操作（增删查改操作）
	 * 
	 * @param sql
	 * @return
	 */
	public boolean updateData(String sql) {
		try {
			statement = conn.createStatement();
			int result = statement.executeUpdate(sql);
			return (result > 0 ? true : false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 此方法是用PreparedStatement 修改数据库表 * @param sql
	 * 
	 * @param params
	 * @return
	 */
	public static boolean updateData(String sql, List<Object> params) {
		try {
			pStatement = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				pStatement.setObject(i + 1, params.get(i));
			}
			int result = pStatement.executeUpdate();
			return result > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * 此方法是为服务器注册而设计，当所注册的数据在数据库里不存在的时候，调用此方法，将用户的信息存入数据库
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public static boolean insertDate(String sql, List<Object> params) {
		try {
			pStatement = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				pStatement.setObject(i + 1, params.get(i));
			}
			int result = pStatement.executeUpdate();
			return result > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	/**
	 * 得到一个注册用的SQL语句
	 * 
	 * @return select count(*) from test where username=? and password =?
	 */
	public static final String getLoginSQL() {
		return "select count(*) from test where username=? and password =?";
	}

	/**
	 * 得到一个登录用的SQL语句
	 * 
	 * @return select * from test where username=?
	 */
	public static final String getRegisterSQL() {
		return "select * from test where username=?";
	}
}
