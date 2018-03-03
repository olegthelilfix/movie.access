# movie.access demo app
# Где потестить
194.87.234.90:8080

# API для взаимодействия
## Полученние ответа в формате JSON или XML
Для того что управлять форматом ответа, необходимо указать значение 
для заголовка assert.

application/json - для получения ответа в json

application/xml - для получение ответа xml

## получение информации по определенному фильму
### url
GET /movie/info
### параметры запроса
movieId : integer (id фильма, информацию по которому необходимо получить)
### Пример запроса
194.87.234.90:8080/movie/info?movieId=100

## получение списка фильмов
### url
GET /movie/list
### параметры запроса
Любой из параметров описанных в документации themoviedb "GET /discover/movie"
https://developers.themoviedb.org/3/discover/movie-discover
За исключением api_key
### Пример запроса
GET 194.87.234.90:8080/movie/list?page=1

## Средняя оценка по жанру
id жанров можно узнуть тут https://api.themoviedb.org/3/genre/movie/list?api_key=72b56103e43843412a992a8d64bf96e9

#TODO
Список, возможных доработок.
1. кэширование результатов запросов.
GET /movie/list и GET /movie/info


---
V.V.V.V.V.