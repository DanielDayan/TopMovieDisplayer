
# TopMovieDisplayer




### Project Description
Android app that דhows the most popular movies right now in descending order.
All of the data is taken from [TMDB - The movie Data Base](https://www.themoviedb.org/) , with an api request.




### Features

- Scroll list of popular movies at the moment.
- Update the list with every launch of the app with the most populer movies.
- Infinite scrolling, when reached to the end of screen, automatically reload more movies to scrolling.
- View title, poster, current rating and overview of selected movies with the "More Info" button.

### Installation

Just donwload the project or git clone it , open an Android Studio and run the application
with the emulator proiveded or by pluging your phone (only android) via usb and run the app.
 
### Architecture
 The app is written in MVC architecture. 
#### View
 Application has 2 screens for views: 
 1) ***MainActivity*** - main screen , contains the recycle view scroller that contains all the movies
including title, poster and more info button for each movie cell.
 2) ***MovieDetailsActivity*** - contains details about the selected movie by the user.
 
#### Controller
***MovieController*** class is the main cotroller in this architecture.
update the recycle view on load via the model, and detect when user has reached to the end of screen in order
to reload the next page by using api request to TMDB.

### Model
***MovieModel*** model class that do all the network and process the JSON in the background,

### Build dependencies

TopMovieDisplayer is using the following build dependecis included in the build/gradle file :


| Dependency | README |
| ------ | ------ |
| Glide |https://github.com/bumptech/glide|
| Android lifecycle | https://developer.android.com/jetpack/androidx/releases/lifecycle
| Android coroutines | https://developer.android.com/kotlin/coroutines















