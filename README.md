# spring-shop
Formation spring shop application exemple


Customer
========

Instructions:

Dans la couche domain, créer une classe **Customer** comprenant 2 attributs :
- name (String)
- password (String)

Créer l'interface **CustomerRepository** dans la couche domain.

Créer le test unitaire permettant de récupérer un **Customer** depuis la base de données.
(utiliser un identifiant trouvé dans le fichier import.sql) ainsi qu'un test unitaire permettant de stocker le Customer dans la base de Données.

Créer l'implémentation du repoistory en utilisant une interface qui étend **JpaRepository<Customer, String>** à l'aide de 2 méthodes FindById() et Save().

Item
====

Instructions:

Faire de même avec la classe **Item** qui possèdera les 2 attrubuts :
- decription (String)
- price (Integer)

Ajouter le test untaire avec les 2 mêmes méthodes FindById() et Save() ainsi qu'une méthode findAll().
