## 프로젝트 설명
배달어플리케이션의 기본적인 기능들을 구현한 프로젝트입니다

## 개발환경


## 사용기술


## ERD

```mermaid
erDiagram
    USERS {
        BIGINT ID PK
        CHAR EMAIL UNIQUE
        CHAR NAME
        CHAR PASSWORD
        CHAR ADDRESS
        BOOLEAN DELETED
        CHAR USER_ROLE
    }
    STORE {
        BIGINT ID PK
        BIGINT USER_ID FK
        CHAR NAME
        CHAR ADDRESS
        BOOLEAN DELETED
        LOCALTIME OPEN
        LOCALTIME CLOSE
        INT MIN_AMOUNT
    }
    MENU {
        BIGINT ID PK
        BIGINT STORE_ID FK
        CHAR NAME
        BIGINT PRICE
    }
    PURCHASES {
        BIGINT ID PK
        BIGINT MENU_ID FK
        BIGINT STORE_ID FK
        CHAR STATUS
        BIGINT TOTAL_PRICE
        BIGINT USER_ID FK
    }
    REVIEW {
        BIGINT ID PK
        BIGINT STORE_ID FK
        BIGINT ORDER_ID FK
        BIGINT USER_ID FK
        CHAR CONTENTS
        INT RATING
    }
    POPSEARCH {
        BIGINT ID PK
        CHAR KEYWORD
        INT RANKINGCOUNT
        DATETIME SEARCH_DATETIME
    }

    USERS ||--o{ STORE : "manages"
    STORE ||--o{ MENU : "provides"
    MENU ||--o{ PURCHASES : "contains"
    STORE ||--o{ REVIEW : "has"
    PURCHASES ||--o{ REVIEW : "generates"
    USERS ||--o{ PURCHASES : "places"
    USERS ||--o{ REVIEW : "writes"


## 와이어프레임


## API명세서
