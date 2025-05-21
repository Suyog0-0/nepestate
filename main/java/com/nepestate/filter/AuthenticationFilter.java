package com.nepestate.filter;

import com.nepestate.util.SessionUtil;
import com.nepestate.util.CookieUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(asyncSupported = true, urlPatterns = "/*")
public class AuthenticationFilter implements Filter {

    // Constants for various routes
    private static final String LOGIN_CONTROLLER     = "/LoginController";
    private static final String REGISTER_CONTROLLER     = "/RegisterController";
    private static final String LOGOUT               = "/Logout";
    private static final String HOME                 = "/";
    private static final String HOME_CONTROLLER      = "/HomeController";
    private static final String CONTACTUS 			 = "/ContactUsController"; 
    private static final String ABOUTUS 			 = "/AboutUsController";
    private static final String VIEWPROPERTY         = "/ViewPropertyController";
    private static final String VIEWPROPERTYSP       = "/ViewPropertySPController";
    private static final String DELETEPROPERTY		= "/DeletePropertyController";	
    private static final String SEARCH_CONTROLLER    = "/SearchController";
    private static final String FAVOURITE_CONTROLLER = "/FavouriteController";
    private static final String SUCCESS_CONTROLLER   = "/SuccessController"; // Add Success Controller
    private static final String ERROR_CONTROLLER     = "/ErrorController";   // Add Error Controller

    // Admin URLs
    private static final String ADMIN_DASH           = "/AdminDashboardController";
    private static final String PROPERTY_LIST        = "/PropertyListingController";
    private static final String ADMIN_USER_LIST      = "/AdminUserListingController";
    private static final String REPORT_GEN           = "/ReportGenerationController";

    // User URLs
    private static final String USER_DASH            = "/UserDashboardController";
    private static final String USER_PROFILE         = "/UserProfileController";
    private static final String CONTACT_LIST         = "/ContactListingController";
    private static final String POST_PROPERTY        = "/PostPropertyController";
    private static final String UPDATE_PROPERTY      = "/UpdatePropertyController";
    private static final String REGISTER      = "/RegisterController";
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest  req  = (HttpServletRequest)  request;
        HttpServletResponse res  = (HttpServletResponse) response;
        String path = req.getRequestURI()
                         .substring(req.getContextPath().length());

        // 1) LOGOUT: clear session + any cookie, redirect to homepage (logged-out)
        if (LOGOUT.equals(path)) {
            SessionUtil.invalidateSession(req);
            CookieUtil.deleteCookie(res, "role");
            res.sendRedirect(req.getContextPath() + HOME_CONTROLLER);
            return;
        }

        // 2) STATIC RESOURCES: let .css/.js/.png/.jpg/.jpeg through
        if (path.startsWith("/css/")    || path.startsWith("/js/")    ||
            path.startsWith("/images/") || path.endsWith(".css")       ||
            path.endsWith(".js")        || path.endsWith(".png")       ||
            path.endsWith(".jpg")       || path.endsWith(".jpeg")) {
            chain.doFilter(request, response);
            return;
        }
        String sessionRole = (String) SessionUtil.getAttribute(req, "role");
        String cookieRole = CookieUtil.getCookie(req, "role") != null
                          ? CookieUtil.getCookie(req, "role").getValue()
                          : null;
        // 3) CHECK ROLES in session
        boolean isAdmin = "admin".equals(sessionRole) && "admin".equals(cookieRole);
        boolean isUser = "customer".equals(sessionRole) && "customer".equals(cookieRole);
        
        // 4a) ADMIN flow
        if (isAdmin) {
            // If they hit login, bounce to admin dashboard
            if (path.equals(LOGIN_CONTROLLER)) {
                res.sendRedirect(req.getContextPath() + ADMIN_DASH);
                return;
            }
            // Allow admin‐only URLs + Success/Error controllers
            if (
            	path.equals(ADMIN_DASH)      ||
                path.equals(PROPERTY_LIST)   ||
                path.equals(ADMIN_USER_LIST) ||
                path.equals(REPORT_GEN)      ||
                path.equals(HOME)            ||
                path.equals(CONTACTUS)       ||
                path.equals(ABOUTUS)         ||
                path.equals(VIEWPROPERTY)    ||
                path.equals(VIEWPROPERTYSP)  ||
                path.equals(DELETEPROPERTY)	 ||
                path.equals(SEARCH_CONTROLLER)|| 
                path.equals(POST_PROPERTY)   ||
                path.equals(FAVOURITE_CONTROLLER) ||
                path.equals(SUCCESS_CONTROLLER) ||
                path.equals(ERROR_CONTROLLER) ||
                path.equals(HOME_CONTROLLER)) {
                chain.doFilter(request, response);
            } else {
                // Any other URL → back to admin dashboard
                res.sendRedirect(req.getContextPath() + ADMIN_DASH);
            }
            return;
        }

        // 4b) USER flow
        if (isUser) {
            // If they hit login, bounce to home
            if (path.equals(LOGIN_CONTROLLER)) {
                res.sendRedirect(req.getContextPath() + HOME_CONTROLLER);
                return;
            }
            // Allow user URLs + Success/Error controllers
            if (path.equals(HOME)             ||
                path.equals(HOME_CONTROLLER)  ||
                path.equals(USER_DASH)        ||
                path.equals(USER_PROFILE)     ||
                path.equals(PROPERTY_LIST)    ||
                path.equals(CONTACT_LIST)     ||
                path.equals(REPORT_GEN)       ||
                path.equals(CONTACTUS)        ||
                path.equals(ABOUTUS)          ||
                path.equals(VIEWPROPERTY)     ||
                path.equals(VIEWPROPERTYSP)   ||
                path.equals(DELETEPROPERTY)	  ||
                path.equals(SEARCH_CONTROLLER) || 
                path.equals(POST_PROPERTY)    ||
                path.equals(UPDATE_PROPERTY)  ||
                path.equals(FAVOURITE_CONTROLLER) ||
                path.equals(SUCCESS_CONTROLLER) ||
                path.equals(ERROR_CONTROLLER)) {
                chain.doFilter(request, response);
            } else {
                // Any other URL → back to home
                res.sendRedirect(req.getContextPath() + HOME_CONTROLLER);
            }
            return;
        }

     // 4c) NOT LOGGED IN
     // Allow only public pages: login controller, home, and success/error pages
     if (path.equals(LOGIN_CONTROLLER) ||
    	 path.equals(REGISTER_CONTROLLER) ||
         path.equals(HOME)             ||
         path.equals(HOME_CONTROLLER)  ||
         path.equals(CONTACTUS)        ||
         path.equals(ABOUTUS)          ||
         path.equals(VIEWPROPERTY)     ||
         path.equals(VIEWPROPERTYSP)   ||
         path.equals(SEARCH_CONTROLLER) || 
         path.equals(REGISTER) || 
//         path.equals(POST_PROPERTY)    ||
//         path.equals(FAVOURITE_CONTROLLER) ||
         path.equals(SUCCESS_CONTROLLER) || 
         path.equals(ERROR_CONTROLLER) ||
         path.equals("/Success.jsp")   ||  // Allow direct access to Success.jsp
         path.equals("/Error.jsp")) {      // Allow direct access to Error.jsp
         chain.doFilter(request, response);
     } else {
         res.sendRedirect(req.getContextPath() + HOME_CONTROLLER);
     }
    }

    
    @Override
    public void destroy() {
        // Cleanup logic if needed
    }
}