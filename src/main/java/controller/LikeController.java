package controller;

import dao.LikeDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LikeController", value = "/like")
public class LikeController extends HttpServlet {
    LikeDAO likeDAO=new LikeDAO();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        if(action==null){
            action="";
        }
        switch (action){
            case "add":
                break;
            case "delete":
                break;
            case "show":
                showLike(request,response);
                break;

        }
    }

    private void showLike(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        int content=Integer.parseInt(request.getParameter("content"));
        try {
            likeDAO.addLike(id,content);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
