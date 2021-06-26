BackEnd books manager.

URLs:

http://localhost:8080/swagger-ui.html

GET --> http://localhost:8080/BookManager/book/1
GET --> http://localhost:8080/BookManager/books/?page=0&size=3&sort=name
DELETE --> http://localhost:8080/BookManager/book/1

POST --> http://localhost:8080/BookManager/book/
{
    "name": "Book",
    "year": 2021,
    "read": true,
    "dateInclusion": null
}



PUT --> http://localhost:8080/BookManager/book/1
{
    "name": "Book",
    "year": 2021,
    "read": true,
    "dateInclusion": null
}
