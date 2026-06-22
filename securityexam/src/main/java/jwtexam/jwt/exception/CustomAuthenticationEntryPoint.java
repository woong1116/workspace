package jwtexam.jwt.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        String exception = (String)request.getAttribute("exception");
        //1. request.getAttribute("exception") 이 null 이라면 어떻게 처리할지!!

//        spring security가 전달한 메시지를 로그로 남긴다.
        if(exception==null){
            log.error("Commence Occureed :: "+ authException.getMessage());
        }

//        에러를 처리함에 있어서,  뷰를 요청한 것인지,  데이터를 요청 한것인지에 따라서 다르게 처리 될 필요가 있다.
        if(isRestRequest(request)){
//            rest 요청이 들어왔을 때의 오류처리!!
            handleRestResponse(exception,request,response);

        }else{
            //페이지(뷰) 요청이 들어왔을 때 처리
            handlePageResponse(exception,request,response,authException);
        }
    }

    private void handleRestResponse(String execption, HttpServletRequest request,
                                    HttpServletResponse response) throws IOException, ServletException{
        log.error("Page Request - commence : {}",execption);

        JwtExceptionCode code = JwtExceptionCode.findByCode(execption);
        if(code == JwtExceptionCode.UNKNOWN_ERROR && execption == null){
            //이렇게 처리할 거예요.
            log.error("Rest request - :: 인증예외 발생!! ");
        }

        //나머지 경우는 code값에 따라 응답을 보내줄 꺼예요.
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

//      응답에 에러코드랑 에러메시지도 넣어줄거예요.
        HashMap<String, Object> errorInfo = new HashMap<>();
        errorInfo.put("code",code.getCode());
        errorInfo.put("message",code.getMessage());

        String responseJson = objectMapper.writeValueAsString(errorInfo);
        response.getWriter().print(responseJson);
    }

    private void handlePageResponse(String execption, HttpServletRequest request,
                                    HttpServletResponse response, AuthenticationException authenticationException)
                throws  IOException, ServletException{
// 이 구현부는 여러분이 구현하기 나름일 거예요.

        if(execption == null){
            //이 부분에서 추가적으로 할일이 있다면 여기에 구현해주세요.
            log.error("Page Request - commence : {}",execption);
        }else{
            log.error("Page Request - commence : {}",authenticationException.getMessage());
        }
        response.sendRedirect("/loginform");
    }


//    rest 로 요청한건가요???
    private boolean isRestRequest(HttpServletRequest request){
        String header = request.getHeader("X-Requested-With");
        String uri = request.getRequestURI();
        boolean isApi = uri.startsWith("/api/") || uri.equals("/error");
        return "XMLHttpRequest".equals(header) || isApi;
    }
}
