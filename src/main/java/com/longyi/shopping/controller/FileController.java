package com.longyi.shopping.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.StrUtil;
import com.longyi.shopping.common.GetPath;
import com.longyi.shopping.common.Result;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {
    @Resource
    private GetPath getPath;

    private String getFileName(String fileName){
        int index=fileName.lastIndexOf(".");
        if(index>0){
            return fileName.substring(0,index);
        }
        return fileName;
    }
//    头像下载
    @GetMapping("/avatar/{flag}")
    public void  avatarPath(@PathVariable String flag, HttpServletResponse httpServletResponse) throws IOException {
        String filePath=getPath.getProjectPath()+"/avatar/";
//        System.out.println(filePath);
        if(!FileUtil.isDirectory(filePath)){
            FileUtil.mkdir(filePath);
        }
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(filePath);
        String targetFileName=fileNames.stream().filter(name-> name.contains(flag)).findAny().orElse("");
        try{
            if(StrUtil.isNotEmpty(targetFileName)){
                httpServletResponse.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(targetFileName, "UTF-8"));
                httpServletResponse.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + targetFileName);
                os=httpServletResponse.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
                System.out.println();
            }
        }catch (IORuntimeException e  ){
            System.out.println(e.getMessage());
        }
    }
//    头像上传
    @PostMapping("/avatar")
    public Result avatarUpload(@RequestParam("file")MultipartFile file){

        if(file.isEmpty()){
            return Result.fail("上传不能为空");
        }
        String flag = getFileName(file.getOriginalFilename());
        try{
            if(!FileUtil.isDirectory(getPath.getProjectPath()+"/avatar/")){
                FileUtil.mkdir(getPath.getProjectPath()+"/avatar/");
            }
            FileUtil.writeBytes(file.getBytes(),getPath.getProjectPath()+"/avatar/"+file.getOriginalFilename());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Result.suc("http://localhost:8888/file/avatar/"+flag);
    }
    @GetMapping("/image/{flag}")
    public void  imagePath(@PathVariable String flag, HttpServletResponse httpServletResponse) throws IOException {
        String filePath=getPath.getProjectPath()+"/image/";
//        System.out.println(filePath);
        if(!FileUtil.isDirectory(filePath)){
            FileUtil.mkdir(filePath);
        }
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(filePath);
        String targetFileName=fileNames.stream().filter(name-> name.contains(flag)).findAny().orElse("");
        try{
            if(StrUtil.isNotEmpty(targetFileName)){
                httpServletResponse.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(targetFileName, "UTF-8"));
                httpServletResponse.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + targetFileName);
                os=httpServletResponse.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
                System.out.println();
            }
        }catch (IORuntimeException e  ){
            System.out.println(e.getMessage());
        }
    }
    //    头像上传
    @PostMapping("/image")
    public Result imageUpload(@RequestParam("file")MultipartFile file){

        if(file.isEmpty()){
            return Result.fail("上传不能为空");
        }
        String flag = getFileName(file.getOriginalFilename());
        try{
            if(!FileUtil.isDirectory(getPath.getProjectPath()+"/image/")){
                FileUtil.mkdir(getPath.getProjectPath()+"/image/");
            }
            FileUtil.writeBytes(file.getBytes(),getPath.getProjectPath()+"/image/"+file.getOriginalFilename());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return Result.suc("http://localhost:8888/file/image/"+flag);
    }
    @GetMapping("/{flag}")
    public void  filePath(@PathVariable String flag, HttpServletResponse httpServletResponse) throws IOException {
        String filePath=getPath.getProjectPath()+"/file/";
//        System.out.println(filePath);
        if(!FileUtil.isDirectory(filePath)){
            FileUtil.mkdir(filePath);
        }
        OutputStream os;
        List<String> fileNames = FileUtil.listFileNames(filePath);
        String targetFileName=fileNames.stream().filter(name-> name.contains(flag)).findAny().orElse("");
        try{
            if(StrUtil.isNotEmpty(targetFileName)){
                httpServletResponse.addHeader("Content-Disposition","attachment;filename=" + URLEncoder.encode(targetFileName, "UTF-8"));
                httpServletResponse.setContentType("application/octet-stream");
                byte[] bytes = FileUtil.readBytes(filePath + targetFileName);
                os=httpServletResponse.getOutputStream();
                os.write(bytes);
                os.flush();
                os.close();
                System.out.println();
            }
        }catch (IORuntimeException e  ){
            System.out.println(e.getMessage());
        }
    }
}
