package lk.ijse.spring.repo;

import lk.ijse.spring.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileUploadRepo extends JpaRepository<File,Integer> {
}
