-- Initialize Users
INSERT INTO setting(push, gps) VALUES ('false','false');
INSERT INTO member(email, nickname,setting_id) VALUES ('chyin370@naver.com', '김문성',1);
-- Initialize Categories
INSERT INTO category(name) VALUES ('주방');
INSERT INTO category(name) VALUES ('생활용품');
INSERT INTO category(name) VALUES ('운동기구');
INSERT INTO category(name) VALUES ('주방용품');
