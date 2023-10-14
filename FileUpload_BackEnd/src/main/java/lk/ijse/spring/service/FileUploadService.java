package lk.ijse.spring.service;

import lk.ijse.spring.dto.FileDTO;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface FileUploadService {
    void saveFileLocation(@ModelAttribute FileDTO fileDTO);
    String loadFileLocation();
}
