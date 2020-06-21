# GradeTest

Steps to execute the GradeTest

java -Durl=http://localhost:8080/GradeBook/resources -jar GradeTest.jar



Execution History:

Balavignesh:Desktop BalaVignesh$ java -Durl=http://localhost:8080/GradeBook/resources -jar GradeTest.jar

no-Velma: GET http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

no-Scooby%20Doo: GET http://localhost:8080/GradeBook/resources/student/Scooby%20Doo
  succeeded

no-Shaggy: GET http://localhost:8080/GradeBook/resources/student/Shaggy
  succeeded

no-Fred: GET http://localhost:8080/GradeBook/resources/student/Fred
  succeeded

no-Daphne: GET http://localhost:8080/GradeBook/resources/student/Daphne
  succeeded

Velma-posts-a+: POST http://localhost:8080/GradeBook/resources/student/Velma/grade/A+
  succeeded

Velma-included: GET http://localhost:8080/GradeBook/resources/student
  succeeded

Velma-gets-a+: GET http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

Velma-puts-b+: PUT http://localhost:8080/GradeBook/resources/student/Velma/grade/B+
  succeeded

Velma-gets-b+: GET http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

Velma-bogus-grade: POST http://localhost:8080/GradeBook/resources/student/Velma/grade/bogus
  succeeded

Total-count: GET http://localhost:8080/GradeBook/resources/student
  succeeded

goodbye-Velma: DELETE http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

no-Velma: GET http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

Velma-posts-a+: POST http://localhost:8080/GradeBook/resources/student/Velma/grade/A+
  succeeded

Scooby%20Doo-posts-a-: POST http://localhost:8080/GradeBook/resources/student/Scooby%20Doo/grade/A-
  succeeded

Shaggy-posts-b+: POST http://localhost:8080/GradeBook/resources/student/Shaggy/grade/B+
  succeeded

Fred-posts-b-: POST http://localhost:8080/GradeBook/resources/student/Fred/grade/B-
  succeeded

Daphne-posts-c+: POST http://localhost:8080/GradeBook/resources/student/Daphne/grade/C+
  succeeded

Total-count: GET http://localhost:8080/GradeBook/resources/student
  succeeded

Velma-gets-a+: GET http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

Scooby Doo-gets-a-: GET http://localhost:8080/GradeBook/resources/student/Scooby Doo
  succeeded

Shaggy-gets-b+: GET http://localhost:8080/GradeBook/resources/student/Shaggy
  succeeded

Fred-gets-b-: GET http://localhost:8080/GradeBook/resources/student/Fred
  succeeded

Daphne-gets-c+: GET http://localhost:8080/GradeBook/resources/student/Daphne
  succeeded

Velma-puts-c-: PUT http://localhost:8080/GradeBook/resources/student/Velma/grade/C-
  succeeded

Scooby%20Doo-puts-d+: PUT http://localhost:8080/GradeBook/resources/student/Scooby%20Doo/grade/D+
  succeeded

Shaggy-puts-d-: PUT http://localhost:8080/GradeBook/resources/student/Shaggy/grade/D-
  succeeded

Fred-puts-a: PUT http://localhost:8080/GradeBook/resources/student/Fred/grade/A
  succeeded

Daphne-puts-b: PUT http://localhost:8080/GradeBook/resources/student/Daphne/grade/B
  succeeded

Total-count: GET http://localhost:8080/GradeBook/resources/student
  succeeded

Velma-gets-c-: GET http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

Scooby Doo-gets-d+: GET http://localhost:8080/GradeBook/resources/student/Scooby Doo
  succeeded

Shaggy-gets-d-: GET http://localhost:8080/GradeBook/resources/student/Shaggy
  succeeded

Fred-gets-a: GET http://localhost:8080/GradeBook/resources/student/Fred
  succeeded

Daphne-gets-b: GET http://localhost:8080/GradeBook/resources/student/Daphne
  succeeded

Velma-puts-c: PUT http://localhost:8080/GradeBook/resources/student/Velma/grade/C
  succeeded

