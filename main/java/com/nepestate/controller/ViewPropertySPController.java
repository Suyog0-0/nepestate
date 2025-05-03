// src/com/nepestate/controller/ViewPropertySPController.java
package com.nepestate.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.nepestate.model.PropertyModel;
import com.nepestate.service.PropertyService;

@WebServlet("/ViewPropertySPController")
public class ViewPropertySPController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private PropertyService propertyService;

    public ViewPropertySPController() {
        super();
        propertyService = new PropertyService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<PropertyModel> properties = propertyService.getAllProperties();
        request.setAttribute("properties", properties);
        request.getRequestDispatcher("/WEB-INF/pages/ViewPropertySP.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}