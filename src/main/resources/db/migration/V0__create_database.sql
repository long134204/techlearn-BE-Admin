CREATE DATABASE IF NOT EXISTS techlearn_admin;

CREATE TABLE tbl_user
(
    id            BINARY(16)   NOT NULL,
    created_by    VARCHAR(255) NULL,
    created_date  datetime     NULL,
    modified_date datetime     NULL,
    modified_by   VARCHAR(255) NULL,
    full_name     VARCHAR(255) NULL,
    age           INT          NULL,
    is_deleted    BIT(1)       NULL,
    CONSTRAINT pk_tbl_user PRIMARY KEY (id)
);
CREATE TABLE tbl_teacher
(
    id BINARY(16) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    color VARCHAR(7),
    avatar VARCHAR(255),
    created_by VARCHAR(255),
    created_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_by VARCHAR(255),
    modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    is_deleted    BIT(1)       NULL
);