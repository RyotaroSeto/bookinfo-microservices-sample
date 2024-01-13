FROM eclipse-temurin:17-jdk-jammy AS builder

## 作業ディレクトリの設定…（ 2 ）
WORKDIR /mvn

## Maven による依存ライブラリの解決…（ 3 ）
COPY ./pom.xml ./pom.xml
COPY ./mvnw ./mvnw
COPY ./.mvn ./.mvn
RUN ./mvnw package -DskipTests

## Maven によるアプリケーションのビルド…（ 4 ）
COPY ./src ./src
RUN ./mvnw package

## アプリの実行用のベースイメージ…（ 5 ）
FROM tomcat:10.1.13-jre17-temurin-jammy

## コンテナの実行ユーザーの作成と、Tomcat を動作させるための権限の追加…（ 6 ）
RUN groupadd -r reviews \
 && useradd -r -g reviews reviews \
 && chown reviews:reviews -R /usr/local/tomcat/*

## コンテナの実行ユーザーの変更…（ 7 ）
USER reviews:reviews

## ビルド用コンテナからビルド成果物をコピー…（ 8 ）
COPY --from=builder /mvn/target/reviews.war /usr/local/tomcat/webapps/

## 公開ポートの指定…（ 9 ）
EXPOSE 8080
