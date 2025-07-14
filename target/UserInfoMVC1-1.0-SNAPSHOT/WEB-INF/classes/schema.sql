USE sample;

CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    userName VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role INT NOT NULL DEFAULT 0,
    status BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE IF NOT EXISTS steel_info (
    id INT PRIMARY KEY AUTO_INCREMENT,
    steel_type VARCHAR(50) NOT NULL COMMENT '钢材类型',
    steel_origin VARCHAR(100) NOT NULL COMMENT '钢材产地',
    production_date DATE NOT NULL COMMENT '钢材生产日期',
    manufacturer VARCHAR(100) NOT NULL COMMENT '钢材产商',
    price DECIMAL(10,2) NOT NULL COMMENT '钢材价格',
    volume DECIMAL(10,2) NOT NULL COMMENT '钢材体积'
);

-- 插入一些测试数据
INSERT INTO users (userName, password, role, status) VALUES
('admin', 'admin123', 1, true),
('user1', 'user123', 0, true),
('user2', 'user123', 0, false); 