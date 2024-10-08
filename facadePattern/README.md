# `Facade` Pattern with `Strategy` pattern

Здесь находится собственная реализация паттерна `Facade` с дополнением `Strategy` паттерном


## О реализации `Facade` и проблеме

1. Созданы сервисы от `PaymentProvider`, которые выполняют какую-то бизнес задачу
2. Создан класс `PaymentStrategy`, который содержит комплексную работу по выбору нужного нам сервиса, исходя из прибывшего запроса
3. Создан класс `TaxesService` и его реализация, отражающая доплнительную логику
4. Создан класс `PaymentProviderFacade`, содержащий `PaymentStrategy` и `TaxesService`. Тем самым упрощает интерфейс для клиентского кода

   Таким образом получаем скрытие `сложной внутренней логики` между клиентом и подсистемами

## Касаемо `Strategy` в примере

1. `WebAppConfiguration` в одном из методов создает бин, собирающий все нужные реализации
2. Класс `PaymentStrategy` ответственен за выбор нужной реализации сервиса по входящему критерию благодаря инджекту мапы

 Таким образом, благодаря паттерну получаем именно нужную нам реализацию сервиса, код гибок, расширяем

## Спасибо за посещение!