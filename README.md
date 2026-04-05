Backend for a Spring web app simulating remote asynchronous vacuum controls. [Frontend in Angular here.](https://github.com/JelenaMijatovic/VacuumControlAngular)

Vacuums have one of three statuses: **ON**, **OFF** or **DISCHARGING**. It takes a short amount of time to transition between statuses during which no changes can be made. 

**Methods**

POST ```/vacuums/schedule/```

Schedules an action to take place at a determined time.
Accepts body:
```
{
  time: 2025-10-05T14:00:00.000Z, //The time the scheduled action will be carried out. String in ISO date format. 
  action: START,                  //The action to be carried out. Accepts three values, START, STOP and DISCHARGE. 
  vacuumId: 1                     //The numeric id of the vacuum to carry out the action.
}
```

GET ```/vacuums/search```

Searches for vacuums according to request parameters. Available parameters are name, status and a date range of when the vacuum was added.

GET ```/vacuums/{id}/start```

Tells a vacuum of id {id} to start up. Has no effect unless the vacuum status is OFF.

GET ```/vacuums/{id}/stop```

Tells a vacuum of id {id} to stop. Has no effect unless the vacuum status is ON.

GET ```/vacuums/{id}/discharge```

Tells a vacuum of id {id} to start the discharge action. Requires the vacuum status to be OFF. After discharging, the status will automatically return to OFF.

POST ```/vacuums/```

Adds a new vacuum or changes its name. Accepts body:
```
{
  name: myVacuum //A descriptive name for the vacuum.
}
```

DELETE ```/vacuums/{id}```

Removes vacuum with id {id}. The vacuum status must be OFF.
