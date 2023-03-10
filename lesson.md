## Brief

### Preparation

- Check in with learners before lesson to ensure PostgreSQL installation is done.

### Lesson Overview

This lesson is one of the harder lessons because learners have to grasp the database and JPA concept. Both are two separate technologies. Pre-warn learners that this lesson will be on recap during the next coaching session if there are doubts by the end of the lesson.

---
## Self-studies check-in

Q1: How are data stored in relational databases?

A - Data are stored as JSON.

B - Data are stored as Excel Sheet.

C - Data are stored as Tables.

D - Data are stored as source code.

---

Q2: Which of the following is NOT a valid relationship?

A - All to All

B - One to One

C - One to Many

D - Many to Many

---

Q3: Which of the following is NOT a feature of relational database?

A - Indexes

B - Stored Procedures

C - Locking

D - Storing unstructured data

---

## Part 1 - Conceptual Understanding on Database

Review the [study material](https://www.youtube.com/watch?v=OqjJjpjDRLc) (7:53) and revise on the self studies check-in questions again.

---

## Part 2 - JPA Setup

Before we are able to start using annotations and interfaces of Spring JPA, we have to setup database and import JPA dependency on Maven first.
### Database Initialization

Step 1: Use the following command to enter the PSQL session on Terminal or Bash Shell.

```sh
psql postgres
```

Step 2: Setup the database

```sql
CREATE DATABASE exampledb;
CREATE USER exampleuser WITH ENCRYPTED PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE exampledb to exampleuser;
```
### Database Connections

Now, you have setup your database. Navigate to the [application.properties](./src/shoppingcartapi/src/main/resources/application.properties) file and add the follow configuration. 

```
## PostgreSQL
spring.datasource.url=jdbc:postgresql://localhost:5432/exampledb
spring.datasource.username=exampleuser
spring.datasource.password=password

#drop n create table again, good for testing, comment this in production
spring.jpa.hibernate.ddl-auto=create
```

### Install JPA Dependencies

Navigate to the [pom.xml](./src/shoppingcartapi/pom.xml) and add the following dependencies within the `<dependencies>` tag.

```xml
<dependency>
    <groupId>org.postgresql</groupId>
    <artifactId>postgresql</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId> 
</dependency>
```

---

## Part 3 - @Entity & @Repository

Step 1: Create an entity class that maps to a table in the database. Create a new file: `com.skillsunion.shoppingcartapi.entity.Catalogue.java`

```java
package com.skillsunion.shoppingcartapi.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "catalogue", schema = "public")
public class Catalogue {
    
	@Id
    @Column(name = "id", nullable=false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Float price;

    @Column(name = "short_description")
    private String shortDesc;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Timestamp updatedAt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Timestamp getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
    
}

```

Step 2: Create a repository class at `com.skillsunion.shoppingcartapi.repository.CatalogueRepository.java`.

```java
package com.skillsunion.shoppingcartapi.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.skillsunion.shoppingcartapi.entity.Catalogue;

@Repository
public interface CatalogueRepository extends JpaRepository <Catalogue, Integer>{


}

```

Upon extends `JpaRepository`, there are a set of [default methods](https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html) made available. 

If you require a more complex query, you could manually declare the method signature. [Reference](https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html)

Let's declare a new method to query for the `name` attribute of `Catalogue` that contains a given value.

Update `CatalogueRepository.java`
```java
package com.skillsunion.shoppingcartapi.repository;

import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import com.skillsunion.shoppingcartapi.entity.Catalogue;

@Repository
public interface CatalogueRepository extends JpaRepository <Catalogue, Integer>{

	List<Catalogue> findByNameContaining(@Param("name") String name);

}
```

Now, we are able to use the declared method to search for name that contains a given value. 

> Under the hood of @JpaRepository [link](https://stackoverflow.com/questions/14014086/what-is-difference-between-crudrepository-and-jparepository-interfaces-in-spring)


---

## Part 4 - Invoke repository from @RestController class

Let's implement the querying code of `CatalogueRepository.java` in `CatalogueController.java`.

```java
package com.skillsunion.shoppingcartapi.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.skillsunion.shoppingcartapi.entity.Catalogue;
import com.skillsunion.shoppingcartapi.repository.CatalogueRepository;

@RestController
public class CatalogueController {
	
	@Autowired CatalogueRepository repo; // Added
    
	// Updated (produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value="/catalogues", method = RequestMethod.GET ,consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<Catalogue>> list(@RequestParam(defaultValue = "") String search){
        List<Catalogue> results = repo.findByNameContaining(search);
        System.out.println("Results Size: "+results.size());
        if(results.size() == 0) {
        	return ResponseEntity.notFound().build();
        }else {
        	return ResponseEntity.ok(results);
        }
    }

    /*
        Switch between @PathVariable and @RequestParam to help learners understand
        the difference.

        @PathVariable => /catalogues/1
        @RequestParam => /catalogues?id=1
    */
    @GetMapping(value = "/catalogues/{id}")
    public ResponseEntity<Optional<Catalogue>> get(@PathVariable int id){
        Optional<Catalogue> result = (Optional<Catalogue>) repo.findById(id);
        if(result.isPresent()) return ResponseEntity.ok(result);
        
        return ResponseEntity.notFound().build();
    }

    /*
        By default, a @PostMapping assumes application/json content type.
    */
    @PostMapping(value = "/catalogues")
    public ResponseEntity<Catalogue> create(@RequestBody RequestBodyTempData data){
        Catalogue newRecord = new Catalogue();
        newRecord.setName(data.getName());
        newRecord.setPrice(data.getPrice());
        
        try {
        	Catalogue created = repo.save(newRecord);
            return ResponseEntity.created(null).body(created);
        }catch(Exception e) {
        	System.out.println(e);
        	return ResponseEntity.internalServerError().build();
        }
        
    }
}

/*
    This is a private class, not accessible outside this java file.
    We will use this for now. In the future, the request body
*/
class RequestBodyTempData {
    String name;
    float price;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
```

Let's break down what has been added:

- The `@Autowired` annotation is used to inject `CatalogueRepository`.
- The `list` method has been updated to produce JSON data.
- There are implementations of all three methods:
    - list
    - get
    - create

> Note that all return type are being changed to use ResponseEntity [API Doc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/http/ResponseEntity.html)

> Static methods are being used for the lesson.

Run the application with:
```sh
./mvnw spring-boot:run
```

You should be able to successfully call the following APIs following the order: 1, 2, 3 and 4.

|Order|URL|Verb|Body|Remarks|
|-----|---|----|----|-------|
|1|http://localhost:8080/catalogues|POST|`{"name":"sofa","price":1000}`| Create data|
|2|http://localhost:8080/catalogues|POST|`{"name":"candy","price":1000}`| Create data|
|3|http://localhost:8080/catalogues/1|GET|N/A| Read data by ID|
|4|http://localhost:8080/catalogues?search=of|GET|N/A| Find data where name contains "of"|

