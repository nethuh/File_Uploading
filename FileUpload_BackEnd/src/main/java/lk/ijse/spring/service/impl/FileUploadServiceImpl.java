package lk.ijse.spring.service.impl;

import lk.ijse.spring.dto.FileDTO;
import lk.ijse.spring.entity.File;
import lk.ijse.spring.repo.FileUploadRepo;
import lk.ijse.spring.service.FileUploadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    FileUploadRepo repo;

    @Autowired
    ModelMapper mapper;


    public void saveFileLocation(FileDTO fileDTO) {
        repo.save(mapper.map(fileDTO, File.class));
    }

    public String loadFileLocation() {
        return repo.getLastImageLocation();
    }
}
