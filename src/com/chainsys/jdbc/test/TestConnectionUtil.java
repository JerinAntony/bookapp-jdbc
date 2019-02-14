package com.chainsys.jdbc.test;

import java.sql.Connection;

import com.chainsys.jdbc.ConnectionUtil;

public class TestConnectionUtil {

	public static void main(String[] args) {
		Connection connection = ConnectionUtil.getConnection();
		System.out.println(connection);
	}

}
