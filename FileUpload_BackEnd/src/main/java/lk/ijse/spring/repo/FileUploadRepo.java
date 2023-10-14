package lk.ijse.spring.repo;

import lk.ijse.spring.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileUploadRepo extends JpaRepository<File,Integer> {
    @Query(value = "SELECT filePath FROM Image ORDER BY id DESC LIMIT 1", nativeQuery = true)
    String getLastImageLocation();
}
