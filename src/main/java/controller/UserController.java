package controller;

import dao.CommentDAO;
import dao.PostDAO;
import dao.UserDAO;
import model.Comment;
import model.Post;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/users")
public class UserController extends HttpServlet {
    PostDAO postDAO = new PostDAO();
    User user=null;
    UserDAO userDAO=new UserDAO();
    CommentDAO commentDAO=new CommentDAO();
    String message = "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
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
                case "login":
                   showFormLogIn(request,response);
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action){
                case "login":
                   user= login(request,response);
                   if (user==null){
                       RequestDispatcher requestDispatcher=request.getRequestDispatcher("post/login.jsp");
                       message= "Wrong username or password!";
                       request.setAttribute("message",message);
                       requestDispatcher.forward(request,response);
                   }else {
                       findAll(request,response);
                   }
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void showFormLogIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("post/login.jsp");
        requestDispatcher.forward(request,response);
    }
    private User login(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<User> list = userDAO.findAll();
        for (User user : list){
            if (user.getUsername().equalsIgnoreCase(username)&&user.getPassword().equals(password)){
                return user;
            }
        }
        return null;
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
        List<User> listUser= new ArrayList<>();
        List<Comment> listComment=commentDAO.findByPostID(id);
        User user=userDAO.findById(post.getUser_id());
        for (int i=0;i<listComment.size();i++){
            listUser.add(userDAO.findById(listComment.get(i).getUser_id()));
        }
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("post/view.jsp");
        request.setAttribute("post",post);
        request.setAttribute("listComment",listComment);
        request.setAttribute("listUser",listUser);
        request.setAttribute("user",user);
        requestDispatcher.forward(request,response);
    }
    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher=request.getRequestDispatcher("post/edit.jsp");
        request.setAttribute("username",user);
        requestDispatcher.forward(request,response);
    }
    private void findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Post> list = postDAO.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/list.jsp");
        request.setAttribute("list", list);
        request.setAttribute("username",user);
        requestDispatcher.forward(request, response);
    }
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/create.jsp");
        request.setAttribute("username",user);
        requestDispatcher.forward(request,response);
}
}
