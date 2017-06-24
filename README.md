About
Is an android app that can view weather forecast either weekly or current forecast (Today and tomorrow)
it's consisting of one activity and 3 fragments
1. Activities
MainActivity : is the only activity on the project that through it i can display switch between the fragments
2. Fragments
This project consists of three fragments 
Fragmment is a partical view that can appear on the activity and is partical of the activity
  2.1. CurrentForecastFragment : it's a view that appears to the user to display current weather forecast(Today and tomorrow)
  It's components : ListView
  2.2. EditLocationFragment : it's a view that appears to the user to enter the city that want to get weather forecast for it
  It's components : EditText , Button , ImageView
  2.3. WeekForecastsFragment :it's a view that appears to the user to browse week weather forecast
  It's components : ListView

Technologies
-Retrofit & Gson for consuming the api from openweathermap and searilize it by gson to convert json to Java object
-I used Android studio IDE