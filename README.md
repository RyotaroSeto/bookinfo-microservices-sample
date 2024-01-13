# microservices-sample2

## Kind SetUp

```bash
$ kind create cluster --config infra/multi-node.yaml
$ kind delete cluster
$ docker ps -a
$ docker stop <ID>
```

## SetUp

```bash
$ kubectl create ns bookinfo
$ kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml
$ kubectl apply -f infra/productpage/bookinfo-ingress.yaml
$ kubectl apply -f infra/productpage/bookinfo-productpage.yaml
$ kubectl apply -f infra/details
$ kubectl -n bookinfo create secret generic reviews-db-credentials --from-literal rootpasswd=password
$ kubectl apply -f ./infra/reviews/bookinfo-reviews-db-init.yaml
$ kubectl apply -f ./infra/reviews/pv.yaml
$ kubectl apply -f ./infra/reviews/pvc.yaml
$ kubectl apply -f ./infra/reviews/bookinfo-reviews-db.yaml
$ kubectl apply -f ./infra/reviews/bookinfo-reviews.yaml
$ kubectl apply -f infra/ratings
```

## Check Database

```bash
$ kubectl -n bookinfo run -i -t --rm mysql-client --image=mysql -- mysql -hreviews-db-v1 -uroot -ppassword
$ mysql> use reviews;
$ mysql> select id from reviews;
```
