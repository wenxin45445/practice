package com.example.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 持久化接口
 */
public interface ReadingListRespository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
