# bookinfo-microservices-sample

## Kind SetUp

- `kind create cluster --config infra/multi-node.yaml`
- `kind delete cluster`

## Add ingress-nginx

- `kubectl apply -f infra/nginx.yaml`
- `kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/main/deploy/static/provider/kind/deploy.yaml`
- `http:/192.168.3.222:80/`

## SetUp bookinfo

- `kubectl create ns bookinfo`
- `kubectl -n bookinfo create secret generic reviews-db-credentials --from-literal rootpasswd=password`
- `kubectl apply -f ./infra/reviews/bookinfo-reviews-db-init.yaml`
- `kubectl apply -f ./infra/reviews/pv.yaml`
- `kubectl apply -f ./infra/reviews/pvc.yaml`
- `kubectl apply -f ./infra/reviews/bookinfo-reviews-db.yaml`
