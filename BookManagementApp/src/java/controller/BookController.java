package controller;

import Entity.BookDetailEntity;
import Entity.BookEntity;
import Entity.CategoryEntity;
import data.BookDAO;
import data.BookDetailDAO;
import data.CategoryDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BookController", urlPatterns = {"/BookController"})
public class BookController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String url = "/bookList.jsp";
            List<BookEntity> bookList = new ArrayList<>();
            String action = request.getParameter("action");
            if (action == null) {
                action = "list";
            }
            switch (action) {
                case "list":
                    bookList = BookDAO.getBookList();
                    break;
                case "delete":
                    int id = Integer.parseInt(request.getParameter("id"));
                    BookDetailEntity bookDetail = BookDetailDAO.deleteBookDetailByID(id);
                    bookList = BookDAO.deleteBookById(id);
                case "addnew":
                    List<CategoryEntity> categoryList = CategoryDAO.getCategoryID();
                    request.setAttribute("categoryList", categoryList);
                    url = "/addList.jsp";
                    break;
                case "save":
                    String name = request.getParameter("bookName");
                    String author = request.getParameter("author");
                    String isbn = request.getParameter("isbn");
                    int categoryID = Integer.parseInt(request.getParameter("categoryID"));
                    int number = Integer.parseInt(request.getParameter("number"));
                    double price = Double.parseDouble(request.getParameter("price"));
                    BookEntity book = new BookEntity();
                    book.setName(name);
                    book.setAuthor(author);
                    CategoryEntity category = new CategoryEntity();
                    book.setCategory(category);
                    book.getCategory().setId(categoryID);
                    BookDetailEntity bookdetail = new BookDetailEntity(isbn, number, price);
                    book.setBookDetail(bookdetail);

                    boolean t = BookDAO.AddNewBook(book);
                    if (t) {
                        bookList = BookDAO.getBookList();
                        url = "/bookList.jsp";
                    } else {
                        request.setAttribute("error", "insert fail");
                        url = "/addList.jsp";
                    }
                    break;
                case "search":
                    String search = request.getParameter("search");
                    bookList = BookDAO.SearchBook(search);
                    break;
                case "edit":
                    id =Integer.parseInt(request.getParameter("id"));
                    book = new BookEntity();
                    book.setId(id);
                    request.setAttribute("book", book);
                    categoryList = CategoryDAO.getCategoryID();
                    request.setAttribute("categoryList", categoryList);
                    url = "/Edit.jsp";
                    break;
                case "EDIT":
                   id = Integer.parseInt(request.getParameter("id"));
                    name = request.getParameter("name");
                    author = request.getParameter("author");
                    isbn = request.getParameter("isbn");
                    categoryID = Integer.parseInt(request.getParameter("categoryID"));
                    number = Integer.parseInt(request.getParameter("number"));
                    price = Double.parseDouble(request.getParameter("price"));
                    book = new BookEntity();
                    book.setId(id);
                    book.setName(name);
                    book.setAuthor(author);
                    category = new CategoryEntity();
                    book.setCategory(category);
                    book.getCategory().setId(categoryID);             
                    bookdetail = new BookDetailEntity(id,isbn, number, price);
                    t = BookDetailDAO.EditBookDetailEntity(bookdetail);
                    bookList =BookDAO.EditBook(book);
                    break;
            }
            request.setAttribute("bookList", bookList);
            getServletContext().getRequestDispatcher(url).forward(request,response);
        }
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
