-- 회원 테이블
CREATE TABLE member
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '회원 고유 번호 (PK)',
    login_id     VARCHAR(100) NOT NULL UNIQUE COMMENT '로그인용 아이디',
    password     VARCHAR(255) NOT NULL COMMENT '비밀번호 (암호화 저장)',
    name         VARCHAR(100) COMMENT '회원 이름',
    phone_number VARCHAR(20) COMMENT '핸드폰 번호 (숫자 및 구분자 포함)',
    used         VARCHAR(10) DEFAULT 'USED' COMMENT '사용 여부 (USED / NOT_USED)',

    created_by   VARCHAR(100) COMMENT '등록자 (시스템 또는 사용자)',
    created_at   DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    updated_by   VARCHAR(100) COMMENT '수정자 (시스템 또는 사용자)',
    updated_at   DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시'
) COMMENT='회원 마스터 테이블';

-- 역할 테이블
CREATE TABLE role
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '역할 고유 번호 (PK)',
    name       VARCHAR(100) NOT NULL UNIQUE COMMENT '역할 이름 (예: ROLE_ADMIN, ROLE_USER)',
    used       VARCHAR(10) DEFAULT 'USED' COMMENT '사용 여부 (USED / NOT_USED)',

    created_by VARCHAR(100),
    created_at DATETIME    DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_at DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) COMMENT='권한 역할 테이블';

-- 회원 <> 역할 매핑 테이블
CREATE TABLE member_role
(
    member_id BIGINT NOT NULL COMMENT '회원 ID',
    role_id   BIGINT NOT NULL COMMENT '역할 ID',
    PRIMARY KEY (member_id, role_id),

    FOREIGN KEY (member_id) REFERENCES member (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
) COMMENT='회원-역할 매핑 테이블';

-- 화면 테이블
CREATE TABLE screen
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '화면 고유 번호 (PK)',
    code       VARCHAR(100) NOT NULL UNIQUE COMMENT '화면 코드 (예: USER_MANAGEMENT)',
    name       VARCHAR(100) COMMENT '화면 이름 (관리자 화면 등)',
    used       VARCHAR(10) DEFAULT 'USED' COMMENT '사용 여부 (USED / NOT_USED)',

    created_by VARCHAR(100),
    created_at DATETIME    DEFAULT CURRENT_TIMESTAMP,
    updated_by VARCHAR(100),
    updated_at DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) COMMENT='관리 화면 정의 테이블';

-- 역할 <> 화면 <> 권한 설정 테이블
CREATE TABLE screen_permission
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '화면 권한 고유 번호',
    role_id    BIGINT NOT NULL COMMENT '역할 ID',
    screen_id  BIGINT NOT NULL COMMENT '화면 ID',
    can_read   VARCHAR(10) DEFAULT 'NOT_USED' COMMENT '읽기 권한 (USED / NOT_USED)',
    can_write  VARCHAR(10) DEFAULT 'NOT_USED' COMMENT '쓰기 권한 (USED / NOT_USED)',

    created_by VARCHAR(100) COMMENT '등록자',
    created_at DATETIME    DEFAULT CURRENT_TIMESTAMP COMMENT '등록일시',
    updated_by VARCHAR(100) COMMENT '수정자',
    updated_at DATETIME    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일시',

    UNIQUE (role_id, screen_id),
    FOREIGN KEY (role_id) REFERENCES role (id),
    FOREIGN KEY (screen_id) REFERENCES screen (id)
) COMMENT='역할별 화면 접근 권한 테이블';