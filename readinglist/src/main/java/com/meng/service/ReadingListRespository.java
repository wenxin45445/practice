package com.meng.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meng.entity.Book;

import java.util.List;

/**
 * 持久化接口
 */
public interface ReadingListRespository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
