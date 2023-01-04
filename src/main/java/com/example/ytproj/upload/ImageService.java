
package com.example.ytproj.upload;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    String profileImage(MultipartFile mf, String uname) throws IOException;

    String uploadImage(MultipartFile mf, String uname) throws IOException;

    InputStream serveImage(String filename, String uname) throws FileNotFoundException;

    InputStream serveProfileImage(String filename, String uname) throws FileNotFoundException;
}
