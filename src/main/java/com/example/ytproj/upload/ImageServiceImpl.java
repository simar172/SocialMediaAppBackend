
package com.example.ytproj.upload;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.val;

@Service
public class ImageServiceImpl implements ImageService {
    @Value("${project.image}")
    String imagePath;

    @Value("${user.image}")
    String profilePath;

    @Override
    public String uploadImage(MultipartFile mf, String uname) throws IOException {
        // TODO Auto-generated method stub
        File f1 = new File(imagePath);
        if (!f1.exists()) {
            f1.mkdir();
        }
        String f = f1.getAbsolutePath();

        File userF = new File(f1.getAbsolutePath() + File.separator + uname);
        if (!userF.exists())
            userF.mkdir();

        File post = new File(userF.getAbsolutePath() + File.separator + "Post");
        if (!post.exists())
            post.mkdir();

        System.out.println(post.getAbsolutePath());
        Files.copy(mf.getInputStream(),
                Path.of(post +
                        File.separator +
                        mf.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING);
        return mf.getOriginalFilename();
    }

    @Override
    public InputStream serveImage(String filename, String uname) throws FileNotFoundException {
        // TODO Auto-generated method stub
        InputStream isInputStream = new FileInputStream(
                imagePath + File.separator + uname + File.separator + "Post" + File.separator + filename);
        return isInputStream;
    }

    @Override
    public String profileImage(MultipartFile mf, String uname) throws IOException {
        // TODO Auto-generated method stub
        File f1 = new File(profilePath);
        if (!f1.exists()) {
            f1.mkdir();
        }

        String f = f1.getAbsolutePath();

        File userF = new File(f1.getAbsolutePath() + File.separator + uname);
        if (!userF.exists()) {
            userF.mkdir();
        }
        File prof = new File(userF.getAbsolutePath() + File.separator + "Profile");
        if (!prof.exists()) {
            prof.mkdir();
        }
        Files.copy(mf.getInputStream(),
                Path.of(prof +
                        File.separator +
                        mf.getOriginalFilename()),
                StandardCopyOption.REPLACE_EXISTING);
        return mf.getOriginalFilename();
    }

    @Override
    public InputStream serveProfileImage(String filename, String uname) throws FileNotFoundException {
        // TODO Auto-generated method stub
        InputStream isInputStream = new FileInputStream(
                profilePath + File.separator + uname + File.separator + "Profile" + File.separator + filename);
        return isInputStream;
    }

//    @Override
//    public String uploadImage(MultipartFile mf) throws IOException {
//        // TODO Auto-generated method stub
//        File f = new File(imagePath);
//        if (!f.exists()) {
//            f.mkdir();
//        }
//        String fpath = f.getAbsolutePath().toString();
//        File postImg = new File(fpath + "\\" + "Img");
//
//        if (!postImg.exists()) {
//            postImg.mkdir();
//        }
//
//        Files.copy(mf.getInputStream(),
//                Path.of(postImg.getAbsolutePath() +
//                        File.separator +
//                        mf.getOriginalFilename()),
//                StandardCopyOption.REPLACE_EXISTING);
//        return mf.getOriginalFilename();
//    }

}
