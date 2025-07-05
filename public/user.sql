-- 用户表
CREATE TABLE sys_user
(
    id          bigint PRIMARY KEY,
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted     smallint           NOT NULL DEFAULT 0,
    username    VARCHAR(50) UNIQUE NOT NULL,
    password    VARCHAR(255)       NOT NULL,
    status      smallint           NOT NULL default 1,

    full_name   VARCHAR(100),
    phone       VARCHAR(20),
    email       VARCHAR(100) UNIQUE,
    avatar  bigint
);
-- 索引
CREATE INDEX idx_user_username ON sys_user (username);
CREATE INDEX idx_user_phone ON sys_user (phone);

-- 角色表
CREATE TABLE role
(
    id          bigint PRIMARY KEY,
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted     smallint           NOT NULL DEFAULT 0,
    role_name   VARCHAR(50) UNIQUE NOT NULL,
    description TEXT
);

-- 索引
CREATE INDEX idx_role_name ON role (role_name);

-- 权限
CREATE TABLE permission
(
    id              bigint PRIMARY KEY,
    create_time     TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time     TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted         smallint            NOT NULL DEFAULT 0,
    permission_name VARCHAR(100) UNIQUE NOT NULL,
    description     TEXT,
    resource_type   smallint         NOT NULL
);


--  用户-角色关联表 (user_role)
CREATE TABLE user_role
(
    id          bigint PRIMARY KEY,
    create_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted     smallint NOT NULL DEFAULT 0,
    user_id     bigint   NOT NULL,
    role_id     bigint   NOT NULL,
    assigned_by bigint
);

-- 索引
CREATE INDEX idx_user_role_user ON user_role (user_id);
CREATE INDEX idx_user_role_role ON user_role (role_id);
CREATE UNIQUE INDEX idx_user_role_ids ON user_role (user_id, role_id);


--  角色-权限关联表 (role_permission)
CREATE TABLE role_permission
(
    id            bigint PRIMARY KEY,
    create_time   TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    update_time   TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
    deleted       smallint NOT NULL DEFAULT 0,
    role_id       bigint   NOT NULL,
    permission_id bigint   NOT NULL,
    assigned_by   bigint
);

-- 索引
CREATE INDEX idx_role_permission_role ON role_permission (role_id);
CREATE INDEX idx_role_permission_permission ON role_permission (permission_id);
CREATE UNIQUE INDEX idx_role_permission_permission_ids ON role_permission (role_id, permission_id);
