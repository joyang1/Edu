/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet.user;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;

/**
 *
 * @author 挺
 */
public class UploadPhotoServlet extends HttpServlet {
     private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html,charset=utf-8");
        response.getWriter().println("请以POST方式上传文件");
        System.out.println("get.........");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
//      PrintWriter out = response.getWriter();
        File file1 = null;
        DiskFileUpload disFileUpload = new DiskFileUpload();
        try {
            @SuppressWarnings("unchecked")
            List<FileItem> list = disFileUpload.parseRequest(request);
            for (FileItem fileItem : list) {
                if (fileItem.isFormField()) {

                } else {
                    if ("fileAddPic".equals(fileItem.getFieldName())) {
                        File remoteFile = new File(new String(fileItem.getName().getBytes(), "UTF-8"));
                        //System.out.println("开始遍历.....");
                        //System.out.println(fileItem.getContentType() + "----" + remoteFile.getName() + fileItem.getName());
                        file1 = new File(this.getServletContext().getRealPath("attachment"), remoteFile.getName());
                        file1.getParentFile().mkdirs();
                        file1.createNewFile();
                        InputStream ins = fileItem.getInputStream();
                        OutputStream ous = new FileOutputStream(file1);
                        try {
                            byte[] buffer = new byte[1024];
                            int len = 0;
                            while ((len = ins.read(buffer)) > -1) {
                                ous.write(buffer, 0, len);
                            }
                        } finally {
                            ous.close();
                            ins.close();
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }

}
