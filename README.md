# whatsapp-api-webhook-server-java

- [Документация на русском языке](src/main/java/com/greenapi/server/docs/README_RU.md).

whatsapp-api-webhook-server-java is a library for integration with WhatsApp messenger using the API
service [green-api.com](https://green-api.com/en/). You should get a registration token and an account ID in
your [personal cabinet](https://console.green-api.com/) to use the library. There is a free developer account tariff.

## API

The documentation for the REST API can be found at the [link](https://green-api.com/en/docs/). The library is a wrapper
for the REST API, so the documentation at the link above also applies.

## Authorization

To send a message or perform other Green API methods, the WhatsApp account in the phone app must be authorized. To
authorize the account, go to your [cabinet](https://console.green-api.com/) and scan the QR code using the WhatsApp app.

## Example of preparing the environment for Ubuntu Server

### Updating the system

Update the system:

```shell
sudo apt update
sudo apt upgrade -y
```

### Java Installation

Java 17 must be installed on the server. [Инструкция по установке Java](https://openjdk.org/install/).

```shell
sudo apt install openjdk-17-jdk
```

### Firewall

Set up the firewall:

Allow the SSH connection:

```shell
sudo ufw allow ssh
```

Base rules:

```shell
sudo ufw default deny incoming
sudo ufw default allow outgoing
```

Allow HTTP and HTTPS connections:

```shell
sudo ufw allow http
sudo ufw allow https
```

Enable the firewall:

```shell
sudo ufw enable
```

## How to run the web server

### Import

```
```

### Examples

#### How to initialize an object

Set server options in `application.yml`.
The WebhookToken attribute is optional.

```yaml
green-api:
  webhookToken: 1a2b3c4d5e
server:
  port: 8080
```

#### How to run the web server

Applications will start listening the port immediately after running the `main` method; 
for this, do not forget to add the `@ComponentScan(basePackages = "com.greenapi.server")` annotation.

Link to example: [WhatsappApiServerExample.java](src/main/java/com/greenapi/server/examples/WhatsappApiServerExample.java).

```java
@SpringBootApplication
@ComponentScan(basePackages = "com.greenapi.server")
public class WhatsappApiServerExample {
     public static void main(String[] args) {
         SpringApplication.run(WhatsappApiServerExample.class, args);
     }
}
```

The handler function class must implement the `WebhookHandler` interface and be a bean.
To do this, set the `@Component(value = "whatsappWebhookHandler")` annotation on the handler function class.

Link to example: [WebhookHandlerExample.java](src/main/java/com/greenapi/server/examples/WebhookHandlerExample.java).

```java
@Component(value = "whatsappWebhookHandler")
public class WebhookHandlerExample implements WebhookHandler {
    
     @SneakyThrows
     @Override
     public void handle(Notification notification) {
         System.out.println("START " + notification);
         Thread.sleep(20000);
         System.out.println("END " + notification);
     }
}
```

When a new notification is received, your `handle()` handler function will be executed asynchronously.
We recommend processing notifications asynchronously, since they are configured to timeout when receive status code 200 too long time.
After a timeout, the second attempt does not occur immediately, which can cause long processing of notifications and an increase in the message queue.

### Running the application

For JAR file:

```shell
java -jar your_app.jar
```

If using Maven, run from the project directory:

```shell
./mvnw spring-boot:run
```

## Service methods documentation

[Service methods documentation](https://green-api.com/en/docs/api/)

## License

Licensed under [
Attribution-NoDerivatives 4.0 International (CC BY-ND 4.0)
](https://creativecommons.org/licenses/by-nd/4.0/) terms.
Please see file [LICENSE](LICENSE.txt).