# opcua-demo

OPC UA 連携を想定した Spring Boot デモプロジェクト（ポートフォリオ用）。

## 技術スタック

- Java 21
- Spring Boot 4.1
- Spring Data JPA
- MySQL

## セットアップ

### 1. データベース

MySQL でデータベースを作成します。

```sql
CREATE DATABASE opcua_demo CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### 2. 接続設定

`src/main/resources/application-local.properties.example` をコピーし、`application-local.properties` を作成してパスワードを設定します。

```bash
cp src/main/resources/application-local.properties.example src/main/resources/application-local.properties
```

または環境変数 `SPRING_DATASOURCE_PASSWORD` を設定してください。

### 3. 起動

```bash
./mvnw spring-boot:run
```

アプリケーションは `http://localhost:8080` で起動します。

## ライセンス

MIT（個人ポートフォリオ用途）
