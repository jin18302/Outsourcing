## 프로젝트 설명
배달어플리케이션의 기본적인 기능들을 구현한 프로젝트입니다

## 개발환경


## 사용기술


## ERD

```mermaid
erDiagram
    USERS {
        INT ID PK
        STRING EMAIL UNIQUE
        STRING NAME
        STRING PASSWORD
        STRING ADDRESS
        BOOLEAN DELETED
        STRING USER_ROLE
    }
    STORE {
        INT ID PK
        INT USER_ID FK
        STRING NAME
        STRING ADDRESS
        BOOLEAN DELETED
        STRING OPEN
        STRING CLOSE
        INT MIN_AMOUNT
    }
    MENU {
        INT ID PK
        INT STORE_ID FK
        STRING NAME
        INT PRICE
    }
    PURCHASES {
        INT ID PK
        INT MENU_ID FK
        INT STORE_ID FK
        STRING STATUS
        INT TOTAL_PRICE
        INT USER_ID FK
    }
    REVIEW {
        INT ID PK
        INT STORE_ID FK
        INT ORDER_ID FK
        INT USER_ID FK
        STRING CONTENTS
        INT RATING
    }
    POPSEARCH {
        INT ID PK
        STRING KEYWORD
        INT RANKINGCOUNT
        STRING SEARCH_DATETIME
    }

    USERS ||--o{ STORE : "manages"
    STORE ||--o{ MENU : "provides"
    MENU ||--o{ PURCHASES : "contains"
    STORE ||--o{ REVIEW : "has"
    PURCHASES ||--o{ REVIEW : "generates"
    USERS ||--o{ PURCHASES : "places"
    USERS ||--o{ REVIEW : "writes"


