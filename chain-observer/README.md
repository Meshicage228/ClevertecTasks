# `Chain of responsibility` и `Observer` в рамках одного приложения

Здесь находится собственная реализация паттерна `Chain of responsibility` с `Observer` паттерном

## О реализации `Chain of responsibility`

1. В цепочку были собранны классы : `ByTelephoneNumberPaymentProvider`, `QiwiPaymentProvider`, `SteamPaymentProvider`. Началом цепочки был помечен бин с именем `PaymentStartChain`
2. Благодаря переданному типу оплаты по цепочке выбирается та реализация, которая может обработать запрос 
3. Создан класс `AbstractPaymentProvider`, содержащий как ссылку на следующую реализацию, так и список подписанных на эту `реализацию слушателей`
4. Данные задаются через super() наследника

   Таким образом получаем легкое добавление новой реализации в цепочку, уменьшение связанности, уменьшение дополнительной логики на определение нужной реализации через циклы и т.д

## Касаемо `Observer` в примере

1. Создан интерфейс `PaymentObserver` и его реализации `EmailNotifyObserver`, `LoggingObserver` : будут собраны спрингом в единый список
2. Каждый провайдер в цепочке подписывается на все обсерверы в рамках данного примера. 
3. После вызова нужного провайдера оповещаются все подписанные на него обсерверы

Таким образом, в runtime режиме обсерверы могут отреагировать на изменения других обьектов, дополнив логикой, уменьшают связанность кода.

## Спасибо за посещение!