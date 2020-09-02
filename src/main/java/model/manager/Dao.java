package model.manager;

import java.util.List;


public interface Dao<T> {
	  void save(T t);
	  T load(int id);
	  void delete(int id);
	  void update(T t);
	  List<T> loadAll();
	  List<T> selectWhere(String field, String value);
	}
