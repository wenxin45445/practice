
package com.example.readinglist;

import org.springframework.data.jpa.repository.JpaRepository;


/**
 * 持久化接口
 */

public interface ReaderRepository extends JpaRepository<Reader, String> {

}

