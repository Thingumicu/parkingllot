/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.park.newparkinglot.servlet.user;

import com.newparkinglot.ejb.UserBean;
import com.newparkinglot.ejb.InvoiceBean;
import com.park.newparkinglot.common.UserDetails;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author radvo
 */
@ServletSecurity(value = @HttpConstraint(rolesAllowed = {"AdminRole"}))
@WebServlet(name = "Users", urlPatterns = {"/Users"})
public class Users extends HttpServlet {

    @Inject
    private UserBean userBean;
    
    @Inject
    private InvoiceBean invoiceBean;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("activePage", "Users");

        List<UserDetails> users = userBean.getAllUsers();
        request.setAttribute("users", users);
        
        if(!invoiceBean.getUserIds().isEmpty()){
            Collection<String> usernames = userBean.findUsernames(invoiceBean.getUserIds());
            request.setAttribute("invoices", usernames);
        }

        request.getRequestDispatcher("/WEB-INF/pages/user/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] userIdsAsString = request.getParameterValues("user_ids");
        if(userIdsAsString != null){
            Set<Integer> userIds = new HashSet<>();
            for(String userIdAsString : userIdsAsString){
                userIds.add(Integer.parseInt(userIdAsString));
            }
            
            invoiceBean.getUserIds().addAll(userIds);
        }
        response.sendRedirect(request.getContextPath() + "/Users");
    }

    @Override
    public String getServletInfo() {
        return "Users v1.0";
    }

}
