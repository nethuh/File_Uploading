package lk.ijse.spring.controller;


import lk.ijse.spring.dto.FileDTO;
import lk.ijse.spring.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

@RestController
@RequestMapping("upload")
@CrossOrigin
public class FileUploadController {

    @Autowired
    FileUploadService fileUploadService;

    @PostMapping
    public String uploadFileWithSpring(@RequestPart("image") MultipartFile myFile, ModelMap modelMap) {
        modelMap.addAttribute("file", myFile);
        try {
            byte[] byteArray = myFile.getBytes();
            String projectPath = new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()).getParentFile().getParentFile().getAbsolutePath();

            String path = "/" + myFile.getOriginalFilename();
            Path location = Paths.get(projectPath + path);
            Files.write(location,byteArray);
            myFile.transferTo(location);
            System.out.println(projectPath);

            fileUploadService.saveFileLocation(new FileDTO(null, location.toString()));

            return Base64.getEncoder().encodeToString(byteArray);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public  String getAllFilesFromDatabase(){
        return fileUploadService.loadFileLocation();
    }

}
