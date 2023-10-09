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

```
import (
	"github.com/green-api/whatsapp-api-webhook-server-golang/pkg"
)
```

### Примеры

#### Как инициализировать объект

Атрибут WebhookToken является опциональным.

```yaml
green-api:
  webhookToken: 1a2b3c4d5e
server:
  port: 80
```

#### Как запустить веб-сервер

Функция StartServer принимает функцию-обработчик. Класс функции-обработчика должен имплементировать интерфейс `WebhookHandler` и быть бином.
Для этого установите аннотацию `@Component(value = "whatsappWebhookHandler")` над классом функции-обработчика.

Ссылка на пример: [WebhookHandlerExample.java](/src/main/java/com/greenapi/whatsappapiserverjava/examples/WebhookHandlerExample.java).

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

При получении нового уведомления ваша функция-обработчик будет выполнена асинхронно.

Ссылка на пример: [WhatsappApiServerExample.java](/src/main/java/com/greenapi/whatsappapiserverjava/examples/WhatsappApiServerExample.java).

```
_ := webhook.StartServer(func(body map[string]interface{}) {
    fmt.Println(body)
})
```

### Запуск приложения

```shell
go run main.go
```

## Документация по методам сервиса

[Документация по методам сервиса](https://green-api.com/docs/api/)

## Лицензия

Лицензировано на условиях [
Attribution-NoDerivatives 4.0 International (CC BY-ND 4.0)
](https://creativecommons.org/licenses/by-nd/4.0/). [LICENSE](../LICENSE).