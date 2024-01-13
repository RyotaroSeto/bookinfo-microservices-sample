### Mysql 起動

```bash
$ cd ./src/reviews
$ docker run -d --rm --name reviews-db \
   -e MYSQL_ROOT_PASSWORD=password \
   -v $(pwd)/hack/mysql:/docker-entrypoint-initdb.d:ro \
   -p 3306:3306 \
   mysql:8.0
```

### reviews サービスの build,deploy

```bash
$ ./hack/scripts/deploy.sh
```

### Test

```bash
$ curl http://localhost:8080/reviews?productId=0
```

### reviews サービスと DB の停止

```bash
$ ./hack/scripts/shutdown.sh
```

## 既存アプリケーションの課題

- アプリケーションの可搬性が十分ではない
- スケーラビリティに課題がある
  - 1 つの Java 仮想マシンに複数のアプリケーションを動かすと 1 プロセスが重くなる
  - こうなるとスケールアウトする時簡単にインスタンスを追加できない。
  - ステートフルなものがある場合、インスタンス同士が連携し合って動作している場合があり、スケールアウトが複雑になる

## 課題に対して

- Java ランタイムやアプリケーションサーバーと共に単一のアプリケーションを 1 つのコンテナとしてパッケージングする
- こうすることでどの環境でコンテナを実行しても、同じ Java とアプリケーションサーバーが利用されるため高い可搬性が得られる

## アプリを k8s で運用する考慮点

- 設定情報を環境変数から読み込めるようにする
- ログを標準出力、標準エラー出力にする
  - Tomcat は`System.out.Println()`を使うと Java の標準出力ストリームを使って「./catalina-base/logs/catalina.out」というファイルに書き込まれる
  - k8s で大量のアプリがそれぞれのファイルにログ出力していると、回収や管理が大変手間。ログ分析も難しい
  - Tomcat を使用していて Java の標準出力ストリームからログを出力している場合、アプリケーションをコンテナとしてビルドする一工夫で標準出力、標準エラー出力をログの出力先にすることが可能
- アプリケーションをステートレスに実行する
  - インスタンス毎に異なる状態でないと、インスタンス追加、再起動などできない。
