package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exceptions.DBException;

public class DBManager {
	private Connection conn;
	private static DBManager instance;

	private DBManager(String db, String user, String pass, int port, String host) {
		try {
			conn = DriverManager
					.getConnection("jdbc:mysql://" + host + ":" + port + "/" + db, user, pass);
		} catch (SQLException e) {
			throw new DBException(e);
		}
	}

	private DBManager(String db, String user, String pass, int port) {
		this(db, user, pass, port, "localhost");
	}

	private DBManager(String db, String user, String pass) {
		this(db, user, pass, 3306);
	}

	private DBManager(String db, String user) {
		this(db, user, null);
	}

	private DBManager(String db) {
		this(db, "root");
	}

	// private DBManager() {
	// this("i4c");
	// }

	public static DBManager getInstance(String db) {
		if (instance == null) {
			instance = new DBManager(db);
		}
		return instance;
	}

	public Connection getConn() {
		return conn;
	}

	// Array string for creating tables
	private String[] createTables = { """
			CREATE TABLE IF NOT EXISTS countries(
			    id INT AUTO_INCREMENT PRIMARY KEY,
			    country VARCHAR(50) NOT NULL
			)
			""",
			"""
					CREATE TABLE IF NOT EXISTS cities(
					    id INT AUTO_INCREMENT PRIMARY KEY,
					    city VARCHAR(50) NOT NULL,
					    countryid INT REFERENCES countries(id),
					    ucity VARCHAR(60) NOT NULL UNIQUE
					)
					""",
			"""
					CREATE TABLE IF NOT EXISTS hotels(
					    id INT AUTO_INCREMENT PRIMARY KEY,
					    hotel VARCHAR(100) NOT NULL,
					    countryid INT REFERENCES countries(id),
					    cityid INT REFERENCES cities(id),
					    stars TINYINT NOT NULL DEFAULT 0,
					    cost DOUBLE(10,2) NOT NULL DEFAULT 0,
					    info TEXT
					)
					""",
			"""
					CREATE TABLE IF NOT EXISTS images(
					    id INT AUTO_INCREMENT PRIMARY KEY,
					    hotelid INT REFERENCES hotels(id),
					    imagepath VARCHAR(256) NOT NULL
					)
					""",
			"""
					CREATE TABLE IF NOT EXISTS roles(
					    id INT AUTO_INCREMENT PRIMARY KEY,
					    role VARCHAR(20) NOT NULL UNIQUE
					)
					""",
			"""
					CREATE TABLE IF NOT EXISTS users(
					    id INT AUTO_INCREMENT PRIMARY KEY,
					    username VARCHAR(30) NOT NULL,
					    pass VARCHAR(80) NOT NULL,
					    email VARCHAR(128),
					    roleid INT REFERENCES roles(id),
					    discount TINYINT NOT NULL DEFAULT 0,
					    avatar VARCHAR(256)
					)
					""" };

	// Array string for droping tables in db
	private String[] dropTables = {
			"DROP TABLE IF EXISTS users",
			"DROP TABLE IF EXISTS roles",
			"DROP TABLE IF EXISTS images",
			"DROP TABLE IF EXISTS hotels",
			"DROP TABLE IF EXISTS cities",
			"DROP TABLE IF EXISTS countries",
	};

	// for creating DB
	public static String createDB(String db, String user, String pass, int port, String host) {
		String result = null;
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/", user, pass);
			var stmt = connection.createStatement();
			var queryCreateDB = "CREATE DATABASE IF NOT EXISTS `" + db + "`";
			stmt.executeUpdate(queryCreateDB);
			result = db;

		} catch (SQLException e) {
			throw new DBException(e);
		}
		return result;
	}

	public static void main(String[] args) throws SQLException {

		// System.out.println(dbManager.getConn());
		String db = DBManager.createDB("se.tp11.i4", "root",
				null, 3306, "localhost");
		System.out.println(db);

		DBManager dbManager = new DBManager(db);

		// Drop all tables in the DB
		for (String sql : dbManager.dropTables) {
			var stmt = dbManager.conn.createStatement();
			stmt.execute(sql);
		}

		// Create tables in the DB
		for (String sql : dbManager.createTables) {
			var stmt = dbManager.conn.createStatement();
			stmt.execute(sql);
		}
		dbManager.conn.close();
	}
}
