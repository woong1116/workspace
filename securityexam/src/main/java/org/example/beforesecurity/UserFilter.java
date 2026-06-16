package org.example.beforesecurity;

import jakarta.servlet.*;

import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            System.out.println("UserFilter: doFilter 실행전:: Thread Name : " + Thread.currentThread().getName());

//        User user = new User("kang");
//        복잡한 로직이 실행되면서 원하는 값을 얻을거예요.
            User user = extractUserFromRequest(request);


            UserContext.setUser(user);

            chain.doFilter(request, response);
            System.out.println("UserFilter: doFilter 실행후:: Thread Name : "+Thread.currentThread().getName());

        }finally {
            UserContext.clear();
        }
    }

    private User extractUserFromRequest(ServletRequest request){
        //복잡한 일들을 하겠지만.. 우리는 간단하게..
        String name = request.getParameter("name");

        return new User(name);
    }
}
