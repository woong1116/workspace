-- 1. 데이터베이스 생성 (없을 때만)
--    한글 저장을 위해 utf8mb4 사용
CREATE DATABASE IF NOT EXISTS liondb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

-- 2. 사용자 생성 (없을 때만)
--    MySQL 8 기본 플러그인(caching_sha2_password) 대신
--    호환성이 높은 mysql_native_password 사용
CREATE USER IF NOT EXISTS 'lion'@'%'
    IDENTIFIED WITH mysql_native_password BY 'lion1234';

-- 3. 사용자가 이미 있으면 비밀번호와 인증 플러그인을 다시 맞춤
ALTER USER 'lion'@'%'
    IDENTIFIED WITH mysql_native_password BY 'lion1234';

-- 4. liondb 데이터베이스에 대한 모든 권한 부여
GRANT ALL PRIVILEGES ON liondb.* TO 'lion'@'%';

-- 5. 권한 변경 사항 적용
FLUSH PRIVILEGES;