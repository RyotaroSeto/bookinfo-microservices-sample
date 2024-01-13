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
- `kubectl create secret generic reviews-db-credentials --from-literal rootpassword=password`
