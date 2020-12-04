# Spring-Shop
Formation spring : shop application exemple

Installation

    Cloner le projet : https://github.com/desprez/spring-shop.git
    Lancer un mvn package

Customer (couche Domain & Infrastructure)
=========================================

Instructions:
> Dans la couche domain, créer une classe **Customer** comprenant 2 attributs :
 - name (String)
 - password (String)

> Créer l'interface **CustomerRepository** dans la couche **domain** puis l'implémentation dans la couche **infrastructure**.

> Créer le test unitaire correspondant permettant de récupérer un **Customer** depuis la base de données.
(utiliser un identifiant trouvé dans le fichier import.sql) ainsi qu'un test unitaire permettant de stocker le Customer dans la base de Données.

> Créer l'implémentation du repository en utilisant une interface qui étend **JpaRepository<Customer, String>** à l'aide de 2 méthodes **findById()** et **save()**.

voir **correction** dans https://github.com/desprez/spring-shop/tree/add_customer_feature

Item (couche Domain & Infrastructure)
=====================================

Instructions:
> Faire de même avec la classe **Item** qui possèdera les 2 attributs :
 - decription (String)
 - price (Integer)

> Ajouter le test unitaire avec les 2 mêmes méthodes **findById()** et **save()** ainsi qu'une méthode **findAll()** pour le repository adequat.

> Décommenter les ordres SQL Inserts **ITEM** dans le fichier import.sql

voir **correction** dans https://github.com/desprez/spring-shop/tree/add_item_feature

Order (couche Domain & Infrastructure)
======================================

Instructions:
> Faire de même avec la classe **Order** qui possèdera les 2 attributs :
 - customer (Customer)
 - items (List<<Item>>)


> Ajouter le test unitaire avec les 2 mêmes méthodes **findById()** et **save()** pour le repository adequat.

> Décommenter les ordres SQL Inserts **ORDERS** et **ITEMS_ORDERS** dans le fichier import.sql

voir **correction** dans https://github.com/desprez/spring-shop/tree/add_order_feature

ItemService (couche application)
================================

Instructions:
> Dans la couche **application**, implementer le service **ItemService** pour l'interface ci-dessous:

    public interface ItemService {

     /**
      * Add Item to the catalog
      *
      * @param item the item to add
      * @return the new added item
      */
     public Item addItem(Item item);

     /**
      * Display items catalog
      *
      * @return a list of item entities
      */
     public List<Item> getAllItems();

    }

> Les tests devront être autonomes et utiliser des mocks pour bouchonner l'accès au données.

voir **correction** dans https://github.com/desprez/spring-shop/tree/add_order_feature
    
CustomerService (couche application)
====================================

Instructions:
> Dans la couche **application**, implementer le service **OrderService** pour l'interface ci-dessous:

      public interface CustomerService {

       /**
        * Create a new customer
        * @param customer the Customer to create
        * @return the created Customer
        */
       public Customer create(Customer customer);

       /**
        * Retrieve a customer according to the given identifier.
        * @param customerId the customer identifier
        * @return the retrieved Customer
        */
       public Customer findOne(String customerId);

      }
> La méthode **create()** devra vérifier qu'un **Customer** portant le même nom (name) n'existe pas déjà dans la base de données à l'aide d'une méthode findByName(name) de **CustomerJpaRepository**.  
> Les tests devront être autonomes et utiliser des mocks pour bouchonner l'accès au données.

voir **correction** dans https://github.com/desprez/spring-shop/tree/implements_remaining_services

OrderService (couche application)
=================================

> Toujours la couche **application**, implementer les méthodes **getOrdersForCustomer()** et **addOrder()** pour le service **OrderService** tel que l'interface ci-dessous:

      public interface OrderService {

       /**
        * Add new order according to the given customer id and items ids.
        *
        * @param CustomerId the customerId
        * @param itemIds    list of items ids
        * @return new order filled with the order SUM
        */
       public Order addOrder(String CustomerId, List<String> itemIds);

       /**
        * Get Order according to the given orderId
        *
        * @param orderId the order id
        * @return an Order
        */
       public Order findOne(String orderId);

       /**
        * Retrieve all orders for a customer according to the customer id.
        * @param customerId the customer id
        * @return a List of Orders
        */
       public List<Order> getOrdersForCustomer(String customerId);

      }

> La méthode **getOrdersForCustomer()** devra utiliser une méthode findByCustomerId(customerId) de **OrderJpaRepository**. 

> Les tests devront être autonomes et utiliser des mocks pour bouchonner l'accès au données.

voir **correction** dans https://github.com/desprez/spring-shop/tree/implements_remaining_services

ItemResource (couche Exposition)
================================

Instructions:
> Maintenant nous allons exposer les méthodes du service **ItemService** sous forme de API Rest :

voir **correction** dans https://github.com/desprez/spring-shop/tree/add_item_controller

CustomerResource (couche Exposition)
====================================

Instructions:
> Exposer les méthodes du service **CustomerService** sous forme de API Rest :

voir **correction** dans https://github.com/desprez/spring-shop/tree/add_customer_controller

OrderResource (couche Exposition)
=================================

Instructions:
> Exposer les méthodes du service **OrderService** sous forme de API Rest :

voir **correction** dans https://github.com/desprez/spring-shop/tree/add_order_controller

Documentation de l'API avec Swagger
===================================

Instructions:
> Documenter les API de fournir une interface swagger-ui.

voir **correction** dans https://github.com/desprez/spring-shop/tree/add_swagger_documentation
