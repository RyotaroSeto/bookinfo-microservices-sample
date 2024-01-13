reviews
====

[Bookinfo](https://istio.io/latest/docs/examples/bookinfo/)アプリケーションのに含まれるサービスである、Reviewsの互換アプリケーションです。


動かし方
---
以下にローカルPC上で本アプリケーションを実行する手順を記します。現時点ではローカルPCがmacOS Montereyであることが前提の手順となっています。

### 事前準備
ローカルPCなどの実行環境上に以下のソフトウェアがインストールされている必要があります。

- JDK 17+
- Tomcat 10.0+
- MySQL 8+ または Docker 20.10+

> **Note**<br>
> 各ソフトウェアのバージョン番号は動作確認が取れているものを示しており、これ以外でも動作する可能性はあります。

#### JDK及びTomcatのインストール
[SDKMAN](https://sdkman.io/)を使ってインストールしてください。

- JDK

```console
$ sdk install java 17.0.4-oracle
```

- Tomcat

```console
$ sdk install tomcat 10.0.22
```

#### MySQLのインストール（ローカル環境にMySQLを直接インストールする場合のみ）
homebrewを使ってインストールしてください。

```console
$ brew install mysql
```

### データベースのセットアップ

#### ローカル環境に直接インストールしている場合
MySQLを起動して、rootアカウントのセットアップを行います。

```console
$ mysql.server start

$ mysql_secure_installation
```

MySQLのセキュリティ設定のインタラクションが開始されます。
表示されるメッセージに従って以下のような設定で手順を進めてください（これ以外は任意の設定で問題ありません）。

- VALIDATE PASSWORD componentは利用しない
- rootユーザーのパスワードを `password` とする

次に、本アプリケーションのための初期データを作成します。

```console
$ mysql -uroot -ppassword < mysql/CREATE_REVIEWS_DATA.sql
```

#### Dockerを利用する場合
以下のコマンドを実行すると、パスワード、初期データなど必要な初期化が行われた状態で、DockerコンテナとしてMySQLが起動します。

```
$ docker run -d \
    --name reviews-db \
    -e MYSQL_ROOT_PASSWORD=password \
    -v $(pwd)/hack/mysql:/docker-entrypoint-initdb.d:ro \
    -p 3306:3306 \
    mysql:8.0
```

### アプリケーションの起動
アプリケーションを起動して、動作を確認してみます。
`hack/scripts/deploy.sh` スクリプトを実行すると、Tomcatの停止、アプリケーションのビルド、Tomcatへのデプロイ、Tomcatの再起動が自動的に行われます。

```console
$ hack/scripts/deploy.sh
```

次に実際にアプリケーションにアクセスして、正しく応答が返ることを確認します。

```console
$ curl http://localhost:8080/reviews/0 | jq
{
  "id": 0,
  "reviews": [
    {
      "rating": {
        "color": "red",
        "stars": 5
      },
      "reviewer": "Alice",
      "text": "An extremely entertaining play by Shakespeare. The slapstick humour is refreshing!"
    },
    {
      "rating": {
        "color": "red",
        "stars": 4
      },
      "reviewer": "Bob",
      "text": "Absolutely fun and entertaining. The play lacks thematic depth when compared to other plays by Shakespeare."
    }
  ]
}
```

### アプリケーションの停止
Tomcat、MySQLをそれぞれ停止します。

- Tomcat

```console
$ hack/scripts/shutdown.sh
```

- MySQL（ローカル環境に直接インストールしている場合）

```console
$ mysql.server stop
```

- MySQL（Dockerを利用している場合）

```console
$ docker container stop reviews-db
```

以上。
