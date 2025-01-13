## 프로젝트 설명
배달어플리케이션의 기본적인 기능들을 구현한 프로젝트입니다

## 개발환경


## 사용기술


## ERD

```mermaid
erDiagram
    USERS {
        ID
        EMAIL
        NAME
        PASSWORD
        ADDRESS
        DELETED
        USER_ROLE
    }
    STORE {
        ID
        USER_ID
        NAME
        ADDRESS
        DELETED
        OPEN
        CLOSE
        MIN_AMOUNT
    }
    MENU {
        ID
        STORE_ID
        NAME
        PRICE
    }
    PURCHASES {
        ID
        MENU_ID
        STORE_ID
        STATUS
        TOTAL_PRICE
        USER_ID
    }
    REVIEW {
        ID
        STORE_ID
        ORDER_ID
        USER_ID
        CONTENTS
        RATING
    }
    POPSEARCH {
        ID
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