Scooby%20Doo-puts-d: PUT http://localhost:8080/GradeBook/resources/student/Scooby%20Doo/grade/D
  succeeded

Shaggy-puts-e: PUT http://localhost:8080/GradeBook/resources/student/Shaggy/grade/E
  succeeded

Fred-puts-f: PUT http://localhost:8080/GradeBook/resources/student/Fred/grade/F
  succeeded

Daphne-puts-i: PUT http://localhost:8080/GradeBook/resources/student/Daphne/grade/I
  succeeded

Total-count: GET http://localhost:8080/GradeBook/resources/student
  succeeded

Velma-gets-c: GET http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

Scooby Doo-gets-d: GET http://localhost:8080/GradeBook/resources/student/Scooby Doo
  succeeded

Shaggy-gets-e: GET http://localhost:8080/GradeBook/resources/student/Shaggy
  succeeded

Fred-gets-f: GET http://localhost:8080/GradeBook/resources/student/Fred
  succeeded

Daphne-gets-i: GET http://localhost:8080/GradeBook/resources/student/Daphne
  succeeded

Velma-puts-w: PUT http://localhost:8080/GradeBook/resources/student/Velma/grade/W
  succeeded

Scooby%20Doo-puts-z: PUT http://localhost:8080/GradeBook/resources/student/Scooby%20Doo/grade/Z
  succeeded

Shaggy-puts-a+: PUT http://localhost:8080/GradeBook/resources/student/Shaggy/grade/a+
  succeeded

Fred-puts-a%2b: PUT http://localhost:8080/GradeBook/resources/student/Fred/grade/a%2B
  succeeded

Daphne-puts-d: PUT http://localhost:8080/GradeBook/resources/student/Daphne/grade/d
  succeeded

Total-count: GET http://localhost:8080/GradeBook/resources/student
  succeeded

Velma-gets-w: GET http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

Scooby Doo-gets-z: GET http://localhost:8080/GradeBook/resources/student/Scooby Doo
  succeeded

Shaggy-gets-a+: GET http://localhost:8080/GradeBook/resources/student/Shaggy
  succeeded

Fred-gets-a+: GET http://localhost:8080/GradeBook/resources/student/Fred
  succeeded

Daphne-gets-d: GET http://localhost:8080/GradeBook/resources/student/Daphne
  succeeded

Scooby%20Doo-bogus-grade: POST http://localhost:8080/GradeBook/resources/student/Scooby%20Doo/grade/f+
  succeeded

Shaggy-bogus-grade: POST http://localhost:8080/GradeBook/resources/student/Shaggy/grade/
  succeeded

Fred-bogus-grade: POST http://localhost:8080/GradeBook/resources/student/Fred/grade/A!
  succeeded

Daphne-bogus-grade: POST http://localhost:8080/GradeBook/resources/student/Daphne/grade/G
  succeeded

goodbye-Velma: DELETE http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

goodbye-Scooby%20Doo: DELETE http://localhost:8080/GradeBook/resources/student/Scooby%20Doo
  succeeded

goodbye-Shaggy: DELETE http://localhost:8080/GradeBook/resources/student/Shaggy
  succeeded

goodbye-Fred: DELETE http://localhost:8080/GradeBook/resources/student/Fred
  succeeded

goodbye-Daphne: DELETE http://localhost:8080/GradeBook/resources/student/Daphne
  succeeded

goodbye-no-Velma: DELETE http://localhost:8080/GradeBook/resources/student/Velma
  succeeded

goodbye-no-Scooby%20Doo: DELETE http://localhost:8080/GradeBook/resources/student/Scooby%20Doo
  succeeded

goodbye-no-Shaggy: DELETE http://localhost:8080/GradeBook/resources/student/Shaggy
  succeeded

goodbye-no-Fred: DELETE http://localhost:8080/GradeBook/resources/student/Fred
  succeeded

goodbye-no-Daphne: DELETE http://localhost:8080/GradeBook/resources/student/Daphne
  succeeded

Total-count: GET http://localhost:8080/GradeBook/resources/student
  succeeded

0 of 73 tests failed
