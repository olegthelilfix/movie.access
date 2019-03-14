![alt.tag](https://travis-ci.org/olegthelilfix/movie.access.svg?branch=master)
# movie.access 
Spring boot with docker and swagger test app for access to themoviedb API.

It's a simple REST api service.
## routes
### movie info
GET /movie/info
#### parameters
movieId: integer - Required
#### example
ip:port/movie/info?movieId=418457
### find movie
GET /movie/list
#### parameters
![alt.tag](https://pp.userapi.com/c846324/v846324571/1be346/qj7qTxUwg6k.jpg)
origin https://developers.themoviedb.org/3/discover/movie-discover
#### example
ip:port/movie/list?page=1&year=2016&sort_by=popularity.asc
### average grade in genre
GET /movie/average
#### parameters
genreId : integer - Required
#### example 
ip:port/movie/average?genreId=28
### swagger docs
appip:port/swagger-ui.html
### response format
Add header Accept=application/json for get response in json. 
Default - xml

## deploy 
1. add your themoviedb api key to settings.properties
2. mvn install
3. docker build . -t olegthelilfix/demo
4. docker run -p 8080:8080 olegthelilfix/demo

## ps
All comments in the code are on Russian. Sry.

If you have any questions about this repo code just email me.

olegthelilfix@pm.me

Oleg Aleksandrov