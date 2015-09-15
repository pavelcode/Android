package com.cblue.reflect;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class UserInfo {
	private int id;

	private String username;

	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "UserInfo [id=" + id + ", username=" + username + ", password=" + password + "]";
	}
}



public class JdbcUtils {
	// 数据库的登陆名
	private final String NAME = "root";

	// 数据库的密码
	private final String PASSWORD = "root";

	// 数据库的驱动
	private final String URL = "com.mysql.jdbc.Driver";

	// 访问数据库的地址
	private final String PATH = "jdbc:mysql://localhost/student";

	// 数据库的连接对象
	private Connection conn = null;

	// 预处理SQL
	private PreparedStatement ps = null;

	// 返回的结果集
	private ResultSet set = null;

	public JdbcUtils() {
		// 加载驱动程序
		try {
			Class.forName(URL);
			System.out.println("加载驱动成功！");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获得数据库连接
	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection(PATH, NAME, PASSWORD);
			System.out.println("数据库连接成功！");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	/**
	 * 向数据库里执行插入、删除和修改操作
	 * 
	 * @param sql
	 * @param params
	 *            向数据库添加的操作数据，比如执行插入操作，params为用户名(mary),密码(111)
	 * @return flag为true表示执行sql语句成功
	 */
	public boolean updateByPreparedStatement(String sql, List<Object> params) {
		boolean flag = false;// 是否执行操作成功
		try {
			ps = conn.prepareStatement(sql);
			int index = 1;// 定义ps对象里每个params的值的索引
			int result = -1;// 接收ps执行sql语句后的返回值
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					ps.setObject(index++, params.get(i));// 使用给定对象设置指定参数的值。第二个参数必须是
					// Object 类型
				}
			}
			result = ps.executeUpdate();// 执行sql操作，返回为1表示执行成功
			flag = result > 0 ? true : false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * 查询单条记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Map<String, Object> QueryResult(String sql, List<Object> params) {
		// map用来存储字段名及其对应的值，比如：username→mary，password→111
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			ps = conn.prepareStatement(sql);
			int index = 1;
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					ps.setObject(index++, params.get(i));
				}
			}
			set = ps.executeQuery();
			// ResultSetMetaData是返回结果集中列的信息
			ResultSetMetaData rsmd = set.getMetaData();
			int col_len = rsmd.getColumnCount();// 返回列数
			while (set.next()) {
				for (int i = 0; i < col_len; i++) {
					String col_name = rsmd.getColumnName(i + 1);// 获得列名，username，在数据库中索引是从1开始
					System.out.println("i-->" + i + col_name);
					Object col_value = set.getObject(col_name);// 获得列名对应的值，mary
					if (col_value == null) {// 在数据库中，默认值为null
						col_value = "";// 避免空指针
					}
					map.put(col_name, col_value);// 每次循环，将列名与值匹配，比如：①id=1②username=mary③password=111
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * 使用反射机制查询单条记录
	 * 
	 * @param sql
	 * @param params
	 * @param cls
	 *            反射的类，比如UserInfo
	 * @return 返回反射类的对象→userInfo
	 */
	public <T> T ReflectResult(String sql, List<Object> params, Class<T> cls) {
		T resultObject = null;// 声明一个T对象，泛指反射过来类的对象
		try {
			ps = conn.prepareStatement(sql);
			int index = 1;
			if (params != null && !params.isEmpty()) {
				for (int i = 0; i < params.size(); i++) {
					ps.setObject(index++, params.get(i));
				}
			}
			set = ps.executeQuery();
			ResultSetMetaData rsmd = set.getMetaData();
			int col_len = rsmd.getColumnCount();
			while (set.next()) {
				// newInstance获得一个类的实例，是Class类中方法
				resultObject = cls.newInstance();
				for (int i = 0; i < col_len; i++) {
					String col_name = rsmd.getColumnName(i + 1);
					Object col_value = set.getObject(col_name);
					if (col_value == null) {
						col_value = "";
					}
					// 反射的字段可能是一个类（静态）字段或实例字段。
					Field field = cls.getDeclaredField(col_name);
					// 值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。
					field.setAccessible(true);
					// 为resultObject添加值，比如：
					// ①field→id，resultObject→1
					// ②field→username，resultObject→mary
					// ③field→password，resultObject→111
					field.set(resultObject, col_value);
				}
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultObject;
	}

	/**
	 * 关闭结果集，conn连接
	 */
	public void releaseConnection() {
		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JdbcUtils jdbc = new JdbcUtils();
		jdbc.getConnection();
		// //向数据库中插入数据
		// String sql = "INSERT INTO user(username,password) VALUES(?,?);";
		// List<Object> params = new ArrayList<Object>();
		// params.add("mary");
		// params.add("111");
		// boolean result = jdbc.updateByPreparedStatement(sql, params);
		// System.out.println("插入数据-->" + result);
		// jdbc.releaseConnection();
		// 查询单条记录
		// String sql = "SELECT * FROM user WHERE id=?";
		// List<Object> params = new ArrayList<Object>();
		// params.add(1);
		// Map<String,Object> map = null;
		// map = jdbc.QueryResult(sql, params);
		// System.out.println(map);
		// 使用反射机制查询单条记录
		String sql = "SELECT * FROM user WHERE id=?";
		List<Object> params = new ArrayList<Object>();
		params.add(1);
		UserInfo userInfo = jdbc.ReflectResult(sql, params, UserInfo.class);
		System.out.println(userInfo);
	}

}
