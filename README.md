# Transactions at the bank
This project has the implementation for the `transfer` API in a Banking System as described [here](https://github.com/namshi/coding-challenges/blob/master/transactions-at-the-bank.md)
The implementation is done using:
- Java
- SpringMVC 4.2.1
- SpringJDBCTemplate
- Mysql DB

# Getting Started
Below is the list of tools that were used:
- Eclipse
- Tomcat v9.0
- Maven 3.7
- Mysql
- jdk 1.7

# Code Details

The REST Call to the `transfer` API is triggered as a `POST` call as below:
- `URL` : http://localhost:8080/BankTransfer/transfer
- `Content Type` : application/json
- `Request BODY Sample` : 
```{
  "fromAccount": 1,
  "toAccount": 2,
  "amount": 100
}
```

The sql dump for a sample DB with 2 accounts that was setup and used can be found in the root directory of the project by the name `bankdb.sql`.

The Sample Response is as below:
```{
  "transactionId": 47,
  "from":{
    "id": 1,
    "balance": 5800
  },
  "transfered": 100,
  "toModel":{
     "id": 1,
     "balance": 10300
  }
}
```
The Response Codes used are as below:

- `200` - Ok
- `400` - BAD REQUEST
- `420` - METHOD FAILURE

The Custom Exception implemented is `TransferException` which will send 1 of the above response codes with the proper error message in the response body.

# Considerations met in the implementation

- What happens under high concurrency? <br/>
The Connection Pooling properties are set in the sprint-servlet.xml file for connection pooling
The `initialSize` property has been set to `2` as of now and the `maxActive` property has been set to `20`.
These values can be varied as per the application requirements for high concurrency.
 
- What happens if your database becomes unavailable in the middle of your logic? <br/>
This has been handled by including all the steps in a `Single Transaction` and `Roll-back` in-case of any issues during the execution.

- What happens if 2 users (A, B) transfer money to user C at the same time?<br/>
This has been handled by synchronizing the update on the Balance table object.

- How do you handle and structure the errors that you return to the client?<br/>
Custom Exception Class is written by the name `TransferException` and proper error messages are passed in to the Exception object. while handling the Exception the RestController class, specific error codes are set and the error message is sent in JSON format as ResponseEntity.


