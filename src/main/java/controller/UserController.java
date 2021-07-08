package controller;

import dao.PostDAO;
import model.Post;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/users")
public class UserController extends HttpServlet {
    PostDAO postDAO = new PostDAO();
    String username= "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "list":
                    findAll(request, response);
                    break;
                case "view":
                    findByID(request,response);
                    break;
                case "viewbyname":
                    findByName(request, response);
                    break;
                case "create":
                    showCreateForm(request,response);
                    break;
                case "edit":
                    showFormEdit(request, response);
                    break;
                default:
                    findAll(request, response);
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    private void findByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String name=request.getParameter("name");
        List<Post> list= postDAO.findByUserName(name);
    RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/list.jsp");
    request.setAttribute("listPost", list);
    requestDispatcher.forward(request, response);
}
    private void findByID(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Post post=postDAO.findById(id);
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("post/view.jsp");
        request.setAttribute("post",post);
        requestDispatcher.forward(request,response);
    }
    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("post/edit.jsp");
        request.setAttribute("username",username);
        requestDispatcher.forward(request,response);
    }
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Post> list = postDAO.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/list.jsp");
        request.setAttribute("listPost", list);
        requestDispatcher.forward(request, response);
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/create.jsp");
        request.setAttribute("username",username);
        requestDispatcher.forward(request,response);
}
}
