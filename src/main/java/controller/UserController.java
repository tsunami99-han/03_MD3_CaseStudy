package controller;

import dao.CommentDAO;
import dao.LikeDAO;
import dao.PostDAO;
import dao.UserDAO;
import model.Comment;
import model.Like;
import model.Post;
import model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UserController", urlPatterns = "/users")
public class UserController extends HttpServlet {
    PostDAO postDAO = new PostDAO();
    User user = null;
    UserDAO userDAO = new UserDAO();
    CommentDAO commentDAO = new CommentDAO();
    LikeDAO likeDAO = new LikeDAO();
    String message = "";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

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
                    findByID(request, response);
                    break;
                case "viewbyname":
                    findByName(request, response);
                    break;
                case "create":
                    showCreateForm(request, response);
                    break;
                case "edit":
                    showFormEdit(request, response);
                    break;
                case "login":
                    showFormLogIn(request, response);
                    break;
                case "comment":
                    commentDao(request, response);
                    break;
                case "like":
                    likeDao(request, response);
                    break;
                case "dislike":
                    dislike(request, response);
                    break;
                case "viewuser":
                    informationPage(request, response);
                    break;
                case "search":
                    searchByTitle(request, response);
                    break;
                case "toplike" :
                    topLike(request, response);
                    break;
                case "topcmt" :
                    topComment(request, response);
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


    private void topLike(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Post> list = postDAO.topLike();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/list.jsp");
        request.setAttribute("list", list);
        request.setAttribute("username", user);
        requestDispatcher.forward(request, response);
    }
    private void topComment(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Post> list = postDAO.topCommnent();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/list.jsp");
        request.setAttribute("list", list);
        request.setAttribute("username", user);
        requestDispatcher.forward(request, response);
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
            switch (action) {
                case "login":
                    user = login(request, response);
                    if (user == null) {
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/login.jsp");
                        message = "Wrong username or password!";
                        request.setAttribute("message", message);
                        requestDispatcher.forward(request, response);
                    } else {
                        findAll(request, response);
                    }
                    break;
                case "create":
                    addNewPost(request, response);
                    break;
                case "comment":
                    commentDao(request, response);
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void likeDao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int post_id = Integer.parseInt(request.getParameter("id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        Like like = new Like(post_id, user_id);
        likeDAO.addLike(like);
        postDAO.likeUpdateQuery(post_id);
        findByID(request, response);
    }

    private void dislike(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int post_id = Integer.parseInt(request.getParameter("id"));
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        likeDAO.deleteLike(user_id, post_id);
        postDAO.likeDeleteUpdate(post_id);
        findByID(request, response);
    }

    private void commentDao(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("userid"));
        int post_id = Integer.parseInt(request.getParameter("id"));
        String content = request.getParameter("content");
        LocalDateTime timeNow = LocalDateTime.now();
        String timeString = timeNow.format(formatter);
        LocalDateTime time = LocalDateTime.parse(timeString, formatter);
        Comment comment = new Comment(post_id, user_id, content, time);
        commentDAO.addComment(comment);
        postDAO.commentUpdateQuery(post_id);
        findByID(request, response);
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("");
    }

    private void showFormLogIn(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/login.jsp");
        requestDispatcher.forward(request, response);
    }

    private User login(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<User> list = userDAO.findAll();
        for (User user : list) {
            if (user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private void findByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("name");
        List<Post> list = postDAO.findByUserName(name);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/list.jsp");
        request.setAttribute("listPost", list);
        requestDispatcher.forward(request, response);
    }

    private void findByID(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Post post = postDAO.findById(id);
        List<User> listUser = new ArrayList<>();
        List<Comment> listComment = commentDAO.findByPostID(id);
        List<Like> likeList = likeDAO.findByPostID(id);
        for (Like like : likeList) {
            if (user.getId() == like.getUser_id()) {
                request.setAttribute("like", "dalike");
                break;
            } else {
                request.setAttribute("like", "chualike");
            }
        }
        User users = userDAO.findById(post.getUser_id());
        for (int i = 0; i < listComment.size(); i++) {
            listUser.add(userDAO.findById(listComment.get(i).getUser_id()));
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/view.jsp");
        request.setAttribute("post", post);
        request.setAttribute("listComment", listComment);
        request.setAttribute("listUser", listUser);
        request.setAttribute("user", users);
        request.setAttribute("username", user);
        request.setAttribute("listLike", likeList);
        requestDispatcher.forward(request, response);
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/edit.jsp");
        request.setAttribute("username", user);
        requestDispatcher.forward(request, response);
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        List<Post> list = postDAO.findAll();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/list.jsp");
        request.setAttribute("list", list);
        request.setAttribute("username", user);
        requestDispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/create.jsp");
        request.setAttribute("username", user);
        List<Post> list = postDAO.findAll();
        request.setAttribute("list", list);
        requestDispatcher.forward(request, response);
    }

    private void addNewPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        LocalDateTime timeNow = LocalDateTime.now();
        String timeString = timeNow.format(formatter);
        LocalDateTime time = LocalDateTime.parse(timeString, formatter);
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String status = request.getParameter("status");
        Post post = new Post(user_id, time, title, content, status);
        postDAO.add(post);
        findAll(request, response);
    }

    private void informationPage(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        request.setAttribute("user", user);
        List<Post> postList = postDAO.findByUserID(user.getId());
        request.setAttribute("list", postList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/information.jsp");
        requestDispatcher.forward(request, response);
    }

    private void searchByTitle(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String title = request.getParameter("search");
        List<Post> list = postDAO.findByTitle(title);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("post/list.jsp");
        request.setAttribute("list", list);
        request.setAttribute("username", user);
        requestDispatcher.forward(request, response);
    }
}
