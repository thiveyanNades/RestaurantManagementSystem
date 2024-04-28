The Restauraunt Management System is used for the restauraunt to manage reservation slots. The customer has the option to select a reservation slot after searching it with the date they want to make a reservation. After searching the reservation slot with an inputted date, the database will be accessed through REST API and allow the user to retrieve the information on the database.

As the system is a microservice architecture, there are two se

After the manager logs in, the manager will be able to search reservation slots as well as add reservation slots in the database. This is done with the help of persistence and helper classes, where the manager will enter the details of the reservation slots including the date, time and table occupancy. The helper class will then create a new reservation slot and with the perisitence class, it'll update the database. 

