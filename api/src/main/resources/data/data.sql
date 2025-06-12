/*
| login\_id | role        | 화면          | 읽기   | 쓰기        |
| --------- | ----------- | ------------ | ----- | ---------- |
| admin001  | ROLE\_ADMIN | 전체          | USED  | USED      |
| user001   | ROLE\_USER  | 대시보드, 리포트 | USED  | NOT\_USED |
*/

INSERT INTO role (id, name, used, created_by)
VALUES (1, 'ROLE_ADMIN', 'USED', 'system'),
       (2, 'ROLE_USER', 'USED', 'system');

INSERT INTO member (id, login_id, password, name, phone_number, used, created_by)
VALUES (1, 'admin001', '$2a$10$38DtP9K7WTrnFDiDlPCn7eZaDnvZwrscI9IrsVzt4goBorA0498MK', '관리자',
        '010-1111-1111', 'USED', 'system'), -- password 1234
       (2, 'user001', '$2a$10$38DtP9K7WTrnFDiDlPCn7eZaDnvZwrscI9IrsVzt4goBorA0498MK', '일반유저',
        '010-2222-2222', 'USED', 'system');

INSERT INTO member_role (member_id, role_id)
VALUES (1, 1), -- admin001 → ROLE_ADMIN
       (2, 2); -- user001 → ROLE_USER

INSERT INTO screen (id, code, name, used, created_by)
VALUES (1, 'USER_MANAGEMENT', '회원관리 화면', 'USED', 'system'),
       (2, 'DASHBOARD', '대시보드', 'USED', 'system'),
       (3, 'REPORT_VIEW', '리포트 조회 화면', 'USED', 'system');

-- ROLE_ADMIN: 모든 권한 허용
INSERT INTO screen_permission (role_id, screen_id, can_read, can_write, created_by)
VALUES (1, 1, 'USED', 'USED', 'system'),
       (1, 2, 'USED', 'USED', 'system'),
       (1, 3, 'USED', 'USED', 'system');

-- ROLE_USER: 일부 화면만 읽기 가능, 쓰기 불가
INSERT INTO screen_permission (role_id, screen_id, can_read, can_write, created_by)
VALUES (2, 2, 'USED', 'NOT_USED', 'system'), -- 대시보드: 읽기만
       (2, 3, 'USED', 'NOT_USED', 'system'); -- 리포트: 읽기만

