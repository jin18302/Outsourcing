## 프로젝트 설명
배달어플리케이션의 기본적인 기능들을 구현한 프로젝트입니다

## 개발환경


## 사용기술


## ERD

```mermaid
erDiagram
    USERS {
        ID PK
        EMAIL UNIQUE
        NAME
        PASSWORD
        ADDRESS
        DELETED
        USER_ROLE
    }
    STORE {
        ID PK
        USER_ID FK
        NAME
        ADDRESS
        DELETED
        OPEN
        CLOSE
        MIN_AMOUNT
    }
    MENU {
        ID PK
        STORE_ID FK
        NAME
        PRICE
    }
    PURCHASES {
        ID PK
        MENU_ID FK
        STORE_ID FK
        STATUS
        TOTAL_PRICE
        USER_ID FK
    }
    REVIEW {
        ID PK
        STORE_ID FK
        ORDER_ID FK
        USER_ID FK
        CONTENTS
        RATING
    }
    POPSEARCH {
        ID PK
        KEYWORD
        RANKINGCOUNT
        SEARCH_DATETIME
    }

    USERS ||--o{ STORE : "manages"
    STORE ||--o{ MENU : "provides"
    MENU ||--o{ PURCHASES : "contains"
    STORE ||--o{ REVIEW : "has"
    PURCHASES ||--o{ REVIEW : "generates"
    USERS ||--o{ PURCHASES : "places"
    USERS ||--o{ REVIEW : "writes"
