# Spring Boot: Java Persistent API

## Dependencies

Refer to the following markdown file for the respective sections of the class:
- [Self Studies](./studies.md)
- [Lesson](./lesson.md)
- [Assignment](./assignment.md)

## Lesson Objectives

Learners will understand:
- What are databases.
- How are data stored in relational database like PostgreSQL.
- The relationships mapping between tables (one to one, one to many, many to many).
- What is JPA and why use it.
- What are the various implementations of JPA.
- What is ORM.
- What is hibernate.
- How to query database using and which to choose between JpaRepository default methods, CriteriaBuilder and JPQL. 

Learners will be able to:
- Access the database using psql CLI client to verify data written.
- Setup database connection using PostgreSQL.
- Define an entity and perform CRUD operations using methods provided by `@JpaRepository`.
- Query the database with JPQL using `@Query`. 
- (Optional - content developer feedback on whether this is achievable) Query database with CriteriaBuilder.

> Provide learners with PostgreSQL installation guide and a set of SQL Script with explanations to setup database for lesson as part of self studies.
> This lesson will be one of the toughest as learners need to absorb the knowledge of database and setting it up BEFORE the lesson, and grasping JPA within the lesson.
> Content writer should prepare code base with `@RestController` and `@Service` implemented so that learners will only need to implement the JPA layer.

## Lesson Plan

|Duration|What|How or Why|
|--------|-----|-------|
|- 5mins |Start zoom session|So that students can join early and start class on time|
|15 mins|Self studies check-in|Does every students have JDK installed? Are there any questions on self-studies? If the students do not have SDK installed, they can practice in class using w3schools links provided in the self studies.|
|15 mins|I DO Part 1|Instructor demonstrates the syntax of writing a class with main method.|
|15 mins|WE DO Part 1|Learners write a class with main method.|
|15 mins|I DO Part 2|Instructors demonstrate compiling and running Java code using `javac` and `java` on CLI|
|15 mins|WE DO Part 2|Learners compile and run Java Code using `javac` and `java` on CLI|
|5 mins| Break||
|||**1 HR 20 MIN**|
|15 mins|I DO Part 3| Instructor demonstrates initializing String and Integer variables and print it.|
|15 mins|WE DO part 3| Learners initialize String, Integer, Char and Float variables. Then, print it.|
|10 mins|Assignment briefing|
|||**2 HR 00 MIN**|
|40 mins|Learners self attempt on assignments|
|20 mins|Instructors walk through at least 1 question of the assignmentsd|
|||**END CLASS 2 HR 30 MIN**|

