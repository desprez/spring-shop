# Spring-Shop
Formation spring : shop application exemple


Customer
========

Instructions:
> Dans la couche domain, créer une classe **Customer** comprenant 2 attributs :
 - name (String)
 - password (String)

> Créer l'interface **CustomerRepository** dans la couche domain.

> Créer le test unitaire permettant de récupérer un **Customer** depuis la base de données.
(utiliser un identifiant trouvé dans le fichier import.sql) ainsi qu'un test unitaire permettant de stocker le Customer dans la base de Données.

> Créer l'implémentation du repository en utilisant une interface qui étend **JpaRepository<Customer, String>** à l'aide de 2 méthodes **findById()** et **save()**.

Item
====

Instructions:
> Faire de même avec la classe **Item** qui possèdera les 2 attributs :
 - decription (String)
 - price (Integer)

> Ajouter le test unitaire avec les 2 mêmes méthodes **findById()** et **save()** ainsi qu'une méthode **findAll()**.

> Décommenter les ordres SQL Inserts dans le fichier import.sql

Order
=====

Instructions:
> Faire de même avec la classe **Order** qui possèdera les 2 attributs :
 - customer (customer)
 - items (List<Item>)


> Ajouter le test unitaire avec les 2 mêmes méthodes **findById()** et **save()**.

ItemService
===========

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
    
CustomerService
===============

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
> Les tests devront être autonomes et utiliser des mocks pour bouchonner l'accès au données.

OrderService
============

> Toujours la couche **application**, implementer le service **ItemService** pour l'interface ci-dessous:

      public interface OrderService {

       /**
        * Add new order according to the given customer id and items ids.
        *
        * @param CustomerId the cutomerId
        * @param itemIds    list of items ids
        * @return new order
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
        * @param customerId
        * @return a List of OrderEntity
        */
       public List<Order> getOrdersForCustomer(String customerId);

      }

> Les tests devront être autonomes et utiliser des mocks pour bouchonner l'accès au données.
