# Nginx 경량 이미지 사용
FROM nginx:alpine

# 작성한 HTML, CSS, JS 파일을 Nginx의 웹 루트로 복사
COPY index.html /usr/share/nginx/html/
COPY style.css /usr/share/nginx/html/
COPY script.js /usr/share/nginx/html/

# Nginx 포트
EXPOSE 80

# 컨테이너 실행 시 Nginx 실행
CMD ["nginx", "-g", "daemon off;"]