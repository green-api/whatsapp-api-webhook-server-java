# whatsapp-api-webhook-server-java

whatsapp-api-webhook-server-java - библиотека для интеграции с мессенджером WhatsApp через API
сервиса [green-api.com](https://green-api.com/). Чтобы воспользоваться библиотекой, нужно получить регистрационный токен
и ID аккаунта в [личном кабинете](https://console.green-api.com/). Есть бесплатный тариф аккаунта разработчика.

## API

Документация к REST API находится по [ссылке](https://green-api.com/docs/api/). Библиотека является оберткой к REST API,
поэтому документация по ссылке выше применима и к самой библиотеке.

## Авторизация

Чтобы отправить сообщение или выполнить другие методы Green API, аккаунт WhatsApp в приложении телефона должен быть в
авторизованном состоянии. Для авторизации аккаунта перейдите в [личный кабинет](https://console.green-api.com/) и
сканируйте QR-код с использованием приложения WhatsApp.

## Пример подготовки среды для Ubuntu Server

### Обновление системы

Обновим систему:

```shell
sudo apt update
sudo apt upgrade -y
```

### Установка java

На сервере должен быть установлен Java 17. [Инструкция по установке Java](https://openjdk.org/install/).

```shell
sudo apt install openjdk-17-jdk
```

### Брандмауэр

Настроим брандмауэр:

Разрешим соединение по SSH:

```shell
sudo ufw allow ssh
```

Базовые правила:

```shell
sudo ufw default deny incoming
sudo ufw default allow outgoing
```

Разрешаем соединения по HTTP и HTTPS:

```shell
sudo ufw allow http
sudo ufw allow https
```

Активируем брандмауэр:

```shell
sudo ufw enable
```

## Как запустить веб-сервер

### Импорт

Установите библиотеку в проект своего сервера. Библиотека доступна в центральном репозитории Maven.

Maven
```xml
<dependency>
    <groupId>com.green-api</groupId>
    <artifactId>whatsapp-api-webhook-server-java</artifactId>
    <version>{{version}}</version>
</dependency>
```

Gradle
```yaml
implementation group: 'com.green-api', name: 'whatsapp-api-client-java', version: 'version'
```

### Примеры

#### Как инициализировать объект

Установите параметры сервера в `application.yml`.
Атрибут WebhookToken является опциональным, ему можно не присваивать значение, однако он должен быть
в `application.yml`.
Если вы не хотите устанавливать пароль, вы можете просто оставить параметр webhookToken без значения.

```yaml
green-api:
  webhookToken:
server:
  port: 8080
```

Приложения начнет слушать порт, сразу после запуска метода `main`, для этого не забудьте поставить
аннотацию `@ComponentScan(basePackages = "com.greenapi.server")`, чтобы в вашем проекте были доступны все стандартные бины библиотеки.

Ссылка на пример: [WhatsappApiServerExample.java](/com/greenapi/server/examples/WhatsappApiServerExample.java).

```java
@SpringBootApplication
@ComponentScan(basePackages = "com.greenapi.server")
public class WhatsappApiServerExample {
    public static void main(String[] args) {
        SpringApplication.run(WhatsappApiServerExample.class, args);
    }
}
```

Класс функции-обработчика должен имплементировать интерфейс `WebhookHandler` и быть бином с именем `whatsappWebhookHandler`.
Для этого установите аннотацию `@Component(value = "whatsappWebhookHandler")` над классом функции-обработчика.

Ссылка на пример: [WebhookHandlerExample.java](/com/greenapi/server/examples/WebhookHandlerExample.java).

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

Webhook URL: `{{your host}}/green-api/async/webhook`
Если вы хотите чтобы ваша функция-обработчик `handle()` будет выполнена асинхронно.

Webhook URL: `{{your host}}/green-api/webhook`
Если вы хотите чтобы ваша функция-обработчик `handle()` вызывалась для каждого вебхука последовательно в одном потоке.

Так как каждое уведомление автоматически кастится до java объекта, вы можете фильтровать уведомление по любому полю самостоятельно.
С описанием структуры объектов уведомлений можно ознакомиться по этой ссылке: [Документация](https://green-api.com/docs/api/receiving/notifications-format/type-webhook/)
Для удобства все типы хуков и сообщений названы аналогично документации:

| Java объект                          | Webhook's json объект                                                                                                                                     |
|--------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------|
| `TextMessageWebhook`                 | [TextMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/TextMessage/)                                                |
| `TemplateMessageWebhook`             | [TemplateMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/TemplateMessage/)                                        |
| `StickerMessageWebhook`              | [StickerMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/StickerMessage/)                                          |
| `ReactionMessageWebhook`             | [ReactionMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/ReactionMessage/)                                        |
| `QuotedMessageWebhook`               | [QuotedMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/QuotedMessage/)                                            |
| `PollUpdateMessageWebhook`           | [PollUpdateMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/PollUpdateMessage/)                                    |
| `PollMessageWebhook`                 | [PollMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/PollMessage/)                                                |
| `LocationMessageWebhook`             | [LocationMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/LocationMessage/)                                        |
| `ListMessageWebhook`                 | [ListMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/ListMessage/)                                                |
| `GroupInviteMessageWebhook`          | [GroupInviteMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/GroupInviteMessage/)                                  |
| `FileMessageWebhook`                 | [imageMessage, videoMessage, documentMessage, audioMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/ImageMessage/) |
| `ExtendedTextMessageWebhook`         | [ExtendedTextMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/ExtendedTextMessage/)                                |
| `ButtonsMessageWebhook`              | [ButtonsMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/ButtonsMessage/)                                          |
| `ContactMessageWebhook`              | [ContactMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/ContactMessage/)                                          |
| `ContactsArrayMessageWebhook`        | [ContactMessage](https://green-api.com/docs/api/receiving/notifications-format/incoming-message/ContactsArrayMessage/)                                    |
| `TemplateButtonsReplyMessageWebhook` | [TemplateButtonsReplyMessage](https://green-api.com/docs/api/receiving/notifications-format/selected-buttons/TemplateButtonsReplyMessage/)                |
| `ButtonsResponseMessageWebhook`      | [ButtonsResponseMessage](https://green-api.com/docs/api/receiving/notifications-format/selected-buttons/ButtonsResponseMessage/)                          |
| `ListResponseMessageWebhook`         | [ListResponseMessage](https://green-api.com/docs/api/receiving/notifications-format/selected-buttons/ListResponseMessage/)                                |

### Запуск приложения

Для JAR-файла:

```shell
java -jar ваше_приложение.jar
```

Если используется Maven запустите из директории проекта:

```shell
./mvnw spring-boot:run
```

## Документация по методам сервиса

[Документация по методам сервиса](https://green-api.com/docs/api/)

## Лицензия

Лицензировано на условиях [
Attribution-NoDerivatives 4.0 International (CC BY-ND 4.0)
](https://creativecommons.org/licenses/by-nd/4.0/). [LICENSE](https://github.com/green-api/whatsapp-api-webhook-server-java/blob/master/LICENSE.txt).