# GuaBao2
好家割包總店 點餐系統
# 好家割包總店 - 點餐系統

本專案為一個基於 Java Swing (WindowBuilder) 開發的餐飲點餐系統，核心架構嚴格遵循 **MVC (Model-View-Controller) 與 DAO (Data Access Object) 模式**進行三層式軟體工程設計。透過 Maven 進行依賴管理，並完美結合 MySQL 8.0 資料庫實作完整的會員 CRUD 操作與具備 ACID 特性的交易（Transaction）結帳機制。

## 🛠️ 開發環境

* **作業系統環境**：跨平台 (Windows / Mac / Linux)
* **開發工具**：Eclipse IDE (搭配 WindowBuilder 外掛)
* **建構工具**：Maven Project
* **程式語言**：Java SE 11 (JDK 11)
* **資料庫**：MySQL 8.0+
* **JDBC 驅動**：`mysql-connector-j` (版本 8.0.33)

---

## 📂 專案目錄與分層結構

專案嚴格依據軟體架構最佳實踐進行套件（Package）分類，目錄結構如下：

```text
GuaBao2/
 ├── pom.xml                               # Maven 專案設定檔
 └── src/main/java/
       ├── util/                           # 基礎工具層（資料庫連線管理）
       │     └── DbConnection.java
       ├── exception/                      # 異常處理層（自訂業務邏輯異常）
       │     └── GuaBaoException.java
       ├── model/                          # 資料實體層（POJO 實體模型）
       │     ├── Member.java
       │     ├── Porder.java
       │     └── PorderDetail.java
       ├── dao/                            # 資料訪問介面層 (Data Access Object)
       │     ├── MemberDao.java
       │     └── PorderDao.java
       ├── dao.impl/                       # 資料訪問實作層 (MySQL 原生 SQL)
       │     ├── MemberDaoImpl.java
       │     └── PorderDaoImpl.java
       ├── service/                        # 業務邏輯介面層 (Business Logic)
       │     └── MemberService.java
       ├── service.impl/                   # 業務邏輯實作層 (交易控制、點數計算)
       │     └── MemberServiceImpl.java
       └── controller/                     # 控制器與視窗展示層 (Swing GUI / Event)
             └── GuaBaoOrderSystem.java
