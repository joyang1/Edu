/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.action;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author 小小一
 */
public class news_servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int type = Integer.parseInt(request.getParameter("type"));
        switch (type) {
            case 1:
                response.sendRedirect("admin/news/newsMana.jsp");
                break;
            case 2: {
                response.sendRedirect("admin/news/newsAdd.jsp");
            }
            break;
            case 3: {
                response.sendRedirect("admin/user/userAdd.jsp");
            }
            break;
            case 4: {
                response.sendRedirect("admin/notice/noticesMana.jsp");
            }
            break;
            case 5: {
                response.sendRedirect("admin/notices/noticesAdd.jsp");
            }
            break;
            case 6: {
                response.sendRedirect("admin/actions/actAdd.jsp");
            }
            break;
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
