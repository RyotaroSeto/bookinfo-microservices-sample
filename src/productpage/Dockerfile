FROM python:3.7.7-slim

RUN apt update && apt install -y python3-dev build-essential

COPY requirements.txt ./
RUN pip install --no-cache-dir -r requirements.txt && \
    opentelemetry-bootstrap -a install

COPY productpage.py /opt/microservices/
COPY tests/unit/* /opt/microservices/
COPY templates /opt/microservices/templates
COPY static /opt/microservices/static
COPY requirements.txt /opt/microservices/

ARG flood_factor
ENV FLOOD_FACTOR ${flood_factor:-0}

WORKDIR /opt/microservices

EXPOSE 9080

CMD ["opentelemetry-instrument", "python", "productpage.py", "9080"]
