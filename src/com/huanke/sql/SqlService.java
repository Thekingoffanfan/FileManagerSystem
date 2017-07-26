package com.huanke.sql;

import java.sql.ResultSet;

public interface SqlService {

	public void insertData(String name, String message);

	public ResultSet query(String message);
}
