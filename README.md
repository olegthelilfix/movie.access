# movie.access demo app
# Где протестировать
194.87.234.90:8080

# API для взаимодействия
## получение информации по определенному фильму
### url
GET /movie/info
### параметры запроса
movieId : integer (id фильма, информацию по которому необходимо получить)
### Пример запроса
194.87.234.90:8080/movie/info?movieId=100

## получение списка фильмов
### url
GET /movie/info
### параметры запроса
Любой из параметров описанных в документации themoviedb "GET /discover/movie"
https://developers.themoviedb.org/3/discover/movie-discover
За исключением api_key
### Пример запроса
194.87.234.90:8080/movie/list?page=1

---
V.V.V.V.V.