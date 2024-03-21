package main.java.org.example.week05.hotel_exercise.DAO;

import java.util.List;

public interface IDAO<T>{
    public List<T> getAll();
    public T getById(int id);
    public T create(T t);
    public T update(T t);
    public void delete(T t);
}
