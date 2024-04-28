The Restauraunt Management System is used for the restauraunt to manage reservation slots. It was developed using Java, HTML and mySQL. The customer has the option to view reservation slots after searching it with the date they want to make a reservation. After searching the reservation slot with an inputted date, the database will be accessed through REST API and allow the user to retrieve the information in the GUI.

As the system is a microservice architecture, there are two databases for each of the microservice that I implemented (add and search reservation slots).

![Screenshot 2024-04-28 145417](https://github.com/thiveyanNades/RestaurantManagementSystem/assets/132423635/fd1c3c85-f124-46e8-8864-335eb27d4bc7)

![Screenshot 2024-04-28 145428](https://github.com/thiveyanNades/RestaurantManagementSystem/assets/132423635/a55a1787-8e66-4827-9c3f-8876f1a5663e)

After the manager logs in, the manager will be able to search reservation slots as well as add reservation slots in the database. This is done with the help of persistence and helper classes, where the manager will enter the details of the reservation slots including the date, time and table occupancy. The helper class will then create a new reservation slot and with the perisitence class, it'll update the database. 

This is the menu page of the restaurant management system.
![image](https://github.com/thiveyanNades/RestaurantManagementSystem/assets/132423635/50f06a01-db28-4fe0-9e67-14f6fca812bb)
The manager GUI could be accessed when they login in the top right.

The manager has the additional option to add a reservation slot to the system.
![image](https://github.com/thiveyanNades/RestaurantManagementSystem/assets/132423635/39fd4630-83b3-49bc-945d-6d43d407083a)

When the reservation slot is searched from the backend microservice, the XML file of the reservation slots will be shown.
![image](https://github.com/thiveyanNades/RestaurantManagementSystem/assets/132423635/bcc0607a-cc9a-410c-a5a1-593b1758512a)

When the reservation slot is searched from the front end microservice, the reservation slots will be shown as below.
![image](https://github.com/thiveyanNades/RestaurantManagementSystem/assets/132423635/67a56b1c-ff0e-4d16-8303-98b626ac3d6c)
