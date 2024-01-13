# microservices-sample2

## Kind SetUp

- `kind create cluster --config infra/multi-node.yaml`
- `kind delete cluster`

## Add ingress-nginx

- `kubectl apply -f infra/nginx.yaml`
- `kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml`

## SetUp bookinfo

- `kubectl create ns bookinfo`
- `kubectl -n bookinfo create secret generic reviews-db-credentials --from-literal rootpasswd=password`
- `kubectl apply -f ./infra/reviews/bookinfo-reviews-db-init.yaml`
- `kubectl apply -f ./infra/reviews/pv.yaml`
- `kubectl apply -f ./infra/reviews/pvc.yaml`
- `kubectl apply -f ./infra/reviews/bookinfo-reviews-db.yaml`
- `kubectl -n bookinfo run -i -t --rm mysql-client --image=mysql -- mysql -hreviews-db-v1 -uroot -ppassword`
- `mysql> use reviews;`
- `mysql> select id from reviews;`
- `kubectl apply -f ./infra/reviews/bookinfo-reviews.yaml`
- `kubectl -n bookinfo port-forward service/reviews 8080:9080`
