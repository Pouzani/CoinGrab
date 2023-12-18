package test.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import test.CoinAPI;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import test.model.*;


import test.dao.*;


/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/controller/*")
@MultipartConfig
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String USER = "user";

	private String test;

	private UserDAO userDao;
	private AdminDAO adminDao;
	private ClientDAO clientDao;
	private VoteDAO voteDAO;
	private VotePercentageDAO votePercentageDAO;
	private ArticleDAO articleDAO;
	private VerificationDAO verificationDAO;
	
	public void init() throws ServletException{
	    DAOFactory daoFactory = DAOFactory.getInstance();
	    	
		this.userDao = daoFactory.getUserDao();
		this.adminDao = daoFactory.getAdminDao();
		this.clientDao = daoFactory.getClientDao();
		this.voteDAO = daoFactory.getVoteDao();
		this.votePercentageDAO = daoFactory.getVotePercentageDAO();
		this.articleDAO = daoFactory.getArticleDao();
		this.verificationDAO = daoFactory.getVerificationDao();
	}
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getRequestURI();
		action = action.substring(action.lastIndexOf("/"));
		
		switch(action) {
		case "/home":
			home(request,response);
			break;
		case "/details":
			details(request,response);
			break;
		case "/vote":
			vote(request,response);
			break;
		case "/forum":
			forum(request,response);
			break;
		case "/login":
			login(request,response);
			break;
		case "/loginView":
			loginView(request,response);
			break;
		case "/loginerror":
			loginError(request,response);
			break;
		case "/admin":
			admin(request,response);
			break;
		case "/adminedit":
			adminEditView(request,response);
			break;
		case "/edit":
			editUser(request,response);
			break;
		case "/delete":
			deleteUser(request,response);
			break;
		case "/search":
			searchUser(request, response);
			break;
		case "/logout":
			logout(request, response);
			break;
		case "/createArticle":
			createArticle(request, response);
			break;
		case "/signupView":
			signupView(request, response);
			break;
		case "/signup":
			signup(request, response);
			break;
		case "/profile":
			profile(request, response);
			break;
		case "/updateUser":
			updateUser(request, response);
			break;
		case "/viewImage":
			viewImage(request, response);
			break;
		case "/updateUserImage":
			updateUserImage(request, response);
			break;
		case "/test":
			test(request, response);
			break;
		case "/aboutus":
			aboutus(request, response);
			break;
		}
	}
	
	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] coinNames = {"bitcoin","ethereum","tether","binancecoin","ripple","binance-usd","cardano"};
	    CoinAPI coinList = null;
	    User user = null;
		try {
			coinList = new CoinAPI(coinNames);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		user=(User) session.getAttribute(USER);
		request.setAttribute("user", user);
	    request.setAttribute("coinList", coinList.getCoinList());
	    request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
	
	protected void details(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
		User user = null;
		HttpSession session = request.getSession();
		user=(User) session.getAttribute(USER);
		request.setAttribute("user", user);
		String coinId = request.getParameter("coinId");
	    String[] coinNames={coinId};
	    CoinAPI coinList = null;
	    int voteCountUp = voteDAO.getVoteCount("up", coinId);
	    int voteCountDown = voteDAO.getVoteCount("down", coinId);
	    String voteAvgUp= voteDAO.getVoteAvg("up", coinId);
	    String voteAvgDown= voteDAO.getVoteAvg("down", coinId);
	    String votePercentageUp = votePercentageDAO.getVotePercentageUp(coinId); 
	    String votePercentageDown = votePercentageDAO.getVotePercentageDown(coinId);
		try {
			coinList = new CoinAPI(coinNames);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    String color = "#00C537";
	    if (Float.parseFloat(coinList.getCoinList().get(0).getMarket_data().getPrice_change_percentage_24h()) < 0 )
	    {
	    	color = "red";
	    }
	    else{
	    	color ="#00C537";
	    }
	    
	    request.setAttribute("coinList", coinList);
	    request.setAttribute("color", color);
	    request.setAttribute("imageURL", coinList.getCoinList().get(0).getImage().getSmall());
	    request.setAttribute("coinId", coinId);
	    request.setAttribute("voteCountUp",voteCountUp);
	    request.setAttribute("voteCountDown",voteCountDown);
	    request.setAttribute("voteAvgUp",voteAvgUp);
	    request.setAttribute("voteAvgDown",voteAvgDown);
	    request.setAttribute("votePercentageUp",votePercentageUp);
	    request.setAttribute("votePercentageDown",votePercentageDown);
	    
	    request.getRequestDispatcher("/details.jsp").forward(request, response);
	    
	}
	
	protected void vote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String coinId = request.getParameter("coinId");
		String percentage;
		String voteType;
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role != null) {
			if(request.getParameter("percentageUp") != null) {
				percentage = request.getParameter("percentageUp");
				voteType = "up";
		}
			else {
				percentage = request.getParameter("percentageDown");
				voteType = "down";
		}
		voteDAO.addVote(coinId, voteType, Double.parseDouble(percentage));
		response.sendRedirect("details?coinId="+coinId);
		}
		else {
			response.sendRedirect("loginView");
		}
	}
	
	protected void forum(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		User user = null;
		List<Article> articles = articleDAO.getAllArticles();
		HttpSession session = request.getSession();
		user = (User) session.getAttribute(USER);
		request.setAttribute(USER, user);
		request.setAttribute("articles", articles);
		request.getRequestDispatcher("/forum.jsp").forward(request, response);
	}
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String email = request.getParameter("email");
        String password = request.getParameter("password");

        if(userDao.validate(email, password)) {

			User user = userDao.getUserByEmail(email);
			String role=user.getRole();

			if( role.equals("Admin")) {

                Admin admin = adminDao.getAdminByIdUser(user.getIdUser());

                HttpSession session = request.getSession();
                session.setAttribute(USER, user);
                session.setAttribute("admin", admin);
                session.setAttribute("role", role);
				response.sendRedirect("admin");
			}
			
			else if(role.equals("Client")){
				
                Client client = clientDao.getClientByIdUser(user.getIdUser());

                HttpSession session = request.getSession();
                session.setAttribute(USER, user);
                session.setAttribute("client", client);
                session.setAttribute("role", role);
                response.sendRedirect("home");
				
			}
			else {
				HttpSession session = request.getSession();
				session.setAttribute(USER, user);
				session.setAttribute("role", role);
				response.sendRedirect("home");
			}
			
		}
		else {
			response.sendRedirect("loginerror");
		}
	}
	
	protected void loginView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/login.jsp").forward(request, response);
	}
	
	protected void loginError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/loginerror.jsp").forward(request, response);
	}
	
	protected void admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> allUsers = userDao.getAllUsers();
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if(role != null) {
			if(role.equals("Admin")) {
				request.setAttribute("Users", allUsers);
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
		}
			else {
				request.getRequestDispatcher("/forbidden.jsp").forward(request, response);
		}
		}
		else {
			request.getRequestDispatcher("/forbidden.jsp").forward(request, response);
		}
	}
	
	protected void adminEditView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idUser"));
		User user = userDao.getUserById(id);
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role !=null) {
			if(role.equals("Admin")) {
				request.setAttribute(USER, user);
				request.getRequestDispatcher("/adminedit.jsp").forward(request, response);
		}
			else {
				request.getRequestDispatcher("/forbidden.jsp").forward(request, response);
		}
		}
		else {
			request.getRequestDispatcher("/forbidden.jsp").forward(request, response);
		}
	}
	
	protected void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		User user = new User();
		String userName,email,phone,roleUser;
		int idUser;
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role !=null) {
			if(role.equals("Admin")) {
				idUser=Integer.parseInt(request.getParameter("idUser"));
				userName=request.getParameter("userName");
				email=request.getParameter("email");
				phone=request.getParameter("phone");
				roleUser = request.getParameter("role");
				user.setIdUser(idUser);
				user.setUserName(userName);
				user.setEmail(email);
				user.setPhone(phone);
				user.setRole(roleUser);
				userDao.update(user);
				response.sendRedirect("admin");
		}
			else {
				request.getRequestDispatcher("/forbidden.jsp").forward(request, response);
		}
		}
		else {
			request.getRequestDispatcher("/forbidden.jsp").forward(request, response);
		}
	}
	
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idUser;
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if (role != null) {
			if(role.equals("Admin")) {
				idUser=Integer.parseInt(request.getParameter("idUser"));
				userDao.delete(idUser);
				response.sendRedirect("admin");
			}
			else {
				request.getRequestDispatcher("/forbidden.jsp").forward(request, response);
			}
		}
		else {
			request.getRequestDispatcher("/forbidden.jsp").forward(request, response);
		}
	}
	
	protected void searchUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name = request.getParameter("search");
		List<User> allUsers = userDao.searchUserByName(name);
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		if(role != null) {
			if(role.equals("Admin")) {
				request.setAttribute("search", name);
				request.setAttribute("Users", allUsers);
				request.getRequestDispatcher("/admin.jsp").forward(request, response);
		}
			else {
				request.getRequestDispatcher("/forbidden.jsp").forward(request, response);
		}
		}
		else {
			request.getRequestDispatcher("/forbidden.jsp").forward(request, response);
		}
	}
	
	protected void logout (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("home");
	}
	
	protected void createArticle (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		String role = (String) session.getAttribute("role");
		Article article = new Article();
		if(role != null) {
			User user = (User) session.getAttribute(USER);
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String firstName = user.getFirstName();
			article.setTitle(title);
			article.setContent(content);
			article.setUser(firstName);
			articleDAO.addArticle(article);;
			response.sendRedirect("forum");
		}
		else {
			response.sendRedirect("loginView");
		}
	}
	
	protected void signupView (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/signup.jsp").forward(request, response);
	}
	
	protected void signup (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("fName");
		String lastName = request.getParameter("lName");
		int Age = Integer.parseInt(request.getParameter("age"));
		String nationality = request.getParameter("nation");
		String phone = request.getParameter("phone");
		user.setUserName(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(Age);
		user.setNationality(nationality);
		user.setPhone(phone);
		user.setRole("Client");
		if (userDao.emailExist(email)) {
			request.setAttribute(USER, user);
			request.getRequestDispatcher("/signupemailerror.jsp").forward(request, response);
			
		}
		else if (userDao.userNameExist(username)) {
			request.setAttribute(USER, user);
			request.getRequestDispatcher("/signupusernameerror.jsp").forward(request, response);
		}
		else {
			userDao.create(user);
			HttpSession session = request.getSession();
            session.setAttribute(USER, user);
            response.sendRedirect("home");
		}
	}
	
	protected void profile (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		User user = new User();
		HttpSession session = request.getSession();
		user = (User) session.getAttribute(USER);
		request.setAttribute(USER, user);
		request.getRequestDispatcher("/profile.jsp").forward(request, response);
	}
	
	protected void updateUser (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		User userTemp = new User();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String firstName = request.getParameter("fName");
		String lastName = request.getParameter("lName");
		int Age = Integer.parseInt(request.getParameter("age"));
		String nationality = request.getParameter("nation");
		String phone = request.getParameter("phone");
		HttpSession session = request.getSession();
		userTemp = (User) session.getAttribute(USER);
		user.setIdUser(userTemp.getIdUser());
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setAge(Age);
		user.setNationality(nationality);
		user.setPhone(phone);
		if (userDao.emailExist(email) && email.equals(userTemp.getEmail()) == false) {
			request.setAttribute(USER, user);
			request.getRequestDispatcher("/profileerror.jsp").forward(request, response);
		}
		else {
			userDao.update(user);
			session.setAttribute(USER, user);
			response.sendRedirect("profile");
		}
	}
	
	protected void updateUserImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		User userTemp = new User();
		Part imagePart = request.getPart("image");
		InputStream imageStream = imagePart.getInputStream();
		byte[] imageData = inputStreamToByteArray(imageStream);
		HttpSession session = request.getSession();
		userTemp = (User) session.getAttribute(USER);
		user.setIdUser(userTemp.getIdUser());
		user.setImageData(imageData);
		response.sendRedirect("profile");
	}
	
	protected void viewImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(USER);
		if (user.getImageData() != null) {
		response.setContentType("image/jpeg");
        response.getOutputStream().write(user.getImageData());
        }
		else {
			
		}
	}
	
	protected void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/imageTest.jsp").forward(request, response);
	}
	
	private byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();
        return buffer.toByteArray();
    }
	
	protected void aboutus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getRequestDispatcher("/aboutus.jsp").forward(request, response);
	}
}



