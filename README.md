# About Weather app                                                                                                                             
Is an android app that can view weather forecast either weekly or current forecast (Today and tomorrow)
it's consisting of one activity and 3 fragments , I used  shared preference to store city name till when app is close and restart the app still have data of current city and you can browse weather forecast easily for that city that is restored in shared preferences
### 1. Activities
MainActivity : is the only activity on the project that through it I can switch between the fragments with a different views
### 2. Fragments
This project consists of three fragments 
Fragmment is a partical view that can appear on the activity and is partical of the activity                                               
  * CurrentForecastFragment  : it's a view that appears to the user to display current weather forecast(Today and tomorrow)             
  It's components : ListView                                                                                                               
  * EditLocationFragment  : it's a view that appears to the user to enter the city that want to get weather forecast for it             
  It's components : EditText , Button , ImageView                                                                                         
  * WeekForecastsFragment  : it's a view that appears to the user to browse week weather forecast                                        
  It's components : ListView                                                                                                            

### 3. DataBase                                                                                                                         
* I used shared preferences to store city name to can get weather forecast based on that city

### 4. Models                                                                                                                           
Those are models that represent json data to store data from parsing in it
*  Forecast  : representing the object of the json that is coming from the api                                                       


* City  : representing city data from json                                                                                            


* Coord  : representing coordination data from json                                                                                    


* Day  : representing day forecast data from json                                                                                      


* Temp : representing temperature data from json                                                                                      


* Weather : representing Weather data from json                                                                                       


* BaseInfo  : is object that have basic variables to store data in it and main methods                                                  



### 5. API URL
* Get 7 weather forecast for 7 days for london                                                                                           

  http://api.openweathermap.org/data/2.5/forecast/dailyappid=XXXX&mode=json&units=metric&q=london&cnt=7
* Get icon of the weather state                                                                                                        

  http://openweathermap.org/img/w/io2.png                                                                                                                                                                                                                
### 6. Built With
                                                                                                                     
* Retrofit - for consuming the api from openweathermap


* Gson - to convert json to Java object                                                                                                


* Picasso - for downloading images from url into imageview                                                              


* Android studio  - Integreted development environment                                                                                  

