## Assignment

### Brief

In this assignment, you are to produce the following API Endpoints:

|#|URL|Verb|Body|Remarks|
|-|---|----|----|-------|
|1|http://localhost:8080/carts|GET|N/A|Get all records in the `cart` table|
|2|http://localhost:8080/carts/{id}|GET|N/A|Get a record from `cart` table by given ID|
|3|http://localhost:8080/carts/|POST|`{"itemId":1, "quantity":10}`|Create a record in `cart` table with the data in the request body. The given `itemId` must be an existing record in the `catalogue` table.

[Cart.java](./src/shoppingcartapi/src/main/java/com/skillsunion/shoppingcartapi/entity/Cart.java) has already been created for you. Note additional annotations `@JoinColumn` and `@OneToOne` creates a relationship between `cart` and `catalogue` table.

Task:
1. Create `CartRepository.java`.
1. Implement the logic for the respective methods in `CartController.java`.

*Hint - This is how you set an item to the cart.*

```java
Catalogue item = new Catalogue();
item.setId(itemId);
Cart cart = new Cart();
cart.setItem(item);
```

### Submission 

- Submit the URL of the GitHub Repository that contains your work to NTU black board.
- Should you reference the work of your classmate(s) or online resources, give them credit by adding either the name of your classmate or URL. 

### References

_Example of Referencing Classmate_

Referenced the code block below from Terence.
```js
    function printMe(){
        console.log("I am a reference example");
    }
```

_Example of Referencing Online Resources_

- https://developer.mozilla.org/en-US/
- https://www.w3schools.com/html/
- https://stackoverflow.com/questions/14494747/how-to-add-images-to-readme-md-on-github

