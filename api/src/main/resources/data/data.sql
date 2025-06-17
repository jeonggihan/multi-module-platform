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

-- USD 기준
INSERT INTO exchange_rate (base_currency, target_currency, exchange_rate, exchange_date, used, created_by)
VALUES
    ('USD', 'KRW', 1378.25, '2025-06-17', 'USED', 'system'),
    ('USD', 'JPY', 154.90, '2025-06-17', 'USED', 'system'),
    ('USD', 'EUR', 0.9312, '2025-06-17', 'USED', 'system'),
    ('USD', 'GBP', 0.7895, '2025-06-17', 'USED', 'system'),
    ('USD', 'CNY', 7.2301, '2025-06-17', 'USED', 'system'),
    ('USD', 'AUD', 1.4998, '2025-06-17', 'USED', 'system'),
    ('USD', 'CAD', 1.3625, '2025-06-17', 'USED', 'system'),
    ('USD', 'SGD', 1.3432, '2025-06-17', 'USED', 'system');

-- KRW 기준 (역산 환율)
INSERT INTO exchange_rate (base_currency, target_currency, exchange_rate, exchange_date, used, created_by)
VALUES
    ('KRW', 'USD', 0.0007254, '2025-06-17', 'USED', 'system'),
    ('KRW', 'JPY', 0.1124, '2025-06-17', 'USED', 'system'),
    ('KRW', 'EUR', 0.0006755, '2025-06-17', 'USED', 'system'),
    ('KRW', 'GBP', 0.0005727, '2025-06-17', 'USED', 'system'),
    ('KRW', 'CNY', 0.00525, '2025-06-17', 'USED', 'system'),
    ('KRW', 'AUD', 0.0007269, '2025-06-17', 'USED', 'system'),
    ('KRW', 'CAD', 0.0007281, '2025-06-17', 'USED', 'system'),
    ('KRW', 'SGD', 0.0007276, '2025-06-17', 'USED', 'system');

