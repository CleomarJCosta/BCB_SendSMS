# BCB_SendSMS

Instalar postgres ou via docker com o seguinte comando:


docker run -d \
  --name sendsms-postgres \
  -e POSTGRES_DB=SendSMS \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5433:5432 \
  postgres:latest
